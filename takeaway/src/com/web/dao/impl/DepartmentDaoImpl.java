package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IDepartmentDao;
import com.web.po.Department;
import com.web.po.MaterialRequirement;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.DepartmentInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class DepartmentDaoImpl implements IDepartmentDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String selectAll = "SELECT d.*,s.staffName FROM pw_department d JOIN pw_staff s "
			+ "ON d.staffId = s.staffId ORDER BY departmentId DESC LIMIT ?,?";
	private String getTotalRows = "SELECT COUNT(*) FROM pw_department d JOIN pw_staff s "
			+ "ON d.staffId = s.staffId";
	private String findById = "SELECT * FROM pw_department WHERE departmentId=?";
	private String insert = "INSERT INTO pw_department(staffId,departmentName,departmentTell,"
			+ "remark) VALUES(?,?,?,?)";
	private String update = "UPDATE pw_department SET staffId=?,departmentName=?,"
			+ "departmentTell=?,remark=? WHERE departmentId=?";
	private String delete = "DELETE FROM pw_department WHERE departmentId=?";
	private String findRequirement = "SELECT m.*,r.`raw_material_num`,r.`raw_material_name`,"
			+ "r.`raw_material_price`,r.`raw_material_small_id`,s.`raw_material_big_id`,r.`unitId`,"
			+ "u.`unitName` FROM `pw_material_requirement` m JOIN `pw_raw_material` r "
			+ "ON m.`raw_material_id` = r.`raw_material_id` JOIN `sys_raw_material_small` s "
			+ "ON r.`raw_material_small_id` = s.`raw_material_small_id` JOIN `sys_raw_material_big` b "
			+ "ON s.`raw_material_big_id` = b.`raw_material_big_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` WHERE m.`departmentId`=? ORDER BY r.`raw_material_id` LIMIT ?,?";
	private String getTotalRow = "SELECT COUNT(*) FROM `pw_material_requirement` m JOIN `pw_raw_material` r "
			+ "ON m.`raw_material_id` = r.`raw_material_id` JOIN `sys_raw_material_small` s ON "
			+ "r.`raw_material_small_id` = s.`raw_material_small_id` JOIN `sys_raw_material_big` b "
			+ "ON s.`raw_material_big_id` = b.`raw_material_big_id` JOIN `sys_unit` u "
			+ "ON r.`unitId` = u.`unitId` WHERE m.`departmentId`=?";
	private String insertRequirement = "INSERT INTO pw_material_requirement(departmentId,raw_material_id,"
			+ "quantity_required) VALUES(?,?,?)";
	private String updateRequirement = "UPDATE pw_material_requirement SET departmentId=?,raw_material_id=?,"
			+ "quantity_required=? WHERE material_requirement_id=?";

	@Override
	public List<Department> selectAll() {
		List<Department> departments = new ArrayList<Department>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM pw_department");
			rs = ps.executeQuery();
			departments = JdbcHelper.getResult(rs, Department.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return departments;
	}

	@Override
	public Department findById(int id) {
		Department department = DaoHelper.findByID(findById, Department.class,
				id);
		return department;
	}

	@Override
	public int insert(Department department) {
		int flag = DaoHelper.insertUpdate(insert, department);
		return flag;
	}

	@Override
	public int update(Department department) {
		int flag = DaoHelper.insertUpdate(update, department);
		return flag;
	}

	@Override
	public int delete(int id) {
		int flag = DaoHelper.delete(delete, id);
		return flag;
	}

	@Override
	public List<DepartmentInfo> selectAll(Page page) {
		List<DepartmentInfo> infos = new ArrayList<DepartmentInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectAll);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			infos = JdbcHelper.getResult(rs, DepartmentInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return infos;
	}

	@Override
	public long getTotalRows() {
		long totalRows = DaoHelper.getTotalRow(getTotalRows);
		return totalRows;
	}

	@Override
	public List<RawMaterialInfo> findRequirement(Page page, int departmentId) {
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findRequirement);
			ps.setInt(1, departmentId);
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
	public long getTotalRow(int departmentId) {
		long totalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(getTotalRow);
			ps.setInt(1, departmentId);
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
	public int insert(MaterialRequirement requirement) {
		int flag = DaoHelper.insertUpdate(insertRequirement, requirement);
		return flag;
	}

	@Override
	public int update(MaterialRequirement requirement) {
		int flag = DaoHelper.insertUpdate(updateRequirement, requirement);
		return flag;
	}

}
