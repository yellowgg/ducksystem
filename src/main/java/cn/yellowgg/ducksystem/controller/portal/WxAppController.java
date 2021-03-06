package cn.yellowgg.ducksystem.controller.portal;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.yellowgg.ducksystem.entity.Account;
import cn.yellowgg.ducksystem.service.AccountService;
import cn.yellowgg.ducksystem.service.base.ServiceResult;
import cn.yellowgg.ducksystem.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @Description: 微信小程序在这里处理
 * @Author: yellowgg
 * @Date: Created in 2020/4/14 22:55
 */
@Controller
@RequestMapping("/wxappPortal")
@Api(tags = "微信小程序门户")
public class WxAppController {

    //region 配置变量
    @Value("${wx.app_id}")
    private String appId;
    @Value("${wx.app_secret}")
    private String secret;
    @Value("${wx.jscode2session_url}")
    private String jscodeUrl;
    //endregion

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    AccountService accountService;

    @ApiOperation(value = "获取用户信息-code", notes = "如果没有用户信息设置success为false")
    @GetMapping("/getInfoBycode/{code}")
    @ResponseBody
    public ServiceResult<Account> getInfoBycode(@PathVariable
                                                @NotBlank(message = "code不能为空")
                                                @ApiParam(value = "wx.login获取的code") String code) {
        String data = HttpRequest.get(getRequestUrl(code)).execute().body();
        return getInfoByOpenId(JSONUtil.parse(data).getByPath("openid").toString());
    }

    @ApiOperation(value = "获取用户信息-openid", notes = "如果没有用户信息设置success为false")
    @GetMapping("/getInfoByOpenId/{openId}")
    @ResponseBody
    public ServiceResult<Account> getInfoByOpenId(@NotBlank(message = "openid不能为空")
                                                  @PathVariable String openId) {
        ServiceResult result = null;
        if (!redisUtils.hasKey(openId)) {
            return ServiceResult.asFail(openId, "用户未授权");
        }
        if (Objects.nonNull(redisUtils.hGet(openId, "hasInfo"))) {
            result = ServiceResult.asSuccess(accountService.findByOpenId(openId));
        } else {
            String isAdmin = (String) redisUtils.hGet(openId, "isAdmin");
            JSONObject json = new JSONObject();
            json.put("openId", openId);
            json.put("isAdmin", Integer.parseInt(isAdmin));
            result = ServiceResult.asFail(json, "用户未授权，但已使用过小程序");
        }
        return result;
    }

    /**
     * 拼接URL
     *
     * @param code wx.login获取的code
     * @return
     */
    private String getRequestUrl(String code) {
        StringBuilder urlPath = new StringBuilder(jscodeUrl);
        urlPath.append(String.format("?appid=%s", appId));
        urlPath.append(String.format("&secret=%s", secret));
        urlPath.append(String.format("&js_code=%s", code));
        urlPath.append(String.format("&grant_type=%s", "authorization_code"));
        return urlPath.toString();
    }
}
