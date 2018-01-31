package com.arts.org.web.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidAccessAuthorizationFilter extends AuthorizationFilter {

	private static final Logger logger = LoggerFactory.getLogger(InvalidAccessAuthorizationFilter.class);
	@Override
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) throws Exception {
		logger.debug("===========invoke InvalidAccessAuthorizationFilter=================");
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String referer=req.getHeader("Referer");
//		if(StringUtils.isBlank(referer)){
//			logger.error("##########referer is null##################");
//			return false;
//		}
		return true;
	}

}
