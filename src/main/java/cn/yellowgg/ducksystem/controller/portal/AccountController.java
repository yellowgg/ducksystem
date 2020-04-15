package cn.yellowgg.ducksystem.controller.portal;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.Account;
import cn.yellowgg.ducksystem.service.AccountService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/14 15:37
 */
@RestController
@RequestMapping("/accountPortal")
@Api(tags = "用户信息门户")
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiOperation("保存微信用户信息")
    @PostMapping("/saveAccount")
    public ServiceResult saveAccount(@Valid Account accout) {
        return accountService.register(accout) > UtilConstants.Number.ZERO
                ? ServiceResult.asSuccess(null, "保存成功")
                : ServiceResult.asFail("保存失败");
    }
}
