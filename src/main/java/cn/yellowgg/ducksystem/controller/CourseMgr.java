package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.enums.CourseTypeEnum;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Description: 课程后台
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 15:42
 */
@Controller
@RequestMapping("/course")
@RequiresRoles(value = {"superAdmin", "courseAdmin"}, logical = Logical.OR)
public class CourseMgr {
    @Autowired
    CourseService courseService;

    @GetMapping("/page")
    public String page(Model model) {
        Map<Integer, String> map = CourseTypeEnum.getValueAndNameMap();
        model.addAttribute("courseTypes", map);
        return "courseList";
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Course course) {
        return ServiceQueryResult.asSuccess(courseService.queryByAllSelectiveOrderByIdwithPage(pageNum, pageSize, course));
    }

    @PostMapping("/addOrUp")
    @ResponseBody
    public ServiceResult insertOrUpdateSelective(@Valid Course course) {
        return courseService.insertOrUpdateSelective(course) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "操作成功")
                : ServiceResult.asFail("操作失败");
    }

    @PostMapping("/del/{id}")
    @ResponseBody
    public ServiceResult del(@PathVariable String id) {
        return courseService.deleteByPrimaryKey(Long.parseLong(Base64Utils.decodeStrofCount(id,
                UtilConstants.Number.THREE))) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "删除成功")
                : ServiceResult.asFail("删除失败,请稍后重试");
    }
}
