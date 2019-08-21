package com.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
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
	 */
	public static int setSinglePreparedStatement(PreparedStatement ps,
			Object obj) {
		int flag = 0;
		try {
			Class<?> class1 = obj.getClass();
			Field[] fields = class1.getDeclaredFields();
			String className = class1.getSimpleName() + "ID";
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if (name.equals("serialVersionUID")) {
					continue;
				}
				Class<?> type = class1.getDeclaredField(name).getType();
				// ---获取getter方法
				// 首字母大写
				String replace = name.substring(0, 1).toUpperCase()
						+ name.substring(1);
				Method getMethods = class1.getMethod("get" + replace);
				String str = "";
				try {
					str = getMethods.invoke(obj).toString();
					if (className.equals(replace)) {
						if (type.isAssignableFrom(int.class)
								|| type.isAssignableFrom(Integer.class)) {
							int j = fields.length - 1;
							ps.setInt(j, Integer.parseInt(str));
						}
						continue;
					}
				} catch (NullPointerException e) {
					continue;
				}
				int j = i - 1;
				// ---判断读取数据的类型
				if (type.isAssignableFrom(String.class)) {
					ps.setString(j, str);
				} else if (type.isAssignableFrom(int.class)
						|| type.isAssignableFrom(Integer.class)) {
					ps.setInt(j, Integer.parseInt(str));
				} else if (type.isAssignableFrom(Double.class)
						|| type.isAssignableFrom(double.class)) {
					ps.setDouble(j, Double.parseDouble(str));
				} else if (type.isAssignableFrom(Boolean.class)
						|| type.isAssignableFrom(boolean.class)) {
					ps.setBoolean(j, Boolean.parseBoolean(str));
				} else if (type.isAssignableFrom(Date.class)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					@SuppressWarnings("unused")
					Date date = new Date(dateFormat.parse(str).getTime());
					// ps.setDate(i, date);
				} else if (type.isAssignableFrom(Timestamp.class)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Timestamp timestamp = new Timestamp(dateFormat.parse(str)
							.getTime());
					ps.setTimestamp(i, timestamp);
				}

			}
			flag = ps.executeUpdate();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	public static int setPreparedStatement(PreparedStatement ps, Object obj,
			String sql) {
		int flag = 0;
		try {
			String[] cellNames = null;
			if (Pattern.matches("update.*", sql)
					|| Pattern.matches("UPDATE.*", sql)
					|| Pattern.matches("Update.*", sql)) {
				Matcher matcher = Pattern.compile(
						"(set|SET)\\s+(\\S+?)($|;|\\s+.+)",
						Pattern.CASE_INSENSITIVE).matcher(sql);
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
				Matcher mat = Pattern.compile("(?<=\\()(\\S+)(?=\\))").matcher(
						sql);
				List<String> list = new ArrayList<String>();
				while (mat.find()) {
					list.add(mat.group());
				}
				cellNames = list.get(0).split(",");
			}
			Class<?> class1 = obj.getClass();
			// 反射获取所有字段
			Field[] fields = class1.getDeclaredFields();
			for (int i = 0; i < cellNames.length; i++) {
				for (Field field : fields) {
					// 获取字段名
					String name = field.getName();
					// 获取字段数据类型
					Class<?> type = class1.getDeclaredField(name).getType();
					// 首字母大写
					String firstBig = name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					// 首字母小写
					String firstSmall = name.substring(0, 1).toLowerCase()
							+ name.substring(1);
					String cellName = cellNames[i].replace("=?", "");
					cellName = toJavaField(cellName);
					if (firstBig.equals(cellName)
							|| firstSmall.equals(cellName)) {
						// ---获取getter方法
						Method getMethods = class1.getMethod("get" + firstBig);
						String str = "";
						try {
							str = getMethods.invoke(obj).toString();
							if (type.isAssignableFrom(String.class)) {
								if (!"".equals(str) && !str.isEmpty()) {
									str = str.trim();
									ps.setString(i + 1, str);
								}
							} else if (type.isAssignableFrom(int.class)
									|| type.isAssignableFrom(Integer.class)) {
								ps.setInt(i + 1, Integer.parseInt(str));
							} else if (type.isAssignableFrom(Double.class)
									|| type.isAssignableFrom(double.class)) {
								ps.setDouble(i + 1, Double.parseDouble(str));
							} else if (type.isAssignableFrom(BigDecimal.class)) {
								BigDecimal bigDecimal = new BigDecimal(str);
								bigDecimal = bigDecimal.setScale(2,
										BigDecimal.ROUND_DOWN);
								ps.setBigDecimal(i + 1, bigDecimal);
							} else if (type.isAssignableFrom(Boolean.class)
									|| type.isAssignableFrom(boolean.class)) {
								ps.setBoolean(i + 1, Boolean.parseBoolean(str));
							} else if (type
									.isAssignableFrom(java.util.Date.class)
									|| type.isAssignableFrom(Date.class)) {
								@SuppressWarnings("deprecation")
								java.util.Date date = new java.util.Date(str);
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyy-MM-dd");
								String strDate = dateFormat.format(date);
								ps.setDate(i + 1,
										new Date(dateFormat.parse(strDate)
												.getTime()));
							} else if (type.isAssignableFrom(Timestamp.class)) {
								@SuppressWarnings("deprecation")
								java.util.Date date = new java.util.Date(str);
								SimpleDateFormat dateFormat = new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss");
								String strDate = dateFormat.format(date);
								Timestamp timestamp = new Timestamp(dateFormat
										.parse(strDate).getTime());
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

	public static int addEdit(String sql, Object obj) {
		int flag = 0;
		boolean bool = false;
		try {
			con = DBUtil.getConnection();
			if (Pattern.matches("update.*", sql)
					|| Pattern.matches("UPDATE.*", sql)
					|| Pattern.matches("Update.*", sql)) {
				ps = con.prepareStatement(sql);
			} else if (Pattern.matches("insert.*", sql)
					|| Pattern.matches("INSERT.*", sql)
					|| Pattern.matches("Insert.*", sql)) {
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bool = true;
			} else {
				return 0;
			}
			flag = setSinglePreparedStatement(ps, obj);
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

	public static int insertUpdate(String sql, Object obj) {
		int flag = 0;
		boolean bool = false;
		try {
			con = DBUtil.getConnection();
			if (Pattern.matches("update.*", sql)
					|| Pattern.matches("UPDATE.*", sql)
					|| Pattern.matches("Update.*", sql)) {
				ps = con.prepareStatement(sql);
			} else if (Pattern.matches("insert.*", sql)
					|| Pattern.matches("INSERT.*", sql)
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
				split[i] = string.substring(0, 1).toUpperCase()
						+ string.substring(1);
				builder.append(split[i]);
			}
		}

		return builder.toString();
	}

}
