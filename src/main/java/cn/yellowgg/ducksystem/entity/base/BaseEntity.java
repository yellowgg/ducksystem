package cn.yellowgg.ducksystem.entity.base;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 实体类父类
 * @Author: yellowgg
 * @Date: Created in 2020/3/24 14:42
 */
@Data
@AllArgsConstructor
public class BaseEntity {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private LocalDateTime gmtCreate;
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private LocalDateTime gmtModify;
    /**
     * 软删除 （0：未删除 1：已删除）
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Integer isDelete;

    public BaseEntity() {
        /**
         * 去掉后面的毫秒值
         */
        setGmtCreate(LocalDateTime.now().withNano(UtilConstants.Number.ZERO));
        setGmtModify(LocalDateTime.now().withNano(UtilConstants.Number.ZERO));
        setIsDelete(UtilConstants.Number.ZERO);
    }
}
