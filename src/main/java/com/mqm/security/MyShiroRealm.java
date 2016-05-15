package com.mqm.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/****
 * 自定义Realm
 */

@Component
public class MyShiroRealm extends AuthorizingRealm {
	/**
	 * 认证
	 */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	//登录信息
    	//1.从token中获取登录的用户名，并不获取密码
    	
    	//2.通过用户名，从数据库查询用户信息
    	
    	//3.创建SimpleAuthenticationInfo对象，并返回，密码校验由shiro进行
    	
    	//4.密码加密
    	//1)选择加密方式
    	//2)设置盐值：一般从数据库中得到
    	//认证信息
    	return null;
    }
    
    // @PostConstruct 相当于bean节点的 init-method 配置
//    @PostConstruct
//    public void setCredentialMatcher(){
//    	HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//    	credentialsMatcher.setHashAlgorithmName("MD5");
//    	setCredentialsMatcher(credentialsMatcher);
//    }
    
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	return null;
    }

}
