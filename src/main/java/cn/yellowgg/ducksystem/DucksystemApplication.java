package cn.yellowgg.ducksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "cn.yellowgg.ducksystem.*")
public class DucksystemApplication {

    public static void main(String[] args) {
        // TODO yellowgg 热部署会导致很多东西有毛病，测试的时候加上注释
        /**
         * 已知错误：1. shiro的标签会失效
         */
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(DucksystemApplication.class, args);
    }

}
