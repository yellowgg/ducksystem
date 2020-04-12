package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.service.TeacherService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/11 21:21
 */
@Controller
@RequestMapping("/teacher")
@Api(tags = "教师后台")
public class TeacherMgr {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/page")
    public String page() {
        return "teacherList";
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer limit,
                                      Teacher teacher) {
        return ServiceQueryResult.asSuccess(teacherService.queryByAllOrderByIdwithPage(page, limit, teacher), null);
    }

    @PostMapping("/addOrUp")
    @ResponseBody
    public String insertOrUpdateSelective(@Valid Teacher teacher) {
        return teacherService.insertOrUpdateSelective(teacher) > 0
                ? ServiceResult.asSuccess(null, "添加成功").toJson()
                : ServiceResult.asFail("添加失败").toJson();
    }
}
