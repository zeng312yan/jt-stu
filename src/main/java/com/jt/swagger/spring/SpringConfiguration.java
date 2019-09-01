package com.jt.swagger.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan("com.jt.swagger")
@EnableWebMvc
@EnableSwagger2
public class SpringConfiguration {
        
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
                registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/swagger/");
        }

        @Bean
        public Docket docket() {
                List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
                responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_OK).message("操作成功").build());
                responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_NOT_FOUND).message("资源不存在").build());
                responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).message("服务器异常").build());
                
                return new Docket(DocumentationType.SWAGGER_2)
                                .apiInfo(apiInfo())
                                .useDefaultResponseMessages(false)
                                .globalResponseMessage(RequestMethod.GET, responseMessages)
                                .globalResponseMessage(RequestMethod.POST, responseMessages)
                                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                                .select()
                                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                                .paths(PathSelectors.any())
                        .build();
        }

        private ApiInfo apiInfo() {
                return new ApiInfoBuilder().title("测试项目").version("1.0.0").description("这是项目描述").build();
        }
}

