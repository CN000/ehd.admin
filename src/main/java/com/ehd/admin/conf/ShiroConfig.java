package com.ehd.admin.conf;

import com.ehd.admin.common.Base64Util;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {
    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * 开启thymeleaf模板访问，注释则使用配置的jsp或者HTML访问模式
     * @return
     */
//	@Bean
//	public ShiroDialect shiroDialect() {
//		return new ShiroDialect();
//	}

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("--------ShiroConfiguration.shirFilter()");
        // 添加安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/index.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        // shiroFilterFactoryBean.setSuccessUrl("/?path=user/main");
        // 添加shiro内置过滤器
        /**
         * anon:表示可以匿名使用。 authc:表示需要认证(登录)才能使用，没有参数
         *
         * roles：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，
         * 例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
         *
         * perms：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，
         * 例如/admins/user/**=perms["user:add:*,user:modify:*"]，
         * 当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
         *
         * rest：根据请求的方法，相当于/admins/user/**=perms[user:method]
         * ,其中method为post，get，delete等。
         *
         * port：当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,
         * 其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，
         * queryString是你访问的url里的？后面的参数。 authcBasic：没有参数表示httpBasic认证
         *
         * ssl:表示安全的url请求，协议为https user:当登入操作时不做检查
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 静态资源的处理
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/build/**", "anon");
        filterChainDefinitionMap.put("/address/getAddress", "anon");
        filterChainDefinitionMap.put("/withdraw/list", "anon");
        filterChainDefinitionMap.put("/recharge/list", "anon");
        filterChainDefinitionMap.put("/withdraw/notifyStatus", "anon");
        filterChainDefinitionMap.put("/frontUser/list", "anon");
        filterChainDefinitionMap.put("/main/**", "anon");
        filterChainDefinitionMap.put("/sys/**", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");
        filterChainDefinitionMap.put("/withdraw/**", "anon");
        filterChainDefinitionMap.put("/vendors/**", "anon");
        filterChainDefinitionMap.put("/frontUser/createChiIds", "anon");
        // 退出系统的过滤器 要在filterChainDefinitionMap.put("/**", "authc");之前，否则不生效
        filterChainDefinitionMap.put("/logOut", "logout");
        // 默认所有资源必须认证才能访问
        filterChainDefinitionMap.put("/**", "authc");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);// 散列的次数（默认一次），比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        try {
            cookieRememberMeManager.setCipherKey(Base64Util.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookieRememberMeManager;
    }

    /**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");// 数据库异常处理
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings); // None by default
        r.setDefaultErrorView("error"); // No default
        r.setExceptionAttribute("ex"); // Default is "exception"
        // r.setWarnLogCategory("example.MvcLogger"); // No default
        return r;
    }
}
