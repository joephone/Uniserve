package com.transcendence.universe.utils.algorithm;

/**
 * 
 * @Description  转换工具类
 * @author  liufeng@solot.com
 * @date 2014年12月16日
 */
public class UtilityConversion {

    /** 
     * 将62进制转换成10进制数 
     *  
     * @param ident62 
     * @return 
     */  
    public static String convertBase62ToDecimal( String ident62 ) {  
        int decimal = 0;  
        int base = 62;  
        int keisu = 0;  
        int cnt = 0;  
  
        byte ident[] = ident62.getBytes();  
        for ( int i = ident.length - 1; i >= 0; i-- ) {  
            int num = 0;  
            if ( ident[i] > 48 && ident[i] <= 57 ) {  
                num = ident[i] - 48;  
            }  
            else if ( ident[i] >= 65 && ident[i] <= 90 ) {  
                num = ident[i] - 65 + 10;  
            }  
            else if ( ident[i] >= 97 && ident[i] <= 122 ) {  
                num = ident[i] - 97 + 10 + 26;  
            }  
            keisu = (int) Math.pow( (double) base, (double) cnt );
            decimal += num * keisu;  
            cnt++;  
        }  
        return String.format( "%08d", decimal );  
    }  

	/**
	 * 显示 东西经 南北纬 度分秒
	 * 
	 * @param lat
	 * @param lng
	 * @return
	 */
	public static String getLatLngString2(double lat, double lng) {
		String latString = lat > 0 ? "N " + formatLatLng(lat) : "S "
				+ formatLatLng(Math.abs(lat));
		String lngString = lng > 0 ? "E " + formatLatLng(lng) : "W "
				+ formatLatLng(Math.abs(lng));
		return latString + " " + lngString;
	}
	public static String getLatLngString3(double lat, double lng) {
		String latString = lat > 0 ? "N:" + formatLatLng(lat) : "S:"
				+ formatLatLng(Math.abs(lat));
		String lngString = lng > 0 ? "E:" + formatLatLng(lng) : "W:"
				+ formatLatLng(Math.abs(lng));
		return latString + "  " + lngString;
	}
	private static String formatLatLng(double loc) {
		double total = loc * 3600;
		int d = (int) (total / 3600);
		double tmp = total % 3600;
		int m = (int) (tmp / 60);
		tmp = tmp % 60;
		int s = (int) tmp;
		return d + "°" + m + "′" + s + "″";
	}

	/**
	 * 米转英尺
	 * 
	 * @param num
	 * @return
	 */
	public static double MtoFt(double num) {
		num = num * 3.2808398950131;
		return num;
	}
	/**
	 * 摄氏转华氏
	 * 
	 * @return
	 */
	public static double CtoF(double num) {
		num = num * 9 / 5 + 32;
		return num;
	}

	public static double rad(double d) {
		return d * Math.PI / 180.0d;
	}
}
