package com.jackguo.config.shiro;

//用户认证

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jackguo.entil.SysUser;
import lombok.Data;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class MyShiroConfig {

    //    1.shiroFilterFactoryBean
//    2.DefaultWebSercurtiyManager
//    3.创建realm对象

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //        设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
//        添加shiro的内置过滤器             user:必须拥有记住我功能的才能访问
////      role：拥有某个角色权限才能访问     perms:拥有对某个资源的权限才能访问
//        anon:无需认证就可以访问       authc:必须认证才能访问
//        拦截一些操作
        Map<String, String> filterMap = new LinkedHashMap<>();
//
//      配置不用认证的资源
        filterMap.put("/page/login", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/json/**", "anon");
        filterMap.put("/layui/**", "anon");
        filterMap.put("/dtree/**", "anon");
        filterMap.put("/sliderVerify/**", "anon");

//      需要认证的资源
        filterMap.put("/page/**", "authc");

        bean.setFilterChainDefinitionMap(filterMap);

//         配置需要授权的权限
//        filterMap.put("/views/add", "perms[user:add]");
//        filterMap.put("/views/save", "perms[user:save]");
////        设置登录的请求
        bean.setLoginUrl("/page/login");
//        设置未授权页面
//        bean.setUnauthorizedUrl("/unerror");

        return bean;

    }


    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

//关联MyAuthortion
        securityManager.setRealm(myRealm);
        return securityManager;
    }
//  加入自己的领域
    @Bean(name = "myRealm")
    public MyRealm myRealm() {
        return new MyRealm();
    }

    //    整合thymeleaf：shiroDialect
    @Bean
    public ShiroDialect getShiroDialect() {

        return new ShiroDialect();
    }
}

