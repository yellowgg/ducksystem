package cn.yellowgg.ducksystem.entity.expand;

import cn.yellowgg.ducksystem.entity.Course;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: 课程类扩展一些老师信息
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 21:56
 */
@Data
@ToString(callSuper = true)
public class CourseExpand extends Course {
    private String tName;
    private Long tId;
    private String tJobTitle;
    private String tEducation;
    private String tImgUrl;
}
