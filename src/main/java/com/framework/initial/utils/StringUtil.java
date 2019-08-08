package com.framework.initial.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @description
 * @author Abby.Wang
 * @creation time 2017年12月28日 下午3:07:40
 */
public class StringUtil {

	/**
	 * 判断是否不为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 判断是否为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null) || "".equals(str) || "null".equals(str);
	}

	/**
	 * 判断手机号码是否正确
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 判断是否含有空格和单引号，因为有可能就有可能存在注入攻击
	 *
	 * @param text
	 * @return
	 */
	public static boolean isHaveBlank(String text) {
		// 如果为空， 则不肯定没有空格
		if (isBlank(text)) {
			return false;
		}
		return text.indexOf(" ") == -1 && text.indexOf("'") == -1 ? false : true;
	}

	/**
	 * 转化变量为字符串,为空就返回""
	 *
	 * @param obj
	 * @return
	 */
	public static String convertObject(Object obj) {
		return obj == null || "".equals(obj.toString().trim()) || "null".equals(obj.toString()) ? "" : obj.toString();
	}

	/**
	 * String删除双引号
	 *
	 * @param sourceString
	 * @return
	 */
	public static String deleteString(String sourceString) {
		sourceString = sourceString.replace("\"", "");
		return sourceString;
	}

	/**
	 * 转化变量为字符串类型的数据,为空就返回"0"
	 *
	 * @param obj
	 * @return
	 */
	public static String convertObjectToNumber(Object obj) {
		return obj == null || "".equals(obj.toString().trim()) ? "0" : obj.toString();
	}

	/**
	 * 转化变量为整型, 为空就返回0
	 *
	 * @param obj
	 * @return
	 */
	public static int convertObjectToInt(Object obj) {
		String string = convertObject(obj);
		if (isBlank(string)) {
			return 0;
		}
		return Integer.valueOf(string.trim());
	}

	/**
	 * 转化变量为浮点型, 为空就返回0.0
	 *
	 * @param obj
	 * @return
	 */
	public static double convertObjectToDouble(Object obj) {
		String string = convertObject(obj);
		if (isBlank(string)) {
			return 0.0;
		}
		return Double.valueOf(string.trim());
	}
	/**
	 * 转化变量为long, 为空就返回0
	 *
	 * @param obj
	 * @return
	 */
	public static long convertObjectToLong(Object obj) {
		String string = convertObject(obj);
		if (isBlank(string)) {
			return 0;
		}
		return Long.valueOf(string.trim());
	}

	/**
	 *
	 * @return Description: 将两个字符串相加成double
	 */
	public static double addParseToDouble(String str1, String str2) {
		if (isBlank(str1)) {
			str1 = "0.00";
		}
		if (isBlank(str2)) {
			str2 = "0.00";
		}
		BigDecimal d1 = new BigDecimal(str1);
		BigDecimal d2 = new BigDecimal(str2);
		return d1.add(d2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * 判断是否是手机号码
	 */
	public static boolean isPhone(String obj) {
		if (obj == null) {
			return false;
		}
		Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
		Matcher m = p.matcher(obj);
		return m.matches();
	}

	/**
	 * @Title: isTelPhone
	 * @Description: 判断是否是固定电话号码
	 * @param obj
	 * @return
	 */
	public static boolean isTelPhone(String obj) {
		if (obj == null) {
			return false;
		}
		Pattern p = Pattern.compile("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$");
		Matcher m = p.matcher(obj);
		return m.matches();
	}

	/**
	 * @Title: isTelPhoneAndReturn
	 * @Description: 判断是否是固话，并且返回去除"-"的固话号码,如果不是固化，返回“”
	 * @param obj
	 * @return
	 */
	public static String isTelPhoneAndReturn(String obj) {
		if (isTelPhone(obj)) {
			return obj.replace("-", "");
		}
		return "";
	}

	/**
	 * 判断是否是Email
	 */
	public static boolean isEmail(String obj) {
		if (obj == null) {
			return false;
		}
		Pattern p = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
		Matcher m = p.matcher(obj);
		return m.matches();
	}

	/**
	 * 将多个不带引号的SQL参数转化为带引号的参数格式 例如: 123,456,789通过此方法转化为'123','456','789'
	 *
	 * @param str
	 * @return
	 */
	public static String transformationToInSql(String str) {
		if ("".equals(str)) {
			return "";
		}
		if (str.indexOf("'") != -1) {
			return str;
		}
		String[] strs = str.split(",");
		StringBuffer buffer = new StringBuffer();
		for (String string : strs) {
			if (StringUtil.isBlank(string)) {
				continue;
			}
			buffer.append("'").append(string).append("',");
		}
		return buffer.substring(0, buffer.length() - 1).toString();
	}

	/**
	 * 删除字符串最后一个字符
	 *
	 * @param str
	 * @return
	 */
	public static String deleteLastChar(String str) {
		if (str.length() > 0) {
			return str.substring(0, str.length() - 1);
		}
		return str;

	}

	/**
	 * 将参数字符串转化为JSON对象
	 *
	 * @param params
	 * @return
	 */
	public static JSONObject parseParamsToJSONObject(String params) {
		JSONObject runParams = new JSONObject();
		if (params == null || params.length() == 0) {
			return runParams;
		}
		String[] paramList = params.split("&");
		for (String param : paramList) {
			runParams.put(param.split("=")[0], param.split("=").length == 1 ? "" : param.split("=")[1]);
		}
		return runParams;
	}

	/**
	 * @Title: getJosnItem
	 * @Description: 把json对象转换成List
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> getJosnItem(String jsonStr) {
		if (null == jsonStr || jsonStr.trim().length() == 0) {
			return null;
		}
		List<Map> items = new ArrayList();
		items = JSON.parseArray(jsonStr, Map.class);

		return items;
	}

	/**
	 * @Title: deleteCharForSplit
	 * @Description: 删除某个拼接字符中的一个值
	 * @param handldChar
	 *            要处理的拼接字符串
	 * @param deleteChar
	 *            要删除的值
	 * @param splitChar
	 *            拼接符号
	 * @return
	 */
	public static String deleteCharForSplit(String handldChar, String deleteChar, String splitChar) {
		if (isBlank(handldChar) || isBlank(deleteChar) || isBlank(splitChar)) {
			return handldChar;
		}
		List<String> resultList = new ArrayList<String>();
		String[] handleCharArr = handldChar.split(splitChar);
		for (String temp : handleCharArr) {
			if (temp.equals(deleteChar)) {
				continue;
			}
			resultList.add(temp);
		}

		if (resultList.size() == 0) {
			return "";
		}

		// 把List中的值拼接成字符串
		String result = "";
		for (String temp : resultList) {
			result += temp + splitChar;
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * @Title: isBetween
	 * @Description: 判断一个字符串的长度在某个区间
	 * @param str
	 * @param compareLength1
	 *            区间长度1
	 * @param compareLength2
	 *            区间长度2
	 * @return
	 */
	public static boolean isBetween(String str, int compareLength1, int compareLength2) {
		if (null == str) {
			return false;
		}
		int strLength = str.length();
		if (strLength >= compareLength1 && strLength <= compareLength2) {
			return true;
		}
		return false;
	}

	/**
	 * 得到参数长度的星号*
	 *
	 * @param length
	 * @return
	 */
	private static String getStarMark(int length) {
		String valueString = "";
		for (int i = 0; i < length; i++) {
			valueString += "*";
		}
		return valueString;
	}

	/**
	 * 混淆名称：手机号码/邮箱号码 或者普通昵称
	 *
	 * @param
	 * @return
	 */
	public static String confusionName(Object nickName) {
		String newName = "";
		if (nickName != null && !nickName.toString().trim().equals("")) {
			String name = nickName.toString();
			// 混淆昵称
			if (isEmail(name)) {// 邮箱的处理
				int endIndex = name.indexOf('@');
				String temp = name.substring(0, endIndex);
				if (temp.length() < 5) {
					newName = temp.substring(0, 1) + getStarMark(temp.length() - 1) + name.substring(endIndex);
				} else {
					newName = temp.substring(0, 2) + getStarMark(temp.length() - 3) + temp.substring(temp.length() - 1) + name.substring(endIndex);
				}

			} else if (isPhone(name)) { // 手机号码的处理
				newName = name.substring(0, 3) + "****" + name.substring(7);
			} else {
				// 新昵称的处理
				if (name.length() > 2) {
					newName = name.substring(0, 1) + getStarMark(name.length() - 2) + name.substring(name.length() - 1);
				} else {
					newName = name.substring(0, 1) + "*";
				}

				// newName = name;//新昵称不处理
			}
		}
		return newName;
	}

	/**
	 * 判断字符串是否乱码
	 *
	 * @param strName
	 * @return true(乱码)
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否为中文
	 *
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断密码复杂度 (必须包含数字和字母,长度为8-20位)
	 *
	 * @param
	 * @return
	 */
	public static boolean isComplex(String passWard) {
		Pattern p = Pattern.compile("^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]{8,20}$");
		Matcher m = p.matcher(passWard);
		return m.matches();
	}

	/**
	 * 间隔拼接字符串
	 *
	 * @param strA
	 * @param strB
	 * @return
	 */
	public static String splitJoin(String strA, String strB) {
		StringBuffer data = new StringBuffer();
		for (int i = 0; i < strA.length(); i++) {
			data.append(strA.charAt(i));
			// 如果strB索引最大值大于等于当前索引值 直接拼接
			if (strB.length() - 1 >= i) {
				data.append(strB.charAt(i));
			}
			// 如果strB长度大于A并且是最后一次拼接则全部加上
			if (strB.length() > strA.length() && i == strA.length() - 1) {
				data.append(strB.substring(i + 1));
			}
		}
		return data.toString();
	}

	/**
	 * 解析混淆的菜单编号以及用户编号
	 *
	 * @param
	 * @param strB
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap analysis(String strA, String strB) {
		StringBuffer menuId = new StringBuffer();
		StringBuffer userId = new StringBuffer();
		while (strA.length() > 0) {
			menuId.append(strA.charAt(0));
			userId.append(strA.charAt(1));
			strA = strA.substring(2);
		}

		String[] strBsplitStr = strB.split("#");
		if (strBsplitStr.length < 3) {
			return null;
		}
		// 获取需要解析的字符串
		String needAnalysisStr = StringUtil.convertObject(strBsplitStr[0]);
		// 获取循环次数
		int loop = StringUtil.convertObjectToInt(strBsplitStr[1]);
		for (int i = 1; i <= loop; i++) {
			menuId.append(needAnalysisStr.charAt(0));
			userId.append(needAnalysisStr.charAt(1));
			needAnalysisStr = needAnalysisStr.substring(2);
		}
		if (needAnalysisStr.length() > 0) {
			userId.append(needAnalysisStr);
		}
		HashMap map = new HashMap();
		map.put("menuId", menuId.toString());
		map.put("userId", userId.toString());
		return map;
	}

	public static double formatNumber(double obj, int decimal) {
		String obj1 = new BigDecimal(obj).setScale(2 * decimal, BigDecimal.ROUND_HALF_UP).toString();
		double obj2 = new BigDecimal(obj1).setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
		return obj2;
	}
}