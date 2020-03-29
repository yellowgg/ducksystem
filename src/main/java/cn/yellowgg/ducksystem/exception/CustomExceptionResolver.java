package cn.yellowgg.ducksystem.exception;

import cn.yellowgg.ducksystem.utils.LogUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 全局异常处理器
 * springmvc提供一个HandlerExceptionResolver接口
 * 只要实现该接口，并配置到spring 容器里，该类就能成为默认全局异常处理类
 * 全局异常处理器只有一个，配置多个也没用
 * @Author:yellowgg
 * @Date: Created in 2020/3/26 10:42
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private static final transient Logger log = LogUtils.getExceptionLogger();

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        CustomException customException;
        if (e instanceof CustomException) {
            customException = (CustomException) e;
        } else if (e instanceof UnknownAccountException) {
            //用户名错误异常
            modelAndView.addObject("message", "没有该用户");
            modelAndView.setViewName("/error");
            return modelAndView;
        } else if (e instanceof IncorrectCredentialsException) {
            //用户名错误异常
            modelAndView.addObject("message", "密码错误");
            modelAndView.setViewName("/error");
            return modelAndView;
        } else {
            customException = new CustomException(e.getMessage());
        }
        //错误信息
        String message = customException.getMessage();
        log.error(message);
        //错误信息传递和错误页面跳转
        modelAndView.addObject("message", message);
        modelAndView.setViewName("/error");
        return modelAndView;
    }
}
