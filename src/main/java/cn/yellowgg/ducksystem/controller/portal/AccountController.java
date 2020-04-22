package cn.yellowgg.ducksystem.controller.portal;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.*;
import cn.yellowgg.ducksystem.exception.CustomException;
import cn.yellowgg.ducksystem.service.AccountService;
import cn.yellowgg.ducksystem.service.CourseService;
import cn.yellowgg.ducksystem.service.CourseCollectionService;
import cn.yellowgg.ducksystem.service.WalletService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/14 15:37
 */
@RestController
@RequestMapping("/accountPortal")
@Api(tags = "用户信息门户")
@Validated
public class AccountController {

    private static final transient Logger log = LogUtils.getBussinessLogger();

    @Autowired
    AccountService accountService;
    @Autowired
    WalletService walletService;
    @Autowired
    CourseCollectionService coursecollectionService;
    @Autowired
    CourseService courseService;

    @ApiOperation("保存微信用户信息")
    @PostMapping("/saveAccount")
    public ServiceResult saveAccount(@Valid Account accout) {
        return accountService.register(accout) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(accout.getId(), "保存成功")
                : ServiceResult.asFail("保存失败");
    }

    @ApiOperation(value = "保存微信用户的身份", notes = "如果没有用户信息设置success为false且只返回openid")
    @PostMapping("/saveRole")
    public ServiceResult insertOpenIdAndisAdmin(@NotBlank(message = "openid不能为空") String openId,
                                                @NotNull(message = "isAdmin不能为空") @Range(max = 1) Integer isAdmin) {
        return accountService.addRole(Account.init(openId, isAdmin)) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "保存成功")
                : ServiceResult.asFail("保存失败");

    }

    @ApiOperation("获取用户钱包")
    @GetMapping("/getWallet")
    public ServiceResult getWallet(@NotBlank(message = "用户ID不能为空") String accountId) {
        Wallet wallet = walletService.findByAccountId(Long.parseLong(accountId));
        return Objects.nonNull(wallet) ? ServiceResult.asSuccess(wallet) : ServiceResult.asFail("用户未开通钱包");
    }

    @ApiOperation("资金明细")
    @GetMapping("/getFinancialDetails")
    public ServiceQueryResult getDetail(@NotBlank(message = "用户ID不能为空") String accountId) {
        List<ExpensesRecord> recordList = walletService.getExpensesRecordList(Long.parseLong(accountId));
        return CollectionUtils.isEmpty(recordList)
                ? ServiceQueryResult.asFail("暂无资金明细")
                : ServiceQueryResult.asSuccess(recordList);
    }

    @ApiOperation("交易")
    @PostMapping("/trading")
    public ServiceResult trading(@Range(min = 1L, max = 9999L, message = "不能多充也不能少充，1~9999刚刚好")
                                 @NotNull(message = "不充钱怎么变强") String amount,
                                 @NotBlank(message = "用户ID不许为空") String accountId,
                                 String courseId) {
        ServiceResult result = null;
        BigDecimal amountBigDecimal = new BigDecimal(amount);
        if (UtilConstants.Number.MINUSONE == amountBigDecimal.signum()) {
            return ServiceResult.asFail("金额不能为负数！");
        }
        try {
            result = walletService.trading(Long.parseLong(accountId), amountBigDecimal,
                    StrUtil.isNotBlank(courseId) ? Long.parseLong(courseId) : null) > UtilConstants.Number.ZERO
                    ? ServiceResult.asSuccess(null)
                    : ServiceResult.asFail("操作失败,请重试");
        } catch (CustomException e) {
            log.info("用户使用钱包交易出错,{}", e.getMsg());
            return ServiceResult.asFail("操作失败，请重试");
        }
        return result;
    }

    @ApiOperation("收藏的课程列表")
    @GetMapping("/collectList")
    public ServiceResult collectList(@NotBlank(message = "用户ID不许为空") String accountId) {
        JSONObject json = new JSONObject();
        // 收藏的课程ID
        List<Long> collectCourseIds = coursecollectionService.findCourseIdByAccountId(Long.parseLong(accountId));
        // 课程 有的课程可能会被删
        List<Course> courseList = courseService.findAllByIdIn(collectCourseIds);
        // 找出被删除的课程的ID
        json.put("failureCourseIds",
                courseList.stream()
                        .filter(x -> UtilConstants.Number.ONE == x.getIsDelete())
                        .map(Course::getId)
                        .collect(Collectors.toList()));
        json.put("courseList", courseList);
        //region 因为是这样返回的json，所以需要手动去掉字段
        JSONArray jsonArray = (JSONArray) json.get("courseList");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            jsonObject.remove("isDelete");
            jsonObject.remove("gmtCreate");
            jsonObject.remove("gmtModify");
        }
        //endregion
        return ServiceResult.asSuccess(json);
    }

    @ApiOperation("查询是否收藏")
    @GetMapping("/isCollect")
    public ServiceResult isCollect(@NotBlank(message = "用户ID不许为空") String accountId,
                                   @NotBlank(message = "课程ID不许为空") String courseId) {
        CourseCollection collection = coursecollectionService.findByAccountIdAndCourseId(Long.parseLong(accountId), Long.parseLong(courseId));
        return Objects.nonNull(collection) ? ServiceResult.asSuccess(collection.getIsDelete()) : ServiceResult.asFail("暂无收藏");
    }

    @ApiOperation("课程的收藏与否动作")
    @PostMapping("/collectCourse")
    public ServiceResult collectCourse(@Range(max = 1L, message = "只能0或1来收藏") @ApiParam(value = "1取消收藏 0收藏")
                                       @NotNull(message = "收藏不能空") Integer isCollect,
                                       @NotBlank(message = "用户ID不许为空") String accountId,
                                       @NotBlank(message = "课程ID不许为空") String courseId) {
        return coursecollectionService.insertOrUpdate(new CourseCollection(
                Long.parseLong(accountId), Long.parseLong(courseId)), isCollect) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess("保存成功")
                : ServiceResult.asFail("保存失败");
    }

}
