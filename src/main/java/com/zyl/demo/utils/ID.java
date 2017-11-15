package com.zyl.demo.utils;

import java.util.UUID;
/**
 * 生成随机的32位id
 * @author Zyl
 *
 */
public class ID {
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
