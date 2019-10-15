package com.web.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IWineGreDao;
import com.web.po.WineGre;
import com.web.po.WineGreBig;
import com.web.po.WineGreSmall;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.util.PublicUtil;
import com.web.vo.Page;

public class WineGreDaoImpl implements IWineGreDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String findBig = "select * from sys_winegrebig limit ?,?";
	private String count = "select count(*) from sys_winegrebig";
	private String findBigById = "select * from sys_winegrebig where wineGreBigId=?";
	private String insertBig = "insert into sys_winegrebig(wineGreBigNum,wineGreBigName) values(?,?)";
	private String updateBig = "update sys_winegrebig set wineGreBigNum=?,wineGreBigName=? where wineGreBigId=?";
	private String deleteBig = "delete from sys_winegrebig where wineGreBigId=?";
	private String findSmalll = "select * from sys_winegresmall where wineGreBigId=? limit ?,?";
	private String getTotalRows = "select count(*) from sys_winegresmall where wineGreBigId=?";
	private String findSmalllById = "select * from sys_winegresmall where wineGreSmallId=?";
	private String insertSmalll = "insert into sys_winegresmall(wineGreBigId,wineGreSmallNum,"
			+ "wineGreSmallName) values(?,?,?)";
	private String updateSmalll = "update sys_winegresmall set wineGreBigId=?,wineGreSmallNum=?,"
			+ "wineGreSmallName=? where wineGreSmallId=?";
	private String deleteSmalll = "delete from sys_winegresmall where wineGreSmallId=?";
	private String selectAll = "select * from sys_winegre where wineGreSmallId=? limit ?,?";
	private String getTotalRow = "select count(*) from sys_winegre where wineGreSmallId=?";
	private String findById = "select * from sys_winegre where wineGreId=?";
	private String insert = "insert into sys_winegre(wineGreSmallId,wineGreNum,wineGreName,"
			+ "wineGrePrice,bigPrice,smallPrice,memberPrice,isDiscount,picture) values(?,?,?,?,?,?,?,?,?)";
	private String update = "update sys_winegre set wineGreSmallId=?,wineGreNum=?,wineGreName=?,"
			+ "wineGrePrice=?,bigPrice=?,smallPrice=?,memberPrice=?,isDiscount=?";
	private String delete = "delete from sys_winegre where wineGreId=?";
	private String findHotWineGres = "SELECT * FROM (SELECT COUNT(*) total,d.`wineGreId`,w.`wineGreName` "
			+ "FROM `pw_indentdetail` d JOIN `sys_winegre` w ON d.`wineGreId` = w.`wineGreId` "
			+ "GROUP BY d.`wineGreId`,w.`wineGreName`) a ORDER BY a.total DESC";

	@Override
	public List<WineGre> selectAll() {
		List<WineGre> wineGres = new ArrayList<WineGre>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement("select wineGreNum from sys_winegre");
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGres = JdbcHelper.getResult(rs, WineGre.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGres;
	}

	@Override
	public WineGre findById(int id) {
		// 调用DaoHelper反射类的findByID方法根据id查询酒菜信息
		WineGre wineGre = DaoHelper.findByID(findById, WineGre.class, id);
		return wineGre;
	}

	@Override
	public int insert(WineGre wineGre) {// 新增酒菜信息
		// 调用DaoHelper反射类的insertUpdate方法新增酒菜信息
		int flag = DaoHelper.insertUpdate(insert, wineGre);
		return flag;
	}

	@Override
	public int update(WineGre wineGre) {// 修改酒菜信息
		StringBuilder builder = new StringBuilder(update);
		if (wineGre.getPicture() != null) {// 判断二进制数组即图片是否为空，若不为空则修改图片
			builder.append(",Picture=? WHERE wineGreId=?");
		} else {// 为空则不修改图片
			builder.append(" WHERE wineGreId=?");
		}
		int flag = DaoHelper.insertUpdate(builder.toString(), wineGre);
		return flag;
	}

	@Override
	public int delete(int id) {// 删除酒菜信息
		// 调用DaoHelper反射类的delete方法删除酒菜信息
		int flag = DaoHelper.delete(delete, id);
		return flag;
	}

	@Override
	public List<WineGreBig> findBig(Page page) {// 查询酒菜大类信息
		List<WineGreBig> wineGreBigs = new ArrayList<WineGreBig>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(findBig);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGreBigs = JdbcHelper.getResult(rs, WineGreBig.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGreBigs;
	}

	@Override
	public long count() {
		// 调用DaoHelper反射类的getTotalRow方法获取酒菜大类信息总条数
		long intTotalRow = DaoHelper.getTotalRow(count);
		return intTotalRow;
	}

	@Override
	public int insert(WineGreBig big) {
		// 调用DaoHelper反射类的insertUpdate方法新增酒菜大类信息
		int flag = DaoHelper.insertUpdate(insertBig, big);
		return flag;
	}

	@Override
	public WineGreBig findByID(int id) {
		// 调用DaoHelper反射类的findByID方法根据id查询酒菜大类信息
		WineGreBig wineGreBig = DaoHelper.findByID(findBigById, WineGreBig.class, id);
		return wineGreBig;
	}

	@Override
	public int update(WineGreBig big) {
		// 调用DaoHelper反射类的insertUpdate方法修改酒菜大类信息
		int flag = DaoHelper.insertUpdate(updateBig, big);
		return flag;
	}

	@Override
	public int deleteBig(int id) {
		// 调用DaoHelper反射类的delete方法删除酒菜大类信息
		int flag = DaoHelper.delete(deleteBig, id);
		return flag;
	}

	@Override
	public List<WineGreSmall> findSmalll(Page page, int id) {// 根据酒菜大类id查询酒菜小类信息
		List<WineGreSmall> wineGreSmalls = new ArrayList<WineGreSmall>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(findSmalll);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGreSmalls = JdbcHelper.getResult(rs, WineGreSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGreSmalls;
	}

	@Override
	public long getTotalRows(int id) {// 根据酒菜大类id查询酒菜小类信息总条数
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(getTotalRows);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				intTotalRow = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return intTotalRow;
	}

	@Override
	public int insert(WineGreSmall small) {
		// 调用DaoHelper反射类的insertUpdate方法新增酒菜小类信息
		int flag = DaoHelper.insertUpdate(insertSmalll, small);
		return flag;
	}

	@Override
	public WineGreSmall findBYID(int id) {
		// 调用DaoHelper反射类的findByID方法根据id查询酒菜小类信息
		WineGreSmall wineGreSmall = DaoHelper.findByID(findSmalllById, WineGreSmall.class, id);
		return wineGreSmall;
	}

	@Override
	public int update(WineGreSmall small) {
		// 调用DaoHelper反射类的insertUpdate方法修改酒菜小类信息
		int flag = DaoHelper.insertUpdate(updateSmalll, small);
		return flag;
	}

	@Override
	public int deleteSmall(int id) {
		// 调用DaoHelper反射类的insertUpdate方法删除酒菜小类信息
		int flag = DaoHelper.delete(deleteSmalll, id);
		return flag;
	}

	@Override
	public List<WineGre> selectAll(Page page, int id) {// 根据酒菜小类id查询酒菜信息
		List<WineGre> wineGres = new ArrayList<WineGre>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(selectAll);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGres = JdbcHelper.getResult(rs, WineGre.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGres;
	}

	@Override
	public long getTotalRow(int id) {// 根据酒菜小类id查询酒菜信息总条数
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();// 获取连接
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
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return intTotalRow;
	}

	@Override
	public List<WineGreBig> bigNumber() {// 查询酒菜大类编号
		List<WineGreBig> wineGreBigs = new ArrayList<WineGreBig>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement("select wineGreBigNum from sys_winegrebig");
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGreBigs = JdbcHelper.getResult(rs, WineGreBig.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGreBigs;
	}

	@Override
	public List<WineGreSmall> smallNumber() {// 查询酒菜小类编号
		List<WineGreSmall> wineGreSmalls = new ArrayList<WineGreSmall>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement("select wineGreSmallNum from sys_winegresmall");
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGreSmalls = JdbcHelper.getResult(rs, WineGreSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGreSmalls;
	}

	@Override
	public byte[] findPicture(int id) {// 查询菜品图片
		Blob blob = null;
		byte[] bs = null;
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement("select Picture from sys_winegre where WineGreID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				blob = rs.getBlob("Picture");
			}
			bs = PublicUtil.blobToBytes(blob);//blobToBytes方法把blob类型转换为byte数组
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return bs;
	}

	@Override
	public List<WineGre> selectAll(Page page) {
		List<WineGre> wineGres = new ArrayList<WineGre>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement("SELECT * FROM sys_winegre LIMIT ?,?");
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGres = JdbcHelper.getResult(rs, WineGre.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGres;
	}

	@Override
	public long getTotalRows() {
		// 调用DaoHelper反射类的getTotalRow方法获取总条数
		long totalRows = DaoHelper.getTotalRow("SELECT COUNT(*) FROM sys_winegre");
		return totalRows;
	}

	@Override
	public int findSumById(int id) {
		int sum = 0;
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement("SELECT COUNT(*) SUM FROM `pw_indent` i JOIN `pw_indentdetail` d "
					+ "ON i.`indent_id`=d.`indent_id` WHERE i.`order_status_id`!=2 AND d.`wineGreId`=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("SUM");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return sum;
	}

	@Override
	public List<WineGre> findHotWineGres() {
		List<WineGre> wineGres = new ArrayList<WineGre>();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(findHotWineGres);
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取list集合数据
			wineGres = JdbcHelper.getResult(rs, WineGre.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return wineGres;
	}

}
