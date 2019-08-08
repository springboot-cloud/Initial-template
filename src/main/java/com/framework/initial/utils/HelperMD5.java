package com.framework.initial.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: litao
 * @description:
 * @createTime: 2019/4/16 0016 09:22
 */
public class HelperMD5 {

    /**
     * 使用spring框架提供的md5接口
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * 使用java原生提供的md5接口
     *
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        String passwordMd5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes("utf-8"));
            passwordMd5 = HelperStringByte.bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordMd5;
    }
}
