package com.fzw.authority.controller.back.systems;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzw.authority.entity.systems.RoleEntity;
import com.fzw.authority.enums.StatusEnum;
import com.fzw.authority.service.systems.MenuService;
import com.fzw.authority.service.systems.RoleService;
import com.fzw.authority.util.BaseResult;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.StringUtil;
import com.fzw.authority.vo.systems.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
@Api(value="角色controller",tags={"角色操作接口"})
@RestController
@RequestMapping("/back/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @PostMapping("saveRole")
    @RequiresPermissions("systems:role:saveRole")
    @ApiOperation(value = "添加角色信息接口", notes = "添加角色信息接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam( name = "roleName", value = "角色名称", required = true, dataType = "String", paramType = "query")
    })
    public BaseResult saveRole(RoleVO roleVO){
        if(StringUtil.isNull(roleVO.getRoleName())){
            return  BaseResult.error(BaseResultStatus.ROLE_NAME_IS_NULL);
        }
        if(!roleService.isRoleName(roleVO.getRoleName())){
            return  BaseResult.error(BaseResultStatus.ROLE_ALREADY_EXIST);
        }
        roleService.saveRole(roleVO);
        return BaseResult.done();
    }

    @PostMapping("editRole")
    @RequiresPermissions("systems:role:editRole")
    @ApiOperation(value = "更新角色信息接口", notes = "更新角色信息接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam( name = "id", value = "角色主键ID", required = true, dataType = "String", paramType = "query")
    })
    public BaseResult editRole(RoleVO roleVO){
        if(StringUtil.isNull(roleVO.getId())){
            return  BaseResult.error(BaseResultStatus.ROLE_ID_IS_NULL);
        }
        roleService.saveRole(roleVO);
        return BaseResult.done();
    }

    @PostMapping("getRoleListPage")
    @RequiresPermissions("systems:role:getRoleListPage")
    @ApiOperation(value = "分页查询角色列表接口", notes = "分页查询角色列表接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam( name = "id", value = "角色主键ID",  dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "roleName", value = "角色名",  dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "roleStatus", value = "角色状态", dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "pageNumber", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam( name = "pageSize", value = "每页条数", required = true, dataType = "int", paramType = "query"),
    })
    public BaseResult<IPage<RoleEntity>> getRoleListPage(String id, String roleName, StatusEnum roleStatus, Integer pageNumber, Integer pageSize){
        QueryWrapper<RoleEntity> qw = new QueryWrapper<>();
        if(!StringUtil.isNull(id)){
            qw.eq("id", id);
        }
        if(!StringUtil.isNull(roleName)){
            qw.eq("role_name", roleName);
        }
        if(roleStatus != null){
            qw.eq("role_status", roleStatus);
        }
        if(pageNumber == null || pageSize == null){
            return BaseResult.error(BaseResultStatus.PAGE_ERROR);
        }
        Page<RoleEntity> page = new Page<>(pageNumber, pageSize);
        IPage<RoleEntity> user = roleService.page(page, qw);
        return BaseResult.done(user);
    }

    @PostMapping("addMenu")
    @RequiresPermissions("systems:role:addMenu")
    @ApiOperation(value = "角色添加菜单信息", notes = "角色添加菜单信息接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam( name = "roleId", value = "角色主键ID",  dataType = "String", paramType = "query")
    })
    public BaseResult addMenu(String roleId,@RequestParam("menuIds") List<String> menuIds){
        if(StringUtil.isNull(roleId)){
            return  BaseResult.error(BaseResultStatus.ROLE_ID_IS_NULL);
        }
        if(CollectionUtils.isEmpty(menuIds)){
            return BaseResult.error(BaseResultStatus.MENU_LIST_IS_NULL);
        }
        menuService.roleAddMenu(roleId, menuIds);
        return BaseResult.done();
    }
}
