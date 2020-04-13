package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Teacher;
import cn.yellowgg.ducksystem.service.TeacherService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.Base64Utils;
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
    public ServiceResult insertOrUpdateSelective(@Valid Teacher teacher) {
        return teacherService.insertOrUpdateSelective(teacher) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "添加成功")
                : ServiceResult.asFail("添加失败");
    }

    @PostMapping("/del/{id}")
    @ResponseBody
    public ServiceResult del(@PathVariable String id) {
        return teacherService.deleteByPrimaryKey(Long.parseLong(Base64Utils.decodeStrofCount(id,
                UtilConstants.Number.THREE))) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "删除成功")
                : ServiceResult.asFail("删除失败,检查老师是否有在教课");
    }
}
