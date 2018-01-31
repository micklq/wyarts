package com.arts.org.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arts.org.webcomn.ueditor.ActionEnter;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("ueditor")
public class UEditorController {
	
	private static final Logger logger = LoggerFactory.getLogger(UEditorController.class);
	
//	@RequestMapping("welcome")
//	public String index(){		
//		return "index/welcome";
//	}
	
    @RequestMapping("config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
    	
        response.setContentType("text/html");
        String rootPath = request.getSession().getServletContext().getRealPath("/"); 
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
//            writer.flush();
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}
