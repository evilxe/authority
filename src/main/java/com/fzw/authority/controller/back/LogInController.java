package com.fzw.authority.controller.back;

import com.fzw.authority.util.BaseResult;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/back")
@Api(value="登陆相关controller",tags={"登陆相关操作接口"})
public class LogInController {

    @PostMapping("logIn")
    @ApiOperation(value = "登陆接口", notes = "用户登陆接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    public BaseResult logIN(String account, String password){
        if(StringUtil.isNull(account)){
            return BaseResult.error(BaseResultStatus.LOG_IN_ACCOUNT);
        }
        if(StringUtil.isNull(password)){
            return BaseResult.error(BaseResultStatus.LOG_IN_PASSWORD);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account, password);
        subject.login(usernamePasswordToken);
        return BaseResult.done(subject.getSession().getId().toString());
    }

    @PostMapping("logOut")
    @ApiOperation(value = "退出登陆", notes = "用户推出登陆接口", httpMethod = "POST")
    @RequiresPermissions("systems:user:getUser")
    public BaseResult logOut(){
        try{
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return BaseResult.done("推出登陆成功");
        } catch (Exception e){
            return BaseResult.error(BaseResultStatus.ERROR);
        }
    }

    @RequestMapping("needLogin")
    public BaseResult needLogin(){
        return BaseResult.error(BaseResultStatus.NOTLOGIN);
    }

    @RequestMapping("notPermit")
    public BaseResult notPermit(){
        return BaseResult.error(BaseResultStatus.NOPERMISSION);
    }
}
