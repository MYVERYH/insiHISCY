package com.web.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IBillDao;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.MinimumStock;
import com.web.po.Repertory;
import com.web.po.SupplierPayment;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.BillsInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;
import com.web.vo.RepertoryInfo;
import com.web.vo.SelectBills;
import com.web.vo.SupplierPaymentInfo;

public class BillDaoImpl implements IBillDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String insert = "INSERT INTO pw_bills(warehouseId,staffId,bills_entry_time,bills_num,"
			+ "bills_money,bills_remark) VALUES(?,?,?,?,?,?)";
	private String insertDetail = "INSERT INTO pw_bills_detail(bills_id,raw_material_id,"
			+ "raw_material_amount,remark) VALUES(?,?,?,?)";
	private String findRepertory = "SELECT * FROM pw_repertory WHERE warehouseId=? AND raw_material_id=?";
	private String insertRepertory = "INSERT INTO pw_repertory(warehouseId,raw_material_id,"
			+ "raw_material_quantity,total_price) VALUES(?,?,?,?)";
	private String updateRepertory = "UPDATE pw_repertory SET warehouseId=?,raw_material_id=?,"
			+ "raw_material_quantity=?,total_price=? WHERE repertory_id=?";
	private String findSingle = "SELECT `raw_material_price` FROM `pw_raw_material` "
			+ "WHERE `raw_material_id`=?";
	private String selectCheck = "SELECT r.`bills_id`,b.`bills_num`,w.`warehouseName`,s.`staffName`,"
			+ "b.`bills_entry_time`,b.`bills_remark` FROM `pw_repertory_check` r JOIN `pw_bills` b "
			+ "ON r.`bills_id` = b.`bills_id` JOIN `pw_warehouse` w ON b.`warehouseId` = w.`warehouseId` "
			+ "JOIN `pw_staff` s ON b.`staffId` = s.`staffId`";
	private String getCheckTotalRow = "SELECT COUNT(*) FROM `pw_repertory_check` r JOIN `pw_bills` b "
			+ "ON r.`bills_id` = b.`bills_id` JOIN `pw_warehouse` w ON b.`warehouseId` = w.`warehouseId` "
			+ "JOIN `pw_staff` s ON b.`staffId` = s.`staffId`";
	private String selectOrderMaterial = "SELECT r.`raw_material_id`,r.`raw_material_num`,"
			+ "r.`raw_material_name`,b.`warehouseId`,w.`warehouseName`,u.`unitName`,r.`raw_material_price`,"
			+ "d.`raw_material_amount` FROM `pw_bills` b JOIN `pw_warehouse` w ON b.`warehouseId` = "
			+ "w.`warehouseId` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "JOIN `pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId`";
	private String getOrderMaterialTotalRow = "SELECT COUNT(*) FROM `pw_bills` b JOIN `pw_warehouse` w "
			+ "ON b.`warehouseId` = w.`warehouseId` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "JOIN `pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` "
			+ "JOIN `sys_unit` u ON r.`unitId` = u.`unitId`";
	private String selectOrderDetail = "SELECT r.`raw_material_id`,r.`raw_material_num`,"
			+ "r.`raw_material_name`,g.`raw_material_big_name`,u.`unitName`,r.`raw_material_price`,"
			+ "d.`raw_material_amount`,d.`remark` FROM `pw_bills` b JOIN `pw_warehouse` w "
			+ "ON b.`warehouseId` = w.`warehouseId` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "JOIN `pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` JOIN `sys_raw_material_small` s "
			+ "ON r.`raw_material_small_id` = s.`raw_material_small_id` JOIN `sys_raw_material_big` g "
			+ "ON s.`raw_material_big_id` = g.`raw_material_big_id` WHERE b.`bills_id` = ? "
			+ "ORDER BY r.`raw_material_id` LIMIT ?,?";
	private String getOrderDetailTotalRow = "SELECT COUNT(*) FROM `pw_bills` b JOIN `pw_warehouse` w "
			+ "ON b.`warehouseId` = w.`warehouseId` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "JOIN `pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` JOIN `sys_raw_material_small` s "
			+ "ON r.`raw_material_small_id` = s.`raw_material_small_id` JOIN `sys_raw_material_big` g "
			+ "ON s.`raw_material_big_id` = g.`raw_material_big_id` WHERE b.`bills_id` = ?";
	private String findOrder = " JOIN `pw_bills` b ON a.`bills_id` = b.`bills_id` JOIN `pw_warehouse` w "
			+ "ON b.`warehouseId` = w.`warehouseId` JOIN `pw_staff` s1 ON a.`staffId` = s1.`staffId`";
	private String selectNowRepertory = "SELECT p.`repertory_id`,p.`warehouseId`,w.`warehouseName`,"
			+ "r.`raw_material_num`,r.`raw_material_name`,u.`unitName`,b.`raw_material_big_name`,"
			+ "r.`raw_material_price`,p.`raw_material_quantity`,p.`total_price` FROM `pw_repertory` p "
			+ "JOIN `pw_warehouse` w ON p.`warehouseId` = w.`warehouseId` join `pw_raw_material` r "
			+ "ON p.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId` "
			+ "JOIN `sys_raw_material_small` s ON r.`raw_material_small_id` = s.`raw_material_small_id` "
			+ "JOIN `sys_raw_material_big` b ON s.`raw_material_big_id` = b.`raw_material_big_id`";
	private String getNowRepertoryTotalRow = "SELECT COUNT(*) FROM `pw_repertory` p JOIN `pw_warehouse` w "
			+ "ON p.`warehouseId` = w.`warehouseId` join `pw_raw_material` r "
			+ "ON p.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` join `sys_raw_material_small` s "
			+ "ON r.`raw_material_small_id` = s.`raw_material_small_id` JOIN `sys_raw_material_big` b "
			+ "ON s.`raw_material_big_id` = b.`raw_material_big_id`";
	private String selectRepertory = "SELECT r.`raw_material_num`,r.`raw_material_name`,u.`unitName`,"
			+ "(SELECT SUM(d.`raw_material_amount`) FROM `pw_godown_orders` g JOIN `pw_bills` b "
			+ "ON g.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` LIKE ?) godownQuantity,(SELECT SUM(d.`raw_material_amount`) "
			+ "FROM `pw_credit_orders` g JOIN `pw_bills` b ON g.`bills_id` = b.`bills_id` "
			+ "JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` WHERE d.`raw_material_id` = "
			+ "r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` AND b.`bills_entry_time` LIKE ?) "
			+ "goOutQuantity,(SELECT SUM(d.`raw_material_amount`) FROM `pw_warehouse_transfer_order` g "
			+ "JOIN `pw_bills` b ON g.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = "
			+ "d.`bills_id` WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = "
			+ "p.`warehouseId` AND b.`bills_entry_time` LIKE ?) adjustQuantity,p.`raw_material_quantity`,"
			+ "r.`raw_material_price` FROM `pw_repertory` p JOIN `pw_raw_material` r "
			+ "ON p.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId` "
			+ "JOIN `pw_bills` b ON p.`warehouseId` = b.`warehouseId` WHERE b.`bills_entry_time` LIKE ?";
	private String getRepertoryTotalRows = "SELECT COUNT(*) FROM (SELECT COUNT(*) FROM  `pw_repertory` p "
			+ "JOIN `pw_raw_material` r ON p.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` JOIN `pw_bills` b ON p.`warehouseId` = b.`warehouseId` "
			+ "WHERE b.`bills_entry_time` LIKE ?";
	private String findRepertoryDetail = "SELECT r.`raw_material_num`,r.`raw_material_name`,u.`unitName`,"
			+ "(SELECT SUM(d.`raw_material_amount`) FROM `pw_godown_orders` g JOIN `pw_bills` b "
			+ "ON g.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?) godownQuantity, (SELECT "
			+ "SUM(d.`raw_material_amount`) FROM `pw_credit_orders` g JOIN `pw_bills` b ON g.`bills_id` = "
			+ "b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?) goOutQuantity, (SELECT "
			+ "SUM(d.`raw_material_amount`) FROM `pw_stocks_requisition` g JOIN `pw_bills` b ON g.`bills_id` "
			+ "= b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?) llQuantity, (SELECT "
			+ "SUM(d.`raw_material_amount`) FROM `pw_picking_credit_orders` g JOIN `pw_bills` b "
			+ "ON g.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?) ltQuantity, (SELECT "
			+ "SUM(d.`raw_material_amount`) FROM `pw_warehouse_transfer_order` g JOIN `pw_bills` b "
			+ "ON g.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND g.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?) dcQuantity,(SELECT "
			+ "SUM(d.`raw_material_amount`) FROM `pw_warehouse_transfer_order` g JOIN `pw_bills` b "
			+ "ON g.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "WHERE d.`raw_material_id` = r.`raw_material_id` AND b.`warehouseId` = p.`warehouseId` "
			+ "AND b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?) drQuantity, "
			+ "p.`raw_material_quantity`,r.`raw_material_price`,b.`bills_entry_time` FROM `pw_repertory` p "
			+ "JOIN `pw_raw_material` r ON p.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` JOIN `pw_bills` b ON p.`warehouseId` = b.`warehouseId` "
			+ "WHERE b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?";
	private String getRepertoryDetailTotalRows = "SELECT COUNT(*) FROM (SELECT COUNT(*) "
			+ "FROM `pw_repertory` p JOIN `pw_raw_material` r ON p.`raw_material_id` = r.`raw_material_id` "
			+ "JOIN `sys_unit` u ON r.`unitId` = u.`unitId` JOIN `pw_bills` b ON p.`warehouseId` = "
			+ "b.`warehouseId` WHERE b.`bills_entry_time` >= ? AND b.`bills_entry_time` <= ?";
	private String selectSupplier = "SELECT s.`supplierName`,b.`bills_entry_time`,r.`raw_material_name`,"
			+ "rb.`raw_material_big_name`,u.`unitName`,d.`raw_material_amount`,r.`raw_material_price`,"
			+ "sf.`staffName`,(SELECT s1.`staffName` FROM `pw_staff` s1 WHERE s1.`staffId` = b.`staffId`) "
			+ "staffNames FROM `pw_supplier` s JOIN `pw_purchase_orders` p ON s.`supplierId` = "
			+ "p.`supplierId` JOIN `pw_staff` sf ON p.`staffId` = sf.`staffId` JOIN `pw_bills` b "
			+ "ON p.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d ON b.`bills_id` = d.`bills_id` "
			+ "JOIN`pw_raw_material` r ON d.`raw_material_id` = r.`raw_material_id` "
			+ "JOIN `sys_raw_material_small` rs ON r.`raw_material_small_id` = rs.`raw_material_small_id` "
			+ "JOIN `sys_raw_material_big` rb ON rs.`raw_material_big_id` = rb.`raw_material_big_id` "
			+ "JOIN `sys_unit` u ON r.`unitId` = u.`unitId`";
	private String getSupplierTotalRow = "SELECT COUNT(*) FROM `pw_supplier` s JOIN `pw_purchase_orders` p "
			+ "ON s.`supplierId` = p.`supplierId` JOIN `pw_staff` sf ON p.`staffId` = sf.`staffId` "
			+ "JOIN `pw_bills` b ON p.`bills_id` = b.`bills_id` JOIN `pw_bills_detail` d "
			+ "ON b.`bills_id` = d.`bills_id`JOIN`pw_raw_material` r ON d.`raw_material_id` = "
			+ "r.`raw_material_id` JOIN `sys_raw_material_small` rs ON r.`raw_material_small_id` = "
			+ "rs.`raw_material_small_id` JOIN `sys_raw_material_big` rb ON rs.`raw_material_big_id` = "
			+ "rb.`raw_material_big_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId`";
	private String findPaymentInfo = "(SELECT *, SUM(bills_money) total FROM (SELECT s.`supplierId`, "
			+ "r.`raw_material_big_name`, s.`supplierName`, b.`bills_money`, b.`bills_entry_time` "
			+ "FROM `pw_supplier` s JOIN `pw_purchase_orders` p ON s.`supplierId` = p.`supplierId` "
			+ "JOIN `pw_bills` b ON p.`bills_id` = b.`bills_id` JOIN `sys_raw_material_big` r "
			+ "ON s.`raw_material_big_id` = r.`raw_material_big_id`";
	private String selectPaymentInfo = "SELECT s.`supplierName`,a.`total_money`,a.`payment_date` "
			+ "FROM `pw_supplier` s JOIN `pw_supplier_payment` a ON s.`supplierId` = a.`supplierId`";
	private String getPaymentTotalRows = "SELECT COUNT(*) FROM `pw_supplier` s JOIN `pw_supplier_payment` a"
			+ " ON s.`supplierId` = a.`supplierId`";
	private String selectMinimumStock = "SELECT p.`repertory_id`,p.`warehouseId`,w.`warehouseName`,"
			+ "r.`raw_material_num`,r.`raw_material_name`,u.`unitName`,b.`raw_material_big_name`,"
			+ "p.`raw_material_quantity`,m.`minimum_quantity` FROM `pw_repertory` p JOIN `pw_warehouse` w "
			+ "ON p.`warehouseId` = w.`warehouseId` JOIN `pw_raw_material` r "
			+ "ON p.`raw_material_id` = r.`raw_material_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId` "
			+ "JOIN `sys_raw_material_small` s ON r.`raw_material_small_id` = s.`raw_material_small_id` "
			+ "JOIN `sys_raw_material_big` b ON s.`raw_material_big_id` = b.`raw_material_big_id` "
			+ "LEFT JOIN `pw_minimum_stock` m ON p.`repertory_id` = m.`repertory_id`";
	private String getMinimumStockTotalRow = "SELECT COUNT(*) FROM `pw_repertory` p JOIN `pw_warehouse` w "
			+ "ON p.`warehouseId` = w.`warehouseId` JOIN `pw_raw_material` r ON p.`raw_material_id` = "
			+ "r.`raw_material_id` JOIN `sys_unit` u ON r.`unitId` = u.`unitId` "
			+ "JOIN `sys_raw_material_small` s ON r.`raw_material_small_id` = s.`raw_material_small_id` "
			+ "JOIN `sys_raw_material_big` b ON s.`raw_material_big_id` = b.`raw_material_big_id` "
			+ "LEFT JOIN `pw_minimum_stock` m ON p.`repertory_id` = m.`repertory_id`";

	@Override
	public List<Bills> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bills findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Bills t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Bills t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Bills bills, List<BillsDetail> billsDetails) {// 新增单据和单据明细
		int flag = 0;
		int key = 0;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);// 关闭自动提交
			// 调用DaoHelper反射类的setPsToSQLException方法新增单据信息，返回新增的主键id
			key = DaoHelper.setPsToSQLException(con, bills, insert);
			if (key > 0) {
				int total = billsDetails.size();// 记录需要新增的数据条数
				int f = 0;// 记录新增成功的数据条数
				for (BillsDetail billsDetail : billsDetails) {
					billsDetail.setBillsId(key);
					// 调用DaoHelper反射类的setPsToSQLException方法新增单据明细信息，返回新增的主键id
					flag = DaoHelper.setPsToSQLException(con, billsDetail,
							insertDetail) > 0 ? 1 : 0;// 三目运算符判断新增的主键id是否大于0，大于0则返回1，否则返回0
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
		return key;
	}

	@Override
	public int insertRepertory(Bills bills, List<BillsDetail> billsDetails) {// 新增库存信息
		int flag = 0;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);// 关闭自动提交
			int total = billsDetails.size();// 记录需要新增数据条数
			int f = 0;// 记录新增成功数据条数
			for (BillsDetail billsDetail : billsDetails) {
				// 查询库存是否已有当前原料信息
				ps = con.prepareStatement(findRepertory);
				ps.setInt(1, bills.getWarehouseId());
				ps.setInt(2, billsDetail.getRawMaterialId());
				rs = ps.executeQuery();
				Repertory repertory = JdbcHelper.getSingleResult(rs,
						Repertory.class);
				ps = con.prepareStatement(findSingle);
				ps.setInt(1, billsDetail.getRawMaterialId());
				rs = ps.executeQuery();
				BigDecimal bigDecimal = null;
				Repertory repertory2 = null;
				while (rs.next()) {
					bigDecimal = rs.getBigDecimal("raw_material_price");
				}
				if (repertory != null) {// 若有，则更新库存信息
					int count = repertory.getRawMaterialQuantity()
							+ billsDetail.getRawMaterialAmount();
					BigDecimal decimal = new BigDecimal(count);
					repertory2 = new Repertory(repertory.getRepertoryId(),
							bills.getWarehouseId(),
							billsDetail.getRawMaterialId(), count,
							decimal.multiply(bigDecimal));
					flag = DaoHelper.setPsToSQLException(con, repertory2,
							updateRepertory) > 0 ? 1 : 0;
				} else {// 若没有，则新增当前原料信息
					BigDecimal decimal = new BigDecimal(
							billsDetail.getRawMaterialAmount());
					repertory2 = new Repertory(null, bills.getWarehouseId(),
							billsDetail.getRawMaterialId(),
							billsDetail.getRawMaterialAmount(),
							decimal.multiply(bigDecimal));
					flag = DaoHelper.setPsToSQLException(con, repertory2,
							insertRepertory) > 0 ? 1 : 0;
				}
				f += flag;// 新增成功叠加
			}
			if (total == f) {// 判断新增成功条数是否与需要新增数据条数是否相等，相等则提交
				con.commit();// 提交事务
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
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public int updateRepertory(Bills bills, List<BillsDetail> billsDetails,
			boolean b) {// 更新库存信息
		int flag = 0;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);// 关闭自动提交
			int total = billsDetails.size();// 记录需要新增的数据条数
			int f = 0;// 记录新增成功的数据条数
			for (BillsDetail billsDetail : billsDetails) {
				int count = 0;
				ps = con.prepareStatement(findRepertory);
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
				if (b) {
					count = repertory.getRawMaterialQuantity()
							- billsDetail.getRawMaterialAmount();
				} else {
					count = repertory.getRawMaterialQuantity()
							+ billsDetail.getRawMaterialAmount();
				}
				if (count > -1) {
					BigDecimal decimal = new BigDecimal(count);
					Repertory repertory2 = new Repertory(
							repertory.getRepertoryId(), bills.getWarehouseId(),
							billsDetail.getRawMaterialId(), count,
							decimal.multiply(bigDecimal));
					flag = DaoHelper.setPsToSQLException(con, repertory2,
							updateRepertory) > 0 ? 1 : 0;
					f += flag;// 新增成功叠加
				}
			}
			if (total == f) {// 判断新增成功条数是否与需要新增数据条数是否相等，相等则提交
				con.commit();// 提交事务
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
	public List<BillsInfo> selectCheck(SelectBills selectBills, Page page) {// 查询盘点单据信息
		List<BillsInfo> infos = new ArrayList<BillsInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectCheck);
			// 查询条件拼接
			if (!"".equals(selectBills.getBillsNum())
					&& !selectBills.getBillsNum().isEmpty()) {
				buffer.append(" WHERE b.`bills_num` = '"
						+ selectBills.getBillsNum() + "'");
			}
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" AND b.`warehouseId` = "
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" AND b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (selectBills.getStaffId() > 0) {
				buffer.append(" AND b.`staffId` = " + selectBills.getStaffId());
			}
			buffer.append(" ORDER BY r.`repertory_check_id` DESC LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, BillsInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getCheckTotalRow(SelectBills selectBills) {
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer(getCheckTotalRow);
			if (!"".equals(selectBills.getBillsNum())
					&& !selectBills.getBillsNum().isEmpty()) {
				buffer.append(" WHERE b.`bills_num` = '"
						+ selectBills.getBillsNum() + "'");
			}
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" AND b.`warehouseId` = "
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" AND b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (selectBills.getStaffId() > 0) {
				buffer.append(" AND b.`staffId` = " + selectBills.getStaffId());
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
	public List<RawMaterialInfo> selectOrderMaterial(SelectBills selectBills,
			Page page, String findType) {// 查询单据原料信息
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectOrderMaterial);
			// 根据findType参数判断查询不同的单据下的原料信息
			if ("findMaterial1".equals(findType)) {// 库存盘点单据
				buffer.append(" JOIN `pw_repertory_check` a ON b.`bills_id` = a.`bills_id`");
			} else if ("findMaterial2".equals(findType)) {// 仓库调拔单
				buffer.append(" JOIN `pw_warehouse_transfer_order` a ON b.`bills_id` = a.`bills_id`");
			} else if ("findMaterial3".equals(findType)) {// 领料单
				buffer.append(" JOIN `pw_stocks_requisition` a ON b.`bills_id` = a.`bills_id`");
			}
			// 查询条件的拼接
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" WHERE b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" AND b.`warehouseId` = "
						+ selectBills.getWarehouseId());
			}
			if (selectBills.getWarehouseID() > 0) {
				buffer.append(" AND a.`warehouseId` = "
						+ selectBills.getWarehouseID());
			}
			if (selectBills.getDepartmentId() > 0) {
				buffer.append(" AND a.`departmentId` = "
						+ selectBills.getDepartmentId());
			}
			if (!"".equals(selectBills.getRawMaterialNum())
					&& !selectBills.getRawMaterialNum().isEmpty()) {
				buffer.append(" AND r.`raw_material_num` = '"
						+ selectBills.getRawMaterialNum() + "'");
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			buffer.append(" ORDER BY b.`bills_id` DESC,r.`raw_material_id` LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getOrderMaterialTotalRow(SelectBills selectBills,
			String findType) {
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer(getOrderMaterialTotalRow);
			if ("findMaterial1".equals(findType)) {
				buffer.append(" JOIN `pw_repertory_check` a ON b.`bills_id` = a.`bills_id`");
			} else if ("findMaterial2".equals(findType)) {
				buffer.append(" JOIN `pw_warehouse_transfer_order` a ON b.`bills_id` = a.`bills_id`");
			} else if ("findMaterial3".equals(findType)) {
				buffer.append(" JOIN `pw_stocks_requisition` a ON b.`bills_id` = a.`bills_id`");
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" WHERE b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" AND b.`warehouseId` = "
						+ selectBills.getWarehouseId());
			}
			if (selectBills.getWarehouseID() > 0) {
				buffer.append(" AND a.`warehouseId` = "
						+ selectBills.getWarehouseID());
			}
			if (selectBills.getDepartmentId() > 0) {
				buffer.append(" AND a.`departmentId` = "
						+ selectBills.getDepartmentId());
			}
			if (!"".equals(selectBills.getRawMaterialNum())
					&& !selectBills.getRawMaterialNum().isEmpty()) {
				buffer.append(" AND r.`raw_material_num` = '"
						+ selectBills.getRawMaterialNum() + "'");
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
	public List<RawMaterialInfo> selectOrderDetail(int id, Page page) {// 查询单据明细原料信息
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectOrderDetail);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getOrderDetailTotalRow(int id) {
		long totalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(getOrderDetailTotalRow);
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
	public List<BillsInfo> selectOrder(SelectBills selectBills, Page page,
			String findType) {// 查询单据信息
		List<BillsInfo> infos = new ArrayList<BillsInfo>();
		try {
			StringBuffer buffer = new StringBuffer();
			// 根据findType参数判断查询不同的单据信息
			if ("findGodownOrders".equals(findType)) {// 入库单
				buffer.append("SELECT b.`bills_id`,b.`bills_num`,s.`supplierName`,w.`warehouseName`,"
						+ "s1.`staffName`,(SELECT `staffName` FROM `pw_staff` WHERE `staffId` = b.`staffId`) "
						+ "staffNames,b.`bills_entry_time`,b.`bills_remark`,b.`bills_money` FROM "
						+ "`pw_godown_orders` a JOIN `pw_supplier` s ON a.`supplierId` =  s.`supplierId`");
			} else if ("findCreditOrders".equals(findType)) {// 退货单
				buffer.append("SELECT b.`bills_id`,b.`bills_num`,s.`supplierName`,w.`warehouseName`,"
						+ "s1.`staffName`,(SELECT `staffName` FROM `pw_staff` WHERE `staffId` = b.`staffId`) "
						+ "staffNames,b.`bills_entry_time`,b.`bills_remark`,b.`bills_money` FROM "
						+ "`pw_credit_orders` a JOIN `pw_supplier` s ON a.`supplierId` =  s.`supplierId`");
			} else if ("findStocksRequisition".equals(findType)) {// 领料单
				buffer.append("SELECT b.`bills_id`,b.`bills_num`,d.`departmentName`,w.`warehouseName`,"
						+ "s1.`staffName`,(SELECT `staffName` FROM `pw_staff` WHERE `staffId` = b.`staffId`) "
						+ "staffNames,b.`bills_entry_time`,b.`bills_remark`,b.`bills_money` "
						+ "FROM `pw_stocks_requisition` a JOIN `pw_department` d "
						+ "ON a.`departmentId` = d.`departmentId`");
			} else if ("findPickingCreditOrders".equals(findType)) {// 领料退货单
				buffer.append("SELECT b.`bills_id`,b.`bills_num`,d.`departmentName`,w.`warehouseName`,"
						+ "s1.`staffName`,(SELECT `staffName` FROM `pw_staff` WHERE `staffId` = b.`staffId`) "
						+ "staffNames,b.`bills_entry_time`,b.`bills_remark`,b.`bills_money` "
						+ "FROM `pw_picking_credit_orders` a JOIN `pw_department` d "
						+ "ON a.`departmentId` = d.`departmentId`");
			} else if ("findWarehouseTransferOrder".equals(findType)) {// 仓库调拔单
				buffer.append("SELECT b.`bills_id`,b.`bills_num`,w.`warehouseName`,(SELECT `warehouseName` "
						+ "FROM `pw_warehouse` WHERE `warehouseId` = a.`warehouseId`) warehouseNames,"
						+ "s1.`staffName`,(SELECT `staffName` FROM `pw_staff` WHERE `staffId` = b.`staffId`) "
						+ "staffNames,b.`bills_entry_time`,b.`bills_remark`,b.`bills_money` "
						+ "FROM `pw_warehouse_transfer_order` a");
			}
			buffer.append(findOrder);
			// 查询条件拼接
			if (!"".equals(selectBills.getBillsNum())
					&& !selectBills.getBillsNum().isEmpty()) {
				buffer.append(" WHERE b.`bills_num` = '"
						+ selectBills.getBillsNum() + "'");
			}
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" AND b.`warehouseId` = "
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" AND b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (selectBills.getSupplierId() > 0) {
				buffer.append(" AND a.`supplierId` = "
						+ selectBills.getSupplierId());
			}
			if (selectBills.getDepartmentId() > 0) {
				buffer.append(" AND a.`departmentId` = "
						+ selectBills.getDepartmentId());
			}
			if (selectBills.getWarehouseID() > 0) {
				buffer.append(" AND a.`warehouseId` = "
						+ selectBills.getWarehouseID());
			}
			if (selectBills.getStaffID() > 0) {
				buffer.append(" AND b.`staffId` = " + selectBills.getStaffID());
			}
			if (selectBills.getStaffId() > 0) {
				buffer.append(" AND a.`staffId` = " + selectBills.getStaffId());
			}
			buffer.append(" ORDER BY b.`bills_id` DESC LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, BillsInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getOrderTotalRow(SelectBills selectBills, String findType) {
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			if ("findGodownOrders".equals(findType)) {
				buffer.append("SELECT COUNT(*) FROM `pw_godown_orders` a JOIN `pw_supplier` s "
						+ "ON a.`supplierId` =  s.`supplierId`");
			} else if ("findCreditOrders".equals(findType)) {
				buffer.append("SELECT COUNT(*) FROM `pw_credit_orders` a JOIN `pw_supplier` s "
						+ "ON a.`supplierId` =  s.`supplierId`");
			} else if ("findStocksRequisition".equals(findType)) {
				buffer.append("SELECT COUNT(*) FROM `pw_stocks_requisition` a JOIN `pw_department` d "
						+ "ON a.`departmentId` = d.`departmentId`");
			} else if ("findPickingCreditOrders".equals(findType)) {
				buffer.append("SELECT COUNT(*) FROM `pw_picking_credit_orders` a JOIN `pw_department` d "
						+ "ON a.`departmentId` = d.`departmentId`");
			} else if ("findWarehouseTransferOrder".equals(findType)) {
				buffer.append("SELECT COUNT(*) FROM `pw_warehouse_transfer_order` a JOIN `pw_warehouse` w2 "
						+ "ON a.`warehouseId` = w2.`warehouseId`");
			}
			buffer.append(findOrder);
			if (!"".equals(selectBills.getBillsNum())
					&& !selectBills.getBillsNum().isEmpty()) {
				buffer.append(" WHERE b.`bills_num` = '"
						+ selectBills.getBillsNum() + "'");
			}
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" AND b.`warehouseId` = "
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" AND b.`bills_entry_time`BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (selectBills.getSupplierId() > 0) {
				buffer.append(" AND a.`supplierId` = "
						+ selectBills.getSupplierId());
			}
			if (selectBills.getDepartmentId() > 0) {
				buffer.append(" AND a.`departmentId` = "
						+ selectBills.getDepartmentId());
			}
			if (selectBills.getWarehouseID() > 0) {
				buffer.append(" AND a.`warehouseId` = "
						+ selectBills.getWarehouseID());
			}
			if (selectBills.getStaffID() > 0) {
				buffer.append(" AND b.`staffId` = " + selectBills.getStaffID());
			}
			if (selectBills.getStaffId() > 0) {
				buffer.append(" AND a.`staffId` = " + selectBills.getStaffId());
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
	public List<RawMaterialInfo> selectNowRepertory(SelectBills selectBills,
			Page page) {
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectNowRepertory);
			// 查询条件拼接
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" WHERE p.`warehouseId`="
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getRawMaterialNum())
					&& !selectBills.getRawMaterialNum().isEmpty()) {
				buffer.append(" AND r.`raw_material_num`="
						+ selectBills.getRawMaterialNum());
			}
			if (selectBills.getRawMaterialBigId() > 0) {
				buffer.append(" AND s.`raw_material_big_id`="
						+ selectBills.getRawMaterialBigId());
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			buffer.append(" ORDER BY w.`warehouseId`,r.`raw_material_id` LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getNowRepertoryTotalRow(SelectBills selectBills) {
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer(getNowRepertoryTotalRow);
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" WHERE p.`warehouseId`="
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getRawMaterialNum())
					&& !selectBills.getRawMaterialNum().isEmpty()) {
				buffer.append(" AND r.`raw_material_num`="
						+ selectBills.getRawMaterialNum());
			}
			if (selectBills.getRawMaterialBigId() > 0) {
				buffer.append(" AND s.`raw_material_big_id`="
						+ selectBills.getRawMaterialBigId());
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
	public List<RepertoryInfo> selectRepertory(SelectBills selectBills,
			Page page, String findType) {// 查询库存原料信息
		List<RepertoryInfo> infos = new ArrayList<RepertoryInfo>();
		try {
			con = DBUtil.getConnection();
			StringBuffer buffer = new StringBuffer();
			// 根据findType参数判断不同库存原料信息
			if ("findInOrOutRepertory".equals(findType)) {// 库存进销查询
				buffer.append(selectRepertory);
				if (selectBills.getWarehouseId() > 0) {
					buffer.append(" AND p.`warehouseId`="
							+ selectBills.getWarehouseId());
				}
				if (!"".equals(selectBills.getRawMaterialNum())
						&& !selectBills.getRawMaterialNum().isEmpty()) {
					buffer.append(" AND r.`raw_material_num`="
							+ selectBills.getRawMaterialNum());
				}
				if (!"".equals(selectBills.getRawMaterialName())
						&& !selectBills.getRawMaterialName().isEmpty()) {
					buffer.append(" AND r.`raw_material_name` like '%"
							+ selectBills.getRawMaterialName() + "%'");
				}
				buffer.append(" GROUP BY b.`warehouseId`,p.`raw_material_id` ORDER BY p.`warehouseId`,"
						+ "p.`raw_material_id` LIMIT ?,?");
				ps = con.prepareStatement(buffer.toString());
				ps.setString(1, selectBills.getMonth() + "%");
				ps.setString(2, selectBills.getMonth() + "%");
				ps.setString(3, selectBills.getMonth() + "%");
				ps.setString(4, selectBills.getMonth() + "%");
				ps.setInt(5, page.getStartIndex());
				ps.setInt(6, page.getLimit());
			} else if ("findRepertoryDetail".equals(findType)) {// 库存明细查询
				buffer.append(findRepertoryDetail);
				if (selectBills.getWarehouseId() > 0) {
					buffer.append(" AND p.`warehouseId`="
							+ selectBills.getWarehouseId());
				}
				if (!"".equals(selectBills.getRawMaterialNum())
						&& !selectBills.getRawMaterialNum().isEmpty()) {
					buffer.append(" AND r.`raw_material_num`="
							+ selectBills.getRawMaterialNum());
				}
				if (!"".equals(selectBills.getRawMaterialName())
						&& !selectBills.getRawMaterialName().isEmpty()) {
					buffer.append(" AND r.`raw_material_name` like '%"
							+ selectBills.getRawMaterialName() + "%'");
				}
				buffer.append(" GROUP BY b.`warehouseId`,p.`raw_material_id` ORDER BY p.`warehouseId`,"
						+ "p.`raw_material_id` LIMIT ?,?");
				ps = con.prepareStatement(buffer.toString());
				for (int i = 1; i < 15; i = i + 2) {
					ps.setString(i, selectBills.getStartTime());
					ps.setString(i + 1, selectBills.getEndTime());
				}
				ps.setInt(15, page.getStartIndex());
				ps.setInt(16, page.getLimit());
			}
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RepertoryInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getRepertoryTotalRows(SelectBills selectBills, String findType) {
		long totalRow = 0;
		try {
			con = DBUtil.getConnection();
			StringBuffer buffer = new StringBuffer();
			if ("findInOrOutRepertory".equals(findType)) {
				buffer.append(getRepertoryTotalRows);
				if (selectBills.getWarehouseId() > 0) {
					buffer.append(" AND p.`warehouseId`="
							+ selectBills.getWarehouseId());
				}
				if (!"".equals(selectBills.getRawMaterialNum())
						&& !selectBills.getRawMaterialNum().isEmpty()) {
					buffer.append(" AND r.`raw_material_num`="
							+ selectBills.getRawMaterialNum());
				}
				if (!"".equals(selectBills.getRawMaterialName())
						&& !selectBills.getRawMaterialName().isEmpty()) {
					buffer.append(" AND r.`raw_material_name` like '%"
							+ selectBills.getRawMaterialName() + "%'");
				}
				buffer.append(" GROUP BY b.`warehouseId`,p.`raw_material_id`) a");
				ps = con.prepareStatement(buffer.toString());
				ps.setString(1, selectBills.getMonth() + "%");
			} else if ("findRepertoryDetail".equals(findType)) {
				buffer.append(getRepertoryDetailTotalRows);
				if (selectBills.getWarehouseId() > 0) {
					buffer.append(" AND p.`warehouseId`="
							+ selectBills.getWarehouseId());
				}
				if (!"".equals(selectBills.getRawMaterialNum())
						&& !selectBills.getRawMaterialNum().isEmpty()) {
					buffer.append(" AND r.`raw_material_num`="
							+ selectBills.getRawMaterialNum());
				}
				if (!"".equals(selectBills.getRawMaterialName())
						&& !selectBills.getRawMaterialName().isEmpty()) {
					buffer.append(" AND r.`raw_material_name` like '%"
							+ selectBills.getRawMaterialName() + "%'");
				}
				buffer.append(" GROUP BY b.`warehouseId`,p.`raw_material_id`) a");
				ps = con.prepareStatement(buffer.toString());
				ps.setString(1, selectBills.getStartTime());
				ps.setString(2, selectBills.getEndTime());
			}
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
	public List<BillsInfo> selectSupplier(SelectBills selectBills, Page page) {// 查询供应商供货信息
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BillsInfo> infos = new ArrayList<BillsInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectSupplier);
			// 查询条件拼接
			if (selectBills.getSupplierId() > 0) {
				buffer.append(" WHERE p.`supplierId`="
						+ selectBills.getSupplierId());
			}
			if (selectBills.getRawMaterialBigId() > 0) {
				buffer.append(" AND s.`raw_material_big_id`="
						+ selectBills.getRawMaterialBigId());
			}
			if (selectBills.getStaffId() > 0) {
				buffer.append(" AND p.`staffId`=" + selectBills.getStaffId());
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" AND b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			buffer.append(" ORDER BY b.`bills_id`,r.`raw_material_id` LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, BillsInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getSupplierTotalRow(SelectBills selectBills) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer(getSupplierTotalRow);
			if (selectBills.getSupplierId() > 0) {
				buffer.append(" WHERE p.`supplierId`="
						+ selectBills.getSupplierId());
			}
			if (selectBills.getRawMaterialBigId() > 0) {
				buffer.append(" AND s.`raw_material_big_id`="
						+ selectBills.getRawMaterialBigId());
			}
			if (selectBills.getStaffId() > 0) {
				buffer.append(" AND p.`staffId`=" + selectBills.getStaffId());
			}
			if (!"".equals(selectBills.getStartTime())
					&& !selectBills.getStartTime().isEmpty()
					&& !"".equals(selectBills.getEndTime())
					&& !selectBills.getEndTime().isEmpty()) {
				buffer.append(" AND b.`bills_entry_time` BETWEEN '"
						+ selectBills.getStartTime() + "' AND '"
						+ selectBills.getEndTime() + "'");
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
	public List<SupplierPaymentInfo> findPaymentInfo(SelectBills selectBills,
			Page page, String findType) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SupplierPaymentInfo> infos = new ArrayList<SupplierPaymentInfo>();
		try {
			StringBuffer buffer = new StringBuffer();
			// 根据findType参数判断查询不同的供应商信息
			if ("findSupplierPayment".equals(findType)) {// 供应商应付款信息
				buffer.append("SELECT *, s.`total` - (CASE WHEN (SELECT sp.`total_money` "
						+ "FROM `pw_supplier_payment` sp WHERE s.`supplierId` = sp.`supplierId`");
				if (!"".equals(selectBills.getStartTime())
						&& !selectBills.getStartTime().isEmpty()
						&& !"".equals(selectBills.getEndTime())
						&& !selectBills.getEndTime().isEmpty()) {
					if ("2000-01-01".equals(selectBills.getStartTime())
							&& "2100-12-31".equals(selectBills.getEndTime())) {
						buffer.append(") IS NULL THEN '0' ELSE (SELECT sp.`total_money` "
								+ "FROM `pw_supplier_payment` sp WHERE s.`supplierId` = sp.`supplierId`) "
								+ "END) totalMoney FROM" + findPaymentInfo);
					} else {
						buffer.append(" AND sp.`start_date` <= '"
								+ selectBills.getStartTime()
								+ "' AND sp.`end_date` >= '"
								+ selectBills.getEndTime()
								+ "') IS NULL THEN '0' "
								+ "ELSE (SELECT sp.`total_money` FROM `pw_supplier_payment` sp WHERE "
								+ "s.`supplierId` = sp.`supplierId` AND sp.`start_date` <= '"
								+ selectBills.getStartTime()
								+ "' AND sp.`end_date` >= '"
								+ selectBills.getEndTime()
								+ "') END) totalMoney FROM" + findPaymentInfo);
					}
					buffer.append(" WHERE b.`bills_entry_time` BETWEEN '"
							+ selectBills.getStartTime() + "' AND '"
							+ selectBills.getEndTime()
							+ "') supplier GROUP BY supplierId) s ");
				}
				if (selectBills.getSupplierId() > 0) {
					buffer.append(" WHERE s.`supplierId`="
							+ selectBills.getSupplierId());
				}
				buffer.append(" LIMIT ?,?");
			} else if ("findSupplierPaymentInfo".equals(findType)) {// 供应商付款记录信息
				buffer.append(selectPaymentInfo);
				if (selectBills.getSupplierId() > 0) {
					buffer.append(" WHERE s.`supplierId`="
							+ selectBills.getSupplierId());
				}
				buffer.append(" ORDER BY a.`supplier_payment_id` DESC LIMIT ?,?");
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, SupplierPaymentInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getPaymentTotalRow(SelectBills selectBills, String findType) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			if ("findSupplierPayment".equals(findType)) {
				buffer.append("SELECT COUNT(*) FROM " + findPaymentInfo);
				if (!"".equals(selectBills.getStartTime())
						&& !selectBills.getStartTime().isEmpty()
						&& !"".equals(selectBills.getEndTime())
						&& !selectBills.getEndTime().isEmpty()) {
					buffer.append(" WHERE b.`bills_entry_time` BETWEEN '"
							+ selectBills.getStartTime() + "' AND '"
							+ selectBills.getEndTime()
							+ "') supplier GROUP BY supplierId) s ");
				}
				if (selectBills.getSupplierId() > 0) {
					buffer.append(" WHERE s.`supplierId`="
							+ selectBills.getSupplierId());
				}
			} else if ("findSupplierPaymentInfo".equals(findType)) {
				buffer.append(getPaymentTotalRows);
				if (selectBills.getSupplierId() > 0) {
					buffer.append(" WHERE s.`supplierId`="
							+ selectBills.getSupplierId());
				}
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
	public int insertPayment(SupplierPayment payment) {
		// 调用DaoHelper反射类的insertUpdate新增或修改方法新增供应商付款信息
		int flag = DaoHelper
				.insertUpdate(
						"INSERT INTO `pw_supplier_payment`(supplierId,total_money,"
								+ "payment_date,start_date,end_date) VALUES(?,?,?,?,?)",
						payment);
		return flag;
	}

	@Override
	public int insertMinimumStock(MinimumStock stock) {// 新增仓库最低库存信息
		int flag = 0;
		MinimumStock minimumStock = new MinimumStock();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM `pw_minimum_stock` WHERE repertory_id=?");
			ps.setInt(1, stock.getRepertoryId());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getSingleResult方法获取单条数据
			minimumStock = JdbcHelper.getSingleResult(rs, MinimumStock.class);
			if (minimumStock == null) {// 判断是否设置最低库存，若没有，则新增最低库存信息
				flag = DaoHelper.insertUpdate(
						"INSERT INTO pw_minimum_stock(repertory_id,minimum_quantity)"
								+ " VALUES(?,?)", stock);
			} else {// 否则更新最低库存信息
				stock.setMinimumStockId(minimumStock.getMinimumStockId());
				flag = DaoHelper.insertUpdate(
						"UPDATE pw_minimum_stock SET repertory_id=?,minimum_quantity=?"
								+ " WHERE minimum_stock_id=?", stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public List<RawMaterialInfo> selectMinimumStock(SelectBills selectBills,
			Page page) {// 查询最低库存信息
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectMinimumStock);
			// 查询条件拼接
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" WHERE p.`warehouseId`="
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getRawMaterialNum())
					&& !selectBills.getRawMaterialNum().isEmpty()) {
				buffer.append(" AND r.`raw_material_num`="
						+ selectBills.getRawMaterialNum());
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			buffer.append(" ORDER BY p.`warehouseId`,p.`raw_material_id` LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getMinimumStockTotalRow(SelectBills selectBills) {
		long totalRow = 0;
		try {
			StringBuffer buffer = new StringBuffer(getMinimumStockTotalRow);
			// 查询条件拼接
			if (selectBills.getWarehouseId() > 0) {
				buffer.append(" WHERE p.`warehouseId`="
						+ selectBills.getWarehouseId());
			}
			if (!"".equals(selectBills.getRawMaterialNum())
					&& !selectBills.getRawMaterialNum().isEmpty()) {
				buffer.append(" AND r.`raw_material_num`="
						+ selectBills.getRawMaterialNum());
			}
			if (selectBills.getRawMaterialBigId() > 0) {
				buffer.append(" AND s.`raw_material_big_id`="
						+ selectBills.getRawMaterialBigId());
			}
			if (!"".equals(selectBills.getRawMaterialName())
					&& !selectBills.getRawMaterialName().isEmpty()) {
				buffer.append(" AND r.`raw_material_name` like '%"
						+ selectBills.getRawMaterialName() + "%'");
			}
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
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
