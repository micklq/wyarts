package com.arts.org.web.security;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arts.org.webcomn.security.CaptchaAuthenticationToken;
import com.arts.org.webcomn.security.IncorrectCaptchaException;
import com.arts.org.webcomn.security.MemberShipService;
import com.arts.org.webcomn.security.Principal;
import com.arts.org.base.Constants;
import com.arts.org.model.entity.*;
import com.arts.org.model.enums.PassportStatus;
import com.arts.org.service.*;


/**
 * 基于口令和验证码的Realm<br/>
 * 根据username在数据库中取出User信息<br/>
 * 根据User信息取出Role信息<br/>
 * 根据Role信息取出Permission信息<br/>
 */
public class WebCaptchaUserAuthorizingRealm extends AuthorizingRealm {

    @Resource(name = "memberShipService")
    private MemberShipService memberShipService;


    private boolean validateCaptcha(String captcha) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attr == null) {
            return false;
        }
        HttpSession session = attr.getRequest().getSession(false);
        if (session == null) {
            return false;
        }
        String rcaptcha = (String) session.getAttribute(Constants.CAPTCHA_SESSION_KEY);
        if (rcaptcha == null) {
            return false;
        }
        if (StringUtils.equals(rcaptcha, captcha)) {
            return true;
        }
        return false;
    }

    /**
     * 进行认证，获取认证信息
     *
     * @param token the token
     * @return the authentication info
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        CaptchaAuthenticationToken localAuthenticationToken = (CaptchaAuthenticationToken) token;
        String username = localAuthenticationToken.getUsername();
        String password = new String(localAuthenticationToken.getPassword());
        String captcha = localAuthenticationToken.getCaptcha();
        String host = localAuthenticationToken.getHost();

        if (captcha!=null&&!validateCaptcha(captcha)) {
            throw new IncorrectCaptchaException("captcha");
        }
        if ((username != null) && (password != null)) {
        	UserPassport passport =  memberShipService.findUserPassportByUsername(username);
        	
            if (passport == null) {
                throw new UnknownAccountException("passport");
            }
            if (passport.getPassportStatus()==PassportStatus.Hibernation.getValue() || passport.getPassportStatus()==PassportStatus.Cancellation.getValue()) {
                throw new DisabledAccountException();
            }
            
            if (passport.getPassportStatus()==PassportStatus.Locked.getValue()) {
            	 throw new LockedAccountException();
            }  
            UserSecurity uSecurity =  memberShipService.findUserSecurityByPassportId(passport.getPassportId());
            if (uSecurity == null) {
                throw new UnknownAccountException("security");
            }
       	    String  psw = password+ uSecurity.getPasswordSalt();           
            if (!DigestUtils.md5Hex(psw).equals(uSecurity.getPassword())) { // 验证密码,5次锁死
                Integer loginFailureCount = uSecurity.getFailedPasswordAttemptCount() + 1;
                if (loginFailureCount >= 5) {
                	memberShipService.lock(passport.getPassportId()); //锁定用户
                	              
                }               
                throw new IncorrectCredentialsException("password");
            }
            //memberShipService.unlock(passport.getPassportId()); //验证成功  解锁用户
            return new SimpleAuthenticationInfo(new Principal(passport.getPassportId(),passport.getRoleId(),username), password, getName());
        }
        throw new UnknownAccountException();
    }

    /**
     * 进行授权，获取授权信息
     *
     * @param principals the principals
     * @return the authorization info
     */
    protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principals) {
        Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
        if (principal != null) {
            List<RolePermission> authorities = memberShipService.findPermissionByRoleId(principal.getRoleid());
            if (authorities != null && authorities.size()>0) {
                SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
                Set<String> sets = new HashSet<String>();
                for(RolePermission o : authorities){
                	
                	sets.add(String.format("r%s-p%s-a%s",o.getRoleId(),o.getPermissionId(),o.getActionValue()));
                }
                simpleAuthorizationInfo.addStringPermissions(sets);
                return simpleAuthorizationInfo;
            }
        }
        return null;
    }
}
