package com.arts.org.base;

/**
 * Created by
 * User: djyin
 * Date: 12/6/13
 * Time: 9:56 AM
 * <p/>
 * 记录一些多个包协同使用的常量
 */
public class Constants {
    /**
     * The constant ENTITY_CONSTRAINT_VIOLATIONS.
     * request attribute 中存放数据格式校验的错误集合时,使用的key
     */
    public static final String ENTITY_CONSTRAINT_VIOLATIONS = "baseEntityConstraintViolations";
    /**
     * The constant ENTITY_CUSTUM_VIOLATIONS. custom
     */
    public static final String ENTITY_CUSTOM_VIOLATIONS = "baseEntityCustomViolations";    
    public static final String CAPTCHA_SESSION_KEY = "captchaSessionBorryOrgKey";
    

    /**
     * The constant DATE_PATTERNS.
     * 系统中可能用到的时间格式
     */
    public static final String[] DATE_PATTERNS = {"yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd","yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss", "HH:mm:ss", "HHmmss"};
    
    public static final String RESULT = "result";
	public static final String MESSAGE = "message";
	public static final String SESSION_WEB_UID = "webUserId";
	public static final String SESSION_WEB_UNAME = "webUersName";
	public static final String SESSION_WEB_RID = "webRoleId";

}
