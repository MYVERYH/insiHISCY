package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IWarehouseDao;
import com.web.po.Warehouse;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.Page;
import com.web.vo.WarehouseInfo;

public class WarehouseDaoImpl implements IWarehouseDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String selectAll = "SELECT * FROM pw_warehouse w JOIN pw_staff s "
			+ "ON w.staffId = s.staffId  LIMIT ?,?";
	private String getTotalRows = "SELECT COUNT(*) FROM pw_warehouse w JOIN pw_staff s "
			+ "ON w.staffId = s.staffId";
	private String findById = "SELECT * FROM pw_warehouse WHERE warehouseId=?";
	private String insert = "INSERT INTO pw_warehouse(staffId,warehouseNum,warehouseName,warehouseRemark) "
			+ "VALUES(?,?,?,?)";
	private String update = "UPDATE pw_warehouse SET staffId=?,warehouseNum=?,warehouseName=?,"
			+ "warehouseRemark=? WHERE warehouseId=?";
	private String delete = "DELETE FROM pw_warehouse WHERE warehouseId=?";

	@Override
	public List<Warehouse> selectAll() {
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM pw_warehouse");
			rs = ps.executeQuery();
			warehouses = JdbcHelper.getResult(rs, Warehouse.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return warehouses;
	}

	@Override
	public Warehouse findById(int id) {
		Warehouse warehouse = new Warehouse();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findById);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			warehouse = JdbcHelper.getSingleResult(rs, Warehouse.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return warehouse;
	}

	@Override
	public int insert(Warehouse warehouse) {
		int flag = DaoHelper.insertUpdate(insert, warehouse);
		return flag;
	}

	@Override
	public int update(Warehouse warehouse) {
		int flag = DaoHelper.insertUpdate(update, warehouse);
		return flag;
	}

	@Override
	public int delete(int id) {
		int flag = DaoHelper.delete(delete, id);
		return flag;
	}

	@Override
	public List<WarehouseInfo> selectAll(Page page) {
		List<WarehouseInfo> infos = new ArrayList<WarehouseInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectAll);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			infos = JdbcHelper.getResult(rs, WarehouseInfo.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getTotalRows() {
		long row = DaoHelper.getTotalRow(getTotalRows);
		return row;
	}

}
