package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 角色-权限
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleAndPerm extends BaseEntity {
    private Long roleId;

    private Long permId;

    public static RoleAndPerm init(Long roleId, Long permId) {
        RoleAndPerm roleAndPerm = new RoleAndPerm();
        roleAndPerm.setPermId(permId);
        roleAndPerm.setRoleId(roleId);
        return roleAndPerm;
    }
}