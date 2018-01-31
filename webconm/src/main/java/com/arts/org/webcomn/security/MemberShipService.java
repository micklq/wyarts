package com.arts.org.webcomn.security;

import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.arts.org.webcomn.MethodResult;
import com.arts.org.model.entity.*;
import com.arts.org.model.entity.view.UserPassportView;


/**
 * Created by
 * User: djyin
 * Date: 12/19/13
 * Time: 4:05 AM
 * 提供查询用户权限列表的服务
 */
public interface MemberShipService {
	
	
	boolean isAuthenticated();

    /**
     * Gets current username.
     *
     * @return the current username
     */
     String getCurrentUsername();
    
    /**
     * Find UserPassport.
     *
     * @return the UserPassport
     */
    UserPassport findUserPassportByUsername(String username);
    
    /**
     * Find UserSecurity By PassportId.
     * @param passportId
     * @return the UserSecurity
     */
    UserSecurity findUserSecurityByPassportId(long passportId);
    
    /**
     * Find UserProfile By PassportId.
     * @param passportId
     * @return the UserProfile
     */
    UserProfile findUserProfileByPassportId(long passportId);

    /**
     * Gets User Profile.
     *
     * @return the string permission
     */
    Role findRole(Long roleid);

    /**
     * Gets string permission.
     *
     * @return the string permission
     */
    List<RolePermission> findPermissionByRoleId(Long roleid);
    
    /**
     * 锁定用户
     * @param passportId
     */
    void lock(long passportId);
    /**
     * 解锁用户
     * @param passportId
     */
    void unlock(long passportId);
    
    /**
     * 用户注册
     * @param mobile
     * @param password
     * @return
     */
    public MethodResult<UserPassport> signUp(String mobile,long roleId, String password);
    
    /**
     * 添加会员
     * @param user
     * @return
     */
    public MethodResult<UserPassport> addMember(UserPassportView user); 
    
    /**
     * 更新会员信息
     * @param user
     * @return
     */
    public MethodResult<Boolean> updateMember(UserPassportView user,boolean isAdmin); 
    
    public MethodResult<Boolean> checkOriginalPassword(UserPassportView user); 
    
    public boolean mobileExists(String mobile); 
    
    public boolean userNameExists(String userName); 
    
    public MethodResult<Boolean> updatePassword(UserPassportView user);    
    
    public MethodResult<Boolean> updateMobile(UserPassportView user); 

}
