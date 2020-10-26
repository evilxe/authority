package com.fzw.authority.dao.systems;

import com.fzw.authority.entity.systems.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
public interface RoleDao extends BaseMapper<RoleEntity> {

    /**
     * @Author FangZhiWei
     * @Description TODO 根据用户ID查询角色信息
     * @Date 17:36 2020/8/27 0027
     */
    RoleEntity selectByUserId(String userId);
}
