package com.fzw.authority.vo.systems;

import com.fzw.authority.enums.systems.MenuTypeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserVO", description = "菜单VO")
public class MenuVO {

    @ApiModelProperty("菜单主键ID")
    private String id;

    @ApiModelProperty("父级菜单ID")
    private String parentId;

    @ApiModelProperty(value = "菜单名称", required = true )
    private String menuName;

    @ApiModelProperty("菜单地址")
    private String menuUrl;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @ApiModelProperty(value = "CATALOGUE:目录，MENUS:菜单，BUTTON:按钮", required = true)
    private MenuTypeEnums menuType;

    @ApiModelProperty(value = "排序", required = true)
    private Integer orderNum;

    @ApiModelProperty("组件路径")
    private String component;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public MenuTypeEnums getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuTypeEnums menuType) {
        this.menuType = menuType;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
