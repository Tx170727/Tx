package com.tx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumCheck {

    /**
     * 手机号验证
     * @param phoneNum
     * @return
     */

    public static boolean CheckMobilePhoneNum(String phoneNum) {
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNum);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }


}
