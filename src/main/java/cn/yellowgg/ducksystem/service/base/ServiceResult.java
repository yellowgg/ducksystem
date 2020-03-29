package cn.yellowgg.ducksystem.service.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Description: 自定义响应结构
 * 前端接受此类数据（json object)后，可自行根据业务去实现相关功能
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 * @Author: yellowgg
 * @Date: Created in 2020/3/27 15:08
 */
@Data
@ApiModel(value = "自定义响应结构类")
public class ServiceResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    @JsonIgnore
    private String ok; // 不使用

    public static ServiceResult build(Integer status, String msg, Object data) {
        return new ServiceResult(status, msg, data);
    }

    public static ServiceResult build(Integer status, String msg, Object data, String ok) {
        return new ServiceResult(status, msg, data, ok);
    }

    public static ServiceResult ok(Object data) {
        return new ServiceResult(data);
    }

    public static ServiceResult ok() {
        return new ServiceResult(null);
    }

    public static ServiceResult errorMsg(String msg) {
        return new ServiceResult(500, msg, null);
    }

    public static ServiceResult errorMap(Object data) {
        return new ServiceResult(501, "error", data);
    }

    public static ServiceResult errorTokenMsg(String msg) {
        return new ServiceResult(502, msg, null);
    }

    public static ServiceResult errorException(String msg) {
        return new ServiceResult(555, msg, null);
    }

    public ServiceResult() {
    }

    public ServiceResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public ServiceResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

}
