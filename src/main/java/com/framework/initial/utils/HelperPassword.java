package com.framework.initial.utils;

public class HelperPassword {

    public static boolean verifyPassword(String password, String md5Password, String salty) {
        return beforeSave(password, salty).equals(md5Password);
    }

    public static String beforeSave(String password, String salty) {
        return HelperMD5.getMD5(password + salty);
    }
}
