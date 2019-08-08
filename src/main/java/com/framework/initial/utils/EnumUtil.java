package com.framework.initial.utils;


import com.framework.initial.service.common.CodeEnum;

/**
 * @author XiongFeiYang
 * @description 枚举工具类
 * @createTime 2019-08-08 14:17
 **/
public class EnumUtil {

    public static <T extends CodeEnum> String getByCode(Integer code, Class<T> t) {
        for (T item : t.getEnumConstants()) {
            if (item.getCode() == code) {
                return item.getDesc();
            }
        }
        return null;
    }

    /**
     * nameOf,传入的参数name指的是枚举值的名称，一般是大写加下划线的
     */
    public static <T extends Enum<T>> T nameOf(Class<T> clazz, String name) {
        return (T) Enum.valueOf(clazz, name);
    }

}
