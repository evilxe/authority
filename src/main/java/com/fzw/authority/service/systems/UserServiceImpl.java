package com.fzw.authority.service.systems;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzw.authority.dao.systems.RoleDao;
import com.fzw.authority.dao.systems.UserRoleDao;
import com.fzw.authority.entity.systems.UserEntity;
import com.fzw.authority.dao.systems.UserDao;
import com.fzw.authority.enums.StatusEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzw.authority.exception.AuthorityException;
import com.fzw.authority.util.BaseResultStatus;
import com.fzw.authority.util.OrikaUtil;
import com.fzw.authority.util.ShiRoUserUtil;
import com.fzw.authority.util.StringUtil;
import com.fzw.authority.vo.systems.UserVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(UserVO userVO){
        UserEntity userEntity = new UserEntity();
        OrikaUtil.map(userVO, userEntity);
        String salt = StringUtil.getSixRandom();
        String password = DigestUtils.md5Hex(DigestUtils.md5Hex(userVO.getPassword() + salt));
        userEntity.setId(StringUtil.getUUID());
        userEntity.setPassword(password);
        userEntity.setSalt(salt);
        userEntity.setStatus(StatusEnum.ENABLED.getCode());
        userEntity.setCreatorId(ShiRoUserUtil.getUser().getId());
        userEntity.setCreateTime(new Date());
        userDao.insert(userEntity);
    }

    @Override
    public void editUser(UserVO userVO) {
        UserEntity userEntity = userDao.selectById(userVO.getId());
        if(userEntity == null){
            throw new AuthorityException(BaseResultStatus.ADMIN_USER_IS_NULL);
        }
        if(!StringUtil.isNull(userVO.getPassword())){
            String password = DigestUtils.md5Hex(DigestUtils.md5Hex(userVO.getPassword() + userEntity.getSalt()));
            userEntity.setPassword(password);
        }
        if(!StringUtil.isNull(userVO.getEmail())){
            userEntity.setEmail(userVO.getEmail());
        }
        if(!StringUtil.isNull(userVO.getMobile())){
            userEntity.setMobile(userVO.getMobile());
        }
        userEntity.setModifierId(ShiRoUserUtil.getUser().getId());
        userEntity.setModifyTime(new Date());
        userDao.updateById(userEntity);
    }

    @Override
    public Boolean isMobile(String mobile) {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("mobile", mobile);
        UserEntity userEntity = userDao.selectOne(qw);
        if(userEntity != null){
            return true;
        }
        return false;
    }

    @Override
    public Boolean isAccount(String account) {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("account", account);
        UserEntity userEntity = userDao.selectOne(qw);
        if(userEntity != null){
            return true;
        }
        return false;
    }

    @Override
    public UserEntity getUserByAccount(String account) {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("account", account);
        return userDao.selectOne(qw);
    }

    @Override
    public List<UserEntity> getUserListPage(int pageNumber, int pageSize) throws AuthorityException {
        if(pageNumber == 0){
            throw new AuthorityException(10000000);
        }
        return null;
    }

    @Override
    public void addRole(String id, String roleId) {
        UserEntity userEntity = userDao.selectById(roleId);

    }
}
