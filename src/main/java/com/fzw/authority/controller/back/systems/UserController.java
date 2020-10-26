package com.fzw.authority.controller.back.systems;


import com.fzw.authority.entity.systems.UserEntity;
import com.fzw.authority.exception.AuthorityException;
import com.fzw.authority.service.systems.RoleService;
import com.fzw.authority.service.systems.UserService;
import com.fzw.authority.util.BaseResult;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.ShiRoUserUtil;
import com.fzw.authority.util.StringUtil;
import com.fzw.authority.vo.systems.UserVO;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/back/user")
@Api(value="用户controller",tags={"用户操作接口"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("saveUser")
    @RequiresPermissions("systems:user:saveUser")
    @ApiOperation(value = "添加用户信息", notes = "添加用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
    })
    public BaseResult saveUser(UserVO userVO){
        try {
            if(StringUtil.isNull(userVO.getAccount())){
                return BaseResult.error(BaseResultStatus.ACCOUNT_IS_NULL);
            }
            if(userService.isAccount(userVO.getAccount())){
                return BaseResult.error(BaseResultStatus.ACCOUNT_ALREADY_EXIST);
            }
            if(StringUtil.isNull(userVO.getPassword())){
                return BaseResult.error(BaseResultStatus.PASSWORD_IS_NULL);
            }
            if(StringUtil.isNull(userVO.getConfirmPassword())){
                return BaseResult.error(BaseResultStatus.CONFIRM_PASSWORD_IS_NULL);
            }
            if(StringUtil.isNull(userVO.getMobile())){
                return BaseResult.error(BaseResultStatus.MOBILE_IS_NULL);
            }
            if(userService.isMobile(userVO.getMobile())){
                return BaseResult.error(BaseResultStatus.MOBILE_ALREADY_EXIST);
            }
            if(StringUtil.isNull(userVO.getRealName())){
                return BaseResult.error(BaseResultStatus.REAL_NAME_IS_NULL);
            }
            userService.saveUser(userVO);
            return BaseResult.done("添加成功");
        } catch (Exception e){
            return BaseResult.error(BaseResultStatus.ERROR);
        }
    }


    @PostMapping("getUser")
    @RequiresPermissions("systems:user:getUser")
    public BaseResult getUser(String id){
        try{
            UserEntity userEntity = ShiRoUserUtil.getUser();
            return BaseResult.done(userEntity.getAccount());
        } catch (Exception e){
            return BaseResult.error(BaseResultStatus.ERROR);
        }
    }

    @PostMapping("getUserListPage")
    @RequiresPermissions("systems:user:getUserListPage")
    public BaseResult getUserListPage(int pageNumber, int pageSize) throws AuthorityException {
        userService.getUserListPage(0, 10);
        return BaseResult.done();
    }

    @PostMapping("addRole")
    @RequiresPermissions("systems:user:addRole")
    @ApiOperation(value = "添加角色信息", notes = "添加角色信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "用户主键ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色主键ID", required = true, dataType = "String", paramType = "query")
    })
    public BaseResult addRole(String id, String roleId){
        if(StringUtil.isNull(id)){
            return BaseResult.error(BaseResultStatus.USER_ID_IS_NULL);
        }
        if(StringUtil.isNull(roleId)){
            return BaseResult.error(BaseResultStatus.ROLE_ID_IS_NULL);
        }
        roleService.userAddRole(id, roleId);
        return BaseResult.done();
    }
}
