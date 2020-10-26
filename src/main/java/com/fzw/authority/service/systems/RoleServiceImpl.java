package com.fzw.authority.service.systems;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzw.authority.dao.systems.MenuDao;
import com.fzw.authority.dao.systems.RoleMenuDao;
import com.fzw.authority.dao.systems.UserRoleDao;
import com.fzw.authority.entity.systems.*;
import com.fzw.authority.dao.systems.RoleDao;
import com.fzw.authority.enums.StatusEnum;
import com.fzw.authority.exception.AuthorityException;
import com.fzw.authority.service.systems.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzw.authority.util.BaseResult;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.ShiRoUserUtil;
import com.fzw.authority.util.StringUtil;
import com.fzw.authority.vo.systems.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public RoleEntity getRoleByUserId(String userId) {
        if(!StringUtil.isNull(userId)){
            return roleDao.selectByUserId(userId);
        }
        return null;
    }

    @Override
    public void saveRole(RoleVO roleVO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(StringUtil.getUUID());
        roleEntity.setRoleName(roleVO.getRoleName());
        roleEntity.setRemark(roleVO.getRemark() != null ? roleVO.getRoleName() : null);
        roleEntity.setRoleStatus(StatusEnum.ENABLED.getCode());
        roleEntity.setCreatorId(ShiRoUserUtil.getUser().getId());
        roleEntity.setCreateTime(new Date());
        roleDao.insert(roleEntity);
    }

    @Override
    public void editRole(RoleVO roleVO) {
        RoleEntity roleEntity = roleDao.selectById(roleVO.getId());
        if(roleEntity == null){
            throw  new AuthorityException(BaseResultStatus.ROLE_IS_NULL);
        }
        if(!StringUtil.isNull(roleVO.getRoleName()) && !roleVO.getRoleName().equals(roleEntity.getRoleName())){
            roleEntity.setRoleName(roleVO.getRoleName());
        }
        roleEntity.setRemark(roleVO.getRemark() != null ? roleVO.getRemark() : null);
        roleEntity.setModifierId(ShiRoUserUtil.getUser().getId());
        roleEntity.setModifyTime(new Date());
        roleDao.updateById(roleEntity);
    }

    @Override
    public Boolean isRoleName(String roleName) {
        QueryWrapper<RoleEntity> qw = new QueryWrapper<>();
        qw.eq("role_name", roleName);
        RoleEntity roleEntity = roleDao.selectOne(qw);
        if(roleEntity != null){
            return false;
        }
        return true;
    }



    @Override
    public void userAddRole(String userId, String roleId) {
        RoleEntity roleEntity = roleDao.selectById(roleId);
        if(roleEntity == null){
            throw  new AuthorityException(BaseResultStatus.ROLE_IS_NULL);
        }
        QueryWrapper<UserRoleEntity> qw = new QueryWrapper();
        qw.eq("user_id", userId);
        UserRoleEntity userRole = userRoleDao.selectOne(qw);
        if(userRole != null){
            userRole.setRoleId(roleId);
            userRole.setModifierId(ShiRoUserUtil.getUser().getId());
            userRole.setModifyTime(new Date());
            userRoleDao.updateById(userRole);
        }
        userRole = new UserRoleEntity();
        userRole.setId(StringUtil.getUUID());
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setCreatorId(ShiRoUserUtil.getUser().getId());
        userRole.setCreateTime(new Date());
        userRoleDao.insert(userRole);
    }
}
