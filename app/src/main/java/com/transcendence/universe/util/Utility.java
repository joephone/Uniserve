package com.transcendence.universe.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static String tag = "Utility";
    public static final String ZH_CN = "zh_cn";
    public static final String ZH_TW = "zh_tw";
    public static final String JA = "ja";
    public static final String EN = "en";


    public static String getVideoTime(String url) {
        String[] ss = url.split("/");
        int len = 0;
        if (ss != null && ss.length > 1) {
            String tempS = ss[ss.length - 1];
            len = StringUtils.toInt(tempS.substring(tempS.indexOf("_") + 1, tempS.indexOf(".")));
        }
        return getTimeeStr(len);
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


    private static String getTimeeStr(int times) {
        String str = "";
        int minutes = times / 60;
        int seconds = times % 60;
        if (minutes < 10) {
            str = "0" + minutes;
        } else {
            str = minutes + "";
        }
        if (seconds < 10) {
            str = str + ":0" + seconds;
        } else {
            str = str + ":" + seconds;
        }
        return str;
    }

    /**
     * 显示隐藏VIP标志
     *
     * @param view
     * @param url  原始url
     */
    public static void showVip(ImageView view, String url) {
        Loger.i(tag, "显示隐藏VIP标志---:" + url);
        if (view == null) {
            return;
        }
        if (isVip(url)) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 判断是否是vip
     *
     * @param url
     * @return
     */
    public static boolean isVip(String url) {
        if (!StringUtils.isStringNull(url) && url.contains("_1_")) {
            String[] array = url.split("_1_");
            if (array == null) {
                array = url.split("_2_");
            }
            Loger.i(tag, "显示隐藏VIP标志---array:" + array.length);
            long time = System.currentTimeMillis();
            if (array.length >= 2 && StringUtils.toLong(array[1]) >= time) {
                return true;
            }

        }
        return false;
    }


    public static String getFileSize(long size) {
        float ret = size / 1024 / 1024.f;
        if (ret < 1) {
            return size / 1024 + "KB";
        } else {
            return keep(ret, 1) + "MB";
        }
    }

    public static float keep(float f, int digits) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(digits, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static Drawable getDrawable(Context context, int id) {
        return ResourcesCompat.getDrawable(context.getResources(), id, null);
    }

    /**
     * 文字所占地方
     *
     * @param paint
     * @param str
     * @return
     */
    public static Rect getRect(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    public static String formDate(String dateStr) {
        Date d;
        try {
            d = parseDate(dateStr);
            DateFormat df = DateFormat.getDateInstance();
            return df.format(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }

    // 将字符串转换成date类型方法
    public static Date parseDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(s);
    }

    public static String formMonth(String dateStr) {
        Date d;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            d = format.parse(dateStr);
            DateFormat df = DateFormat.getDateInstance(DateFormat.MONTH_FIELD);
            return df.format(d);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 生成文字画笔
     *
     * @param color
     * @param size
     * @return
     */
    public static Paint makeTextPaint(int color, float size) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);// 去锯齿
        paint.setColor(color);// 颜色
        paint.setTextSize(size);
        return paint;
    }


    public static Date toDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return new Date();
    }


    /**
     * 根据日期获得星期
     *
     * @param
     * @return
     */
//    public static int getWeekOfDate(String dateStr) {
//        Date date = toDate(dateStr);
//        int[] weekDaysName = {R.string.public_Week_Sunday, R.string.public_Week_Monday, R.string.public_Week_Tuesday, R.string.public_Week_Wednesday, R.string.public_Week_Thursday, R.string.public_Week_Friday,
//                R.string.public_Week_Saturday};
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
//        return weekDaysName[intWeek];
//    }


    // 获取当前时区
    public static float getTimeZoneD() {
        float mTimeZone = 0.f;
        // 格林尼治标准时间+0800
        TimeZone tz = TimeZone.getDefault();
        mTimeZone = tz.getOffset(new Date().getTime()) / 1000.f / 60.f / 60.f;
        Log.i(tag, "mTimeZone:" + mTimeZone);
        return mTimeZone;
    }

    // 获取当前时区
    public static String getTimeZoneString() {
        float mTimeZone = getTimeZoneD();
        String[] temp = (mTimeZone + "").split("\\.");
        String m = ":00";
        if (temp != null && temp.length > 1) {
            int a = (int) (StringUtils.toFloat(temp[1]) / 10f * 60f);
            if (a + "".length() <= 1) {
                m = ":0" + a;
            } else {
                m = ":" + a;
            }

        }
        return mTimeZone > 0 ? "+" + temp[0] + m : temp[0] + m;

    }

    /*
     * 将list转为String[]数组
     */
    public static String[] singString(List<String> list) {

        String[] toBeStored = list.toArray(new String[list.size()]);
        return toBeStored;
    }

    /*
     * List去重复
     */
    public static List<String> removeDuplicate(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /***
     * List<CountryCode> 调整 获取每个首之母字符的个数
     *
     * @return
     */
//    public static List<CountryCodeLetters> getListCountryCode(List<CountryCode> countryList) {
//        List<CountryCodeLetters> CountryCodeLetters = new ArrayList<CountryCodeLetters>();
//        for (int i = 0; i < SideBar.b.length; i++) {
//            CountryCodeLetters letters = new CountryCodeLetters();
//            letters.setSortLetters(SideBar.b[i]);
//            List<CountryCode> child = new ArrayList<CountryCode>();
//            for (int j = 0; j < countryList.size(); j++) {
//                if (letters.getSortLetters().equals(countryList.get(j).getSortLetters())) {
//                    child.add(countryList.get(j));
//                }
//            }
//            letters.setCountryList(child);
//            CountryCodeLetters.add(letters);
//        }
//        return CountryCodeLetters;
//    }

    /**
     * 字体放大缩小X倍
     *
     * @param paint 画笔
     * @param str   要绘制文字
     * @param ratio 比例
     * @param falg  true为高扩大 ，flase 宽扩大
     * @return
     */
    public static float textSize(Paint paint, String str, float ratio, boolean falg) {
        Rect rect4 = getRect(paint, str);
        int temp = (int) ((falg ? rect4.height() : rect4.width()) * ratio);
        boolean flag2 = true;
        if (ratio >= 1) {
            while (flag2) {
                paint.setTextSize(paint.getTextSize() + 1);
                Rect rect42 = getRect(paint, str);
                if ((falg ? rect42.height() : rect42.width()) > temp) {
                    flag2 = false;
                    return paint.getTextSize() - 1;
                }
            }
        } else {
            while (flag2) {
                paint.setTextSize(paint.getTextSize() - 1);
                Rect rect42 = getRect(paint, str);
                if ((falg ? rect42.height() : rect42.width()) < temp) {
                    flag2 = false;
                    return paint.getTextSize() + 1;
                }
            }
        }

        return paint.getTextSize();
    }

    /**
     * 根据“R.string.xx”的字符串找到对应R的ID
     *
     * @param context
     * @param str
     * @return
     */
    public static int getIdFromString(Context context, String str, String defType) {
        String pack = context.getPackageName();
        return context.getResources().getIdentifier(str, defType, pack);
    }

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }


    public static int getDrawableId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
    }


    /**
     * 保留小数
     *
     * @param f
     * @param digits
     * @return
     */
    public static float retainDecimal(double f, int digits) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(digits, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static String getDynamicCode(String str) {
        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while (m.find()) {
            if (m.group().length() == 6) {
                System.out.print(m.group());
                dynamicPassword = m.group();
            }
        }
        return dynamicPassword;
    }

    /*
     * 判断EditText是否为空
     */
    public static boolean isNull(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text != null && text.length() > 0) {
            return false;
        } else {
            if (editText.getHint() != null) {
                /*
                 * String string = editText.getHint().toString();
				 * ToastHelper.getInstance().showToast(string, 0);
				 */
            }
            editText.requestFocus();
        }
        return true;
    }

    /**
     * @Description: EditView 限制最多个数 @param @param str @param @param
     * max @param @param edit @return void @throws
     */
    public static int setEditLimitMax(TextView str, int max, EditText edit) {
        String temp = str.getText().toString();
        temp = StringUtils.replaceBlank(temp);
        Loger.i(tag, "temp:" + temp);
        temp = StringUtils.replaceEnterAndTab(temp);
        int leng = StringUtils.getStringLength(temp);
        int remain = max - leng;
        if (remain < 0) {
            edit.setText(StringUtils.cutString(temp, max));
            edit.setSelection(edit.getText().toString().length());
        }
        temp = str.getText().toString();
        leng = StringUtils.getStringLength(temp);
        remain = max - leng;
        return remain;
    }

    public static boolean isJaSetting() {
        String language = getLanguageEnv();
        if (language != null && (language.equals(Utility.JA)))
            return true;
        else
            return false;
    }

    public static String getLanguageEnv() {
        Locale l = Locale.getDefault();
        String language = l.getLanguage();
        String country = l.getCountry().toLowerCase();
        if ("zh".equals(language)) {
            if ("cn".equals(country)) {
                language = ZH_CN;
            } else {
                language = ZH_TW;
            }
        } else if ("ja".equals(language)) {
            language = JA;
        } else {
            language = EN;
        }
        return language;
    }

//    /**
//     * 根据1或0返回性别字符串
//     *
//     * @param i
//     * @return
//     */
//    public static String getSex(int i) {
//        String sex = "";
//        if (i == 1) {
//            sex = StringUtils.getString(R.string.MeProfile_Gender_Male);
//        } else if (i == 0) {
//            sex = StringUtils.getString(R.string.MeProfile_Gender_Female);
//        }
//        return sex;
//
//    }

    public static String getGoogleLocLanguage() {
        Locale l = Locale.getDefault();
        String language = l.getLanguage();
        String country = l.getCountry().toLowerCase();
        if ("zh".equals(language)) {
            if ("cn".equals(country)) {
                language = "zh-cn";
            } else {
                language = "zh-tw";
            }
        } else if ("ja".equals(language)) {
            language = JA;
        } else {
            language = EN;
        }
        return language;
    }

    public static int[] getDrawable(Context context, String[] array) {
        if (array != null && array.length > 0) {
            int Image[] = new int[array.length];
            for (int i = 0; i < array.length; i++) {

                int imgid = getDrawableId(context, array[i]);
                Image[i] = imgid;
            }
            return Image;
        }
        return null;
    }

    public static String[] getArrayStr(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

//    public static String getNameJson(Name name) {
////        try {
////            if (name != null) {
////                JSONObject obj = new JSONObject();
////                if (!StringUtils.isStringNull(name.getZh_Hans())) {
////                    obj.put("zh_hans", name.getZh_Hans());
////                }
////                if (!StringUtils.isStringNull(name.getEn())) {
////                    obj.put("en", name.getEn());
////                }
////
////                return obj.toString();
////            } else {
////                return "";
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
//        Gson gson = new Gson();
//        return gson.toJson(name);
//    }

//    public static String getInfoName(String nameStr) {
//        if (!StringUtils.isStringNull(nameStr)) {
////            Name name=new Name();
////            try {
////                JSONObject jsonObject = new JSONObject(nameStr);
////                name.setEn(jsonObject.getString("en"));
////                name.setZh_hans(jsonObject.getString("zh_hans"));
////            }catch (Exception e){
////                e.printStackTrace();
////            }
//            Loger.i(tag, "nameStr:--" + nameStr);
//            Name name = JsonParserHelper.getInstance().gsonName(nameStr);
//            Loger.i(tag, "name zh_hans:--" + name.getZh_Hans());
//            return getInfoName(name);
//        }
//        return "";
//
//    }

    /*
     * 菜单,类型等从库中查询出来再通过此方法显示某种语言
     */
//    public static String getInfoName(Name name) {
//        if (name == null) {
//            return "";
//        }
//        String language = LanguageUtil.getInstance().getConfigLanguage();
//        String nameString = name.getEn();
//
//        if (language.equalsIgnoreCase("zh_Hans")) {
//            if (!StringUtils.isStringNull(name.getZh_Hans())) {
//                nameString = name.getZh_Hans();
//            }
//        } else if (language.equalsIgnoreCase("zh_Hant")) {
//            if (!StringUtils.isStringNull(name.getZh_Hant())) {
//                nameString = name.getZh_Hant();
//            }
//        } else if (language.equalsIgnoreCase("en")) {
//            if (!StringUtils.isStringNull(name.getEn())) {
//                nameString = name.getZh_Hant();
//            }
//        } else if (language.equalsIgnoreCase("ja")) {
//            if (!StringUtils.isStringNull(name.getJa())) {
//                nameString = name.getJa();
//            }
//        }
//        return nameString;
//
//    }
//
//    public static Calendar getNow(String time) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        if (JudgeDate.isDate(time, "yyyy-MM-dd")) {
//            try {
//                calendar.setTime(dateFormat.parse(time));
//            } catch (ParseException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        return calendar;
//
//    }

    /*
     * 判断所选日期是否超过当前时间
     */
//    public static boolean isCorrectDate(WheelMain wheelMain) {
//        boolean flag = false;
//        Calendar now = Calendar.getInstance();
//        final int nowYear = now.get(Calendar.YEAR);
//        final int nowMonth = now.get(Calendar.MONTH);
//        final int nowDay = now.get(Calendar.DAY_OF_MONTH);
//        if (wheelMain.getYear() == nowYear
//                && wheelMain.getMonth() > nowMonth + 1) {
//            flag = false;
//        } else {
//            if (wheelMain.getYear() == nowYear
//                    && wheelMain.getMonth() == nowMonth + 1
//                    && wheelMain.getDay() > nowDay) {
//                flag = false;
//            } else {
//                flag = true;
//            }
//        }
//        return flag;
//    }

    public static String arrayParseString(String[] array) {
        String str = "";
        if (null != array) {
            for (int i = 0; i < array.length; i++) {

                if (i == array.length - 1) {
                    str = str + array[i];
                } else {
                    str = str + array[i] + ",";
                }

            }
        }
        return str;
    }



//    public static HashMap<String, String> putFishFly() {
//        List<FishResource> fishTypeArray = StringUtils.getFishResArray("stories_tag_1");
//        HashMap<String, String> map = new HashMap<String, String>();
//        for (FishResource mFishResource : fishTypeArray) {
//            map.put(mFishResource.getName(), mFishResource.getContent());
//        }
//        return map;
//    }

//    public static String getFishFly(String str) {
//        String language = LanguageUtil.getInstance().getConfigLanguage();
//        switch (language) {
//            case "zh_Hans":
//            case "zh_Hant":
//                HashMap<String, String> map = putFishFly();
//                if (TextUtils.isEmpty(map.get(str))) {
//                    return str;
//                }
//                return map.get(str);
//
//            case "en":
//            case "ja":
//                return str;
//
//        }
//        return str;
//
//    }

//    /**
//     * @param str
//     * @return
//     */
//    public static int getIsRelease(String str) {
//        int isRelease;
//        if (StringUtils.getString(R.string.NewCatchRecord_Release_Yes).equals(str)) {
//            isRelease = 1;
//        } else {
//            isRelease = 0;
//        }
//        return isRelease;
//
//    }

    public static String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static int stringToInt(String str) {
        if (!StringUtils.isStringNull(str)) {
            try {
                return Integer.valueOf(str).intValue();
            } catch (NumberFormatException e) {
                // TODO: handle exception
            }
        }
        return 0;
    }
}
