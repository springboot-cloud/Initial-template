package com.framework.initial.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字帮助类
 *
 * @author yangtong 2016年7月5日
 */
public class NumberUtil {
    /**
     * 精确格式化成金额格式(例如：1.199999 转成：1.2 )
     *
     * @param obj
     * @param decimal
     * @return
     */
    public static double formatMoney(double obj, int decimal) {
        String obj1 = new BigDecimal(obj).setScale(2 * decimal, BigDecimal.ROUND_HALF_UP).toString();
        double obj2 = new BigDecimal(obj1).setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
        return obj2;
    }

    /**
     * @return boolean
     * @description 判断数字是否为空且合法
     * @author xiongfeiyang
     * @date 2019/5/28 14:46
     * @params [val]
     */
    public static boolean isBlank(Long val) {
        return null == val || val < 1;
    }

    /**
     * 精确格式化成金额格式(例如：1.199999 转成：1.2 )
     *
     * @param obj
     * @return
     */
    public static double formatMoney(double obj) {
        return formatMoney(obj, 2);
    }

    public static String formatMoneyToString(double obj) {
        DecimalFormat decimal = new DecimalFormat("0.00");
        return decimal.format(formatMoney(obj));
    }

    public static double formatMoney1(double obj) {
        String obj1 = new BigDecimal(obj).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        double obj2 = new BigDecimal(obj1).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return obj2;
    }

    /**
     * double类型保留两位小数 并转化为分
     *
     * @param obj
     * @return
     */
    public static Long doubleToLong(Double obj) {
        Double money = multiply(formatMoney(obj), 100d);
        return money.longValue();
    }

    /**
     * @param obj
     * @return
     * @Title: formatMoneyStr
     * @Description: 返回金额字符串（一定2位小数）
     */
    public static String formatMoneyStr1(double obj) {
        DecimalFormat decimal = new DecimalFormat("0.00");
        double result = formatMoney(obj);
        return decimal.format(result);
    }

    // 两个double类型的字符串相加
    public static double add(String double1, String double2) {
        BigDecimal b1 = new BigDecimal(double1);
        BigDecimal b2 = new BigDecimal(double2);
        return b1.add(b2).doubleValue();
    }

    // 两个double数字相加
    public static double add(double double1, double double2) {
        BigDecimal b1 = new BigDecimal(double1);
        BigDecimal b2 = new BigDecimal(double2);
        return b1.add(b2).doubleValue();
    }

    // 是否是正整数
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[+]?[0-9]+$");
        return pattern.matcher(str).matches();
    }

    // 剪发
    public static double subtract(double double1, double double2) {
        BigDecimal b1 = new BigDecimal(double1);
        BigDecimal b2 = new BigDecimal(double2);
        return b1.subtract(b2).doubleValue();
    }

    // 除
    public static double divide(double double1, double double2) {
        BigDecimal b1 = new BigDecimal(double1);
        BigDecimal b2 = new BigDecimal(double2);
        return b1.divide(b2, 2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double divide(double double1, double double2, int i) {
        BigDecimal b1 = new BigDecimal(double1);
        BigDecimal b2 = new BigDecimal(double2);
        return b1.divide(b2, i, RoundingMode.HALF_UP).doubleValue();
    }

    // 乘法
    public static double multiply(double double1, double double2) {
        BigDecimal b1 = new BigDecimal(double1);
        BigDecimal b2 = new BigDecimal(double2);
        return formatMoney(b1.multiply(b2).doubleValue());
    }

    /**
     * @param num
     * @return
     * @Title: doubleRoundToInt
     * @Description: 四舍五入取整
     */
    public static int doubleRoundToInt(double num) {
        BigDecimal bigDecimal = new BigDecimal(num);
        int result = bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return result;
    }

    /**
     * @param text
     * @return
     * @Title: isNumeric
     * @Description: 判断字符串是否是数字
     */
    public static boolean isNumeric(String text) {
        Pattern pattern = Pattern.compile("[+-]?\\d*[.]?\\d*");
        Matcher isNum = pattern.matcher(text);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param text
     * @return
     * @Title: isNumericForPlus
     * @Description: 判断字符串是否是正数字
     */
    public static boolean isNumericForPlus(String text) {
        Pattern pattern = Pattern.compile("[+]?\\d*[.]?\\d*");
        Matcher isNum = pattern.matcher(text);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param text
     * @return
     * @Title: isNumericForPlusNoZero
     * @Description: 判断字符串是否大于0的数字
     */
    public static boolean isNumericForPlusNoZero(String text) {
        if (!isNumericForPlus(text)) {
            return false;
        }
        if (new Double(text).doubleValue() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * @param text
     * @return
     * @Title: isNumericForNegative
     * @Description: 判断字符串是否是负数字
     */
    public static boolean isNumericForNegative(String text) {
        Pattern pattern = Pattern.compile("[-]\\d*[.]?\\d*");
        Matcher isNum = pattern.matcher(text);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param text
     * @return
     * @Title: isNumericForNegativeNoZero
     * @Description: 判断字符串是否小于0的数字
     */
    public static boolean isNumericForNegativeNoZero(String text) {
        if (!isNumericForNegative(text)) {
            return false;
        }
        if (new Double(text).doubleValue() >= 0) {
            return false;
        }
        return true;
    }

    /**
     * @param text
     * @return
     * @Title: isInt
     * @Description: 判断字符串是否是整数
     */
    public static boolean isInt(String text) {
        Pattern pattern = Pattern.compile("[+-]?\\d*");
        Matcher isIntTemp = pattern.matcher(text);
        if (!isIntTemp.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param text
     * @return
     * @Title: isIntNoZero
     * @Description: 判断字符串是否是不等于0的整数
     */
    public static boolean isIntNoZero(String text) {
        if (!isInt(text)) {
            return false;
        }
        if (new Integer(text).intValue() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 保留x位小数，进行四舍五入
     * @param d
     * @return
     */
    public static Double saveBitXRound(Double d,int x){
        BigDecimal bd = new BigDecimal(d);
        Double tem = bd.setScale(x,BigDecimal.ROUND_HALF_UP).doubleValue();
        return tem;
    }

}
