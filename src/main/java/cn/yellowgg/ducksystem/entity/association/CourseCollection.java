package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 11:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "课程收藏", description = "学生收藏课程,多对多")
public class CourseCollection extends BaseEntity {
    private Long accountId;

    private Long courseId;

    public CourseCollection(Long accountId, Long courseId) {
        this.accountId = accountId;
        this.courseId = courseId;
    }
}