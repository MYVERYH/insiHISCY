package com.web.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

/**
 * 把 ResultSet的结果放到 java对象中
 * 
 * @author en
 * @version 2017.7.11
 */
public class JdbcHelper {
	/**
	 * 把单条数据的ResultSet的结果放到 java对象中
	 * 
	 * @param rs
	 *            ResultSet结果集
	 * @param obj
	 *            java类的class
	 * @return
	 */
	public static <T> T getSingleResult(ResultSet rs, Class<T> obj) {
		// 创建实例
		T instance = null;
		try {
			synchronized (obj) {
				// ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
				ResultSetMetaData metaData = rs.getMetaData();
				// 获取总的列数
				int count = metaData.getColumnCount();
				// 遍历ResultSet
				while (rs.next()) {
					instance = obj.newInstance();
					// 遍历里面的列
					for (int i = 1; i <= count; i++) {
						// ---获取列名
						String name = metaData.getColumnName(i);
						// 改变列名格式成 java 命名格式 主要是针对 _ 分割的情况 如user_id
						name = toJavaField(name);
						// ---获取类型
						Class<?> type = null;
						try {
							type = obj.getDeclaredField(name).getType();
						} catch (NoSuchFieldException e) {
							try {
								name = name.substring(0, 1).toLowerCase()
										+ name.substring(1);
								type = obj.getDeclaredField(name).getType();
							} catch (NoSuchFieldException e2) {
								continue;
							}
						}
						// ---获取setter方法
						// 首字母大写
						String replace = name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						Method setMethod = obj.getMethod("set" + replace, type);

						// ---判断读取数据的类型
						if (type.isAssignableFrom(String.class)) {
							String str = rs.getString(i);
							if (str != null) {
								str = str.trim();
							}
							setMethod.invoke(instance, str);
						} else if (type.isAssignableFrom(int.class)
								|| type.isAssignableFrom(Integer.class)) {
							setMethod.invoke(instance, rs.getInt(i));
						} else if (type.isAssignableFrom(Double.class)
								|| type.isAssignableFrom(double.class)) {
							setMethod.invoke(instance, rs.getDouble(i));
						} else if (type.isAssignableFrom(Boolean.class)
								|| type.isAssignableFrom(boolean.class)) {
							setMethod.invoke(instance, rs.getBoolean(i));
						} else if (type.isAssignableFrom(BigDecimal.class)) {
							setMethod.invoke(instance, rs.getBigDecimal(i));
						} else if (type.isAssignableFrom(Date.class)) {
							Date date = new Date(rs.getDate(i).getTime());
							setMethod.invoke(instance, date);
						} else if (type.isAssignableFrom(Timestamp.class)) {
							setMethod.invoke(instance, rs.getTimestamp(i));
						} else if (type.isAssignableFrom(Blob.class)) {
							Blob b = new SerialBlob(rs.getBlob(i));
							setMethod.invoke(instance, b);
						} else if (type.isAssignableFrom(byte[].class)) {					
							setMethod.invoke(instance, rs.getBytes(i));
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return instance;
	}

	/**
	 * 把多条数据的ResultSet的结果放到 List<T>中
	 * 
	 * @param rs
	 *            ResultSet结果集
	 * @param obj
	 *            java类的class
	 * @return
	 */
	public static <T> List<T> getResult(ResultSet rs, Class<T> obj) {
		try {
			synchronized (obj) {
				List<T> list = new ArrayList<T>();
				// ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
				ResultSetMetaData metaData = rs.getMetaData();
				// 获取总的列数
				int count = metaData.getColumnCount();
				// 遍历ResultSet
				while (rs.next()) {
					// ---创建对象实例
					T instance = obj.newInstance();
					for (int i = 1; i <= count; i++) {
						// ---获取列名
						String name = metaData.getColumnName(i);
						// 改变列名格式成 java 命名格式 主要是针对 _ 分割的情况 如user_id
						name = toJavaField(name);
						// ---获取类型
						Class<?> type = null;
						try {
							type = obj.getDeclaredField(name).getType();
						} catch (NoSuchFieldException e) {
							name = name.substring(0, 1).toLowerCase()
									+ name.substring(1);
							try {
								type = obj.getDeclaredField(name).getType();
							} catch (NoSuchFieldException e2) {
								continue;
							}
						}
						// ---获取setter方法
						// 首字母大写
						String replace = name.substring(0, 1).toUpperCase()
								+ name.substring(1);
						Method setMethod = obj.getMethod("set" + replace, type);

						// ---判断读取数据的类型
						if (type.isAssignableFrom(String.class)) {
							String str = rs.getString(i);
							if (str != null) {
								str = str.trim();
							}
							setMethod.invoke(instance, str);
						} else if (type.isAssignableFrom(int.class)
								|| type.isAssignableFrom(Integer.class)) {
							setMethod.invoke(instance, rs.getInt(i));
						} else if (type.isAssignableFrom(Double.class)
								|| type.isAssignableFrom(double.class)) {
							setMethod.invoke(instance, rs.getDouble(i));
						} else if (type.isAssignableFrom(BigDecimal.class)) {
							setMethod.invoke(instance, rs.getBigDecimal(i));
						} else if (type.isAssignableFrom(Boolean.class)
								|| type.isAssignableFrom(boolean.class)) {
							setMethod.invoke(instance, rs.getBoolean(i));
						} else if (type.isAssignableFrom(Date.class)) {
							Date date = new Date(rs.getDate(i).getTime());
							setMethod.invoke(instance, date);
						} else if (type.isAssignableFrom(Timestamp.class)) {
							setMethod.invoke(instance, rs.getTimestamp(i));
						} else if (type.isAssignableFrom(Blob.class)) {
							Blob b = new SerialBlob(rs.getBlob(i));							
							setMethod.invoke(instance, b);
						} else if (type.isAssignableFrom(byte[].class)) {					
							setMethod.invoke(instance, rs.getBytes(i));
						}
					}
					list.add(instance);
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据库命名格式转java命名格式
	 * 
	 * @param str
	 *            数据库字段名
	 * @return java字段名
	 */
	public static String toJavaField(String str) {
		// 主要是针对 _ 分割的情况 如user_id
		if (str.indexOf('_') > -1) {
			str = str.toLowerCase();
		}
		String[] split = str.split("_");
		StringBuilder builder = new StringBuilder();
		builder.append(split[0]);// 拼接第一个字符

		// 如果数组不止一个单词
		if (split.length > 1) {
			for (int i = 1; i < split.length; i++) {
				String string = split[i];
				// 去掉下划线，首字母变为大写
				split[i] = string.substring(0, 1).toUpperCase()
						+ string.substring(1);
				builder.append(split[i]);
			}
		}

		return builder.toString();
	}

	/**
	 * Blob类型转byte[]
	 * 
	 * @param blob
	 * @return
	 */
	public static byte[] blobToBytes(Blob blob) {
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len
					&& (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}
	}

}
