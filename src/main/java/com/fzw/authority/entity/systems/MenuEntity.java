package com.fzw.authority.entity.systems;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
@TableName("menu")
public class MenuEntity extends Model<MenuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 父级菜单ID
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 菜单类型 CATALOGUE:目录，MENUS:菜单，BUTTON:按钮
     */
    private String menuType;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 状态 ENDBLED：启用，DISABLED：禁用
     */
    private String menuStatus;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String modifierId;

    /**
     * 更新时间
     */
    private Date modifyTime;

    public String getId() {
        return id;
    }

    public MenuEntity setId(String id) {
        this.id = id;
        return this;
    }
    public String getParentId() {
        return parentId;
    }

    public MenuEntity setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }
    public String getMenuName() {
        return menuName;
    }

    public MenuEntity setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }
    public String getMenuUrl() {
        return menuUrl;
    }

    public MenuEntity setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
        return this;
    }
    public String getPerms() {
        return perms;
    }

    public MenuEntity setPerms(String perms) {
        this.perms = perms;
        return this;
    }
    public String getMenuType() {
        return menuType;
    }

    public MenuEntity setMenuType(String menuType) {
        this.menuType = menuType;
        return this;
    }
    public Integer getOrderNum() {
        return orderNum;
    }

    public MenuEntity setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
        return this;
    }
    public String getComponent() {
        return component;
    }

    public MenuEntity setComponent(String component) {
        this.component = component;
        return this;
    }
    public String getMenuStatus() {
        return menuStatus;
    }

    public MenuEntity setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
        return this;
    }
    public String getCreatorId() {
        return creatorId;
    }

    public MenuEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public MenuEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public String getModifierId() {
        return modifierId;
    }

    public MenuEntity setModifierId(String modifierId) {
        this.modifierId = modifierId;
        return this;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public MenuEntity setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", menuName=" + menuName +
            ", menuUrl=" + menuUrl +
            ", perms=" + perms +
            ", menuType=" + menuType +
            ", orderNum=" + orderNum +
            ", component=" + component +
            ", menuStatus=" + menuStatus +
            ", creatorId=" + creatorId +
            ", createTime=" + createTime +
            ", modifierId=" + modifierId +
            ", modifyTime=" + modifyTime +
        "}";
    }
}
