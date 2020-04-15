package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:08
 */
@Data
@ApiModel(value = "用户对象", description = "在本系统可以概念为学生，包含了基本的微信资料")
public class Account extends BaseEntity {
    @ApiModelProperty(hidden = true)
    private String openId;
    @NotBlank(message = "微信用户名不可为空")
    @ApiModelProperty(value = "微信用户名", required = true)
    private String nickName;
    @NotBlank(message = "国家不能为空")
    @ApiModelProperty(value = "国家", required = true)
    private String country;
    @NotBlank(message = "省份不能为空")
    @ApiModelProperty(value = "省份", required = true)
    private String province;
    @NotBlank(message = "城市不能为空")
    @ApiModelProperty(value = "城市", required = true)
    private String city;
    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "头像URL", required = true)
    private String avatarUrl;
    @NotBlank(message = "语言不能为空")
    @ApiModelProperty(value = "语言", required = true)
    private String language;
    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 2, message = "性别选择不正确")
    @ApiModelProperty(value = "性别 0未知 1男 2女", required = true)
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime birthday;
}
