package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:21
 */
@Data
@ApiModel(value = "选课表", description = "学生跟课程的中间表，多对多")
public class AccountAndCourse extends BaseEntity {
    private Long accountId;
    private Long courseId;
}
