package com.fzw.authority.shiro;

import com.fzw.authority.entity.systems.MenuEntity;
import com.fzw.authority.entity.systems.RoleEntity;
import com.fzw.authority.entity.systems.UserEntity;
import com.fzw.authority.enums.StatusEnum;
import com.fzw.authority.service.systems.MenuService;
import com.fzw.authority.service.systems.RoleService;
import com.fzw.authority.service.systems.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BackRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("----------------------------ShiRo--权限验证---------------------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof UserEntity) {
            UserEntity user = (UserEntity) principal;
            RoleEntity roleEntity = roleService.getRoleByUserId(user.getId());
            if(roleEntity != null){
                simpleAuthorizationInfo.addRole(roleEntity.getRoleName());
                List<MenuEntity> menuList = menuService.getByRoleId(roleEntity.getId());
                if(menuList != null && menuList.size() > 0){
                    for(MenuEntity menuEntity : menuList ){
                        simpleAuthorizationInfo.addStringPermission(menuEntity.getPerms());
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  {
        LOGGER.info("----------------------------ShiRo--登陆验证---------------------------------");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 获取用户名
        String userName = usernamePasswordToken.getUsername();
        // 获取密码
        String password = String.valueOf(usernamePasswordToken.getPassword());
        UserEntity user = userService.getUserByAccount(userName);
        if(user == null){
            throw new UnknownAccountException();
        }
        String checkPassword = DigestUtils.md5Hex(DigestUtils.md5Hex(password + user.getSalt()));
        if(!checkPassword.equals(user.getPassword())){
            throw new IncorrectCredentialsException();
        }
        if(user.getStatus().equals(StatusEnum.DISABLED.getCode())){
           throw new DisabledAccountException();
        }
        return new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
