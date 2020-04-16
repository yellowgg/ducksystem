package cn.yellowgg.ducksystem.entity;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import cn.yellowgg.ducksystem.enums.CourseTypeEnum;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 15:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Course extends BaseEntity {
    @NotBlank(message = "简介不能为空")
    private String introduction;
    @NotBlank(message = "特色不能为空")
    private String characteristical;
    @NotBlank(message = "详情不能为空")
    private String details;
    @NotBlank(message = "图片不能为空")
    private String imgUrl;
    @NotBlank(message = "课程名字不能为空")
    private String name;
    @Range(min = 1, max = 100, message = "价格：1-10000")
    private BigDecimal price;
    @Range(max = 4, message = "课程类型别乱来")
    private Integer type;
    @Range(min = 1, max = 100, message = "积分：1-100")
    private Integer integral;
    @Range(max = 1, message = "1为热门 0为非热门")
    private Integer isHotCourse;

    @JsonGetter("typeShow")
    public String typeShow() {
        return CourseTypeEnum.getNameByValue(type);
    }

    @JsonGetter("isHotShow")
    public String isHotShow() {
        return Objects.nonNull(isHotCourse) && UtilConstants.Number.ZERO == isHotCourse ? "否" : "是";
    }
}
