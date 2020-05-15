package cn.yellowgg.ducksystem.vo;

import lombok.Getter;

/**
 * @Description: 专门给小程序的picker设置map数据用的  教师下拉框
 * @Author: yellowgg
 * @Date: Created in 2020/5/16 00:49
 */
@Getter
public class WxAppTeacherPickerMapVo {
    private Long id;
    private String name;

    public WxAppTeacherPickerMapVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
