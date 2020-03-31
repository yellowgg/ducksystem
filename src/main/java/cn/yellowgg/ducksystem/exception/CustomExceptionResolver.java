package cn.yellowgg.ducksystem.exception;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
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

    /**
     * 全局异常捕捉处理
     */

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody
    String errorHandler(Exception ex) {
        return null;
    }

    /**
     * 拦截捕捉 参数校验异常
     *
     * @param ex javax.validation.ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody
    String constraintViolationExceptionHandler(ConstraintViolationException ex) {
        return ServiceResult.asFail(UtilConstants.RespCode.PRECONDITIONFAILED,
                ex.getConstraintViolations().stream().map(x -> x.getMessage()).collect(Collectors.joining(",")))
                .toJson();
    }

}
