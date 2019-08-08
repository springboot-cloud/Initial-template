package com.framework.initial.utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author Created by liuhongguang on 2018年11月06日
 * @Description UUID生成的工具类
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @return java.lang.Long
     * @description 生成18位唯一不重复编号
     * @author xiongfeiyang
     * @date 2019/5/28 14:33
     */
    public static Long getNumber() {
        String dateTime = DateUtil.convertDate(new Date(), 4);
        String numeric = RandomStringUtils.randomNumeric(4);
        return Long.valueOf(dateTime + numeric);
    }
}
