package cn.yellowgg.ducksystem.controller.portal;

import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/15 23:28
 */
@RestController
@RequestMapping("/coursePortal")
@Api(tags = "课程信息门户")
public class CoursePortal {

    @Autowired
    CourseService courseService;

    @ApiOperation("获取课程信息列表")
    @GetMapping("/allData")
    public ServiceQueryResult<Course> getData() {
        List<Course> result = courseService.queryAll();
        return CollectionUtils.isNotEmpty(result)
                ? ServiceQueryResult.asSuccess(result)
                : ServiceQueryResult.asFail("暂无数据");
    }
}
