package com.fzw.authority.service.systems;

import com.fzw.authority.entity.systems.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzw.authority.vo.systems.RoleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
public interface RoleService extends IService<RoleEntity> {



    /**
     * @Author FangZhiWei
     * @Description TODO 添加角色信息
     * @Date 16:47 2020/8/31 0031
     */
    void saveRole(RoleVO roleVO);

    /**
     * @Author FangZhiWei
     * @Description TODO 跟新角色信息
     * @Date 16:47 2020/8/31 0031
     */
    void editRole(RoleVO roleVO);

    /**
     * @Author FangZhiWei
     * @Description TODO 根据账号查询角色信息
     * @Date 17:30 2020/8/27 0027
     */
    RoleEntity getRoleByUserId(String userId);

    /**
     * @Author FangZhiWei
     * @Description TODO 判断该角色名是否存在
     * @Date 16:56 2020/8/31 0031
     */
    Boolean isRoleName(String roleName);



   /**
    * @Author FangZhiWei
    * @Description TODO 用户添加角色信息
    * @Date 14:45 2020/9/1 0001
    */
   void userAddRole(String userId, String roleId);
}
