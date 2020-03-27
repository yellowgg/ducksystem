package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:08
 */
@Data
@ApiModel(value = "用户对象", description = "在本系统可以概念为学生，包含了基本的微信资料")

public class Account extends BaseEntity {
    private String openId;
    private String nickName;
    private String country;
    private String province;
    private String city;
    private String avatarUrl;
    private String language;
    private Integer gender;
    private LocalDateTime birthday;
}
