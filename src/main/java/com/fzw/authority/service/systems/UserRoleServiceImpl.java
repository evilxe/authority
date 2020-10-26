package com.fzw.authority.service.systems;

import com.fzw.authority.entity.systems.UserRoleEntity;
import com.fzw.authority.dao.systems.UserRoleDao;
import com.fzw.authority.service.systems.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleEntity> implements UserRoleService {

}
