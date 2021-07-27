package com.jackguo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author
 * @Description
 */
@Configuration
@EnableSwagger2
class Swagger2Config {

    /**
     * 配置swagger2
     */
    @Bean
    public Docket docket(Environment environment){

        //获取项目环境：如果是dev或者是test返回true ,这个一般用于配置环境和发布环境的切换
        //Profiles profiles = Profiles.of("dev","test");
        //boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("jackGuo")
                //是否开启swagger
                //.enable(flag)
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式 1、basePackage：基于包扫描 2、withMethodAnnotation 扫描方法上的注解
//                .apis(RequestHandlerSelectors.basePackage("com.alan.swaggerdemo.controller"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                //paths(),过滤什么路径，只扫描什么路径下的文件
                //.paths(PathSelectors.ant("/alan/xxx"))
                .build();//build
    }

    /**
     * 配置Swagger信息 apiInfo
     */
    public ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("JackGuo", "www.baidu.com", "1346421829@qq.com");
        return new ApiInfo(
                "JackGuo's SwaggerAPi文档",
                "这个是描述",
                "1.0",
                "www.baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

}