package com.example.webapi.config;

import com.example.webapi.interceptors.LoginInterceptors;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private LoginInterceptors loginInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptors).excludePathPatterns("/employees/login", "/doc.html", "/webjars/**")
                .excludePathPatterns("/swagger-resources/**","/swagger-ui/**", "/v3/**", "/error");
    }

    /*@Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("员工管理").version("1.0").description("员工管理集成Swagger").build();
        Docket docket = new Docket(DocumentationType.OAS_30).apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage("com.example.webapi.controller")).paths(PathSelectors.any()).build();
        return docket;
    }*/
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("十六进制说")
                        .description("十六进制说API文档")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://springshop.wiki.github.org/docs"));
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
