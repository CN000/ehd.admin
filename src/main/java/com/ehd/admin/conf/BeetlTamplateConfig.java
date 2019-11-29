package com.ehd.admin.conf;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeetlTamplateConfig {

    @Value("templates") String templatesPath;//模板根目录 ，比如 "templates"
    @Bean(name = "BeetlTamplateConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        //获取Spring Boot 的ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader==null){
            loader = BeetlTamplateConfig.class.getClassLoader();
        }
        //beetlGroupUtilConfiguration.setConfigProperties(extProperties);//额外的配置，可以覆盖默认配置，一般不需要
        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader,
                templatesPath);
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
        return beetlGroupUtilConfiguration;

    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("BeetlTamplateConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        beetlSpringViewResolver.setSuffix(".html");      //设置后缀后即可在controller中使用 return "/hello" 而不必 return "hello.html"
        return beetlSpringViewResolver;
    }


}