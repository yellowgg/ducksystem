package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 其实也就是买课
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "选课表", description = "学生跟课程的中间表，多对多")
public class AccountAndCourse extends BaseEntity {
    private Long accountId;

    private Long courseId;

    public AccountAndCourse(Long accountId, Long courseId) {
        this.accountId = accountId;
        this.courseId = courseId;
    }

}