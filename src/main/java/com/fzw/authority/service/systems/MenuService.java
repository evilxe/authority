package com.fzw.authority.service.systems;

import com.fzw.authority.entity.systems.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzw.authority.vo.systems.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
public interface MenuService extends IService<MenuEntity> {

    /**
     * @Author FangZhiWei
     * @Description TODO 添加菜单
     * @Date 17:26 2020/8/31 0031
     */
    void saveMenu(MenuVO menuVO);

    /**
     * @Author FangZhiWei
     * @Description TODO 更新菜单信息
     * @Date 17:26 2020/8/31 0031
     */
    void editMenu(MenuVO menuVO);

    /**
     * @Author FangZhiWei
     * @Description TODO 判断菜单名是否存在
     * @Date 20:17 2020/8/31 0031
     */
    Boolean isMenuName(String menuName);

    /**
     * @Author FangZhiWei
     * @Description TODO 根据角色查询菜单权限信息
     * @Date 17:40 2020/8/27 0027
     */
    List<MenuEntity> getByRoleId(String roleId);

    /**
     * @Author FangZhiWei
     * @Description TODO 角色添加菜单信息
     * @Date 11:34 2020/9/1 0001
     */
    void roleAddMenu(String roleId, List<String> menuIds);
}
