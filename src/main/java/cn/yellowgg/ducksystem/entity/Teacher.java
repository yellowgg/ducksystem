package cn.yellowgg.ducksystem.entity;

import cn.hutool.core.date.DateUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.base.BaseEntity;
import cn.yellowgg.ducksystem.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/11 21:11
 */
@Data
@ApiModel(value = "教师对象", description = "本系统中教师只作为展示用，因为是线上看视频，不参与对学生的操作")
public class Teacher extends BaseEntity {
    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;
    @NotBlank(message = "头像不能为空")
    @ApiModelProperty(value = "照片URL")
    private String picUrl;
    @Min(value = 0, message = "性别选择不正确")
    @Max(value = 1, message = "性别选择不正确")
    @ApiModelProperty(hidden = true)
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private LocalDateTime birthday;
    @NotBlank(message = "学历不能为空")
    @ApiModelProperty(value = "学历")
    private String education;
    @NotBlank(message = "介绍不能为空")
    @ApiModelProperty(value = "个人介绍")
    private String introduction;
    @NotBlank(message = "职称不能为空")
    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @ApiModelProperty(value = "年龄")
    @JsonGetter("age")
    public int age() {
        return DateUtil.ageOfNow(DateUtil.format(birthday, UtilConstants.DateFormatStr.NORM_DATE_PATTERN));
    }

    @ApiModelProperty(value = "生日", example = "2020-10-24 00:00:00")
    @JsonGetter("birthdayShow")
    public String birthdayShow() {
        return DateUtil.format(birthday, UtilConstants.DateFormatStr.NORM_DATETIME_PATTERN);
    }

    @ApiModelProperty(value = "性别")
    @JsonGetter("sexShow")
    public String sexShow() {
        return SexEnum.getSexStrByValue(sex);
    }
}