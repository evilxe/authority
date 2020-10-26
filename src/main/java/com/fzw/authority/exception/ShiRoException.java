package com.fzw.authority.exception;

import com.fzw.authority.util.BaseResult;
import com.fzw.authority.util.BaseResultStatus;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ShiRoException {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiRoException.class);

    /**
     * @Author FangZhiWei
     * @Description TODO  权限错误
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResult ErrorHandler(UnauthorizedException e) {
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(BaseResultStatus.NOPERMISSION);
    }

    /**
     * @Author FangZhiWei
     * @Description TODO  自定义异常
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(AuthorityException.class)
    public BaseResult authorityException(AuthorityException e){
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(e.getCode());
    }

    /**
     * @Author FangZhiWei
     * @Description TODO  shiro 用户不存在
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(UnknownAccountException.class)
    public BaseResult UnknownAccountException(UnknownAccountException e) {
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(BaseResultStatus.ADMIN_USER_IS_NULL);
    }

    /**
     * @Author FangZhiWei
     * @Description TODO  shiro 密码错误
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public BaseResult incorrectCredentialsException(IncorrectCredentialsException e) {
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(BaseResultStatus.PASSWORD_ERROR);
    }

    /**
     * @Author FangZhiWei
     * @Description TODO  shiro 用户被禁用
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(DisabledAccountException.class)
    public BaseResult DisabledAccountException(DisabledAccountException e) {
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(BaseResultStatus.ADMIN_IS_DISABLED);
    }

    /**
     * @Author FangZhiWei
     * @Description TODO  RuntimeException异常捕获
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult runtimeExcept(RuntimeException e) {
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(BaseResultStatus.ERROR);
    }

    /**
     * @Author FangZhiWei
     * @Description TODO  Exception异常捕获
     * @Date 8:54 2020/8/26 0026
     */
    @ExceptionHandler(Exception.class)
    public BaseResult exception(HttpServletRequest req,  Exception e){
        LOGGER.error("-----------------------" + e.getMessage());
        return BaseResult.error(BaseResultStatus.ERROR);
    }
}


