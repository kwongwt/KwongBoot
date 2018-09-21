package com.kwong.boot.config.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kwong.boot.core.intercept.KwongUserFilter;
import com.kwong.boot.core.shiro.ShiroDbRealm;



/**
 * @author kwong shiro权限管理配置
 */
@Configuration
public class ShiroConfig {
	
    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(CookieRememberMeManager rememberMeManager, CacheManager cacheShiroManager, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.shiroDbRealm());
        securityManager.setCacheManager(cacheShiroManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
	
	
    /**
     * 项目自定义的Realm
     */
    @Bean
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }
	
	/**
	 * Shiro的过滤器链
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		/**
		 * 默认的登陆访问url
		 */
		shiroFilter.setLoginUrl("/login");
		/**
		 * 登陆成功后跳转的url
		 */
		shiroFilter.setSuccessUrl("/");
		/**
		 * 没有权限跳转的url
		 */
		shiroFilter.setUnauthorizedUrl("/global/error");

		/**
		 * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
		 */
		HashMap<String, Filter> myFilters = new HashMap<>();
		myFilters.put("user", new KwongUserFilter());
		shiroFilter.setFilters(myFilters);

		/**
		 * 配置shiro拦截器链
		 *
		 * anon 不需要认证 authc 需要认证 user 验证通过或RememberMe登录的都可以
		 *
		 * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
		 *
		 * 顺序从上到下,优先级依次降低
		 *
		 * api开头的接口，走rest api鉴权，不走shiro鉴权
		 *
		 */
		Map<String, String> hashMap = new LinkedHashMap<>();
		hashMap.put("/static/**", "anon");
		hashMap.put("/kwongApi/**", "anon");
		hashMap.put("/login", "anon");
		hashMap.put("/global/sessionError", "anon");
		hashMap.put("/kaptcha", "anon");
		hashMap.put("/**", "user");
		shiroFilter.setFilterChainDefinitionMap(hashMap);
		return shiroFilter;
	}

	
}
