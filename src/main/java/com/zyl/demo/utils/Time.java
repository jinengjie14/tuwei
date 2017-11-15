package com.zyl.demo.utils;

import java.sql.Timestamp;
/**
 * 自动生成当前时间辅助类
 * @author Zyl
 *
 */
public class Time {
	public static Timestamp timestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}
