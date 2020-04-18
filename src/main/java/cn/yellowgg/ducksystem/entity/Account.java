package cn.yellowgg.ducksystem.entity;

import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import cn.yellowgg.ducksystem.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:08
 */
@Data
@ApiModel(value = "用户对象", description = "在本系统可以概念为学生，包含了基本的微信资料")
public class Account extends BaseEntity implements Serializable {
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
    @Range(max = 1, message = "1为管理员 0为非管理员")
    @ApiModelProperty(value = "是否是管理员")
    private Integer isAdmin;

    /**
     * 随便挑选几个字段看看是否已授权
     */
    @JsonIgnore
    public boolean hasInfo() {
        return !StrUtil.isAllBlank(getNickName(), getAvatarUrl(), getCountry());
    }

    public static Account init(String openId, Integer isAdmin) {
        Account account = new Account();
        account.setOpenId(openId);
        account.setIsAdmin(isAdmin);
        return account;
    }

    @ApiModelProperty(value = "性别")
    @JsonGetter("sexShow")
    public String sexShow() {
        return SexEnum.getSexStrByValue(gender);
    }

    @ApiModelProperty(value = "地址：国家省份城市")
    @JsonGetter("addressShow")
    public String addressShow() {
        return getCountry() + getProvince() + getCity();
    }
}
