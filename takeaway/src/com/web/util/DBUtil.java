package com.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.web.vo.LayuiJSON;

public class DBUtil {
	private static String username = null;
	private static String password = null;
	private static String Driver = null;
	private static String url = null;

	static {
		Properties properties = new Properties();
		InputStream in = DBUtil.class.getClassLoader().getResourceAsStream(
				"jdbc.properties");
		try {
			properties.load(in);
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			Driver = properties.getProperty("driver");
			url = properties.getProperty("url");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(Driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (rs != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public static <T> LayuiJSON<T> layuiReturn(String str, Long totalRow,
			List<T> t) {
		LayuiJSON<T> layuiJSON = new LayuiJSON<T>();
		layuiJSON.setCode(0);
		layuiJSON.setMsg(str);
		layuiJSON.setCount(totalRow);
		layuiJSON.setData(t);
		return layuiJSON;
	}

	public static void jsonObjectReturn(HttpServletResponse response,
			Object object) throws IOException {
		JSONObject jsonObject = JSONObject.fromObject(object);
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();
	}

	public static void jsonArrayReturn(HttpServletResponse response,
			Object object) throws IOException {
		JSONArray jsonArray = JSONArray.fromObject(object);
		PrintWriter out = response.getWriter();
		out.write(jsonArray.toString());
		out.flush();
		out.close();
	}	

}
