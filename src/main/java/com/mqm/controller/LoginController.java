package com.mqm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mqm.service.UserServiceI;


@Controller
@RequestMapping("web")
public class LoginController {
	@Autowired UserServiceI userService;
	
	@RequestMapping("/toLogin")
	public String loginGet(ModelMap model){
		return "/web/login/login";
	}
	
	@RequestMapping(value = "/loginPost",method = RequestMethod.POST)
	public String loginPost(@RequestParam("username") String username,@RequestParam("password") String password){
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());  
        // 登录后存放进shiro token  
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
        Subject subject = SecurityUtils.getSubject();  
       
        try {
        	 subject.login(token);  
		    }
		    catch (AuthenticationException ae) {
		    	System.out.println("登录失败:"+ae.getMessage());
		    	return "/web/login/error";
		    }
		return "/web/login/loginSuccess";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "/web/login/admin";
	}
	
}