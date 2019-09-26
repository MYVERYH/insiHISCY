package com.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.Statement;

public class DaoHelper {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	/**
	 * 把java对象中的单条数据放到 PrepareStatement值中
	 * 
	 * @throws SQLException
	 * 
	 */

	public static int setPsToSQLException(Connection con, Object obj, String sql) throws SQLException {
		boolean bool = false;
		int flag = 0;
		try {
			String[] cellNames = null;
			if (Pattern.matches("update.*", sql) || Pattern.matches("UPDATE.*", sql)
					|| Pattern.matches("Update.*", sql)) {
				ps = con.prepareStatement(sql);
				Matcher matcher = Pattern.compile("(set|SET)\\s+(\\S+?)($|;|\\s+.+)", Pattern.CASE_INSENSITIVE)
						.matcher(sql);
				StringBuffer buffer = new StringBuffer();
				String str = "";
				while (matcher.find()) {
					buffer.append(matcher.group(2));
					str = matcher.group(3);
				}
				str = str.replace(" WHERE ", ",");
				str = str.replace(" Where ", ",");
				str = str.replace(" where ", ",");
				buffer.append(str);
				cellNames = buffer.toString().split(",");// 通过逗号分割称字段数组
			} else if (Pattern.matches("insert.*", sql) || Pattern.matches("INSERT.*", sql)
					|| Pattern.matches("Insert.*", sql)) {
				bool = true;
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(sql);
				List<String> list = new ArrayList<String>();
				while (mat.find()) {
					list.add(mat.group());
				}
				cellNames = list.get(0).split(",");// 通过逗号分割称字段数组
			}
			Class<?> objClass = obj.getClass();
			synchronized (objClass) {
				for (int i = 0; i < cellNames.length; i++) {
					String cellName = "";
					if (cellNames[i].contains("=?")) {
						cellName = cellNames[i].replace("=?", "");
					} else if (cellNames[i].contains(" = ?")) {
						cellName = cellNames[i].replace(" = ?", "");
					} else {
						cellName = cellNames[i];
					}
					if (cellName != "" && !"".equals(cellName)) {
						// 获取字段名
						cellName = toJavaField(cellName);
						// 首字母大写
						String firstBig = cellName.substring(0, 1).toUpperCase() + cellName.substring(1);
						// 首字母小写
						String firstSmall = cellName.substring(0, 1).toLowerCase() + cellName.substring(1);
						// 获取字段数据类型
						Class<?> type = objClass.getDeclaredField(firstSmall).getType();
						if (type == null) {// 判断通过首字母小写的字段是否获取到数据类型
							type = objClass.getDeclaredField(firstBig).getType();
						}
						// ---获取getter方法
						Method getMethod = objClass.getMethod("get" + firstBig);
						String value = "";
						try {
							value = getMethod.invoke(obj).toString();
							if (type.isAssignableFrom(String.class)) {
								if (!"".equals(value) && !value.isEmpty()) {
									value = value.trim();
								}
								ps.setString(i + 1, value);
							} else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
								ps.setInt(i + 1, Integer.parseInt(value));
							} else if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
								ps.setDouble(i + 1, Double.parseDouble(value));
							} else if (type.isAssignableFrom(BigDecimal.class)) {
								BigDecimal bigDecimal = new BigDecimal(value);
								bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
								ps.setBigDecimal(i + 1, bigDecimal);
							} else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
								ps.setBoolean(i + 1, Boolean.parseBoolean(value));
							} else if (type.isAssignableFrom(java.util.Date.class)
									|| type.isAssignableFrom(Date.class)) {
								SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy",
										Locale.ENGLISH);
								java.util.Date date = dateFormat1.parse(value);
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								String strDate = dateFormat.format(date);
								ps.setDate(i + 1, new Date(dateFormat.parse(strDate).getTime()));
							} else if (type.isAssignableFrom(Timestamp.class)) {
								SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy",
										Locale.ENGLISH);
								java.util.Date date = dateFormat1.parse(value);
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String strDate = dateFormat.format(date);
								Timestamp timestamp = new Timestamp(dateFormat.parse(strDate).getTime());
								ps.setTimestamp(i + 1, timestamp);
							} else if (type.isAssignableFrom(byte[].class)) {
								byte[] bs = toByteArray(getMethod.invoke(obj));
								InputStream in = new ByteArrayInputStream(bs);
								ps.setBinaryStream(i + 1, in, in.available());
							}
						} catch (NullPointerException e) {
							ps.setString(i + 1, "");
						}
					}
				}
			}
			flag = ps.executeUpdate();
			if (bool) {
				rs = ps.getGeneratedKeys();
				if (rs != null) {
					while (rs.next()) {
						flag = Integer.parseInt(rs.getLong(1) + "");
					}
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static int setPreparedStatement(PreparedStatement ps, Object obj, String sql) {
		int flag = 0;
		try {
			String[] cellNames = null;
			if (Pattern.matches("update.*", sql) || Pattern.matches("UPDATE.*", sql)
					|| Pattern.matches("Update.*", sql)) {
				Matcher matcher = Pattern.compile("(set|SET)\\s+(\\S+?)($|;|\\s+.+)", Pattern.CASE_INSENSITIVE)
						.matcher(sql);
				StringBuffer buffer = new StringBuffer();
				String str = "";
				while (matcher.find()) {
					buffer.append(matcher.group(2));
					str = matcher.group(3);
				}
				str = str.replace(" WHERE ", ",");
				str = str.replace(" Where ", ",");
				str = str.replace(" where ", ",");
				buffer.append(str);
				cellNames = buffer.toString().split(",");
			} else {
				Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(sql);
				List<String> list = new ArrayList<String>();
				while (mat.find()) {
					list.add(mat.group());
				}
				cellNames = list.get(0).split(",");
			}
			Class<?> objClass = obj.getClass();
			synchronized (objClass) {
				// 反射获取所有字段
				Field[] fields = objClass.getDeclaredFields();
				for (int i = 0; i < cellNames.length; i++) {
					for (Field field : fields) {
						// 获取字段名
						String name = field.getName();
						// 获取字段数据类型
						Class<?> type = objClass.getDeclaredField(name).getType();
						// 首字母大写
						String firstBig = name.substring(0, 1).toUpperCase() + name.substring(1);
						// 首字母小写
						String firstSmall = name.substring(0, 1).toLowerCase() + name.substring(1);
						String cellName = cellNames[i].replace("=?", "");
						cellName = toJavaField(cellName);
						if (firstBig.equals(cellName) || firstSmall.equals(cellName)) {
							// ---获取getter方法
							Method getMethods = objClass.getMethod("get" + firstBig);
							String value = "";
							try {
								value = getMethods.invoke(obj).toString();
								if (type.isAssignableFrom(String.class)) {
									if (!"".equals(value) && !value.isEmpty()) {
										value = value.trim();
										ps.setString(i + 1, value);
									}
								} else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
									ps.setInt(i + 1, Integer.parseInt(value));
								} else if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
									ps.setDouble(i + 1, Double.parseDouble(value));
								} else if (type.isAssignableFrom(BigDecimal.class)) {
									BigDecimal bigDecimal = new BigDecimal(value);
									bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_DOWN);
									ps.setBigDecimal(i + 1, bigDecimal);
								} else if (type.isAssignableFrom(Boolean.class)
										|| type.isAssignableFrom(boolean.class)) {
									ps.setBoolean(i + 1, Boolean.parseBoolean(value));
								} else if (type.isAssignableFrom(java.util.Date.class)
										|| type.isAssignableFrom(Date.class)) {
									SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy",
											Locale.ENGLISH);
									java.util.Date date = dateFormat1.parse(value);
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									String strDate = dateFormat.format(date);
									ps.setDate(i + 1, new Date(dateFormat.parse(strDate).getTime()));
								} else if (type.isAssignableFrom(Timestamp.class)) {
									SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy",
											Locale.ENGLISH);
									java.util.Date date = dateFormat1.parse(value);
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String strDate = dateFormat.format(date);
									Timestamp timestamp = new Timestamp(dateFormat.parse(strDate).getTime());
									ps.setTimestamp(i + 1, timestamp);
								} else if (type.isAssignableFrom(byte[].class)) {
									byte[] bs = toByteArray(getMethods.invoke(obj));
									InputStream in = new ByteArrayInputStream(bs);
									ps.setBinaryStream(i + 1, in, in.available());
								}
							} catch (NullPointerException e) {
								ps.setString(i + 1, "");
							}
							break;
						}
					}
				}
			}
			flag = ps.executeUpdate();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	public static <T> T findByID(String sql, Class<T> obj, int id) {
		// 创建实例
		T instance = null;
		try {
			instance = obj.newInstance();
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			instance = JdbcHelper.getSingleResult(rs, obj);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;

	}

	public static long getTotalRow(String sql) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				intTotalRow = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return intTotalRow;
	}

	public static int insertUpdate(String sql, Object obj) {
		int flag = 0;
		boolean bool = false;
		try {
			con = DBUtil.getConnection();
			if (Pattern.matches("update.*", sql) || Pattern.matches("UPDATE.*", sql)
					|| Pattern.matches("Update.*", sql)) {
				ps = con.prepareStatement(sql);
			} else if (Pattern.matches("insert.*", sql) || Pattern.matches("INSERT.*", sql)
					|| Pattern.matches("Insert.*", sql)) {
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bool = true;
			} else {
				System.out.println("sql语句错误：" + sql);
				return 0;
			}
			flag = setPreparedStatement(ps, obj, sql);
			if (bool) {
				rs = ps.getGeneratedKeys();
				if (rs != null) {
					while (rs.next()) {
						flag = Integer.parseInt(rs.getLong(1) + "");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	public static int delete(String str, int id) {
		int flag = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, id);
			flag = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
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
				split[i] = string.substring(0, 1).toUpperCase() + string.substring(1);
				builder.append(split[i]);
			}
		}

		return builder.toString();
	}

}
