package com.arts.org.webcomn.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.arts.org.webcomn.MethodResult;
import com.arts.org.webcomn.RespBodyBuilder;
import com.arts.org.webcomn.Violation;
import com.arts.org.base.Constants;
import com.arts.org.base.util.Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.groups.Default;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	protected int page = 1;
	protected int size = 15;
	protected Map<String,String> pmap;
	
    @Resource(name = "validator")
    private Validator validator;
    @Resource(name = "localeResolver")
    private FixedLocaleResolver localeResolver;
    @Resource(name = "messageSource")
    private MessageSource messageSource;

    
    
    /***
	 * ModelAttribute的作用 1)放置在方法的形参上：表示引用Model中的数据
	 * 2)放置在方法上面：表示请求该类的每个Action前都会首先执行它，也可以将一些准备数据的操作放置在该方法里面。
	 * 
	 */	
	
	@ModelAttribute
	public void prepareModel(HttpServletRequest request, HttpServletResponse response, Model viewData) {
		this.request = request;
		this.response = response;
		
		int rows = Util.toInt(request.getParameter("rows"),10);		
		page = Util.toInt(request.getParameter("page"), 1);
		size = Util.toInt(request.getParameter("size"), rows);		

	}
	
	public Map<String,String> getParameterMap(HttpServletRequest request){
		
		if(pmap == null){
			pmap = new HashMap<String,String>();
			
			Enumeration<String> names = request.getParameterNames();
			
			if(names != null){
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					pmap.put(name, request.getParameter(name));
		        }
			}
		}
		return pmap;
	}
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new MutliFormatDateEditor(true));
    }


    /**
     * The Resp body writer.
     * 输出RespBody对象
     */
    @Resource(name = "respBodyBuilder")
    protected RespBodyBuilder respBodyWriter = new RespBodyBuilder();


    /**
     * 通过注解,验证实体的全部属性
     *
     * @param entity
     *         被验证的实体对象
     * @param groups
     *         验证分组,输入NULL则使用默认的分组
     *         (default to {@link javax.validation.groups.Default})
     * @return 验证结果的集合
     * @throws IllegalArgumentException
     *         if object is null
     *         or if null is passed to the varargs groups
     * @throws javax.validation.ValidationException
     *         if a non recoverable error happens
     *         during the validation process
     */
    @SuppressWarnings("unchecked")
	protected boolean validator(Object entity, Class<?>... groups) {
        if (groups == null || groups.length < 1) {
            groups = new Class[]{Default.class};
        }
        Set<ConstraintViolation<Object>> set = validator.validate(entity, groups);
        if (set.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Set<ConstraintViolation<Object>> oldSet = 
        		(Set<ConstraintViolation<Object>>)requestAttributes.getAttribute(Constants.ENTITY_CONSTRAINT_VIOLATIONS, RequestAttributes.SCOPE_REQUEST);
        if (oldSet != null) {
            set.addAll(oldSet);
        }
        requestAttributes.setAttribute(Constants.ENTITY_CONSTRAINT_VIOLATIONS, set, RequestAttributes.SCOPE_REQUEST);
        return false;
    }

    /**
     * 通过注解,验证实体的某一个属性
     * <p/>
     * Validates all constraints placed on the property named <code>propertyName</code>
     * of the class <code>beanType</code> would the property value be <code>value</code>
     * <p/>
     * <code>ConstraintViolation</code> objects return null for
     * {@link javax.validation.ConstraintViolation#getRootBean()} and {@link javax.validation.ConstraintViolation#getLeafBean()}
     *
     * @param beanType
     *         验证的Model对象的类
     * @param propertyName
     *         被验证的属性名
     * @param value
     *         属性对应的值
     * @param groups
     *          验证分组,输入NULL则使用默认的分组
     *         (default to {@link javax.validation.groups.Default})
     * @return 验证结果的集合
     * @throws IllegalArgumentException
     *         if <code>beanType</code> is null,
     *         if <code>propertyName</code> null, empty or not a valid object property
     *         or if null is passed to the varargs groups
     * @throws javax.validation.ValidationException
     *         if a non recoverable error happens
     *         during the validation process
     */
    @SuppressWarnings("unchecked")
	protected boolean validator(Class<?> beanType, String propertyName, Object value, Class<?>... groups) throws ValidationException {
        if (groups == null || groups.length < 1) {
            groups = new Class[]{Default.class};
        }
        Set<?> set = validator.validateValue(beanType, propertyName, value, groups);
        if (set.isEmpty()) {
            return true;
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Set<ConstraintViolation<Object>> oldSet = (Set<ConstraintViolation<Object>>) requestAttributes.getAttribute(Constants.ENTITY_CONSTRAINT_VIOLATIONS, RequestAttributes.SCOPE_REQUEST);
        if (oldSet != null) {
            oldSet.addAll((Set<ConstraintViolation<Object>>)set);
            requestAttributes.setAttribute(Constants.ENTITY_CONSTRAINT_VIOLATIONS, oldSet, RequestAttributes.SCOPE_REQUEST);
        }else{
            requestAttributes.setAttribute(Constants.ENTITY_CONSTRAINT_VIOLATIONS, set, RequestAttributes.SCOPE_REQUEST);

        }
        return false;
    }

    /**
     * 增加一个自定义的数据验证错误
     *
     * @param v the v
     * @throws javax.validation.ValidationException the validation exception
     */
    protected void addViolation(Violation v) throws ValidationException {

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Set<Violation> set = (Set<Violation>) requestAttributes.getAttribute(Constants.ENTITY_CUSTOM_VIOLATIONS, RequestAttributes.SCOPE_REQUEST);
        if (set == null) {
            set = new HashSet<Violation>();
        }
        set.add(v);
        requestAttributes.setAttribute(Constants.ENTITY_CUSTOM_VIOLATIONS, set, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 检查当前是否已经存在数据验证错误
     *
     * @return 存在错误,则返回true
     */
    @SuppressWarnings("unchecked")
	public boolean checkViolation() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        Set<ConstraintViolation<?>> c = 
        		(Set<ConstraintViolation<?>>) requestAttributes.getAttribute(Constants.ENTITY_CONSTRAINT_VIOLATIONS, RequestAttributes.SCOPE_REQUEST);
        if (c != null && !c.isEmpty()) {
            return true;
        }
        Set<Violation> set = 
        		(Set<Violation>) requestAttributes.getAttribute(Constants.ENTITY_CUSTOM_VIOLATIONS, RequestAttributes.SCOPE_REQUEST);
        if (set != null && !set.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * Gets I18N message.
     *
     * @param code
     *         the code
     * @param args
     *         the args
     * @return the message
     */
    protected String getMessage(String code, Object... args) {
        Locale locale = localeResolver.resolveLocale(null);
        return messageSource.getMessage(code, args, locale);
    }
    
    
    public <T> MethodResult<T> SuccessResultJson(T data) {

		MethodResult<T> result = new MethodResult<T>();

		return result.SuccessResult(data);
	}

	public <T> MethodResult<T> SuccessResultJson(T data, String code) {

		MethodResult<T> result = new MethodResult<T>();

		return result.SuccessResult(data, code);
	}

	public <T> MethodResult<T> SuccessResultJson(T data, String code, String message) {

		MethodResult<T> result = new MethodResult<T>();

		return result.SuccessResult(data, code, message);
	}

	public <T> MethodResult<T> FailResultJson() {
		MethodResult<T> result = new MethodResult<T>();

		return result.FailResult("参数错误", "ParamError");
	}

	public <T> MethodResult<T> FailResultJson(String message) {

		MethodResult<T> result = new MethodResult<T>();

		return result.FailResult(message);
	}

	public <T> MethodResult<T> FailResultJson(String message, String code) {

		MethodResult<T> result = new MethodResult<T>();

		return result.FailResult(message, code);
	}

	public <T> MethodResult<T> LoginRequired() {
		MethodResult<T> result = new MethodResult<T>();

		return result.FailResult("请登录", "LoginRequired");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("upload")
	@ResponseBody
	public Map upload(@RequestParam MultipartFile[] file,HttpServletRequest req,HttpServletResponse resp){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.RESULT, "success");
		
		String uploadupath=setUploadPath(req);
		
		log.info(uploadupath);
		for(MultipartFile myfile : file){
            if(myfile.isEmpty()){
            	log.error("上传文件为空xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                map.put(Constants.MESSAGE, "上传失败");
            }else{
                String originalFilename = myfile.getOriginalFilename();
                log.info("文件原名: " + originalFilename);
                log.info("上传文件input name: " + myfile.getName());
                log.info("文件长度: " + myfile.getSize());
                log.info("文件类型: " + myfile.getContentType());
                log.info("========================================");
                try {
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(uploadupath, DateFormatUtils.format(new Date(), "yyyy-MM-dd_HHmmss_")+originalFilename));
                } catch (IOException e) {
                	log.error("文件[" + originalFilename + "]上传失败,堆栈轨迹如下",e);
                    map.put(Constants.MESSAGE, "上传失败，请重试");
                }
            }
		}
		if(map.containsKey(Constants.MESSAGE)){
			map.put(Constants.RESULT, "error");
		}
		return map;
	}
    
    /**
     * 可供继承，设置上传文件的路径
     * @param req
     * @return
     */
    protected String setUploadPath(HttpServletRequest req){
    	File devFile=FileUtils.getFile(req.getSession().getServletContext().getRealPath(""));
		String uploadupath=devFile.getParentFile().getAbsolutePath()+File.separator+"upload"+File.separator+DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		return uploadupath;
    }
	
	


}
