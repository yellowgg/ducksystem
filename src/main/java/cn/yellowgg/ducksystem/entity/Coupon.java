package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Description: 优惠券
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:12
 */
@Data
@ApiModel(value = "优惠券对象", description = "优惠券的信息")
public class Coupon extends BaseEntity {
    private LocalDate startTime;
    private LocalDate endTime;
    private String name;
    private Long accountId;
    private Integer status;
    /**
     * 满多少
     */
    private BigDecimal limitValue;
    /**
     * 减多少
     */
    private BigDecimal value;
    /**
     * 优惠码，用于换取优惠券
     */
    private String couponCode;
}
