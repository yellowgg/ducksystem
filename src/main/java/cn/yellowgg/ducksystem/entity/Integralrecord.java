package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 积分记录  目前只有在买课的时候加积分
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Integralrecord extends BaseEntity {
    private Long walletId;

    private Long integral;

    private String description;

    public Integralrecord(Long walletId) {
        this.walletId = walletId;
    }
}