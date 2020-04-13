package cn.yellowgg.ducksystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Description:丝袜哥配置类
 * @Author: yellowgg
 * @Date: Created in 2020/3/24 15:27
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("cn.yellowgg.ducksystem.controller.portal"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("小黄鸭天下第一").description("描述：🐥🐥小黄鸭IT培训机构后台管理系统的文档🐥🐥")
                .termsOfServiceUrl("https://github.com/yellowgg").version("1.0.0")
                .contact(new Contact("yellowgg", "", "yellowgggg@gmail.com")).build();
    }
}
