package cn.yellowgg.ducksystem.controller.portal;

import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.service.TeacherService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/13 19:37
 */
@RestController
@RequestMapping("/teacherPortal")
@Api(tags = "教师信息门户")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation("获取教师信息列表")
    @GetMapping("/allData")
    public ServiceQueryResult<Teacher> getData() {
        return ServiceQueryResult.asSuccess(teacherService.queryAll());
    }
}
