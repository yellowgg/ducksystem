package cn.yellowgg.ducksystem.entity.association;

import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/25 11:18
 */
@Data
@ApiModel(value = "教课表", description = "教师和课程的中间表，多对多")
@NoArgsConstructor
public class TeacherAndCourse extends BaseEntity {

    private Long teacherId;
    private Long courseId;

    public TeacherAndCourse(Long teacherId, Long courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }
}
