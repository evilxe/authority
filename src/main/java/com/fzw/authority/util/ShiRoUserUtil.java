package com.fzw.authority.util;

import com.fzw.authority.entity.systems.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;



public class ShiRoUserUtil {

    //
    public static UserEntity getUser(){
        Subject subject = SecurityUtils.getSubject();
        return  (UserEntity) subject.getPrincipal();
    }
}
