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
@TableName("user_role")
public class UserRoleEntity extends Model<UserRoleEntity> {

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
     * 用户主键ID
     */
    private String userId;

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

    public UserRoleEntity setId(String id) {
        this.id = id;
        return this;
    }
    public String getRoleId() {
        return roleId;
    }

    public UserRoleEntity setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }
    public String getUserId() {
        return userId;
    }

    public UserRoleEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public String getCreatorId() {
        return creatorId;
    }

    public UserRoleEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public UserRoleEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public String getModifierId() {
        return modifierId;
    }

    public UserRoleEntity setModifierId(String modifierId) {
        this.modifierId = modifierId;
        return this;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public UserRoleEntity setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", userId=" + userId +
            ", creatorId=" + creatorId +
            ", createTime=" + createTime +
            ", modifierId=" + modifierId +
            ", modifyTime=" + modifyTime +
        "}";
    }
}
