package com.web.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.web.util.PublicUtil;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
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
	private String insertSmalll = "insert into sys_winegresmall(wineGreBigId,wineGreSmallNum,wineGreSmallName) values(?,?,?)";
	private String updateSmalll = "update sys_winegresmall set wineGreBigId=?,wineGreSmallNum=?,wineGreSmallName=? where wineGreSmallId=?";
	private String deleteSmalll = "delete from sys_winegresmall where wineGreSmallId=?";
	private String selectAll = "select * from sys_winegre where wineGreSmallId=? limit ?,?";
	private String getTotalRow = "select count(*) from sys_winegre where wineGreSmallId=?";
	private String findById = "select * from sys_winegre where wineGreId=?";
	private String insert = "insert into sys_winegre(wineGreSmallId,wineGreNum,wineGreName,"
			+ "wineGrePrice,bigPrice,smallPrice,memberPrice,isDiscount,picture) values(?,?,?,?,?,?,?,?,?)";
	private String update = "update sys_winegre set wineGreSmallId=?,wineGreNum=?,wineGreName=?,"
			+ "wineGrePrice=?,bigPrice=?,smallPrice=?,memberPrice=?,isDiscount=?";
	private String delete = "delete from sys_winegre where wineGreId=?";

	@Override
	public List<WineGre> selectAll() {
		List<WineGre> wineGres = new ArrayList<WineGre>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select wineGreNum from sys_winegre");
			rs = ps.executeQuery();
			wineGres = JdbcHelper.getResult(rs, WineGre.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return wineGres;
	}

	@Override
	public WineGre findById(int id) {
		WineGre wineGre = DaoHelper.findByID(findById, WineGre.class, id);
		return wineGre;
	}

	@Override
	public int insert(WineGre wineGre) {
		int flag = 0;
		// flag = DaoHelper.insertUpdate(insert, wineGre);
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(insert);
			ps.setInt(1, wineGre.getWineGreSmallId());
			ps.setString(2, wineGre.getWineGreNum());
			ps.setString(3, wineGre.getWineGreName());
			ps.setDouble(4, wineGre.getWineGrePrice());
			ps.setDouble(5, wineGre.getBigPrice());
			ps.setDouble(6, wineGre.getSmallPrice());
			ps.setDouble(7, wineGre.getMemberPrice());
			ps.setBoolean(8, wineGre.getIsDiscount());
			InputStream in = new ByteArrayInputStream(wineGre.getPicture());
			ps.setBinaryStream(9, in, in.available());
			flag = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public int update(WineGre wineGre) {
		int flag = 0;
		// flag = DaoHelper.insertUpdate(update, wineGre);
		try {
			StringBuilder builder = new StringBuilder(update);
			if (wineGre.getPicture() != null) {
				builder.append(",Picture=? where WineGreID=?");
				con = DBUtil.getConnection();
				ps = con.prepareStatement(builder.toString());
				ps.setInt(1, wineGre.getWineGreSmallId());
				ps.setString(2, wineGre.getWineGreNum());
				ps.setString(3, wineGre.getWineGreName());
				ps.setDouble(4, wineGre.getWineGrePrice());
				ps.setDouble(5, wineGre.getBigPrice());
				ps.setDouble(6, wineGre.getSmallPrice());
				ps.setDouble(7, wineGre.getMemberPrice());
				ps.setBoolean(8, wineGre.getIsDiscount());
				InputStream in = new ByteArrayInputStream(wineGre.getPicture());
				ps.setBinaryStream(9, in, in.available());
				ps.setInt(10, wineGre.getWineGreId());
				flag = ps.executeUpdate();
			} else {
				builder.append(" where wineGreId=?");
				flag = DaoHelper.insertUpdate(builder.toString(), wineGre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public int delete(int id) {
		int flag = 0;
		flag = DaoHelper.delete(delete, id);
		return flag;
	}

	@Override
	public List<WineGreBig> findBig(Page page) {
		List<WineGreBig> wineGreBigs = new ArrayList<WineGreBig>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findBig);
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			wineGreBigs = JdbcHelper.getResult(rs, WineGreBig.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return wineGreBigs;
	}

	@Override
	public long count() {
		long intTotalRow = DaoHelper.getTotalRow(count);
		return intTotalRow;
	}

	@Override
	public int insert(WineGreBig big) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(insertBig, big);
		return flag;
	}

	@Override
	public WineGreBig findByID(int id) {
		WineGreBig wineGreBig = DaoHelper.findByID(findBigById,
				WineGreBig.class, id);
		return wineGreBig;
	}

	@Override
	public int update(WineGreBig big) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(updateBig, big);
		return flag;
	}

	@Override
	public int deleteBig(int id) {
		int flag = 0;
		flag = DaoHelper.delete(deleteBig, id);
		return flag;
	}

	@Override
	public List<WineGreSmall> findSmalll(Page page, int id) {
		List<WineGreSmall> wineGreSmalls = new ArrayList<WineGreSmall>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findSmalll);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			wineGreSmalls = JdbcHelper.getResult(rs, WineGreSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return wineGreSmalls;
	}

	@Override
	public long getTotalRows(int id) {
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();
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
			DBUtil.close(con, ps, rs);
		}
		return intTotalRow;
	}

	@Override
	public int insert(WineGreSmall small) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(insertSmalll, small);
		return flag;
	}

	@Override
	public WineGreSmall findBYID(int id) {
		WineGreSmall wineGreSmall = DaoHelper.findByID(findSmalllById,
				WineGreSmall.class, id);
		return wineGreSmall;
	}

	@Override
	public int update(WineGreSmall small) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(updateSmalll, small);
		return flag;
	}

	@Override
	public int deleteSmall(int id) {
		int flag = 0;
		flag = DaoHelper.delete(deleteSmalll, id);
		return flag;
	}

	@Override
	public List<WineGre> selectAll(Page page, int id) {
		List<WineGre> wineGres = new ArrayList<WineGre>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectAll);
			ps.setInt(1, id);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			wineGres = JdbcHelper.getResult(rs, WineGre.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return wineGres;
	}

	@Override
	public long getTotalRow(int id) {
		long intTotalRow = 0;
		try {
			con = DBUtil.getConnection();
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
			DBUtil.close(con, ps, rs);
		}
		return intTotalRow;
	}

	@Override
	public List<WineGreBig> bigNumber() {
		List<WineGreBig> wineGreBigs = new ArrayList<WineGreBig>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select wineGreBigNum from sys_winegrebig");
			rs = ps.executeQuery();
			wineGreBigs = JdbcHelper.getResult(rs, WineGreBig.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return wineGreBigs;
	}

	@Override
	public List<WineGreSmall> smallNumber() {
		List<WineGreSmall> wineGreSmalls = new ArrayList<WineGreSmall>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select wineGreSmallNum from sys_winegresmall");
			rs = ps.executeQuery();
			wineGreSmalls = JdbcHelper.getResult(rs, WineGreSmall.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return wineGreSmalls;
	}

	@Override
	public byte[] findPicture(int id) {
		Blob blob = null;
		byte[] bs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select Picture from sys_winegre where WineGreID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				blob = rs.getBlob("Picture");
			}
			bs = PublicUtil.blobToBytes(blob);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bs;
	}

}
