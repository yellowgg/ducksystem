package cn.yellowgg.ducksystem.controller;

import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.CourseExpand;
import cn.yellowgg.ducksystem.entity.CourseVideoInfo;
import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.entity.result.CourseResult;
import cn.yellowgg.ducksystem.enums.CourseTypeEnum;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.CourseVideoInfoService;
import cn.yellowgg.ducksystem.service.TeacherService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @Description: 课程后台
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 15:42
 */
@RestController
@RequestMapping("/course")
public class CourseMgr {
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseVideoInfoService courseVideoInfoService;

    @GetMapping("/page")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("courseList");
        mv.getModel().put("courseTypes", CourseTypeEnum.getValueAndNameMap());
        mv.getModel().put("teacherMap",
                teacherService.queryAll().stream().collect(Collectors.toMap(Teacher::getId, Teacher::getName)));
        return mv;
    }

    @GetMapping("/video/page")
    public ModelAndView videoPage() {
        ModelAndView mv = new ModelAndView("courseVideoList");
        mv.getModel().put("courseMap",
                courseService.findIdAndName().stream().collect(Collectors.toMap(CourseResult::getId, CourseResult::getName)));
        return mv;
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      CourseExpand courseExpand) {
        return ServiceQueryResult.asSuccess(courseService.queryByAllSelectiveOrderByIdwithPage(pageNum, pageSize, courseExpand));
    }

    @RequiresPermissions({"course:add", "course:edit"})
    @PostMapping("/addOrUp")
    @ResponseBody
    public ServiceResult insertOrUpdateSelective(@Valid CourseExpand courseExpand) {
        return courseService.insertOrUpdateSelective(courseExpand) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "操作成功")
                : ServiceResult.asFail("操作失败");
    }

    @RequiresPermissions({"coursevideo:add", "coursevideo:edit"})
    @PostMapping("/video/addOrUp")
    @ResponseBody
    public ServiceResult insertOrUpdateVideoSelective(@Valid CourseVideoInfo courseVideoInfo) {
        return courseVideoInfoService.insertOrUpdateSelective(courseVideoInfo) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "操作成功")
                : ServiceResult.asFail("操作失败");
    }

    /**
     * 删掉课程，客户端的收藏会失效 至于观看，看情况
     */
    @RequiresPermissions({"course:del"})
    @PostMapping("/del/{id}")
    @ResponseBody
    public ServiceResult del(@PathVariable String id) {
        return courseService.deleteByPrimaryKey(Long.parseLong(Base64Utils.decodeStrofCount(id,
                UtilConstants.Number.THREE))) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "删除成功")
                : ServiceResult.asFail("删除失败,请稍后重试");
    }

    @RequiresPermissions({"coursevideo:del"})
    @PostMapping("/video/del/{id}")
    @ResponseBody
    public ServiceResult delVideo(@PathVariable String id) {
        return courseVideoInfoService.deleteByPrimaryKey(Long.parseLong(Base64Utils.decodeStrofCount(id,
                UtilConstants.Number.THREE))) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "删除成功")
                : ServiceResult.asFail("删除失败,请稍后重试");
    }

    @GetMapping("/video/data")
    @ResponseBody
    public ServiceQueryResult getVideoData(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           String courseId) {
        long id = StrUtil.isNotBlank(courseId) ? Long.parseLong(courseId) : UtilConstants.Number.ZERO;
        return ServiceQueryResult.asSuccess(courseVideoInfoService.selectAllByCourseIdwithPage(pageNum, pageSize, id));
    }
}
