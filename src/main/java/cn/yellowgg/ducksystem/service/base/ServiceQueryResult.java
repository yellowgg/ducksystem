package cn.yellowgg.ducksystem.service.base;

import cn.hutool.http.HttpStatus;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/11 22:11
 */
@Data
@ApiModel(value = "自定义响应结构(列表)")
public class ServiceQueryResult<T> {

    @ApiModelProperty(value = "响应码")
    private Integer respCode;

    @ApiModelProperty(value = "附属消息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private List<T> obj;

    @ApiModelProperty(value = "请求结果")
    private boolean success;

    private long totalCount;

    public ServiceQueryResult(Integer respCode, String msg, List<T> obj, boolean success, long totalCount) {
        this.respCode = respCode;
        this.msg = msg;
        this.obj = obj;
        this.success = success;
        this.totalCount = totalCount;
    }

    public static <T> ServiceQueryResult asSuccess(PageInfo<T> pageInfo, String msg) {
        return new ServiceQueryResult(HttpStatus.HTTP_OK, msg, pageInfo.getList(), true, pageInfo.getTotal());
    }

    public static <T> ServiceQueryResult asSuccess(List<T> objs) {
        return new ServiceQueryResult(HttpStatus.HTTP_OK, null, objs, true, objs.size());
    }
}
