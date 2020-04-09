package cn.yellowgg.ducksystem.interceptor;

import cn.hutool.core.util.ArrayUtil;
import cn.yellowgg.ducksystem.annotation.Base64DecodeStr;
import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import cn.yellowgg.ducksystem.utils.LogUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
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

    private static final transient Logger log = LogUtils.getExceptionLogger();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(Base64DecodeStr.class) || parameter.hasParameterAnnotation(Base64DecodeStr.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        int count = parameter.hasParameterAnnotation(Base64DecodeStr.class)
                ? parameter.getParameterAnnotation(Base64DecodeStr.class).count()
                : parameter.getMethodAnnotation(Base64DecodeStr.class).count();
        String[] excludeFields = parameter.hasParameterAnnotation(Base64DecodeStr.class)
                ? parameter.getParameterAnnotation(Base64DecodeStr.class).excludeField()
                : parameter.getMethodAnnotation(Base64DecodeStr.class).excludeField();
        // 参数类型
        if (BaseEntity.class.isAssignableFrom(parameter.getParameterType())) {
            Object obj = parameter.getParameterType().newInstance();
            webRequest.getParameterMap().forEach((k, v) -> {
                try {
                    if (ArrayUtil.contains(excludeFields, k)) {
                        BeanUtils.setProperty(obj, k, v[0]);
                    } else {
                        BeanUtils.setProperty(obj, k, Base64Utils.decodeStrofCount(v[0], count));
                    }
                } catch (Exception e) {
                    log.error("参数解码有误", e);
                    throw new RuntimeException("BeanUtils.setProperty错误");
                }
            });
            return obj;
        } else if (!Iterable.class.isAssignableFrom(parameter.getParameterType())) {
            return Base64Utils.decodeStrofCount((webRequest.getParameter(parameter.getParameterName())), count);
        }
        throw new RuntimeException("参数解码有误");
    }

}
