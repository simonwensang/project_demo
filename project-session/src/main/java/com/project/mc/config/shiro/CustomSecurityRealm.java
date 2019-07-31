package com.project.mc.config.shiro;

import com.alibaba.fastjson.JSON;
import com.project.mc.bean.SessionUser;
import com.allbuywine.mc.common.constants.Constants;
import com.allbuywine.mc.common.constants.UserStatus;

import com.project.mc.convertor.SessionUserConvertor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.crazycake.shiro.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

//@Component("customSecurityRealm")
public class CustomSecurityRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

/*	@Autowired
	private UserServerDal userServerDal;

	@Autowired
	private UserMapper userMapper;*/

	public CustomSecurityRealm() { 
		setName("customSecurityRealm");
		// 采用MD5加密
		//setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
		// 认证
//		super.setAuthenticationCacheName(Constants.AUTHENTICATIONCACHENAME);
//		super.setAuthenticationCachingEnabled(false);

		// 授权
//		super.setAuthorizationCacheName(Constants.AUTHORIZATIONCACHENAME);
	}

	/**
	 * 为当前登录的Subject授予角色和权限
	 *
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	
	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

		/*if( authcToken instanceof   UsernamePasswordCaptchaToken){
			UsernamePasswordCaptchaToken token=(UsernamePasswordCaptchaToken) authcToken;
			UserServer userServer = userServerDal.queryUserServerByAccount(token.getUsername());
			if ( userServer != null ) {
				if(UserStatus.FORBIDDEN.equal(userServer.getStatus())){
					throw new AuthenticationException(Constants.LOCK_ERROR);
				}
			}else {
				throw new AuthenticationException(Constants.NAME_PWD_ERROR);
			}
			return new SimpleAuthenticationInfo(SessionUserConvertor.userServerToSessionUser(userServer) , userServer.getPassword(), getName());

		} else if( authcToken instanceof   MachineToken){
		*//*	MachineToken token=(MachineToken) authcToken;

			UserExample example= new UserExample();
			UserExample.Criteria  criteria= example.createCriteria();
			criteria.andAccountEqualTo(token.getUsername());
			List<User> userEntityList = userMapper.selectByExample(example);
			User user = null;
			Machine machine = machineDal.selectByMachineCode(token.getUsername());
			if ( userEntityList.size()>0 && machine != null) {
				user = userEntityList.get(0);
				if(UserStatus.FORBIDDEN.equal(user.getuStatus())){
					throw new AuthenticationException(Constants.LOCK_ERROR);
				}

				if(  LockedSale.STOP_SELL.equal(machine.getIsLockedSale())){
					throw new AuthenticationException(Constants.LOCK_ERROR);
				}
			}else {
				throw new AuthenticationException(Constants.NAME_PWD_ERROR);
			}
			return new SimpleAuthenticationInfo(user, user.getPassword(), getName());*//*

		}   else if (authcToken instanceof WxLoginAuthenticationToken){
			SessionUser sessionUser = null;
			User user = null;
			try {
				WxLoginAuthenticationToken token = (WxLoginAuthenticationToken) authcToken;
				UserExample example = new UserExample();
				UserExample.Criteria criteria = example.createCriteria();
				criteria.andOpenIdEqualTo(token.getUsername());
				List<User> userEntityList = userMapper.selectByExample(example);

				if (userEntityList.size() > 0) {
					user = userEntityList.get(0);
					if (UserStatus.FORBIDDEN.getKey() == user.getUStatus().byteValue()) {
						throw new AuthenticationException(Constants.LOCK_ERROR);
					}
				} else {
					user = new User();
					user.setOpenId(token.getUsername());
					user.setPassword("0");
					userMapper.insertSelective(user);
				}
				logger.info("toLogin user：" + JSON.toJSONString(user));
				user.setSessionKey(token.getSessionKey());
				sessionUser = SessionUserConvertor.toSessionUser(user);
			}catch (AuthenticationException e){
				throw e;
			}catch (Exception e){
				logger.error(e.getMessage());
				throw new AuthenticationException(Constants.LOGIN_ERROR);
			}
			//SecurityUtils.getSubject().getSession().setAttribute("user", userEntity);
			return new SimpleAuthenticationInfo(sessionUser, user.getPassword(), getName());
		}*/

		throw new AuthenticationException(Constants.TOKEN_ERROR);
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
//        setCredentialsMatcher(new CustomCredentialsMatcher());
    }

	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		if (!CollectionUtils.isEmpty(principals)) {
			RedisCache cache =  (RedisCache)this.getAuthenticationCache() ;
			//cache instance will be non-null if caching is enabled:
			if (cache != null) {
				SessionUser sessionUser = (SessionUser)getAuthenticationCacheKey(principals);
				cache.remove(sessionUser.getAccount());
			}
		}
	}
}
