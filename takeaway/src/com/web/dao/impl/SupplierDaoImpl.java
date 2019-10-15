package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.ISupplierDao;
import com.web.po.Supplier;
import com.web.po.SupplierDetail;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.Page;
import com.web.vo.SupplierInfo;

public class SupplierDaoImpl implements ISupplierDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String selectAll = "SELECT s.*,r.raw_material_big_name,d.supplierDetailId,"
			+ "d.supplierPrincipal,d.supplierLinkman,d.supplierPhone,d.supplierTell,d.supplierSite,"
			+ "d.supplierMail,d.supplierRemark FROM pw_supplier s JOIN sys_raw_material_big r "
			+ "ON s.raw_material_big_id = r.raw_material_big_id JOIN pw_supplier_detail d "
			+ "ON s.supplierId = d.supplierId WHERE supplierNum LIKE ? AND supplierName LIKE ? LIMIT ?,?";
	private String getTotalRows = "SELECT COUNT(*) FROM pw_supplier s JOIN sys_raw_material_big r "
			+ "ON s.raw_material_big_id = r.raw_material_big_id JOIN pw_supplier_detail d "
			+ "ON s.supplierId = d.supplierId WHERE supplierNum LIKE ? AND supplierName LIKE ?";
	private String findById = "SELECT * FROM pw_supplier s JOIN pw_supplier_detail d "
			+ "ON s.supplierId = d.supplierId WHERE s.supplierId=?";
	private String insert = "INSERT INTO pw_supplier(raw_material_big_id,supplierNum,"
			+ "supplierName) VALUES(?,?,?)";
	private String update = "UPDATE pw_supplier SET raw_material_big_id=?,supplierNum=?,"
			+ "supplierName=? WHERE supplierId=?";
	private String delete = "DELETE FROM pw_supplier WHERE supplierId=?";
	private String insertDetail = "INSERT INTO pw_supplier_detail(supplierId,supplierPrincipal,"
			+ "supplierLinkman,supplierPhone,supplierTell,supplierSite,supplierMail,supplierRemark) VALUES("
			+ "?,?,?,?,?,?,?,?)";
	private String updateDetail = "UPDATE pw_supplier_detail SET supplierId=?,supplierPrincipal=?,"
			+ "supplierLinkman=?,supplierPhone=?,supplierTell=?,supplierSite=?,supplierMail=?,"
			+ "supplierRemark=? WHERE supplierId=?";
	private String deleteDetial = "DELETE FROM pw_supplier_detail WHERE supplierId=?";

	@Override
	public List<Supplier> selectAll() {//查询所有供应商
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM pw_supplier");
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			suppliers = JdbcHelper.getResult(rs, Supplier.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return suppliers;
	}

	@Override
	public Supplier findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Supplier t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Supplier t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SupplierInfo> selectAll(Page page, String supplierNum,
			String supplierName) {
		List<SupplierInfo> infos = new ArrayList<SupplierInfo>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(selectAll);
			ps.setString(1, "%" + supplierNum + "%");
			ps.setString(2, "%" + supplierName + "%");
			ps.setInt(3, page.getStartIndex());
			ps.setInt(4, page.getLimit());
			rs = ps.executeQuery();
			infos = JdbcHelper.getResult(rs, SupplierInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return infos;
	}

	@Override
	public long getTotalRows(String supplierNum, String supplierName) {
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(getTotalRows);
			ps.setString(1, "%" + supplierNum + "%");
			ps.setString(2, "%" + supplierName + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				intTotalRow = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return intTotalRow;
	}

	@Override
	public SupplierInfo selectById(int supplierId) {
		SupplierInfo info = DaoHelper.findByID(findById, SupplierInfo.class,
				supplierId);
		return info;
	}

	@Override
	public int insert(Supplier supplier, SupplierDetail detail) {//新增供应商信息
		int flag = 0;
		int key = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			con.setAutoCommit(false);//关闭自动提交
			key = DaoHelper.setPsToSQLException(con, supplier, insert);
			if (key > 0) {//判断新增供应商是否成功
				detail.setSupplierId(key);
				flag = DaoHelper.setPsToSQLException(con, detail, insertDetail);
				con.commit();
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
	public int update(Supplier supplier, SupplierDetail detail) {//修改供应商信息
		int flag = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			con.setAutoCommit(false);//关闭自动提交
			int key = DaoHelper.setPsToSQLException(con, supplier, update);
			if (key > 0) {//判断修改供应商信息是否成功
				flag = DaoHelper.setPsToSQLException(con, detail, updateDetail);
				con.commit();
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
	public int delSupplier(int id) {//删除供应商信息
		int flag = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			con.setAutoCommit(false);//关闭自动提交
			ps = con.prepareStatement(deleteDetial);
			ps.setInt(1, id);
			int key = ps.executeUpdate();
			if (key > 0) {//判断删除供应商明细是否成功
				ps = con.prepareStatement(delete);
				ps.setInt(1, id);
				flag = ps.executeUpdate();
				con.commit();
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

}
