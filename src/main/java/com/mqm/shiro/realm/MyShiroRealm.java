package com.mqm.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
    	
    	//认证信息
    	String credentials = "1234";
    	String realmName = getName();
    	
    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principals,credentials,realmName);
    	return info;
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
