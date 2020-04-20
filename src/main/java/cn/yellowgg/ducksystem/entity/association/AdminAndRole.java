package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 用户-角色
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminAndRole extends BaseEntity implements Serializable {
    private Long adminId;

    private Long roleId;

    public static AdminAndRole init(Long roleId, Long adminId) {
        AdminAndRole adminAndRole = new AdminAndRole();
        adminAndRole.setAdminId(adminId);
        adminAndRole.setRoleId(roleId);
        return adminAndRole;
    }
}