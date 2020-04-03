package cn.yellowgg.ducksystem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义注解 用于Base64解密
 * @Author: yellowgg
 * @Date: Created in 2020/4/3 15:07
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64DecodeStr {
    /**
     * 解析次数
     */
    int count() default 1;
}
