package com.mqm.security;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;
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

import com.mqm.entity.Role;
import com.mqm.entity.User;
import com.mqm.service.UserServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/****
 * 自定义Realm
 */

@Component
public class MyShiroRealm extends AuthorizingRealm {
	@Autowired UserServiceI userService; 
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 认证，验证用户名和密码
	 */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	
    	logger.debug("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(authcToken, ToStringStyle.MULTI_LINE_STYLE));  
    	//登录信息
    	//1.从token中获取登录的用户名，并不获取密码
    	String username = authcToken.getPrincipal().toString();
    	//2.通过用户名，从数据库查询用户信息
    	User user = userService.findUser(username);
    	//3.创建SimpleAuthenticationInfo对象，并返回，密码校验由shiro进行
    	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),getName());
    	//4.密码加密
    	//1)选择加密方式
    	//2)设置盐值：一般从数据库中得到
    	//认证信息
    	return info;
    }
    
    // @PostConstruct 相当于bean节点的 init-method 配置
//    @PostConstruct
//    public void setCredentialMatcher(){
//    	HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//    	credentialsMatcher.setHashAlgorithmName("MD5");
//    	setCredentialsMatcher(credentialsMatcher);
//    }
    
    /**
     * 授权，权限管理
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	String userName = (String)principals.fromRealm(getName()).iterator().next();
    	User user = userService.findUser(userName);
    	if( user != null ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for( Role role : user.getRoles()) {
            	logger.debug("role:"+role.getName()+";permission:"+role.getName());
                info.addRole(role.getName());
                info.addStringPermission( role.getName() );
            }
            return info;
        } else {
            return null;
        }
    }

}
