package com.fzw.authority.service.systems;

import com.fzw.authority.entity.systems.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzw.authority.exception.AuthorityException;
import com.fzw.authority.vo.systems.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author FangZhiWei
 * @since 2020-08-25
 */
public interface UserService extends IService<UserEntity> {

    /**
     * @Author FangZhiWei
     * @Description TODO 添加用户
     * @Date 17:11 2020/8/25 0025
     */
    void saveUser(UserVO userVO);

    /**
     * @Author FangZhiWei
     * @Description TODO 修改用户信息
     * @Date 17:14 2020/8/25 0025
     */
    void editUser(UserVO userVO);
    
    /**
     * @Author FangZhiWei
     * @Description TODO 校验手机号是否存在
     * @Date 17:16 2020/8/25 0025
     */
    Boolean isMobile(String mobile);
    
    /**
     * @Author FangZhiWei
     * @Description TODO 校验账号是否存在
     * @Date 17:17 2020/8/25 0025
     */
    Boolean isAccount(String account);

    /**
     * @Author FangZhiWei
     * @Description TODO 根据账号查询用户信息
     * @Date 13:46 2020/8/26 0026
     */
    UserEntity getUserByAccount(String account);

    /**
     * @Author FangZhiWei
     * @Description TODO 根据条件分压查询用户列表
     * @Date 18:33 2020/8/27 0027
     */
    List<UserEntity> getUserListPage(int pageNumber, int pageSize);

    /**
     * @Author FangZhiWei
     * @Description TODO 用户添加角色信息
     * @Date 14:40 2020/9/1 0001
     */
    void addRole(String id, String roleId);
}
