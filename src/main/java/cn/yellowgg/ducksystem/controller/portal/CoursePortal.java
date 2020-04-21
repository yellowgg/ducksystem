package cn.yellowgg.ducksystem.controller.portal;

import cn.yellowgg.ducksystem.entity.Course;
import cn.yellowgg.ducksystem.entity.CourseVideoInfo;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.CourseVideoInfoService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
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
    @Autowired
    CourseVideoInfoService courseVideoInfoService;

    @ApiOperation("获取课程信息列表")
    @GetMapping("/allData")
    public ServiceQueryResult<Course> getData() {
        List<Course> result = courseService.queryAll();
        return CollectionUtils.isNotEmpty(result)
                ? ServiceQueryResult.asSuccess(result)
                : ServiceQueryResult.asFail("暂无数据");
    }

    @ApiOperation("获取最新的三个热门课程信息")
    @GetMapping("/getThreeHotCourse")
    public ServiceQueryResult<Course> getThreeHotCourse() {
        List<Course> result = courseService.findThreeIsHot();
        return CollectionUtils.isNotEmpty(result)
                ? ServiceQueryResult.asSuccess(result)
                : ServiceQueryResult.asFail("暂无数据");
    }

    @ApiOperation("获取课程视频列表")
    @GetMapping("/getVideo/{courseId}")
    public ServiceQueryResult<CourseVideoInfo> getVideoByCourseId(@ApiParam(value = "课程ID") @PathVariable String courseId) {
        List<CourseVideoInfo> result = courseVideoInfoService.selectAllByCourseId(Long.parseLong(courseId));
        return CollectionUtils.isNotEmpty(result)
                ? ServiceQueryResult.asSuccess(result)
                : ServiceQueryResult.asFail("暂无数据");
    }
}
