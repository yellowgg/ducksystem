package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
@NoArgsConstructor
public class Administrator extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "用户名", notes = "由数字和26个英文字母组成的字符串")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "用户名不能有中文或特殊字符")
    private String userName;
    @ApiModelProperty(value = "密码", notes = "长度在6~18之间，只能包含字母、数字")
    // @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,18}$", message = "密码只能包含字母、数字,长度在6~18之间")
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

    public Administrator(String password, Long id) {
        setPassword(password);
        setId(id);
    }
}
