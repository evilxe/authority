package com.fzw.authority.controller.back.systems;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzw.authority.entity.systems.MenuEntity;
import com.fzw.authority.entity.systems.RoleEntity;
import com.fzw.authority.enums.StatusEnum;
import com.fzw.authority.enums.systems.MenuTypeEnums;
import com.fzw.authority.exception.AuthorityException;
import com.fzw.authority.service.systems.MenuService;
import com.fzw.authority.util.BaseResult;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.StringUtil;
import com.fzw.authority.vo.systems.MenuVO;
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
@RequestMapping("/back/menu")
@Api(value="菜单controller",tags={"菜单操作接口"})
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("saveMenu")
    @RequiresPermissions("systems:menu:saveMenu")
    @ApiOperation(value = "添加菜单信息接口", notes = "添加菜单信息接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
    })
    public BaseResult saveMenu(MenuVO menuVO){
        if(StringUtil.isNull(menuVO.getMenuName())){
            return BaseResult.error(BaseResultStatus.MENU_NAME_IS_NULL);
        }
        if(!menuService.isMenuName(menuVO.getMenuName())){
            throw new AuthorityException(BaseResultStatus.MENU_ALREADY_EXIST);
        }
        if(menuVO.getMenuType() == null){
            return BaseResult.error(BaseResultStatus.MENU_TYPE_IS_NULL);
        }
        if(menuVO.getOrderNum() == null){
            return BaseResult.error(BaseResultStatus.MENU_ORDER_NUM_IS_NULL);
        }
        if(menuVO.getMenuType() == MenuTypeEnums.MENUS){
            if(StringUtil.isNull(menuVO.getComponent())){
                return BaseResult.error(BaseResultStatus.MENU_COMPONENT_IS_NULL);
            }
        } else if(menuVO.getMenuType() == MenuTypeEnums.BUTTON){
            if(StringUtil.isNull(menuVO.getPerms())){
                return BaseResult.error(BaseResultStatus.MENU_PERMS_IS_NULL);
            }
        }
        menuService.saveMenu(menuVO);
        return BaseResult.done("添加菜单成功");
    }

    @PostMapping("editMenu")
    @RequiresPermissions("systems:menu:editMenu")
    @ApiOperation(value = "更新菜单信息接口", notes = "更新菜单信息接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单主键ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header")
    })
    public BaseResult editMenu(MenuVO menuVO){
        if(StringUtil.isNull(menuVO.getId())){
            return BaseResult.error(BaseResultStatus.ROLE_ID_IS_NULL);
        }
        menuService.editMenu(menuVO);
        return BaseResult.done("修改菜单成功");
    }

    @PostMapping("getMenuListPage")
    @RequiresPermissions("systems:menu:getMenuListPage")
    @ApiOperation(value = "分页查询菜单接口", notes = "分页查询菜单接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "Token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam( name = "id", value = "菜单主键ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "parentId", value = "父级菜单", dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "menuName", value = "菜单名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "menuType", value = "菜单类型", dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "menuStatus", value = "菜单状态", dataType = "String", paramType = "query"),
            @ApiImplicitParam( name = "pageNumber", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam( name = "pageSize", value = "每页条数", required = true, dataType = "int", paramType = "query"),
    })
    public BaseResult<IPage<MenuEntity>> getMenuListPage(String id,String parentId, String menuName, MenuTypeEnums menuType, StatusEnum menuStatus,
                                      Integer pageNumber, Integer pageSize){
        QueryWrapper<MenuEntity> qw = new QueryWrapper<>();
        if(!StringUtil.isNull(id)){
            qw.eq("id", id);
        }
        if(!StringUtil.isNull(parentId)){
            qw.eq("parent_id", parentId);
        }
        if(!StringUtil.isNull(menuName)){
            qw.eq("menu_name", menuName);
        }
        if(menuType != null){
            qw.eq("menu_type", menuType.getCode());
        }
        if(menuStatus != null){
            qw.eq("menu_status", menuStatus.getCode());
        }
        if(pageNumber == null || pageSize == null){
            return BaseResult.error(BaseResultStatus.PAGE_ERROR);
        }
        Page<MenuEntity> page = new Page<>(pageNumber, pageSize);
        IPage<MenuEntity> pageResult = menuService.page(page, qw);
        return BaseResult.done(pageResult);
    }
}
