package cn.yellowgg.ducksystem.interceptor;

import cn.hutool.core.codec.Base64;
import cn.yellowgg.ducksystem.annotation.Base64DecodeStr;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @Description: 使用这个接口实现注解的解析 当然 只是在控制层使用 如果想全局使用可以用aop切很多方法
 * @Author: yellowgg
 * @Date: Created in 2020/4/3 15:28
 */
public class Base64DecodeStrResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Base64DecodeStr.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String value = webRequest.getParameter(parameter.getParameterName());
        for (int i = 0; i < parameter.getParameterAnnotation(Base64DecodeStr.class).count(); i++) {
            value = Base64.decodeStr(value);
        }
        return value;
    }
}
