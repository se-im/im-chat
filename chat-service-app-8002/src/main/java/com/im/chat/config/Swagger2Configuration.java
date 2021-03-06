package com.im.chat.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaomanzhou
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("token").description("用户凭证")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false)
                .defaultValue("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJle" +
                        "HAiOjE2MzAwNDg5NTAsImlhdCI6MTU5ODUxMjk1MH0.y1Ciyp3GPZgbayPSlfX03BrJgwv7DO9Kp-Gc0IwR6J8")
                .build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.im.chat.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)  ;
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("会话聊天模块前后端交互api")
                .description("会话聊天模块前后端交互api")
                .termsOfServiceUrl("http://localhost:8002/")
                .version("1.0")
                .build();
    }
}
