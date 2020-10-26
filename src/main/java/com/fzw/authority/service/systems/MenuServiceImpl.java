package com.fzw.authority.service.systems;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzw.authority.dao.systems.RoleDao;
import com.fzw.authority.dao.systems.RoleMenuDao;
import com.fzw.authority.entity.systems.MenuEntity;
import com.fzw.authority.dao.systems.MenuDao;
import com.fzw.authority.entity.systems.RoleEntity;
import com.fzw.authority.entity.systems.RoleMenuEntity;
import com.fzw.authority.enums.StatusEnum;
import com.fzw.authority.enums.systems.MenuTypeEnums;
import com.fzw.authority.exception.AuthorityException;
import com.fzw.authority.service.systems.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.ShiRoUserUtil;
import com.fzw.authority.util.StringUtil;
import com.fzw.authority.vo.systems.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.management.relation.Role;
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
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;


    @Override
    public void saveMenu(MenuVO menuVO) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(StringUtil.getUUID());
        menuEntity.setParentId(menuVO.getParentId() != null ? menuVO.getParentId() : null);
        menuEntity.setMenuName(menuVO.getMenuName());
        menuEntity.setPerms(menuVO.getPerms());
        menuEntity.setMenuUrl(menuVO.getMenuUrl());
        menuEntity.setComponent(menuVO.getComponent());
        menuEntity.setMenuType(menuVO.getMenuType().getCode());
        menuEntity.setOrderNum(menuVO.getOrderNum());
        menuEntity.setMenuStatus(StatusEnum.ENABLED.getCode());
        menuEntity.setCreatorId(ShiRoUserUtil.getUser().getId());
        menuEntity.setCreateTime(new Date());
        menuDao.insert(menuEntity);
    }

    @Override
    public void editMenu(MenuVO menuVO) {
        MenuEntity menuEntity = menuDao.selectById(menuVO.getId());
        if(menuEntity == null){
            throw new AuthorityException(BaseResultStatus.MENU_IS_NULL);
        }
        if(!StringUtil.isNull(menuVO.getMenuName())){
            if(!menuVO.getMenuName().equals(menuEntity.getMenuName())){
                if(!isMenuName(menuVO.getMenuName())){
                    throw new AuthorityException(BaseResultStatus.MENU_ALREADY_EXIST);
                }
            }
            menuEntity.setMenuName(menuVO.getMenuName());
        }
        if(menuVO.getMenuType() != null){
            menuEntity.setMenuStatus(StatusEnum.ENABLED.getCode());
        }
        if(menuVO.getOrderNum() != null){
            menuEntity.setOrderNum(menuVO.getOrderNum());
        }
        menuEntity.setParentId(menuVO.getParentId() != null ? menuVO.getParentId() : null);
        menuEntity.setMenuUrl(menuVO.getMenuUrl() != null ? menuVO.getMenuUrl() : null);
        menuEntity.setComponent(menuVO.getComponent() != null ? menuVO.getComponent() : null);
        menuEntity.setModifierId(ShiRoUserUtil.getUser().getId());
        menuEntity.setModifyTime(new Date());
        menuDao.updateById(menuEntity);
    }

    @Override
    public Boolean isMenuName(String menuName) {
        QueryWrapper<MenuEntity> qw = new QueryWrapper<>();
        qw.eq("menu_name", menuName);
        MenuEntity menuEntity = menuDao.selectOne(qw);
        if(menuEntity != null){
            return false;
        }
        return true;
    }

    @Override
    public List<MenuEntity> getByRoleId(String roleId) {
        if(!StringUtil.isNull(roleId)){
            return  menuDao.selectByRoleId(roleId);
        }
        return null;
    }

    @Override
    @Transactional
    public void roleAddMenu(String roleId, List<String> menuIds) {
        RoleEntity roleEntity = roleDao.selectById(roleId);
        if(roleEntity == null){
            throw  new AuthorityException(BaseResultStatus.ROLE_IS_NULL);
        }
        List<MenuEntity> menuList = menuDao.selectMenuByIdList(menuIds);
        if(CollectionUtils.isEmpty(menuList)){
            throw  new AuthorityException(BaseResultStatus.MENU_IS_NULL) ;
        }
        List<RoleMenuEntity> roleMenuList = new ArrayList<>();
        for(MenuEntity menuEntity : menuList){
            RoleMenuEntity roleMenu = new RoleMenuEntity();
            roleMenu.setId(StringUtil.getUUID());
            roleMenu.setRoleId(roleEntity.getId());
            roleMenu.setMenuId(menuEntity.getId());
            roleMenu.setCreatorId(ShiRoUserUtil.getUser().getId());
            roleMenu.setCreateTime(new Date());
            roleMenuList.add(roleMenu);
        }
        roleMenuDao.deleteByRoleId(roleEntity.getId());
        roleMenuDao.batchInner(roleMenuList);
    }
}
