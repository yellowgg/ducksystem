package cn.yellowgg.ducksystem.controller;

import cn.yellowgg.ducksystem.service.AccountService;
import cn.yellowgg.ducksystem.service.base.ServiceQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/4/18 15:04
 */
@Controller
@RequestMapping("/account")
public class AccountMgr {

    @Autowired
    AccountService accountService;

    @GetMapping("/page")
    public String page() {
        return "accountList";
    }

    @GetMapping("/data")
    @ResponseBody
    public ServiceQueryResult getData(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      String nickName) {
        return ServiceQueryResult.asSuccess(accountService.findByNickNameNotIsAdminwithPage(pageNum, pageSize, nickName));
    }
}
