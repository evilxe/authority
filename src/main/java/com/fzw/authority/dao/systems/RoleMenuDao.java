package com.fzw.authority.dao.systems;

import com.fzw.authority.entity.systems.RoleMenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
public interface RoleMenuDao extends BaseMapper<RoleMenuEntity> {

    /**
     * @Author FangZhiWei
     * @Description TODO 根据角色id删除角色菜单信息
     * @Date 12:08 2020/9/1 0001
     */
    void deleteByRoleId(String roleId);

    /**
     * @Author FangZhiWei
     * @Description TODO 批量添加角色菜单信息
     * @Date 12:13 2020/9/1 0001
     */
    void batchInner(List<RoleMenuEntity> list);
}
