package com.fzw.authority.dao.systems;

import com.fzw.authority.entity.systems.MenuEntity;
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
public interface MenuDao extends BaseMapper<MenuEntity> {

    List<MenuEntity> selectByRoleId(String roleId);

    /**
     * @Author FangZhiWei
     * @Description TODO 根据菜单id集合查询菜单信息
     * @Date 11:54 2020/9/1 0001
     */
    List<MenuEntity> selectMenuByIdList(List<String> list);
}
