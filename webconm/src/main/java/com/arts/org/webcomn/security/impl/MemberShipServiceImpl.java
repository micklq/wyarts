package com.arts.org.webcomn.security.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arts.org.webcomn.MethodResult;
import com.arts.org.webcomn.U;
import com.arts.org.webcomn.security.MemberShipService;
import com.arts.org.webcomn.security.Principal;
import com.arts.org.base.util.Util;
import com.arts.org.model.Filter;
import com.arts.org.model.entity.*;
import com.arts.org.model.entity.view.UserPassportView;
import com.arts.org.model.enums.PassportStatus;
import com.arts.org.service.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("memberShipService")
public class MemberShipServiceImpl  implements MemberShipService {
	    
   
	@Autowired
    private OperationLogService operationLogService;
	
	@Autowired
    private PermissionService permissionService;
	
	@Autowired
    private RoleService roleService;
	
	@Autowired
    private UserPassportService userPassportService;
	
	@Autowired
    private UserProfileService userProfileService;
	
	@Autowired
    private UserSecurityService userSecurityService;
	
	@Autowired
    private RolePermissionService rolePermissionService;
	
	

        
    @Transactional(readOnly = true)
    public boolean isAuthenticated() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return subject.isAuthenticated();
        }
        return false;
    }

    @Transactional(readOnly = true)
    public String getCurrentUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal.getUsername();
            }
        }
        return null;
    }

	@Override
	public UserPassport findUserPassportByUsername(String username) {
		
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("userName",username));
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		List<UserPassport>	list = userPassportService.findAll(0, 2,filters,sort);
		if(list!=null&& list.size()==1)
		{
			return list.get(0);			
		}
		return null;
	}

	@Override
	public UserSecurity findUserSecurityByPassportId(long passportId) {
		if(passportId<=0) {return null;}
		UserSecurity obj = userSecurityService.find(passportId);
		if(obj!=null){
			return obj;			
		}
		return null;
	}

	@Override
	public UserProfile findUserProfileByPassportId(long passportId) {
		if(passportId<=0) {return null;}
		UserProfile obj = userProfileService.find(passportId);
		if(obj!=null){
			return obj;			
		}
		return null;
	}

	@Override
	public Role findRole(Long roleid) {
		if(roleid<=0) {return null;}
		Role obj = roleService.find(roleid);
		if(obj!=null){
			return obj;			
		}
		return null;	
	}

	@Override
	public List<RolePermission> findPermissionByRoleId(Long roleid) {
		if(roleid<=0) {return null;}
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("roleId",roleid));
		Sort sort = new Sort(Sort.Direction.ASC, "permissionId");
		List<RolePermission> list = rolePermissionService.findAll(0,1000,filters,sort);
		if(list!=null && list.size()>0){
			return list;			
		}
		return null;
	}

	@Override
	public void lock(long passportId) {
		if(passportId<=0) {return;}
		UserPassport passport = userPassportService.find(passportId);
		if(passport==null){return;}		
		UserSecurity uSecurity =findUserSecurityByPassportId(passportId);
		if(uSecurity==null){return;}		
		passport.setPassportStatus(PassportStatus.Locked.getValue());		
		uSecurity.setIsLocked(true);
    	uSecurity.setLastLockedTime(new Date()); 
    	uSecurity.setFailedPasswordAttemptCount(uSecurity.getFailedPasswordAttemptCount()+1);
    	userSecurityService.update(uSecurity);	
		userPassportService.update(passport);	
	}   
	
	@Override
	public void unlock(long passportId) {
		if(passportId<=0) {return;}
		UserPassport passport = userPassportService.find(passportId);
		if(passport==null){return;}		
		UserSecurity uSecurity =findUserSecurityByPassportId(passportId);
		if(uSecurity==null){return;}		
		passport.setPassportStatus(PassportStatus.Standard.getValue());		
		uSecurity.setIsLocked(false);    	
    	uSecurity.setFailedPasswordAttemptCount(0);
    	userSecurityService.update(uSecurity);	
		userPassportService.update(passport);
		
	}
	
	@Override
	public boolean userNameExists(String userName) {		
		return userPassportService.exists(Filter.eq("userName", userName));
	}
	
	@Override
	public boolean mobileExists(String mobile) 	{			
	  return userPassportService.exists(Filter.eq("mobile", mobile));
	}

	@Override
	public MethodResult<UserPassport> signUp(String mobile,long roleId, String password) {
		
		MethodResult<UserPassport>  result = new MethodResult<UserPassport>();
		if(userNameExists(mobile)){
			return result.FailResult("手机号码已注册！");
		}
		UserPassport passport = new UserPassport();
		UserSecurity security = new UserSecurity();
		UserProfile profile = new UserProfile();
		passport.setUserName(mobile);
		passport.setMobile(mobile);
		passport.setRoleId(roleId);
		passport.setPassportStatus(PassportStatus.Standard.getValue());
		passport.setDataFrom("signUp");
		passport.setCreatorId(0L);	
		
		String pswSalt = RandomStringUtils.random(6,true,true);
		String  psw = password+pswSalt;  
		security.setPasswordSalt(pswSalt);
		security.setPassword(DigestUtils.md5Hex(psw));
		security.setHashAlgorithm("MD5");
	    security.setIsLocked(false);
	    security.setLastLockedTime(null);
	    security.setLastPasswordChangedTime(null);       
		security.setFailedPasswordAttemptCount(0);
		security.setDataFrom("signUp");		
		security.setCreatorId(0L);	
		
		profile.setMobile(mobile);
		profile.setRealName(mobile);
		profile.setNickName(mobile);
		profile.setDataFrom("signUp");
		profile.setCreatorId(0L);	
		
		userPassportService.save(passport);
		security.setPassportId(passport.getPassportId());		
		userSecurityService.save(security);
		profile.setPassportId(passport.getPassportId());
		userProfileService.save(profile);
		
		return result.SuccessResult(passport);
	}

	@Override
	public MethodResult<UserPassport> addMember(UserPassportView user) {
		MethodResult<UserPassport>  result = new MethodResult<UserPassport>();
		if(userNameExists(user.getUserName())){
			return result.FailResult("用户名已注册！");
		}
//		if(mobileExists(user.getMobile())){
//			return result.FailResult("手机号码已注册！");
//		}
		UserPassport passport = new UserPassport();
		UserSecurity security = new UserSecurity();
		UserProfile profile = new UserProfile();
		passport.setUserName(user.getUserName());
		passport.setMobile(user.getMobile());
		passport.setEmail(user.getEmail());
		passport.setRoleId(user.getRoleId());
		passport.setPassportStatus(PassportStatus.Standard.getValue());
		passport.setDataFrom("addMember");
		passport.setCreatorId(user.getCreatorId());			
		
		String pswSalt = RandomStringUtils.random(6,true,true);
		String  psw = user.getPassword()+pswSalt;  
		security.setPasswordSalt(pswSalt);
		security.setPassword(DigestUtils.md5Hex(psw));
		security.setHashAlgorithm("MD5");
	    security.setIsLocked(false);
	    security.setLastLockedTime(null);
	    security.setLastPasswordChangedTime(null);       
		security.setFailedPasswordAttemptCount(0);
		security.setDataFrom("addMember");
		security.setCreatorId(user.getCreatorId());				
		
		profile.setMobile(user.getMobile());
		profile.setEmail(user.getEmail());
		profile.setRealName(user.getRealName());
		profile.setNickName(user.getNickName());
		profile.setAvatar(user.getAvatar());
		profile.setGender(user.getGender());
		profile.setBirthdate(user.getBirthdate());
		profile.setQq(user.getQq());
		profile.setWeixin(user.getWeixin());		
		profile.setDataFrom("addMember");		
		profile.setCreatorId(user.getCreatorId());	
		
		userPassportService.save(passport);
		security.setPassportId(passport.getPassportId());
		profile.setPassportId(passport.getPassportId());
		userSecurityService.save(security);
		userProfileService.save(profile);
		
		return result.SuccessResult(passport);
	}

	@Override
	public MethodResult<Boolean> updateMember(UserPassportView user,boolean isAdmin) {
		MethodResult<Boolean>  result = new MethodResult<Boolean>();
		if(user.getPassportId()<=0){
			return result.FailResult("参数错误");
		}
		
		UserPassport passport = userPassportService.queryById(user.getPassportId());
		if(passport==null){
			return result.FailResult("参数错误:用户不存在");
		}
		UserProfile profile = userProfileService.queryById(user.getPassportId());
		if(profile==null){
			return result.FailResult("参数错误:用户信息不存在");
		}	
		passport.setEmail(user.getEmail());
		passport.setRoleId(user.getRoleId());
		passport.setDataFrom("updateMember");
		passport.setCreatorId(user.getCreatorId());	
		if(isAdmin){
			passport.setMobile(user.getMobile());			
			passport.setPassportStatus(user.getPassportStatus());
			
		}
		
		profile.setEmail(user.getEmail());
		profile.setRealName(user.getRealName());
		profile.setNickName(user.getNickName());
		profile.setAvatar(user.getAvatar());
		profile.setGender(user.getGender());
		profile.setBirthdate(user.getBirthdate());
		profile.setQq(user.getQq());
		profile.setWeixin(user.getWeixin());		
		profile.setDataFrom("updateMember");
		profile.setCreatorId(user.getCreatorId());	
		if(isAdmin){
			profile.setMobile(user.getMobile());			
		}		
		userPassportService.update(passport);
		userProfileService.update(profile);
		
		if(isAdmin&& user.getPassportStatus()== PassportStatus.Locked.getValue()){
			
			this.lock(user.getPassportId()); 
		}
		
		if(isAdmin && !Util.isNullOrEmpty(user.getPassword())){
			UserSecurity security = userSecurityService.queryById(user.getPassportId());
			String pswSalt = RandomStringUtils.random(6,true,true);
			String  psw = user.getPassword()+pswSalt;  
			security.setPasswordSalt(pswSalt);
			security.setPassword(DigestUtils.md5Hex(psw));
			security.setHashAlgorithm("MD5");
		    security.setIsLocked(false);
		    security.setLastLockedTime(null);
		    security.setLastPasswordChangedTime(null);       
			security.setFailedPasswordAttemptCount(0);
			security.setDataFrom("updateMember");
			security.setCreatorId(user.getCreatorId());	
			userSecurityService.update(security);
		}
		
		return result.SuccessResult(true);
	}

	@Override
	public MethodResult<Boolean> checkOriginalPassword(UserPassportView user) {
		MethodResult<Boolean>  result = new MethodResult<Boolean>();
		if(user.getPassportId()<=0){
			return result.FailResult("参数错误");
		}
		UserSecurity uSecurity =findUserSecurityByPassportId(user.getPassportId());
		if(uSecurity==null){
			return result.FailResult("参数错误:安全信息不存在");
		}		
		String  psw =  user.getOriginalPassword() + uSecurity.getPasswordSalt(); 
        if (DigestUtils.md5Hex(psw).equals(uSecurity.getPassword())) { 
        	return result.SuccessResult(true);
        }	
		
		return result.FailResult("原始密码错误!");
	}

	@Override
	public MethodResult<Boolean> updatePassword(UserPassportView user) {
		MethodResult<Boolean>  result = new MethodResult<Boolean>();
		if(user.getPassportId()<=0){
			return result.FailResult("参数错误");
		}
		UserSecurity security =findUserSecurityByPassportId(user.getPassportId());
		if(security==null){
			return result.FailResult("参数错误:安全信息不存在");
		}
		
    	String pswSalt = RandomStringUtils.random(6,true,true);
		String  psw = user.getPassword()+pswSalt;       	
        security.setPasswordSalt(pswSalt);
		security.setPassword(DigestUtils.md5Hex(psw));	
	    security.setIsLocked(false);
	    security.setLastPasswordChangedTime(new Date());       
		security.setFailedPasswordAttemptCount(0);
		security.setDataFrom("updatePassword");
		security.setCreatorId(user.getCreatorId());	
		userSecurityService.update(security);
		return result.SuccessResult(true);
	}

	@Override
	public MethodResult<Boolean> updateMobile(UserPassportView user) {
		MethodResult<Boolean>  result = new MethodResult<Boolean>();
		if(user.getPassportId()<=0){
			return result.FailResult("参数错误");
		}
		if(userNameExists(user.getMobile())){
			return result.FailResult("手机号码已作为登录名存在！");
		}
		if(mobileExists(user.getMobile())){
			return result.FailResult("手机号码已存在！");
		}
		UserPassport passport = userPassportService.find(user.getPassportId());
		if(passport==null){
			return result.FailResult("参数错误:用户不存在");
		}
		UserProfile profile = userProfileService.find(user.getPassportId());
		if(profile==null){
			return result.FailResult("参数错误:用户信息不存在");
		}	
		passport.setUserName(user.getMobile());
		passport.setMobile(user.getMobile());
		passport.setDataFrom("updateMobile");
		passport.setCreatorId(user.getCreatorId());	
		
		profile.setMobile(user.getMobile());			
		profile.setDataFrom("updateMobile");
		profile.setCreatorId(user.getCreatorId());	
		
		userPassportService.update(passport);
		userProfileService.update(profile);
		
		return result.SuccessResult(true);
	}

	  
    
    
   
}
