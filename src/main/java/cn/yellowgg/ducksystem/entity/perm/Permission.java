package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description: 权限表
 * 添加菜单都是手动数据库插入的
 * 最多二级菜单
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Permission extends BaseEntity {
    /**
     * 格式为fa ****
     */
    private String icon;

    private String title;

    private String url;

    private Long parentId;

    /**
     * 见枚举
     */
    private Integer type;

    /**
     * 细粒度的权限，目录和菜单应该不用
     */
    private String perms;

    /**
     * 排序 越小越靠前
     */
    private Integer orderNum;

    /**
     * 描述备注
     */
    private String description;

}