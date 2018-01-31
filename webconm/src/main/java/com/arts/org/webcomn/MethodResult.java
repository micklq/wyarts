package com.arts.org.webcomn;

import java.util.Set;

/**
 * 默认的页面请求返回的model对象.用来包裹controller的页面输出
 */
public class MethodResult<T> {
 
    
	 private Boolean success;
	 private String message;
	 private String code;
	 private T result; 
	 
	public MethodResult() {}
	
	public MethodResult(Boolean success, String message, String code, T result) {
		this.success= success;
		this.message= message;
		this.code= code;
		this.result= result;
		
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
    
  
    public MethodResult<T> SuccessResult(T result) { 
        
        return new MethodResult<T>(Boolean.TRUE,"success","200",result); 
    }
    public MethodResult<T> SuccessResult(T result,String code) { 
        
        return new MethodResult<T>(Boolean.TRUE,"success",code,result); 
    }
    public MethodResult<T> SuccessResult(T result,String code,String message) { 
    
    return new MethodResult<T>(Boolean.TRUE,message,code,result); 
    }
    
    public MethodResult<T> FailResult(String message) { 
        
        return new MethodResult<T>(Boolean.FALSE,message,"500",null); 
    }
    
    public  MethodResult<T> FailResult(String message,String code) { 
        
        return new MethodResult<T>(Boolean.FALSE,message,code,null); 
    }
    
   

}
