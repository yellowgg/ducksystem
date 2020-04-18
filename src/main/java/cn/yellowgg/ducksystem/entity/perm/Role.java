package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 角色
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @NotBlank(message = "角色名你都想空？")
    private String name;

    @NotBlank(message = "起码描述一下啊")
    private String description;
}