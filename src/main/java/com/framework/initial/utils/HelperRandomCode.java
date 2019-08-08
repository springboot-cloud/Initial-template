package com.framework.initial.utils;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperRandomCode {

    /**
     * 2-9的数字数组（不要0，1，容易和字母引起歧义）
     */
    public final static String[] NUM_ARRAY = {"2", "3", "4", "5", "6", "7", "8", "9"};
    /**
     * 字母数组-大写（不要I,L,O，容易引起歧义）
     */
    public final static String[] CHAR_ARRAY_UPPER = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 字母数组-小写（不要i,l,o，容易引起歧义）
     */
    public final static String[] CHAR_ARRAY_LOWER = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    /**
     * (字母数组-大写)+(字母数组-小写)
     */
    public final static String[] CHAR_ARRAY = concat(CHAR_ARRAY_LOWER, CHAR_ARRAY_UPPER);

    /**
     * 2-9的数字数组+(字母数组-大写)+(字母数组-小写)
     */
    public final static String[] CODE_ARRAY = concat(NUM_ARRAY, CHAR_ARRAY);


    public final static String NUM_REG = "[0-9]+";
    public final static String CHAR_REG = "[a-zA-Z]+";

    /**
     * 纯数字
     *
     * @param str
     * @return
     */
    public static boolean isAllNumeric(String str) {
        Pattern pattern = Pattern.compile(NUM_REG);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 纯字母
     *
     * @param str
     * @return
     */
    public static boolean isAllCharic(String str) {
        Pattern pattern = Pattern.compile(CHAR_REG);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 至少一位数字，一位字母
     *
     * @param length
     * @return
     */
    public static String getRandomCode(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length cannot less than 1");
        Random random = new Random();
        int c = 0;
        String code = "";
        while (c < length) {
            int s = random.nextInt(CODE_ARRAY.length - 1) % (CODE_ARRAY.length);
            code += CODE_ARRAY[s];
            c++;
        }
        if (length < 2)
            return code;//只有1位

        if (isAllNumeric(code)) {//纯数字
            Random random1 = new Random();
            int s = random1.nextInt(CHAR_ARRAY.length - 1) % (CHAR_ARRAY.length);
            code += CHAR_ARRAY[s];
            code = code.substring(1);
        } else if (isAllCharic(code)) {//纯字母
            Random random1 = new Random();
            int s = random1.nextInt(NUM_ARRAY.length - 1) % (NUM_ARRAY.length);
            code += NUM_ARRAY[s];
            code = code.substring(1);
        }
        return code;
    }

    /**
     * 生成指定位数的随机数.比如：<br>
     * length = 2，生成[10,99]<br>
     * length = 6，生成[100000,999999]<br>
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        int min = (int) Math.pow(10.0, length - 1);
        int max = (int) (Math.pow(10.0, length)) - 1;
        Random random = new Random();
        return String.valueOf(random.nextInt(max) % (max - min + 1) + min);
    }


    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
