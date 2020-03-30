package cn.yellowgg.ducksystem.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: springMVC配置
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 17:07
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 很多地方都能使默认配置失效，所以需要手动恢复静态资源路径的配置
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // swagger页面404的解决办法
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
