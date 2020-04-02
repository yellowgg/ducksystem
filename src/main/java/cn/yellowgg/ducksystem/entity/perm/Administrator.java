package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:43
 */
@Data
@ApiModel(value = "管理员", description = "后台管理系统登录的账户")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Administrator extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @NotBlank(message = "起码得有真姓名")
    @ApiModelProperty(hidden = true)
    private String realName;
    @NotBlank(message = "邮箱也必须有")
    @Email
    @ApiModelProperty(hidden = true)
    private String email;
    @ApiModelProperty(hidden = true)
    private LocalDateTime lastLoginTime;
}
