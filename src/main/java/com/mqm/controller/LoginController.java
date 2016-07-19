package com.mqm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mqm.entity.User;
import com.mqm.service.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("web")
public class LoginController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserServiceI userService;
	
	@RequestMapping("/toLogin")
	public String loginGet(){
		logger.debug("进入登录页面");
		return "/web/login/login";
	}

	@RequestMapping("/admin")
	public String admin(){
		logger.debug("进入admin页面");
		return "/web/login/admin";
	}
	
	@RequestMapping(value = "loginPost",method = RequestMethod.POST)
	public String loginPost(@RequestParam("username") String username,@RequestParam("password") String password){
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());  
        // 登录后存放进shiro token 
        String repassword = new Sha256Hash(password).toHex();
        UsernamePasswordToken token = new UsernamePasswordToken(username, repassword);  
        Subject subject = SecurityUtils.getSubject();  
        try {
        	 subject.login(token);
        	 logger.debug("登录成功,用户:"+username);
		    }
		    catch (AuthenticationException ae) {
		    	logger.debug("登录失败:"+ae.getMessage());
		    	return "/error/error";
		    }
		return "/web/login/loginSuccess";
	}
	
	@RequestMapping(value = "/toRegister")
	public String toRegister(Model model){
		return "/web/register/register";
	}
	
	@RequestMapping(value = "/registerPost",method = RequestMethod.POST)
	public String registerPost(@RequestParam("username") String username,@RequestParam("password") String password,ModelMap model){
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());  
        model.addAttribute("user", new User());
        User user = userService.findUser(username);
        
        if(user == null){
        	userService.createUser(username, password);
        	// 登录后存放进shiro token  
        	String repassword = new Sha256Hash(password).toHex();
        	UsernamePasswordToken token = new UsernamePasswordToken(username, repassword);  
        	Subject subject = SecurityUtils.getSubject(); 
        	subject.login(token);
        	
        	return "/web/login/loginSuccess";
        }else{
        	model.put("errMsg", "用户名已存在");
        	return "/error/error";
        }
	}
}