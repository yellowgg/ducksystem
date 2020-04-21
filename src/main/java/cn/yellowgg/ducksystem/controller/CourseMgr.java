package cn.yellowgg.ducksystem.controller;

import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.entity.CourseVideoInfo;
import cn.yellowgg.ducksystem.enums.CourseTypeEnum;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.CourseVideoInfoService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 课程后台
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 15:42
 */
@Controller
@RequestMapping("/course")
public class CourseMgr {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseVideoInfoService courseVideoInfoService;

    @GetMapping("/page")
    public String page(Model model) {
        Map<Integer, String> map = CourseTypeEnum.getValueAndNameMap();
        model.addAttribute("courseTypes", map);
        return "courseList";
    }

    @GetMapping("/video/page")
    public String videoPage(Model model) {
        Map<Long, String> courseIdAndRoleNameMap = courseService.queryAll()
                .stream().collect(Collectors.toMap(Course::getId, Course::getName));
        model.addAttribute("courseMap", courseIdAndRoleNameMap);
        return "courseVideoList";
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Course course) {
        return ServiceQueryResult.asSuccess(courseService.queryByAllSelectiveOrderByIdwithPage(pageNum, pageSize, course));
    }

    @RequiresPermissions({"course:add", "course:edit"})
    @PostMapping("/addOrUp")
    @ResponseBody
    public ServiceResult insertOrUpdateSelective(@Valid Course course) {
        return courseService.insertOrUpdateSelective(course) > UtilConstants.Number.ZERO
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
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      String courseId) {
        long id = StrUtil.isNotBlank(courseId) ? Long.parseLong(courseId) : UtilConstants.Number.ZERO;
        return ServiceQueryResult.asSuccess(courseVideoInfoService.selectAllByCourseIdwithPage(pageNum, pageSize, id));
    }
}
