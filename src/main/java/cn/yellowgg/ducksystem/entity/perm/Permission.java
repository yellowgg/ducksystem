package cn.yellowgg.ducksystem.entity.perm;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 菜单表
 * 添加菜单都是手动数据库插入的
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {
    private String icon;

    private String title;

    private String url;

    private Integer parentId;

    private Integer type;

    private String perms;

    private Integer orderNum;

    private String description;

}