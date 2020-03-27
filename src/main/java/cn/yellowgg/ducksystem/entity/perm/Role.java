package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 角色
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    private String name;

    private String description;
}