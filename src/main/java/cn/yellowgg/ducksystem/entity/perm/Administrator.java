package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Description: 管理员
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:43
 */
@Data
@ApiModel(value = "管理员", description = "后台管理系统登录的账户")
@EqualsAndHashCode(callSuper = true)
public class Administrator extends BaseEntity {
    private String userName;
    private String password;
    private String realName;
    private String email;
    /**
     * 加密盐值
     */
    private String salt;
    private LocalDateTime lastLoginTime;
}
