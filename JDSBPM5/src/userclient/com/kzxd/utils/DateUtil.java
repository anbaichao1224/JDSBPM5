package com.kzxd.utils;

import java.util.Calendar;

public class DateUtil {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
	}
	/**
	 * 得到当前年
	 * @return
	 */
	public static String getYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		return String.valueOf(year);
	}
}
