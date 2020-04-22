package cn.yellowgg.ducksystem.controller.portal;

import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Account;
import cn.yellowgg.ducksystem.entity.ExpensesRecord;
import cn.yellowgg.ducksystem.entity.Wallet;
import cn.yellowgg.ducksystem.exception.CustomException;
import cn.yellowgg.ducksystem.service.AccountService;
import cn.yellowgg.ducksystem.service.WalletService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("保存微信用户信息")
    @PostMapping("/saveAccount")
    public ServiceResult saveAccount(@Valid Account accout) {
        return accountService.register(accout) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "保存成功")
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
}
