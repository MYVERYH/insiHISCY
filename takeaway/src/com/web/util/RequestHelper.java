package com.web.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	/**
	 * 把request请求的数据放到java对象中
	 */
	public static <T> T getSingleRequest(HttpServletRequest request, Class<T> obj) {
		// 创建实例
		T instance = null;
		try {
			synchronized (obj) {
				Field[] fields = obj.getDeclaredFields();
				instance = obj.newInstance();
				for (int i = 0; i < fields.length; i++) {
					String name = fields[i].getName();
					if (name.equals("serialVersionUID")) {
						continue;
					}
					Class<?> type = obj.getDeclaredField(name).getType();
					// ---获取settter方法
					// 首字母大写
					String replace = name.substring(0, 1).toUpperCase() + name.substring(1);
					Method setMethod = null;
					try {
						setMethod = obj.getMethod("set" + replace, type);
					} catch (NoSuchMethodException e) {
						continue;
					}
					String str = request.getParameter(replace);
					if (str == null || "".equals(str)) {
						// 首字母小写
						String small = name.substring(0, 1).toLowerCase() + name.substring(1);
						str = request.getParameter(small);
					}
					if (str != null && !"".equals(str)) {
						if (!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(str))) {
							str = getNewString(str.trim());
						}
						// ---判断读取数据的类型
						if (type.isAssignableFrom(String.class)) {
							setMethod.invoke(instance, str);
						} else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
							setMethod.invoke(instance, Integer.parseInt(str));
						} else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
							setMethod.invoke(instance, Short.parseShort(str));
						} else if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
							setMethod.invoke(instance, Double.parseDouble(str));
						} else if (type.isAssignableFrom(BigDecimal.class)) {
							BigDecimal bigDecimal = new BigDecimal(str);
							bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
							setMethod.invoke(instance, bigDecimal);
						} else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
							setMethod.invoke(instance, Boolean.parseBoolean(str));
						} else if (type.isAssignableFrom(Date.class)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							setMethod.invoke(instance, dateFormat.parse(str));
						} else if (type.isAssignableFrom(Timestamp.class)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							setMethod.invoke(instance, new Timestamp(dateFormat.parse(str).getTime()));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return instance;
	}

	// 封装成工具类
	public static String getNewString(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
	}

	public static byte[] inputStreamToByte(InputStream iStrm) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = iStrm.read()) != -1) {
			bytestream.write(ch);
		}
		byte[] imgdata = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	/**
	 * 判断字符是否是中文
	 * 
	 * @param c
	 *            字符
	 * @return 是否是中文
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

}
