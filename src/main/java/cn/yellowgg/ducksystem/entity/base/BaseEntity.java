package cn.yellowgg.ducksystem.entity.base;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description: 实体类父类
 * @Author: yellowgg
 * @Date: Created in 2020/3/24 14:42
 */
@Data
@AllArgsConstructor
public class BaseEntity {
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModify;
    /**
     * 软删除 （0：未删除 1：已删除）
     */
    private Integer isDelete;

    public BaseEntity() {
        /**
         * 去掉后面的毫秒值
         */
        setGmtCreate(LocalDateTime.now().withNano(0));
        setGmtModify(LocalDateTime.now().withNano(0));
        setIsDelete(UtilConstants.Number.ZERO);
    }
}
