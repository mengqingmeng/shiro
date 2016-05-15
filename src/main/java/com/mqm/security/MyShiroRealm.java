package com.mqm.security;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mqm.service.UserServiceI;

/****
 * 自定义Realm
 */

@Component
public class MyShiroRealm extends AuthorizingRealm {
	@Autowired UserServiceI userService;
	/**
	 * 认证
	 */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	//登录信息
    	Object principals = authcToken.getPrincipal().toString();
    	String username = authcToken.getPrincipal().toString();
    	//1.从token中获取登录的用户名，并不获取密码
    	
    	//2.通过用户名，从数据库查询用户信息
    	
    	//3.创建SimpleAuthenticationInfo对象，并返回，密码校验由shiro进行
    	
    	//4.密码加密
    	//1)选择加密方式
    	//2)设置盐值：一般从数据库中得到
    	//认证信息
    	String credentials = "1234";
    	String realmName = getName();
    	
    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principals,credentials,realmName);
    	return info;
    }
    
    // @PostConstruct 相当于bean节点的 init-method 配置
    @PostConstruct
    public void setCredentialMatcher(){
    	HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
    	credentialsMatcher.setHashAlgorithmName("MD5");
    	setCredentialsMatcher(credentialsMatcher);
    }
    
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    	
    	Object principal = principals .getPrimaryPrincipal();
    	
    	if("admin".equals(principal)){
    		info.addRole("admin");
    	}
    	
    	info.addRole("user");
    	return null;
    }

}
