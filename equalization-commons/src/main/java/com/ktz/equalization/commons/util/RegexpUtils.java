package com.ktz.equalization.commons.util;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午5:22
 */
public class RegexpUtils {
    /**
     * 验证手机号正则表达式
     */
//   旧正则表达式 ^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$
//   新 /^1[3-9]\d{9}$/
    public static final String PHONE = "^1[3-9]\\d{9}$";

    /**
     * 验证邮箱地址正则表达式
     */
    public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

    /**
     * 验证手机号是否符合规则
     * @param phone 手机号
     * @return 符合规则返回 true，反正返回 false
     */
    public static Boolean checkPhone(String phone) {
        return phone.matches(PHONE);
    }

    /**
     * 验证邮箱是否符合规则
     * @param email 邮箱地址
     * @return 符合规则返回 true，反之返回 false
     */
    public static Boolean checkEmail(String email) {
        return email.matches(EMAIL);
    }
}
