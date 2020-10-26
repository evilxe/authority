package com.fzw.authority.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: hosp
 * @Package: com.fzw.hosp.util
 * @ClassName: ResultMessage
 * @Author: FangZhiWei
 * @Description: 返回信息
 * @Date: 2020/7/6 0006 14:50
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ResultMessage {

    String value() default "";
}
