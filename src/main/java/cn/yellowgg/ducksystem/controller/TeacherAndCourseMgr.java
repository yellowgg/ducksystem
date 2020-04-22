package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.entity.association.TeacherAndCourse;
import cn.yellowgg.ducksystem.entity.result.CourseResult;
import cn.yellowgg.ducksystem.entity.result.TeacherResult;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.TeacherAndCourseService;
import cn.yellowgg.ducksystem.service.TeacherService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

/**
 * @Description: 课程 教师 多对多
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 20:52
 */
@Controller
@RequestMapping("/teaandcou")
public class TeacherAndCourseMgr {

    @Autowired
    TeacherAndCourseService teacherandcourseService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;

    @GetMapping("/page")
    public String page(Model model) {
        // 把课程和老师放到下拉框
        model.addAttribute("courseMap",
                courseService.findIdAndName().stream().
                        collect(Collectors.toMap(CourseResult::getId, CourseResult::getName)));
        model.addAttribute("teacherMap",
                teacherService.findIdAndName().stream().
                        collect(Collectors.toMap(TeacherResult::getId, TeacherResult::getName)));
        return "teacherAndCourseList";
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      TeacherAndCourse teacherAndCourse) {
        return ServiceQueryResult.asSuccess(teacherandcourseService.findByAllwithPage(pageNum, pageSize, teacherAndCourse));
    }

    public void getDonteacher() {

    }
}
