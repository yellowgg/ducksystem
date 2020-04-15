package cn.yellowgg.ducksystem.controller.portal;

import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.service.TeacherService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
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
        List<Teacher> result = teacherService.queryAll();
        return CollectionUtils.isNotEmpty(result)
                ? ServiceQueryResult.asSuccess(result)
                : ServiceQueryResult.asFail("暂无数据");
    }

    @ApiOperation("获取教师信息")
    @GetMapping("/getInfo/{teacherId}")
    public ServiceResult<Teacher> getTeacherInfo(@ApiParam(value = "教师ID") @PathVariable String teacherId) {
        Teacher teacher = teacherService.selectByPrimaryKey(Long.parseLong(teacherId));
        return Objects.nonNull(teacher) ? ServiceResult.asSuccess(teacher) : ServiceResult.asFail("老师不存在");
    }
}
