package cn.yellowgg.ducksystem.entity.expand;

import cn.yellowgg.ducksystem.entity.association.AccountAndCourse;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: 买课表的扩展 显示名字啥的
 * @Author: yellowgg
 * @Date: Created in 2020/5/14 16:55
 */
@Data
@ToString(callSuper = true)
public class AccountAndCourseExpand extends AccountAndCourse {
    private String courseName;
    private String nickName;
}
