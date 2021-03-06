package cn.yellowgg.ducksystem.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 自定义异常类
 * @Author:yellowgg
 * @Date: Created in 2020/3/26 10:39
 */
@Getter
@Setter
public class CustomException extends Exception {

    public String msg;

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
