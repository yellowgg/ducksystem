package cn.yellowgg.ducksystem.exception;

import cn.hutool.http.HttpStatus;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @Description: 全局异常处理器
 * @Author:yellowgg
 * @Date: Created in 2020/3/26 10:42
 */
@ControllerAdvice
public class CustomExceptionResolver {

    private static final transient Logger log = LogUtils.getExceptionLogger();

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ServiceResult errorHandler(Exception ex) {
        log.info("【全局异常捕捉】", ex);
        return ServiceResult.asFail("系统错误，请稍后重试");
    }

    /**
     * 拦截捕捉 参数校验异常
     */
    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public ServiceResult constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.info("【参数校验异常捕捉】", ex);
        return ServiceResult.asFail(HttpStatus.HTTP_PRECON_FAILED, ex.getConstraintViolations().stream()
                .map(x -> x.getMessage()).collect(Collectors.joining(",")));
    }

    /**
     * 拦截捕捉 参数校验异常
     */
    @ResponseBody
    @ExceptionHandler({BindException.class})
    public ServiceResult bindExceptionHandler(BindException ex) {
        log.info("【参数绑定异常捕捉】", ex);
        return ServiceResult.asFail(HttpStatus.HTTP_PRECON_FAILED, ex.getBindingResult().getAllErrors().stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
    }


}
