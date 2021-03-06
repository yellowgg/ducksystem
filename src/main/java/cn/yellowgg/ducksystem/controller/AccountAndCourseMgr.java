package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.service.AccountAndCourseService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 买课 学员对课程 多对多
 * @Author: yellowgg
 * @Date: Created in 2020/5/14 16:35
 */
@Controller
@RequestMapping("/accandcou")
public class AccountAndCourseMgr {

    @Autowired
    AccountAndCourseService accountAndCourseService;

    @GetMapping("/page")
    public String page() {
        return "accountAndCourseList";
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      String nickName) {
        return ServiceQueryResult.asSuccess(accountAndCourseService.findAllByNickNamewithPage(pageNum, pageSize, nickName));
    }
}
