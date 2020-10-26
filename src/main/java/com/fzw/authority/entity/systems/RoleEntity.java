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
@TableName("role")
public class RoleEntity extends Model<RoleEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 ENDBLED：启用，DISABLED：禁
     */
    private String roleStatus;

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

    public RoleEntity setId(String id) {
        this.id = id;
        return this;
    }
    public String getRoleName() {
        return roleName;
    }

    public RoleEntity setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
    public String getRemark() {
        return remark;
    }

    public RoleEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public String getRoleStatus() {
        return roleStatus;
    }

    public RoleEntity setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
        return this;
    }
    public String getCreatorId() {
        return creatorId;
    }

    public RoleEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public RoleEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public String getModifierId() {
        return modifierId;
    }

    public RoleEntity setModifierId(String modifierId) {
        this.modifierId = modifierId;
        return this;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public RoleEntity setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
            "id=" + id +
            ", roleName=" + roleName +
            ", remark=" + remark +
            ", roleStatus=" + roleStatus +
            ", creatorId=" + creatorId +
            ", createTime=" + createTime +
            ", modifierId=" + modifierId +
            ", modifyTime=" + modifyTime +
        "}";
    }
}
