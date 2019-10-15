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
		// 调用DaoHelper反射类的insertUpdate方法新增采购单信息
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public List<OrderInfo> findPurchase(Page page) {
		// 调用ThisUtil当前工具类findOrder方法查询采购单信息
		List<OrderInfo> infos = ThisUtil.findOrder("pw_purchase_orders",
				"purchase_orders_id", page);
		return infos;
	}

	@Override
	public long getPurchaseRow() {
		// 调用ThisUtil当前工具类getOrderRow方法查询采购单总条数
		long totalRows = ThisUtil.getOrderRow("pw_purchase_orders");
		return totalRows;
	}

	@Override
	public List<RawMaterialInfo> findMaterial(Page page, int id) {// 查询采购单原料信息
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(findMaterial);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
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
		// 调用DaoHelper反射类的insertUpdate方法新增配送单信息
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public List<OrderInfo> findDispatching(Page page) {
		// 调用ThisUtil当前工具类findOrder方法查询配送单信息
		List<OrderInfo> infos = ThisUtil.findOrder("pw_dispatching_orders",
				"dispatching_orders_id", page);
		return infos;
	}

	@Override
	public long getDispatchingRow() {
		// 调用ThisUtil当前工具类getOrderRow方法查询配送单总条数
		long totalRow = ThisUtil.getOrderRow("pw_dispatching_orders");
		return totalRow;
	}

	@Override
	public List<RawMaterialInfo> findPSMaterial(Page page, int id) {
		// 调用ThisUtil当前工具类findOrderMaterial方法查询配送单原料信息
		List<RawMaterialInfo> infos = ThisUtil.findOrderMaterial(
				"pw_dispatching_orders", "dispatching_orders_id", id, page);
		return infos;
	}

	@Override
	public long getPSMaterialRow(int id) {
		// 调用ThisUtil当前工具类getOrderMaterialRow方法查询配送单原料总条数
		long totalRow = ThisUtil.getOrderMaterialRow("pw_dispatching_orders",
				"dispatching_orders_id", id);
		return totalRow;
	}

	@Override
	public int getRepertoryAmount(int warehouseId, int rawMaterialId) {// 查询仓库原料数量
		int repertoryAmount = 0;
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(getRepertoryAmount);
			ps.setInt(1, warehouseId);
			ps.setInt(2, rawMaterialId);
			rs = ps.executeQuery();
			while (rs.next()) {
				repertoryAmount = rs.getInt("raw_material_quantity");
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
		// 调用DaoHelper反射类的insertUpdate方法新增入库单信息
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public List<OrderInfo> findGodownOrders(Page page) {
		// 调用ThisUtil当前工具类findOrderMaterial方法查询入库单
		List<OrderInfo> infos = ThisUtil.findOrder("pw_godown_orders",
				"godown_orders_id", page);
		return infos;
	}

	@Override
	public long getGodownOrdersRow() {
		// 调用ThisUtil当前工具类getOrderRow方法查询入库单总条数
		long totalRow = ThisUtil.getOrderRow("pw_godown_orders");
		return totalRow;
	}

	@Override
	public List<RawMaterialInfo> findRKMaterial(Page page, int id) {
		// 调用ThisUtil当前工具类findOrderMaterial方法查询入库单原料信息
		List<RawMaterialInfo> infos = ThisUtil.findOrderMaterial(
				"pw_godown_orders", "godown_orders_id", id, page);
		return infos;
	}

	@Override
	public long getRKMaterialRow(int id) {
		// 调用ThisUtil当前工具类getOrderRow方法查询入库单原料信息总条数
		long totalRow = ThisUtil.getOrderMaterialRow("pw_godown_orders",
				"godown_orders_id", id);
		return totalRow;
	}

	@Override
	public int insertCreditOrders(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_credit_orders");
		builder.append(insertOrders);
		// 调用DaoHelper反射类的insertUpdate方法新增退货单信息
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}

	@Override
	public int insertStocksRequisition(Order order,
			List<BillsDetail> billsDetails) {// 新增领料单
		int flag = 0;
		int key = 0;
		try {
			StringBuilder builder = new StringBuilder(
					"INSERT INTO pw_stocks_requisition");
			builder.append(insertOrder);
			con = DBUtil.getConnection();// 获取连接
			con.setAutoCommit(false);// 关闭自动提交
			// 调用DaoHelper反射类的setPsToSQLException方法新增领料单，返回新增的主键id
			key = DaoHelper.setPsToSQLException(con, order, builder.toString());
			if (key > 0) {
				int total = billsDetails.size();// 记录需要新增的数据条数
				int f = 0;// 记录新增成功的数据条数
				for (BillsDetail billsDetail : billsDetails) {
					ps = con.prepareStatement(findRequirement);
					ps.setInt(1, order.getDepartmentId());
					ps.setInt(2, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					// 调用JdbcHelper反射类的getSingleResult方法获取单条数据
					MaterialRequirement requirement = JdbcHelper
							.getSingleResult(rs, MaterialRequirement.class);
					if (requirement != null) {// 判断部门原料需求不为空
						int count = requirement.getQuantityRequired()
								- billsDetail.getRawMaterialAmount();
						if (count > 0) {
							MaterialRequirement materialRequirement = new MaterialRequirement(
									requirement.getMaterialRequirementId(),
									order.getDepartmentId(),
									billsDetail.getRawMaterialId(), count);
							flag = DaoHelper.setPsToSQLException(con,
									materialRequirement, updateRequirement) > 0 ? 1
									: 0;
						} else {
							flag = DaoHelper.delete(delRequirement,
									requirement.getMaterialRequirementId());
						}
						f += flag;// 新增成功叠加
					}
				}
				if (total == f) {// 判断新增成功条数是否与需要新增数据条数是否相等，相等则提交
					con.commit();// 提交事务
				}
			}
		} catch (SQLException e) {
			try {
				con.rollback();// 事务回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return flag;
	}

	@Override
	public List<OrderInfo> findStocksRequisition(Page page) {
		// 调用ThisUtil当前工具类findOrders方法查询领料单
		List<OrderInfo> infos = ThisUtil.findOrders("pw_stocks_requisition",
				"stocks_requisition_id", page);
		return infos;
	}

	@Override
	public long getStocksRequisitionRow() {
		// 调用ThisUtil当前工具类getOrderRows方法查询领料单总条数
		long totalRow = ThisUtil.getOrderRows("pw_stocks_requisition");
		return totalRow;
	}

	@Override
	public List<RawMaterialInfo> findLLMaterial(Page page, int id) {
		// 调用ThisUtil当前工具类findOrderMaterial方法查询领料单原料信息
		List<RawMaterialInfo> infos = ThisUtil.findOrderMaterial(
				"pw_stocks_requisition", "stocks_requisition_id", id, page);
		return infos;
	}

	@Override
	public long getLLMaterialRow(int id) {
		// 调用ThisUtil当前工具类findOrderMaterial方法查询领料单原料信息总条数
		long totalRow = ThisUtil.getOrderMaterialRow("pw_stocks_requisition",
				"stocks_requisition_id", id);
		return totalRow;
	}

	@Override
	public int insertPickingCreditOrders(Order order,
			List<BillsDetail> billsDetails) {// 新增领料退货单
		int flag = 0;
		int key = 0;
		try {
			StringBuilder builder = new StringBuilder(
					"INSERT INTO pw_picking_credit_orders");
			builder.append(insertOrder);
			con = DBUtil.getConnection();// 获取连接
			con.setAutoCommit(false);// 关闭自动提交
			// 调用DaoHelper反射类的setPsToSQLException方法新增领料退货单，返回新增的主键id
			key = DaoHelper.setPsToSQLException(con, order, builder.toString());
			if (key > 0) {
				int total = billsDetails.size();// 记录需要新增的数据条数
				int f = 0;// 记录新增成功的数据条数
				for (BillsDetail billsDetail : billsDetails) {
					flag = 0;
					ps = con.prepareStatement(findRequirement);
					ps.setInt(1, order.getDepartmentId());
					ps.setInt(2, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					MaterialRequirement requirement = JdbcHelper
							.getSingleResult(rs, MaterialRequirement.class);
					MaterialRequirement materialRequirement = null;
					if (requirement != null) {// 判断部门需求是否为空，若不为空则更新部门需求
						int count = requirement.getQuantityRequired()
								+ billsDetail.getRawMaterialAmount();
						materialRequirement = new MaterialRequirement(
								requirement.getMaterialRequirementId(),
								order.getDepartmentId(),
								billsDetail.getRawMaterialId(), count);
						flag = DaoHelper.setPsToSQLException(con,
								materialRequirement, updateRequirement) > 0 ? 1
								: 0;
					} else {// 否则新增部门需求
						materialRequirement = new MaterialRequirement(null,
								order.getDepartmentId(),
								billsDetail.getRawMaterialId(),
								billsDetail.getRawMaterialAmount());
						flag = DaoHelper.setPsToSQLException(con,
								materialRequirement, insertRequirement) > 0 ? 1
								: 0;
					}
					f += flag;// 新增成功叠加
				}
				if (total == f) {// 判断新增成功条数是否与需要新增数据条数是否相等，相等则提交
					con.commit();// 提交事务
				}
			}
		} catch (SQLException e) {
			try {
				con.rollback();// 事务回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return flag;
	}

	@Override
	public List<RawMaterialInfo> findRepertory(Page page, int id) {
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(findRepertory);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
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
		int key = 0;
		String findSingle = "SELECT `raw_material_price` FROM `pw_raw_material` WHERE `raw_material_id`=?";
		try {
			StringBuilder builder = new StringBuilder(
					"INSERT INTO pw_warehouse_transfer_order(bills_id,warehouseId,staffId) VALUES(?,?,?)");
			con = DBUtil.getConnection();// 获取连接
			con.setAutoCommit(false);// 关闭自动提交
			// 调用DaoHelper反射类的setPsToSQLException方法新增仓库调拔单，返回新增的主键id
			key = DaoHelper.setPsToSQLException(con, order, builder.toString());
			if (key > 0) {
				int total = billsDetails.size();// 记录需要新增的数据条数
				int f = 0;// 记录新增成功的数据条数
				for (BillsDetail billsDetail : billsDetails) {
					ps = con.prepareStatement(findRepertorys);
					ps.setInt(1, bills.getWarehouseId());
					ps.setInt(2, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					// 调用JdbcHelper反射类的getResult方法获取单条数据
					Repertory repertory = JdbcHelper.getSingleResult(rs,
							Repertory.class);
					// 根据原料id查询原料价格
					ps = con.prepareStatement(findSingle);
					ps.setInt(1, billsDetail.getRawMaterialId());
					rs = ps.executeQuery();
					BigDecimal bigDecimal = null;
					while (rs.next()) {
						bigDecimal = rs.getBigDecimal("raw_material_price");
					}
					Repertory repertory2 = null;
					if (repertory != null) {//判断仓库库存原料是否为空，若不为空，则更新库存信息
						int count = repertory.getRawMaterialQuantity()
								+ billsDetail.getRawMaterialAmount();
						BigDecimal decimal = new BigDecimal(count);
						repertory2 = new Repertory(repertory.getRepertoryId(),
								bills.getWarehouseId(),
								billsDetail.getRawMaterialId(), count,
								bigDecimal.multiply(decimal));
						ps = con.prepareStatement(updateRepertory);
						flag = DaoHelper.setPsToSQLException(con, repertory2,
								updateRepertory) > 0 ? 1 : 0;
					} else {//否则新增库存信息
						BigDecimal decimal = new BigDecimal(
								billsDetail.getRawMaterialAmount());
						repertory2 = new Repertory(null,
								bills.getWarehouseId(),
								billsDetail.getRawMaterialId(),
								billsDetail.getRawMaterialAmount(),
								bigDecimal.multiply(decimal));
						flag = DaoHelper.setPsToSQLException(con, repertory2,
								insertRepertory) > 0 ? 1 : 0;
					}
					f += flag;// 新增成功叠加
				}
				if (total == f) {// 判断新增成功条数是否与需要新增数据条数是否相等，相等则提交
					con.commit();// 提交事务
				}
			}
		} catch (SQLException e) {
			try {
				con.rollback();//事务回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return flag;
	}

	@Override
	public List<RawMaterialInfo> findCheck(Page page, int id) {//查询盘点原料信息
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findCheck);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
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
		// 调用DaoHelper反射类的insertUpdate方法新增盘点信息
		int flag = DaoHelper
				.insertUpdate(
						"INSERT INTO pw_check(warehouseId,raw_material_id,inventory_count) VALUES(?,?,?)",
						check);
		return flag;
	}

	@Override
	public int updateCheck(Check check) {
		// 调用DaoHelper反射类的insertUpdate方法修改盘点信息
		String update = "UPDATE pw_check SET warehouseId=?,raw_material_id=?,inventory_count=? "
				+ "WHERE check_id=?";
		int flag = DaoHelper.insertUpdate(update, check);
		return flag;
	}

	@Override
	public int delCheck(int id) {
		// 调用DaoHelper反射类的delete方法删除盘点信息
		int flag = DaoHelper
				.delete("DELETE FROM pw_check WHERE check_id=?", id);
		return flag;
	}

	@Override
	public Check selectCheck(int warehouseId, int rawMaterialId) {//根据仓库id和原料id查询唯一的一条盘点数据
		Check check = new Check();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM pw_check WHERE warehouseId=? AND raw_material_id=?");
			ps.setInt(1, warehouseId);
			ps.setInt(2, rawMaterialId);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getSingleResult方法获取单条数据
			check = JdbcHelper.getSingleResult(rs, Check.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return check;
	}

	@Override
	public int insertRepertoryCheck(Order order) {
		StringBuilder builder = new StringBuilder(
				"INSERT INTO pw_repertory_check(bills_id) VALUES(?)");
		// 调用DaoHelper反射类的insertUpdate方法新增盘点单据
		int flag = DaoHelper.insertUpdate(builder.toString(), order);
		return flag;
	}
}
