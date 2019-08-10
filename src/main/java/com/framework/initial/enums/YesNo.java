package com.framework.initial.enums;


import com.framework.initial.service.common.CodeEnum;

/**
 * @author mgLuoBo
 * @description 是否枚举
 * @createTime 2019/1/23 0023 17:47
 */
public enum YesNo implements CodeEnum {
    YES(1, "是"),
    NO(2, "否");

    private int code;
    private String desc;

    YesNo(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
