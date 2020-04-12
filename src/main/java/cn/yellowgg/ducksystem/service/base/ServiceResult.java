package cn.yellowgg.ducksystem.service.base;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 自定义响应结构
 * @Author: yellowgg
 * @Date: Created in 2020/3/27 15:08
 */
@Data
@ApiModel(value = "自定义响应结构类")
public class ServiceResult<T> {

    @ApiModelProperty(value = "响应码")
    private Integer respCode;

    @ApiModelProperty(value = "附属消息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T obj;

    @ApiModelProperty(value = "请求结果")
    private boolean success;

    private ServiceResult(Integer respCode, String msg, T obj, boolean success) {
        this.respCode = respCode;
        this.msg = msg;
        this.obj = obj;
        this.success = success;
    }

    public static <T> ServiceResult asSuccess(T obj) {
        return new ServiceResult(HttpStatus.HTTP_OK, null, obj, true);
    }

    public static <T> ServiceResult asSuccess(T obj, String msg) {
        return new ServiceResult(HttpStatus.HTTP_OK, msg, obj, true);
    }

    public static ServiceResult asFail(String msg) {
        return new ServiceResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, null, false);
    }

    /**
     * @param code 状态码
     */
    public static ServiceResult asFail(Integer code, String msg) {
        return new ServiceResult(code, msg, null, false);
    }

    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }
}
