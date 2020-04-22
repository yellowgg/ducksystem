package cn.yellowgg.ducksystem.entity.expand;

import cn.yellowgg.ducksystem.entity.association.TeacherAndCourse;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: 教课表的一些扩展
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 23:54
 */
@Data
@ToString(callSuper = true)
public class TeacherAndCourseExpand extends TeacherAndCourse {
    private String courseName;
    private String teacherName;
}
