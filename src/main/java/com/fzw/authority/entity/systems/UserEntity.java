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
@TableName("user")
public class UserEntity extends Model<UserEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String status;

    /**
     * 最近登陆时间
     */
    private Date lastLoginDate;

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

    public UserEntity setId(String id) {
        this.id = id;
        return this;
    }
    public String getAccount() {
        return account;
    }

    public UserEntity setAccount(String account) {
        this.account = account;
        return this;
    }
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getSalt() {
        return salt;
    }

    public UserEntity setSalt(String salt) {
        this.salt = salt;
        return this;
    }
    public String getRealName() {
        return realName;
    }

    public UserEntity setRealName(String realName) {
        this.realName = realName;
        return this;
    }
    public String getMobile() {
        return mobile;
    }

    public UserEntity setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getStatus() {
        return status;
    }

    public UserEntity setStatus(String status) {
        this.status = status;
        return this;
    }
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public UserEntity setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }
    public String getCreatorId() {
        return creatorId;
    }

    public UserEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return this;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public UserEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public String getModifierId() {
        return modifierId;
    }

    public UserEntity setModifierId(String modifierId) {
        this.modifierId = modifierId;
        return this;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public UserEntity setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "id=" + id +
            ", account=" + account +
            ", password=" + password +
            ", salt=" + salt +
            ", realName=" + realName +
            ", mobile=" + mobile +
            ", email=" + email +
            ", status=" + status +
            ", lastLoginDate=" + lastLoginDate +
            ", creatorId=" + creatorId +
            ", createTime=" + createTime +
            ", modifierId=" + modifierId +
            ", modifyTime=" + modifyTime +
        "}";
    }
}
