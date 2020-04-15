package cn.yellowgg.ducksystem.service.base;

import cn.hutool.http.HttpStatus;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
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
    @ApiModelProperty(value = "数据列表")
    private List<T> data;
    @ApiModelProperty(value = "请求结果", example = "ture表示成功并存在数据")
    private boolean success;
    @ApiModelProperty(value = "列表数据数量，不是分页数据数量")
    private long totalCount;

    public ServiceQueryResult(Integer respCode, String msg, List<T> data, boolean success, long totalCount) {
        this.respCode = respCode;
        this.msg = msg;
        this.data = data;
        this.success = success;
        this.totalCount = totalCount;
    }

    public static <T> ServiceQueryResult asSuccess(PageInfo<T> pageInfo) {
        return ServiceQueryResult.asSuccess(pageInfo, UtilConstants.Str.EMPTYSTR);
    }

    public static <T> ServiceQueryResult asSuccess(PageInfo<T> pageInfo, String msg) {
        return new ServiceQueryResult(HttpStatus.HTTP_OK, msg, pageInfo.getList(), UtilConstants.Bool.TRUE, pageInfo.getTotal());
    }

    public static <T> ServiceQueryResult asSuccess(List<T> objs) {
        return new ServiceQueryResult(HttpStatus.HTTP_OK, UtilConstants.Str.EMPTYSTR, objs, UtilConstants.Bool.TRUE, objs.size());
    }

    public static <T> ServiceQueryResult asFail(String msg) {
        return new ServiceQueryResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, Lists.newArrayList(), UtilConstants.Bool.TRUE, UtilConstants.Number.ZERO);
    }
}
