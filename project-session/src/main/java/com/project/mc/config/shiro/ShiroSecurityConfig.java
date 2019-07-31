package com.project.mc.config.shiro;

import com.project.mc.properties.UrmsProperties;
import com.project.mc.properties.UrmsProperties;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroSecurityConfig implements   BeanFactoryAware {

	private BeanFactory beanFactory;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
	}

	/**
	 * FilterRegistrationBean
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*"); 
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
	}
	
	/**
	 * @see ShiroFilterFactoryBean
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(UrmsProperties urmsProperties){
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl(urmsProperties.getLoginUrl());
		shiroFilter.setUnauthorizedUrl(urmsProperties.getUnauthorizedUrl());

		Map<String, Filter> filters = new HashMap<>();
	   // filters.put("kickout", kickoutSessionControlFilter());
//	      filters.put("authc", wswyAuthenticationFilter());
		filters.put("anon", new AnonymousFilter());
		shiroFilter.setFilters(filters);
		Map<String, String> definitionsMap = new LinkedHashMap<>();
		// 过滤不需要拦截的请求
		definitionsMap.putAll(urmsProperties.getAnons());
		definitionsMap.put("/**", "authc"); //authc只做用户身份验证, urlc做url验证
		shiroFilter.setFilterChainDefinitionMap(definitionsMap);
		return shiroFilter;
	}
	
	/**
	 * @see org.apache.shiro.mgt.SecurityManager
	 * @return
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(customSecurityRealm());
		manager.setCacheManager(redisCacheManager());
		manager.setSessionManager(defaultWebSessionManager());
		return manager;
	}
	
	/**
	 * @see DefaultWebSessionManager
	 * @return
	 */
	@Bean(name="sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(redisCacheManager());
		sessionManager.setGlobalSessionTimeout(7200000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	@Bean
    public RedisSessionDAO redisSessionDAO(){
    	RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    	redisSessionDAO.setKeyPrefix("wine_shiro_redis_session:");
    	redisSessionDAO.setRedisManager(beanFactory.getBean("redisManager",RedisManager.class));
    	return redisSessionDAO;
    }
	 
	@Bean
    public RedisCacheManager redisCacheManager(){
    	RedisCacheManager rcm =  new RedisCacheManager();
    	rcm.setKeyPrefix("wine_shiro_redis_cache:");
    	rcm.setRedisManager(beanFactory.getBean("redisManager",RedisManager.class));
    	return rcm;
    }

     @Bean
    //@DependsOn(value={"lifecycleBeanPostProcessor", "shrioRedisCacheManager"})
    public CustomSecurityRealm customSecurityRealm(){
    	CustomSecurityRealm userRealm = new CustomSecurityRealm();
    	userRealm.setCacheManager(redisCacheManager());
    	userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCachingEnabled(true);
		userRealm.setAuthorizationCachingEnabled(true);
    	return userRealm;
    }
    
  /*
  	//shiro注释  会和 spring 事务cglib 冲突  双层aop
  	@Bean
    @DependsOn(value="lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }
 
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }*/
    
   /* @Bean(name="shrioRedisCacheManager")
	@DependsOn(value="redisTemplate")
	public ShrioRedisCacheManager redisCacheManager() {
		ShrioRedisCacheManager cacheManager = new ShrioRedisCacheManager(redisTemplate());
		return cacheManager;
	}
	
	@Bean(name="redisTemplate")
	public RedisTemplate<byte[], Object> redisTemplate() {
	    RedisTemplate<byte[], Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(connectionFactory());
	    return template;
	}
	
	@Bean
	public JedisConnectionFactory connectionFactory(){
		JedisConnectionFactory conn = new JedisConnectionFactory();
		conn.setDatabase(3);
		conn.setHostName("127.0.0.1");
		//conn.setPassword("123456");
		conn.setPort(6379);
		conn.setTimeout(3000);
		return conn;
	}*/
    
    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}