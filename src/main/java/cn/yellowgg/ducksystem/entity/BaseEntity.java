package cn.yellowgg.ducksystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description: 实体类父类
 * @Author: yellowgg
 * @Date: Created in 2020/3/24 14:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModify;
    /**
     * 软删除 （0：未删除 1：已删除）
     */
    private int isDelete;
}
