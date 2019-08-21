package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IStaffDao;
import com.web.po.Position;
import com.web.po.Staff;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.Page;
import com.web.vo.StaffInfo;

public class StaffDaoImpl implements IStaffDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String findAll = "select s.*,d.departmentTell,d.departmentName,p.positionName from pw_staff s join pw_department d "
			+ "on s.departmentId = d.departmentId join pw_position p "
			+ "on s.positionId = p.positionId limit ?,?";
	private String getTotalRow = "select count(*) from pw_staff s join pw_department d "
			+ "on s.departmentId = d.departmentId join pw_position p "
			+ "on s.positionId = p.positionId";
	private String findById = "select * from pw_staff where staffId=?";
	private String insert = "insert into pw_staff(departmentId,positionId,staffNum,staffName,"
			+ "staffPhone,staffRemark,staffAddress) values(?,?,?,?,?,?,?)";
	private String update = "update pw_staff set departmentId=?,positionId=?,staffNum=?,"
			+ "staffName=?,staffPhone=?,staffRemark=?,staffAddress=? where staffId=?";
	private String delete = "delete from pw_staff where staffId=?";
	private String findAlls = "select * from pw_position";

	@Override
	public List<Staff> selectAll() {
		List<Staff> staffs = new ArrayList<Staff>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM pw_staff");
			rs = ps.executeQuery();
			staffs = JdbcHelper.getResult(rs, Staff.class);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return staffs;
	}

	@Override
	public Staff findById(int id) {
		Staff staff = new Staff();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findById);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			staff = JdbcHelper.getSingleResult(rs, Staff.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return staff;
	}

	@Override
	public int insert(Staff staff) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(insert, staff);
		return flag;
	}

	@Override
	public int update(Staff staff) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(update, staff);
		return flag;
	}

	@Override
	public int delete(int id) {
		int flag = 0;
		flag = DaoHelper.delete(delete, id);
		return flag;
	}

	@Override
	public List<StaffInfo> findAll(Page page) {
		List<StaffInfo> staffInfos = new ArrayList<StaffInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findAll);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			staffInfos = JdbcHelper.getResult(rs, StaffInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return staffInfos;
	}

	@Override
	public long getTotalRow() {
		long intTotalRow = DaoHelper.getTotalRow(getTotalRow);
		return intTotalRow;
	}

	@Override
	public List<Position> findAll() {
		List<Position> positions = new ArrayList<Position>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findAlls);
			rs = ps.executeQuery();
			positions = JdbcHelper.getResult(rs, Position.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return positions;
	}

}
