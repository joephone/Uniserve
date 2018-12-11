/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.transcendence.universe.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.transcendence.universe.service.MyApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串操作工具包<br>
 * <p>
 * <b>创建时间</b> 2014-8-14
 *
 * @author kymjs (https://github.com/kymjs)
 * @version 1.1
 */
public class StringUtils {
    static String tag = "StringUtils";
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    /**
     * 根据id 得到字符串
     */
    public static String getString(int id) {
        try {
            return MyApplication.getInstance().getString(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }




    public static String getGeohashCut(String geohash) {
        if (geohash.length() > 7) {
            return geohash.substring(0, 7);
        } else {
            return geohash;
        }

    }

    public static String getEditTextStr(EditText editText) {
        String str = editText.getText().toString().trim();
        str = StringUtils.replaceBlank(str);
        return str;
    }

    public static String getTextViewStr(TextView textView) {
        String str = textView.getText().toString().trim();
        str = StringUtils.replaceBlank(str);
        return str;
    }


    //
    // public static boolean isEmpty(EditText edit) {
    // return isEmpty(edit.getText());
    // }
    //
    public static boolean isEmptyAndFocus(EditText edit) {
        boolean isNull = isStringNull(edit.getText().toString());
        if (isNull) {
            edit.setFocusable(true);
            edit.setFocusableInTouchMode(true);
            edit.requestFocus();
        }
        return isNull;
    }


    public static boolean isEmpty(TextView textView) {
        return isStringNull(textView.getText().toString());
    }


    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(CharSequence email) {
        if (isStringNull(email.toString()))
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(CharSequence phoneNum) {
        if (isStringNull(phoneNum.toString()))
            return false;
        return phone.matcher(phoneNum).matches();
    }


    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * String转Float
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static Float toFloat(String obj) {
        try {
            return Float.parseFloat(obj);
        } catch (Exception e) {
        }
        return 0f;
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBoolean(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     *
     * @param data 要转换的字节数组。
     * @return 转换后的结果。
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return d;
    }

    public static  String toHtml(String str){
      return str.replaceAll(" ","%20");
    }
    /**
     * 判断字符串是否为空
     */
    public static boolean isStringNull(String string) {
        if (string == null || string.trim().equals("null") || string.trim().equals("NULL")) {
            return true;
        }
        if (string.trim() != null && string.trim().length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 替换空行 @param @param str @param @return @return
     * String @throws
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            dest = str.replaceAll("(\r?\n(\\s*\r?\n)+)", "\r\n");
        }
        return dest.trim();
    }

    public static String replaceBlankMobile(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    public static String getImageName(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    /**
     *
     * @Description: 替换多个空行 @param @param str @param @return @return
     *               String @throws
     */
    /*
	 * public static String topicRelaceBlank(String str) { String dest = ""; if
	 * (str != null) { Pattern p = Pattern.compile("\n\n"); Matcher m =
	 * p.matcher(str); dest = m.replaceAll("\n"); } return dest; }
	 */

    /**
     * 替换回车和跳格
     *
     * @param str
     * @return
     */
    public static String replaceEnterAndTab(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\r|\t");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest.trim();
    }

    /**
     * @Description: 字符串UTF8下长度 @param @param string @param @return @return
     * int @throws
     */
    public static int getStringLength(String string) {
        // Log.i(tag, "转换前字符串：" + string);
        // Log.i(tag, "转换前长度：" + string.length());
        // Pattern p = Pattern
        // .compile("[\ud800-\udbff][\udc00\udfff]");
        int length = 0;
        string = string.replaceAll("[\ud800\udc00-\udbff\udfff]", "测");
        // Log.i(tag, "替换表情：" + string);
        for (int i = 0; i < string.length(); i++) {
            String tempStr = string.charAt(i) + "";
            Log.i(tag, "i=" + i + "\t\t" + tempStr);
            int count = 1;
            try {
                count = new String(tempStr.getBytes("UTF-8"), "ISO8859_1").length();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (count > 1) {
                length = length + 2;
            } else {
                length++;
            }
        }
        // Log.i(tag, "转换后长度：" + length);
        return length;
    }

    public static String cutString(String string, int max) {
        String str = "";
        int length = 0;
        for (int i = 0; i < string.length(); i++) {
            String tempStr = string.charAt(i) + "";
            // Log.i(tag, "i=" + i + "\t\t" + tempStr);
            int count = 1;
            try {
                count = new String(tempStr.getBytes("UTF-8"), "ISO8859_1").length();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (count > 1) {
                length = length + 2;
            } else {
                length++;
            }
            if (length <= max) {
                str = str + tempStr;
            } else {
                return str;
            }
        }
        return str;
    }

    /**
     * @Description: 判断数组是否越界 @param @param array @param @param
     * pos @param @return @return boolean @throws
     */
    public static boolean isArrayIndexOutOfBounds(String[] array, int pos) {
        if (isArrayStringNull(array)) {
            if (array.length - 1 <= pos) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Description:判读字符数据是否为空 @param @param array @param @return @return
     * boolean @throws
     */
    public static boolean isArrayStringNull(String[] array) {
        if (array != null && array.length > 0) {
            return false;
        }
        return true;
    }

    public static boolean jsonNotEmpty(JSONArray response) {
        if (response != null && !isStringNull(response.toString())) {
            return true;
        }
        return false;
    }

    public static boolean jsonObjNotEmpty(JSONObject response) {
        if (response != null && !isStringNull(response.toString())) {
            return true;
        }
        return false;
    }

    // 判断字符串是否能转换为double
    public static boolean isdouble(String s) {
        try {
            double b = Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * 判断EditText是否为空
     */
    public static boolean isNull(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {
            return false;
        }
        return true;
    }

    /*
     * 判断TextView是否为空
     */
    public static boolean isNull(TextView editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {
            return false;
        }
        return true;
    }


    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static long getFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                blockSize = fis.available();
            } else {
                file.createNewFile();
                Log.e("获取文件大小", "文件不存在!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return blockSize;
    }

}
