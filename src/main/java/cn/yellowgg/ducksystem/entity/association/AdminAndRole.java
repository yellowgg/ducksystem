package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 15:29
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AdminAndRole extends BaseEntity {
    private Long adminId;

    private Long roleId;
}