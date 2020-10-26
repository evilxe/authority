package com.fzw.authority.config;

import com.fzw.authority.properties.RedisProperties;
import com.fzw.authority.shiro.BackRealm;
import com.fzw.authority.shiro.MySessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiRoConfig {

    private static final Logger LOGGER  = LoggerFactory.getLogger(ShiRoConfig.class);

    @Autowired
    private WeChatConfig weChatConfig;

    /**
     * @Author FangZhiWei
     * @Description TODO shiro过滤器
     * @Date 11:40 2020/8/26 0026
     */
    @Bean
    public ShiroFilterFactoryBean shiRoFilter(SecurityManager securityManager){
        LOGGER.info("------------------ShiRO过滤器-----------------------");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 访问需要登录的接口没登录时调此接口
        shiroFilterFactoryBean.setLoginUrl("/back/needLogin");
        // 访问未授权接口无授权信息时调此接口
        shiroFilterFactoryBean.setUnauthorizedUrl("/back/notPermit");

        //自定义拦截器
//         Map<String, Filter> filterMap = new LinkedHashMap<>();
//        filterMap.put("roleOrFilter", new CustomShiRoFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);


        // 设置拦截器地址 LinkedHashMao 有序，无序回造成拦截顺序错乱给
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //退出过滤器
        filterChainDefinitionMap.put("/back/landing","logout");
        //匿名可以访问，也是就游客模式
        filterChainDefinitionMap.put("/back/landing/**","anon");

        filterChainDefinitionMap.put("/back/logIn","anon");

        filterChainDefinitionMap.put("/back/**", "authc");
//        filterChainDefinitionMap.put("/**", "authc, roleOrFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(backRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public BackRealm backRealm(){
        BackRealm backRealm = new BackRealm();
        return  backRealm;
    }

    @Bean
    public MySessionManager sessionManager() {
        SimpleCookie simpleCookie = new SimpleCookie("Token");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);

        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisProperties port = weChatConfig.redisProperties();
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(port.getHost());
        redisManager.setPort(port.getPort());
        redisManager.setTimeout(1800); //设置过期时间
        redisManager.setPassword(port.getPassword());
        return redisManager;
    }

    /**
     * RedisSessionDAO
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("HOSP:SPRINGBOOT_SESSION:");
        return redisSessionDAO;
    }


    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
