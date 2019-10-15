package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IRawMaterialDao;
import com.web.po.RawMaterial;
import com.web.po.RawMaterialBig;
import com.web.po.RawMaterialSmall;
import com.web.po.Unit;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class RawMaterialDaoImpl implements IRawMaterialDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String findById = "SELECT * FROM pw_raw_material WHERE raw_material_id=?";
	private String insert = "INSERT INTO pw_raw_material(unitId,raw_material_small_id,"
			+ "raw_material_num,raw_material_name,raw_material_price,pinyin_code) VALUES(?,?,?,?,?,?)";
	private String update = "UPDATE pw_raw_material SET unitId=?,raw_material_small_id=?,"
			+ "raw_material_num=?,raw_material_name=?,raw_material_price=?,pinyin_code=? "
			+ "WHERE raw_material_id=?";
	private String delete = "DELETE FROM pw_raw_material WHERE raw_material_id=?";
	private String findBig = "SELECT * FROM sys_raw_material_big LIMIT ?,?";
	private String getBigTotalRow = "SELECT COUNT(*) FROM sys_raw_material_big";
	private String findBigById = "SELECT * FROM sys_raw_material_big WHERE raw_material_big_id=?";
	private String insertBig = "INSERT INTO sys_raw_material_big(raw_material_big_num,"
			+ "raw_material_big_name) VALUES(?,?)";
	private String updateBig = "UPDATE sys_raw_material_big SET raw_material_big_num=?,"
			+ "raw_material_big_name=? WHERE raw_material_big_id=?";
	private String deleteBig = "DELETE FROM sys_raw_material_big WHERE raw_material_big_id=?";
	private String findSmall = "SELECT * FROM sys_raw_material_small WHERE raw_material_big_id=? LIMIT ?,?";
	private String getSmallTotalRow = "SELECT COUNT(*) FROM sys_raw_material_small "
			+ "WHERE raw_material_big_id=?";
	private String findSmallById = "SELECT * FROM sys_raw_material_small WHERE raw_material_small_id=?";
	private String insertSmall = "INSERT INTO sys_raw_material_small(raw_material_big_id,"
			+ "raw_material_small_num,raw_material_small_name) VALUES(?,?,?)";
	private String updateSmall = "UPDATE sys_raw_material_small SET raw_material_big_id=?,"
			+ "raw_material_small_num=?,raw_material_small_name=? WHERE raw_material_small_id=?";
	private String delSmall = "DELETE FROM sys_raw_material_small WHERE raw_material_small_id=?";
	private String findAll = "SELECT * FROM pw_raw_material r JOIN sys_unit u ON r.unitId = u.unitId "
			+ "WHERE raw_material_small_id=? LIMIT ?,?";
	private String getTotalRow = "SELECT COUNT(*) FROM pw_raw_material r JOIN sys_unit u "
			+ "ON r.unitId = u.unitId WHERE raw_material_small_id=?";
	private String selectAll = "SELECT r.*,s.raw_material_big_id,b.raw_material_big_name,u.unitName "
			+ "FROM pw_raw_material r JOIN sys_raw_material_small s "
			+ "ON r.raw_material_small_id = s.raw_material_small_id JOIN sys_raw_material_big b "
			+ "ON s.raw_material_big_id = b.raw_material_big_id JOIN sys_unit u "
			+ "ON r.unitId = u.unitId WHERE s.raw_material_big_id=(SELECT raw_material_big_id "
			+ "FROM pw_supplier WHERE supplierId=?) ORDER BY r.raw_material_id LIMIT ?,?";
	private String gettotalrow = "SELECT COUNT(*) FROM pw_raw_material r JOIN sys_raw_material_small s "
			+ "ON r.raw_material_small_id = s.raw_material_small_id JOIN sys_raw_material_big b "
			+ "ON s.raw_material_big_id = b.raw_material_big_id JOIN sys_unit u "
			+ "ON r.unitId = u.unitId WHERE s.raw_material_big_id=(SELECT raw_material_big_id "
			+ "FROM pw_supplier WHERE supplierId=?)";
	private String finSmall = "SELECT * FROM sys_raw_material_small WHERE raw_material_big_id="
			+ "(SELECT raw_material_big_id FROM pw_supplier WHERE supplierId=?)";

	@Override
	public List<RawMaterial> selectAll() {//查询原料信息
		List<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT raw_material_num FROM pw_raw_material");
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			rawMaterials = JdbcHelper.getResult(rs, RawMaterial.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return rawMaterials;
	}

	@Override
	public RawMaterial findById(int id) {
		//调用DaoHelper反射类的findByID方法根据id查询原料信息
		RawMaterial rawMaterial = DaoHelper.findByID(findById,
				RawMaterial.class, id);
		return rawMaterial;
	}

	@Override
	public int insert(RawMaterial rawMaterial) {
		//调用DaoHelper反射类的insertUpdate方法新增原料信息
		int flag = DaoHelper.insertUpdate(insert, rawMaterial);
		return flag;
	}

	@Override
	public int update(RawMaterial rawMaterial) {
		//调用DaoHelper反射类的insertUpdate方法修改原料信息
		int flag = DaoHelper.insertUpdate(update, rawMaterial);
		return flag;
	}

	@Override
	public int delete(int id) {
		//调用DaoHelper反射类的delete方法删除原料信息
		int flag = DaoHelper.delete(delete, id);
		return flag;
	}

	@Override
	public List<RawMaterialBig> findBig(Page page) {//查询原料大类信息
		List<RawMaterialBig> rawMaterialBigs = new ArrayList<RawMaterialBig>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findBig);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			rawMaterialBigs = JdbcHelper.getResult(rs, RawMaterialBig.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return rawMaterialBigs;
	}

	@Override
	public long getBigTotalRow() {
		//调用DaoHelper反射类的getTotalRow方法获取原料大类信息总条数
		long intTotalRow = DaoHelper.getTotalRow(getBigTotalRow);
		return intTotalRow;
	}

	@Override
	public RawMaterialBig findBigById(int id) {
		//调用DaoHelper反射类的findByID方法根据id查询原料大类信息
		RawMaterialBig rawMaterialBig = DaoHelper.findByID(findBigById,
				RawMaterialBig.class, id);
		return rawMaterialBig;
	}

	@Override
	public int insert(RawMaterialBig big) {
		//调用DaoHelper反射类的insertUpdate方法新增原料大类信息
		int flag = DaoHelper.insertUpdate(insertBig, big);
		return flag;
	}

	@Override
	public int update(RawMaterialBig big) {
		//调用DaoHelper反射类的insertUpdate方法修改原料大类信息
		int flag = DaoHelper.insertUpdate(updateBig, big);
		return flag;
	}

	@Override
	public int delBig(int id) {
		//调用DaoHelper反射类的insertUpdate方法删除原料大类信息
		int flag = DaoHelper.delete(deleteBig, id);
		return flag;
	}

	@Override
	public List<RawMaterialSmall> findSmall(Page page, int id) {//查询原料小类信息
		List<RawMaterialSmall> smalls = new ArrayList<RawMaterialSmall>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findSmall);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			smalls = JdbcHelper.getResult(rs, RawMaterialSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return smalls;
	}

	@Override
	public long getSmallTotalRow(int id) {//根据原料大类id获取原料小类信息总条数
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(getSmallTotalRow);
			ps.setInt(1, id);
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
	public RawMaterialSmall findSmallById(int id) {
		//调用DaoHelper反射类的findByID方法根据id查询原料小类信息
		RawMaterialSmall rawMaterialSmall = DaoHelper.findByID(findSmallById,
				RawMaterialSmall.class, id);
		return rawMaterialSmall;
	}

	@Override
	public int insert(RawMaterialSmall small) {
		//调用DaoHelper反射类的insertUpdate方法新增原料小类信息
		int flag = DaoHelper.insertUpdate(insertSmall, small);
		return flag;
	}

	@Override
	public int update(RawMaterialSmall small) {
		//调用DaoHelper反射类的insertUpdate方法修改原料小类信息
		int flag = DaoHelper.insertUpdate(updateSmall, small);
		return flag;
	}

	@Override
	public int delSmall(int id) {
		//调用DaoHelper反射类的delete方法删除原料小类信息
		int flag = DaoHelper.delete(delSmall, id);
		return flag;
	}

	@Override
	public List<RawMaterial> findAll(Page page, int id) {//根据原料小类id查询原料信息
		List<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findAll);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			rawMaterials = JdbcHelper.getResult(rs, RawMaterial.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return rawMaterials;
	}

	@Override
	public long getTotalRow(int id) {//根据原料小类id查询原料信息总条数
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(getTotalRow);
			ps.setInt(1, id);
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
	public List<RawMaterialBig> finBig() {//查询原料大类
		List<RawMaterialBig> bigs = new ArrayList<RawMaterialBig>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM sys_raw_material_big");
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			bigs = JdbcHelper.getResult(rs, RawMaterialBig.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return bigs;
	}

	@Override
	public List<RawMaterialSmall> finSmall() {//查询原料小类
		List<RawMaterialSmall> smalls = new ArrayList<RawMaterialSmall>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM sys_raw_material_small");
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			smalls = JdbcHelper.getResult(rs, RawMaterialSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return smalls;
	}

	@Override
	public List<Unit> findUnit() {//查询原料单位
		List<Unit> units = new ArrayList<Unit>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM sys_unit");
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			units = JdbcHelper.getResult(rs, Unit.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return units;
	}

	@Override
	public List<RawMaterialInfo> selectAll(Page page, int id) {
		List<RawMaterialInfo> rawMaterials = new ArrayList<RawMaterialInfo>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectAll);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			rawMaterials = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return rawMaterials;
	}

	@Override
	public long getTotalRows(int id) {
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(gettotalrow);
			ps.setInt(1, id);
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

	@Override
	public List<RawMaterialSmall> finSmall(int id) {// 根据id查询原料小类信息
		List<RawMaterialSmall> smalls = new ArrayList<RawMaterialSmall>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(finSmall);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			smalls = JdbcHelper.getResult(rs, RawMaterialSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return smalls;
	}

	@Override
	public List<RawMaterialSmall> selectSmall(int id) {// 根据id查询原料小类信息
		List<RawMaterialSmall> smalls = new ArrayList<RawMaterialSmall>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM sys_raw_material_small WHERE raw_material_big_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			smalls = JdbcHelper.getResult(rs, RawMaterialSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return smalls;
	}

	@Override
	public List<RawMaterial> selectRawMaterial(int id) {// 根据id查询原料信息
		List<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement("SELECT * FROM pw_raw_material WHERE raw_material_small_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			rawMaterials = JdbcHelper.getResult(rs, RawMaterial.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return rawMaterials;
	}

	@Override
	public List<RawMaterialInfo> selectAll(Page page, String num, String name) {//多条件查询原料信息
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		try {
			StringBuilder builder = new StringBuilder(
					"SELECT * FROM `pw_raw_material` r JOIN `sys_unit` u ON r.`unitId` = u.`unitId`");
			//查询条件拼接
			if (!"".equals(num) && !num.isEmpty()) {
				builder.append(" AND r.`raw_material_num` = '" + num + "'");
			}
			if (!"".equals(name) && !name.isEmpty()) {
				builder.append(" AND r.`raw_material_name` = '" + name + "'");
			}
			builder.append(" ORDER BY r.`raw_material_id` LIMIT ?,?");
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, RawMaterialInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return infos;
	}

	@Override
	public long getTotalRows(String num, String name) {
		StringBuilder builder = new StringBuilder(
				"SELECT COUNT(*) FROM `pw_raw_material` r JOIN `sys_unit` u ON r.`unitId` = u.`unitId`");
		//查询条件拼接
		if (!"".equals(num) && !num.isEmpty()) {
			builder.append(" AND r.`raw_material_num` = " + num);
		}
		if (!"".equals(name) && !name.isEmpty()) {
			builder.append(" AND r.`raw_material_name` = " + name);
		}
		//调用DaoHelper反射类的getTotalRow方法获取原料信息总条数
		long intTotalRow = DaoHelper.getTotalRow(builder.toString());
		return intTotalRow;
	}

}
