package com.web.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.vo.OrderInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class ThisUtil {

	private static String findOrders1 = "SELECT p.*,b.`warehouseId`,b.`bills_entry_time`,b.`bills_num`,"
			+ "s.`supplierName`,w.`warehouseName` FROM ";
	private static String findOrders2 = " JOIN `pw_bills` b ON p.`bills_id` = b.`bills_id` JOIN `pw_supplier` s "
			+ "ON p.`supplierId` = s.`supplierId` JOIN `pw_warehouse` w "
			+ "ON b.`warehouseId` = w.`warehouseId` ";
	private static String findOrders3 = "SELECT p.*,b.`warehouseId`,b.`bills_entry_time`,b.`bills_num`,"
			+ "d.`departmentName`,w.`warehouseName` FROM ";
	private static String findOrders4 = " JOIN `pw_bills` b ON p.`bills_id` = b.`bills_id` JOIN `pw_department` d "
			+ "ON p.`departmentId` = d.`departmentId` JOIN `pw_warehouse` w "
			+ "ON b.`warehouseId` = w.`warehouseId` ";
	private static String getOrdersRow = " JOIN `pw_bills` b "
			+ "ON p.`bills_id` = b.`bills_id` JOIN `pw_supplier` s ON p.`supplierId` = s.`supplierId` "
			+ "JOIN `pw_warehouse` w ON b.`warehouseId` = w.`warehouseId` ";
	private static String getOrdersRows = " JOIN `pw_bills` b "
			+ "ON p.`bills_id` = b.`bills_id` JOIN `pw_department` d ON p.`departmentId` = d.`departmentId` "
			+ "JOIN `pw_warehouse` w ON b.`warehouseId` = w.`warehouseId` ";
	private static String findOrdersMaterial1 = "SELECT r.*,u.`unitName`,d.`raw_material_amount` FROM ";
	private static String findOrdersMaterial2 = " JOIN `pw_bills_detail` d ON p.`bills_id` = d.`bills_id` "
			+ "JOIN `pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` WHERE ";
	private static String getDJMaterialRow1 = "SELECT COUNT(*) FROM ";
	private static String getDJMaterialRow2 = " JOIN `pw_bills_detail` d ON p.`bills_id` = d.`bills_id` "
			+ "JOIN `pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` WHERE ";

	public static List<OrderInfo> findOrder(String tabName, String orderById, Page page) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderInfo> infos = new ArrayList<OrderInfo>();
		try {
			StringBuilder builder = new StringBuilder(findOrders1);
			builder.append("`" + tabName + "` p");
			builder.append(findOrders2);
			builder.append("ORDER BY p.`" + orderById + "` DESC LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			infos = JdbcHelper.getResult(rs, OrderInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	public static long getOrderRow(String tabName) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(*) FROM `"
				+ tabName + "` p");
		builder.append(getOrdersRow);
		long totalRow = DaoHelper.getTotalRow(builder.toString());
		return totalRow;
	}
	
	public static List<OrderInfo> findOrders(String tabName, String orderById, Page page) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderInfo> infos = new ArrayList<OrderInfo>();
		try {
			StringBuilder builder = new StringBuilder(findOrders3);
			builder.append("`" + tabName + "` p");
			builder.append(findOrders4);
			builder.append("ORDER BY p.`" + orderById + "` DESC LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			infos = JdbcHelper.getResult(rs, OrderInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	public static long getOrderRows(String tabName) {
		StringBuilder builder = new StringBuilder("SELECT COUNT(*) FROM `"
				+ tabName + "` p");
		builder.append(getOrdersRows);
		long totalRow = DaoHelper.getTotalRow(builder.toString());
		return totalRow;
	}

	public static List<RawMaterialInfo> findOrderMaterial(String tabName,
			String orderById, int id, Page page) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			StringBuilder builder = new StringBuilder(findOrdersMaterial1);
			builder.append("`" + tabName + "` p");
			builder.append(findOrdersMaterial2);
			builder.append("p.`" + orderById
					+ "`=? ORDER BY r.raw_material_id LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	public static long getOrderMaterialRow(String tabName, String orderById, int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long totalRow = 0;
		try {
			StringBuilder builder = new StringBuilder(getDJMaterialRow1);
			builder.append(tabName + " p");
			builder.append(getDJMaterialRow2);
			builder.append("p." + orderById + "=?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				totalRow = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return totalRow;
	}
}
