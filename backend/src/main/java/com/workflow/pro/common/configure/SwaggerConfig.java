package com.workflow.pro.common.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.oas.annotations.EnableOpenApi;
import java.util.ArrayList;

/**
 * Swagger 文档配置
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 * */
@Configuration
@EnableOpenApi
public class SwaggerConfig  {

    private static final String BASE_PACKAGE = "com.workflow";

    private static final String DEFAULT_GROUP = "default";

    private static final Boolean ENABLE = true;

    @Bean
    public Docket docker(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName(DEFAULT_GROUP)
                .enable(ENABLE)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Cms Admin API",
                "Spring Boot 企业级开发平台" ,
                "0.0.1",
                "www.workflow.com",new Contact("cms-admin","https://gitee.com/pear-admin","jmys1992@gmail.com"),
                "apache license",
                "https://gitee.com/pear-admin",
                new ArrayList<>()
        );
    }

}
