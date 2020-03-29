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
        SpringApplication.run(DucksystemApplication.class, args);
    }

}
