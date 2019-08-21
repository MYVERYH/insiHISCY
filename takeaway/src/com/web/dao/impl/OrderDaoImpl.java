package com.web.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IOrderDao;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.Check;
import com.web.po.MaterialRequirement;
import com.web.po.Order;
import com.web.po.Repertory;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.util.ThisUtil;
import com.web.vo.OrderInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class OrderDaoImpl implements IOrderDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String findMaterial = "SELECT r.*,s.raw_material_big_id,b.raw_material_big_name,u.unitName,"
			+ "d.`raw_material_amount` FROM `pw_purchase_orders` p JOIN `pw_bills_detail` d "
			+ "ON p.`bills_id` = d.`bills_id` JOIN pw_raw_material r ON d.`raw_material_id` = "
			+ "r.`raw_material_id` JOIN sys_raw_material_small s ON r.raw_material_small_id = "
			+ "s.raw_material_small_id JOIN sys_raw_material_big b ON s.raw_material_big_id = "
			+ "b.raw_material_big_id JOIN sys_unit u ON r.unitId = u.unitId "
			+ "WHERE p.`purchase_orders_id` = ? LIMIT ?,?";
	private String getMaterialRow = "SELECT COUNT(*) FROM `pw_purchase_orders` p JOIN `pw_bills_detail` d "
			+ "ON p.`bills_id` = d.`bills_id` JOIN pw_raw_material r ON d.`raw_material_id` = "
			+ "r.`raw_material_id` JOIN sys_raw_material_small s ON r.raw_material_small_id = "
			+ "s.raw_material_small_id JOIN sys_raw_material_big b ON s.raw_material_big_id = "
			+ "b.raw_material_big_id JOIN sys_unit u ON r.unitId = u.unitId WHERE p.`purchase_orders_id` = ?";
	private String insertOrders = "(bills_id,supplierId,staffId) VALUES(?,?,?)";
	private String insertOrder = "(bills_id,departmentId,staffId) VALUES(?,?,?)";
	private String getRepertoryAmount = "SELECT r.`raw_material_quantity` FROM `pw_repertory` r "
			+ "WHERE r.`warehouseId` = ? AND r.`raw_material_id` = ?  ";
	private String findRequirement = "SELECT * FROM pw_material_requirement WHERE departmentId=? "
			+ "AND raw_material_id=?";
	private String updateRequirement = "UPDATE pw_material_requirement SET departmentId=?,raw_material_id=?,"
			+ "quantity_required=? WHERE material_requirement_id=?";
	private String delRequirement = "DELETE FROM pw_material_requirement WHERE material_requirement_id=?";
	private String insertRequirement = "INSERT INTO pw_material_requirement(departmentId,raw_material_id,"
			+ "quantity_required) VALUES(?,?,?)";
	private String findRepertory = "SELECT r.*,b.`raw_material_big_name`,u.`unitName` FROM `pw_repertory` "
			+ "p JOIN `pw_raw_material` r ON p.`raw_material_id` = r.`raw_material_id` "
			+ "JOIN `sys_raw_material_small` s ON r.`raw_material_small_id` = s.`raw_material_small_id` "
			+ "JOIN `sys_raw_material_big` b ON s.`raw_material_big_id` = b.`raw_material_big_id` "
			+ "JOIN `sys_unit` u ON r.`unitId` = u.`unitId` WHERE p.`warehouseId`=? LIMIT ?,?";
	private String getCKMaterialRow = "SELECT COUNT(*) FROM `pw_repertory` "
			+ "p JOIN `pw_raw_material` r ON p.`raw_material_id` = r.`raw_material_id` "
			+ "JOIN `sys_raw_material_small` s ON r.`raw_material_small_id` = s.`raw_material_small_id` "
			+ "JOIN `sys_raw_material_big` b ON s.`raw_material_big_id` = b.`raw_material_big_id` "
			+ "JOIN `sys_unit` u ON r.`unitId` = u.`unitId` WHERE p.`warehouseId`=?";
	private String findRepertorys = "SELECT * FROM pw_repertory WHERE warehouseId=? AND raw_material_id=?";
	private String insertRepertory = "INSERT INTO pw_repertory(warehouseId,raw_material_id,"
			+ "raw_material_quantity,total_price) VALUES(?,?,?,?)";
	private String updateRepertory = "UPDATE pw_repertory SET warehouseId=?,raw_material_id=?,"
			+ "raw_material_quantity=?,total_price=? WHERE repertory_id=?";
	private String findCheck = "SELECT c.*,r.`raw_material_num`,r.`raw_material_name`,r.`raw_material_price`,"
			+ "u.`unitName` FROM `pw_check` c JOIN `pw_raw_material` r ON c.`raw_material_id` = "
			+ "r.`raw_material_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId` "
			+ "WHERE c.`warehouseId`=? LIMIT ?,?";
	private String getCheckRow = "SELECT COUNT(*) FROM `pw_check` c JOIN `pw_raw_material` r "
			+ "ON c.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId` "
			+ "WHERE c.`warehouseId`=?";

	@Override
	public List<Order> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Order t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Order t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPurchaseOrders(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_purchase_orders");
		builder.append(insertOrders);
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public List<OrderInfo> findPurchase(Page page) {
		List<OrderInfo> infos = ThisUtil.findOrder("pw_purchase_orders",
				"purchase_orders_id", page);
		return infos;
	}

	@Override
	public long getPurchaseRow() {
		long totalRows = ThisUtil.getOrderRow("pw_purchase_orders");
		return totalRows;
	}

	@Override
	public List<RawMaterialInfo> findMaterial(Page page, int id) {
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findMaterial);
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

	@Override
	public long getMaterialRow(int id) {
		long totalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(getMaterialRow);
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

	@Override
	public int insertDispatchingOrders(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_dispatching_orders");
		builder.append(insertOrders);
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public List<OrderInfo> findDispatching(Page page) {
		List<OrderInfo> infos = ThisUtil.findOrder("pw_dispatching_orders",
				"dispatching_orders_id", page);
		return infos;
	}

	@Override
	public long getDispatchingRow() {
		long totalRow = ThisUtil.getOrderRow("pw_dispatching_orders");
		return totalRow;
	}

	@Override
	public List<RawMaterialInfo> findPSMaterial(Page page, int id) {
		List<RawMaterialInfo> infos = ThisUtil.findOrderMaterial(
				"pw_dispatching_orders", "dispatching_orders_id", id, page);
		return infos;
	}

	@Override
	public long getPSMaterialRow(int id) {
		long totalRow = ThisUtil.getOrderMaterialRow("pw_dispatching_orders",
				"dispatching_orders_id", id);
		return totalRow;
	}

	@Override
	public double getRepertoryAmount(int warehouseId, int rawMaterialId) {
		double repertoryAmount = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(getRepertoryAmount);
			ps.setInt(1, warehouseId);
			ps.setInt(2, rawMaterialId);
			rs = ps.executeQuery();
			while (rs.next()) {
				repertoryAmount = rs.getDouble("raw_material_quantity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return repertoryAmount;
	}

	@Override
	public int insertGodownOrders(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_godown_orders");
		builder.append(insertOrders);
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public List<OrderInfo> findGodownOrders(Page page) {
		List<OrderInfo> infos = ThisUtil.findOrder("pw_godown_orders",
				"godown_orders_id", page);
		return infos;
	}

	@Override
	public long getGodownOrdersRow() {
		long totalRow = ThisUtil.getOrderRow("pw_godown_orders");
		return totalRow;
	}

	@Override
	public List<RawMaterialInfo> findRKMaterial(Page page, int id) {
		List<RawMaterialInfo> infos = ThisUtil.findOrderMaterial(
				"pw_godown_orders", "godown_orders_id", id, page);
		return infos;
	}

	@Override
	public long getRKMaterialRow(int id) {
		long totalRow = ThisUtil.getOrderMaterialRow("pw_godown_orders",
				"godown_orders_id", id);
		return totalRow;
	}

	@Override
	public int insertCreditOrders(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_credit_orders");
		builder.append(insertOrders);
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public int insertStocksRequisition(Order order,
			List<BillsDetail> billsDetails) {
		int flag = 0;
		try {
			StringBuilder builder = new StringBuilder(
					"INSERT INTO pw_stocks_requisition");
			builder.append(insertOrder);
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, order.getBillsId());
			ps.setInt(2, order.getDepartmentId());
			ps.setInt(3, order.getStaffId());
			flag = ps.executeUpdate();
			if (flag > 0) {
				for (BillsDetail billsDetail : billsDetails) {
					flag = 0;
					ps = con.prepareStatement(findRequirement);
					ps.setInt(1, order.getDepartmentId());
					ps.setInt(2, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					MaterialRequirement requirement = JdbcHelper
							.getSingleResult(rs, MaterialRequirement.class);
					if (requirement != null) {
						double count = requirement.getQuantityRequired()
								- billsDetail.getRawMaterialAmount();
						if (count > 0) {
							ps = con.prepareStatement(updateRequirement);
							ps.setInt(1, order.getDepartmentId());
							ps.setInt(2, billsDetail.getRawMaterialId());
							ps.setDouble(3, count);
							ps.setInt(4, requirement.getMaterialRequirementId());
						} else {
							ps = con.prepareStatement(delRequirement);
							ps.setInt(1, requirement.getMaterialRequirementId());
						}
						flag = ps.executeUpdate();
					}
				}
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public List<OrderInfo> findStocksRequisition(Page page) {
		List<OrderInfo> infos = ThisUtil.findOrders("pw_stocks_requisition",
				"stocks_requisition_id", page);
		return infos;
	}

	@Override
	public long getStocksRequisitionRow() {
		long totalRow = ThisUtil.getOrderRows("pw_stocks_requisition");
		return totalRow;
	}

	@Override
	public List<RawMaterialInfo> findLLMaterial(Page page, int id) {
		List<RawMaterialInfo> infos = ThisUtil.findOrderMaterial(
				"pw_stocks_requisition", "stocks_requisition_id", id, page);
		return infos;
	}

	@Override
	public long getLLMaterialRow(int id) {
		long totalRow = ThisUtil.getOrderMaterialRow("pw_stocks_requisition",
				"stocks_requisition_id", id);
		return totalRow;
	}

	@Override
	public int insertPickingCreditOrders(Order order,
			List<BillsDetail> billsDetails) {
		int flag = 0;
		try {
			StringBuilder builder = new StringBuilder(
					"INSERT INTO pw_picking_credit_orders");
			builder.append(insertOrder);
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, order.getBillsId());
			ps.setInt(2, order.getDepartmentId());
			ps.setInt(3, order.getStaffId());
			flag = ps.executeUpdate();
			if (flag > 0) {
				for (BillsDetail billsDetail : billsDetails) {
					flag = 0;
					ps = con.prepareStatement(findRequirement);
					ps.setInt(1, order.getDepartmentId());
					ps.setInt(2, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					MaterialRequirement requirement = JdbcHelper
							.getSingleResult(rs, MaterialRequirement.class);
					if (requirement != null) {
						double count = requirement.getQuantityRequired()
								+ billsDetail.getRawMaterialAmount();
						ps = con.prepareStatement(updateRequirement);
						ps.setInt(1, order.getDepartmentId());
						ps.setInt(2, billsDetail.getRawMaterialId());
						ps.setDouble(3, count);
						ps.setInt(4, requirement.getMaterialRequirementId());
					} else {
						ps = con.prepareStatement(insertRequirement);
						ps.setInt(1, order.getDepartmentId());
						ps.setInt(2, billsDetail.getRawMaterialId());
						ps.setDouble(3, billsDetail.getRawMaterialAmount());
					}
					flag = ps.executeUpdate();
				}
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public List<RawMaterialInfo> findRepertory(Page page, int id) {
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findRepertory);
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

	@Override
	public long getCKMaterialRow(int id) {
		long totalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(getCKMaterialRow);
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

	@Override
	public int insertWarehouseTransferOrder(Order order, Bills bills,
			List<BillsDetail> billsDetails) {
		int flag = 0;
		String findSingle = "SELECT `raw_material_price` FROM `pw_raw_material` WHERE `raw_material_id`=?";
		try {
			StringBuilder builder = new StringBuilder(
					"INSERT INTO pw_warehouse_transfer_order(bills_id,warehouseId,staffId) VALUES(?,?,?)");
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, order.getBillsId());
			ps.setInt(2, order.getWarehouseId());
			ps.setInt(3, order.getStaffId());
			flag = ps.executeUpdate();
			if (flag > 0) {
				for (BillsDetail billsDetail : billsDetails) {
					flag = 0;
					ps = con.prepareStatement(findRepertorys);
					ps.setInt(1, bills.getWarehouseId());
					ps.setInt(2, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					Repertory repertory = JdbcHelper.getSingleResult(rs,
							Repertory.class);
					ps = con.prepareStatement(findSingle);
					ps.setInt(1, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					BigDecimal bigDecimal = null;
					while (rs.next()) {
						bigDecimal = rs.getBigDecimal("raw_material_price");
					}
					if (repertory != null) {
						double count = repertory.getRawMaterialQuantity()
								+ billsDetail.getRawMaterialAmount();
						BigDecimal decimal = new BigDecimal(count);
						ps = con.prepareStatement(updateRepertory);
						ps.setInt(1, bills.getWarehouseId());
						ps.setInt(2, billsDetail.getRawMaterialId());
						ps.setDouble(3, count);
						ps.setBigDecimal(4, bigDecimal.multiply(decimal));
						ps.setInt(5, repertory.getRepertoryId());
					} else {
						BigDecimal decimal = new BigDecimal(
								billsDetail.getRawMaterialAmount());
						ps = con.prepareStatement(insertRepertory);
						ps.setInt(1, bills.getWarehouseId());
						ps.setInt(2, billsDetail.getRawMaterialId());
						ps.setDouble(3, billsDetail.getRawMaterialAmount());
						ps.setBigDecimal(4, bigDecimal.multiply(decimal));
					}
					flag = ps.executeUpdate();
				}
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public List<RawMaterialInfo> findCheck(Page page, int id) {
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findCheck);
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

	@Override
	public long getCheckRow(int id) {
		long totalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(getCheckRow);
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

	@Override
	public int insertCheck(Check check) {
		int flag = DaoHelper
				.insertUpdate(
						"INSERT INTO pw_check(warehouseId,raw_material_id,inventory_count) VALUES(?,?,?)",
						check);
		return flag;
	}

	@Override
	public int updateCheck(Check check) {
		String update = "UPDATE pw_check SET warehouseId=?,raw_material_id=?,inventory_count=? "
				+ "WHERE check_id=?";
		int flag = DaoHelper.insertUpdate(update, check);
		return flag;
	}

	@Override
	public int delCheck(int id) {
		int flag = DaoHelper
				.delete("DELETE FROM pw_check WHERE check_id=?", id);
		return flag;
	}

	@Override
	public Check selectCheck(int warehouseId, int rawMaterialId) {
		Check check = new Check();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM pw_check WHERE warehouseId=? AND raw_material_id=?");
			ps.setInt(1, warehouseId);
			ps.setInt(2, rawMaterialId);
			rs = ps.executeQuery();
			check = JdbcHelper.getSingleResult(rs, Check.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return check;
	}

	@Override
	public int insertRepertoryCheck(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_repertory_check(bills_id) VALUES(?)");
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}
}
