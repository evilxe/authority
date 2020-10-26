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
@TableName("role_menu")
public class RoleMenuEntity extends Model<RoleMenuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 角色主键ID
     */
    private String roleId;

    /**
     * 菜单主键ID
     */
    private String menuId;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private Date createTime;


    public String getId() {
        return id;
    }

    public RoleMenuEntity setId(String id) {
        this.id = id;
        return this;
    }
    public String getRoleId() {
        return roleId;
    }

    public RoleMenuEntity setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }
    public String getMenuId() {
        return menuId;
    }

    public RoleMenuEntity setMenuId(String menuId) {
        this.menuId = menuId;
        return this;
    }
    public String getCreatorId() {
        return creatorId;
    }

    public RoleMenuEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public RoleMenuEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RoleMenuEntity{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", menuId=" + menuId +
            ", creatorId=" + creatorId +
            ", createTime=" + createTime +
        "}";
    }
}
