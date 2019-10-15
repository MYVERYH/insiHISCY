package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IOrderListDao;
import com.web.po.DeliveryStaff;
import com.web.po.RatingForm;
import com.web.po.ShoppingCart;
import com.web.po.UserAddress;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.util.PublicUtil;
import com.web.vo.IndentInfo;
import com.web.vo.Page;
import com.web.vo.ShoppingCartInfo;

public class OrderListDaoImpl implements IOrderListDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String selectAll = "SELECT i.`indent_id`,i.`indent_num`,w.`wineGreName`,SUM(w.`wineGrePrice` * "
			+ "d.`indent_quantity`) totalPice,i.`indent_time`,s.`order_status`,i.`indent_remark` "
			+ "FROM `pw_indent` i JOIN `sys_order_status` s ON i.`order_status_id` = s.`order_status_id` "
			+ "JOIN `pw_indentdetail` d ON i.`indent_id` = d.`indent_id` JOIN `sys_winegre` w "
			+ "ON d.`wineGreId` = w.`wineGreId`";
	private String getTotalRows = "SELECT COUNT(*) FROM (SELECT i.`indent_id`,i.`indent_num`,w.`wineGreName`,"
			+ "SUM(w.`wineGrePrice` * d.`indent_quantity`) totalMoney,i.`indent_time`,s.`order_status`,"
			+ "i.`indent_remark` FROM `pw_indent` i JOIN `sys_order_status` s "
			+ "ON i.`order_status_id` = s.`order_status_id` JOIN `pw_indentdetail` d "
			+ "ON i.`indent_id` = d.`indent_id` JOIN `sys_winegre` w ON d.`wineGreId` = w.`wineGreId`";
	private String selectWineGre = "SELECT w.`wineGreId`,w.`wineGreName`,w.`wineGrePrice`,"
			+ "d.`indent_quantity` FROM `pw_indentdetail` d JOIN `sys_winegre` w "
			+ "ON d.`wineGreId` = w.`wineGreId` WHERE d.`indent_id`=?";
	private String getTotalRow = "SELECT COUNT(*) FROM `pw_indentdetail` d JOIN `sys_winegre` w "
			+ "ON d.`wineGreId` = w.`wineGreId`";
	private String selectPicture = "SELECT s.`picture` FROM `sys_winegre` s WHERE s.`wineGreId`=?";
	private String findById = "SELECT i.`indent_num`,a.`user_address`,a.`contact_number`,a.`contacts`,"
			+ "i.`indent_time`,p.`payment_method` FROM `pw_indent` i JOIN `pw_user_address` a  "
			+ "ON i.`userId` = a.`userId` JOIN `sys_payment_method` p "
			+ "ON i.`payment_method_id` = p.`payment_method_id` WHERE i.`indent_id` = ?";
	private String update = "UPDATE `pw_indent` SET order_status_id=? WHERE indent_id=?";
	private String selectPS = "SELECT * FROM `pw_deliverystaff` d WHERE d.`DeliveryState`=? LIMIT ?,?";
	private String getPSTotalRows = "SELECT COUNT(*) FROM `pw_deliverystaff` d WHERE d.`DeliveryState`=?";
	private String insertDS = "INSERT INTO pw_deliverydetail(DeliveryStaffID,indent_id) VALUES(?,?)";
	private String updateDS = "UPDATE pw_deliverystaff SET DeliveryState=? WHERE DeliveryStaffID=?";
	private String findByID = "SELECT * FROM `pw_ratingform` r WHERE r.`indent_id` = ?";
	private String delete = "DELETE FROM `pw_indent` WHERE `indent_id` = ?";
	private String deleteDetail = "DELETE FROM `pw_indentdetail` WHERE `indent_id` = ?";
	private String deleteRF = "DELETE FROM `pw_ratingform` WHERE `indent_id` = ?";
	private String findShoppCart = "SELECT s.*,w.`wineGreName`,w.`wineGrePrice` FROM `pw_shopping_cart` s "
			+ "JOIN `sys_winegre` w ON s.`wineGreId` = w.`wineGreId` WHERE s.`userId`=?";
	private String insertUserAddress = "INSERT INTO `pw_user_address`(userId,contacts,contact_number,"
			+ "user_address) VALUE(?,?,?,?)";
	private String updateUserAddress = "UPDATE `pw_user_address` SET userId=?,contacts=?,contact_number=?,"
			+ "user_address=? WHERE user_address_id=?";

	@Override
	public List<IndentInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndentInfo findById(int id) {// 根据id查询订单信息
		IndentInfo info = new IndentInfo();
		try {
			con = DBUtil.getConnection();// 获取连接
			ps = con.prepareStatement(findById);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			// 调用JdbcHelper反射类的getResult方法获取单条数据
			info = JdbcHelper.getSingleResult(rs, IndentInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);// 关闭con, ps, rs
		}
		return info;
	}

	@Override
	public int insert(IndentInfo t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(IndentInfo info) {
		// 调用DaoHelper反射类的insertUpdate方法修改订单信息
		int flag = DaoHelper.insertUpdate(update, info);
		return flag;
	}

	@Override
	public int delete(int id) {// 删除订单信息，包括订单明细和订单评价信息
		int flag = 0;
		try {
			con = DBUtil.getConnection();// 获取连接
			con.setAutoCommit(false);// 关闭自动提交
			ps = con.prepareStatement(delete);
			ps.setInt(1, id);
			flag = ps.executeUpdate();
			if (flag > 0) {// 判断删除订单信息是否成功
				flag = 0;
				ps = con.prepareStatement(deleteDetail);
				ps.setInt(1, id);
				flag = ps.executeUpdate();
				// 根据id查询订单评价信息
				ps = con.prepareStatement(findByID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				// 调用JdbcHelper反射类的getResult方法获取单条数据
				RatingForm ratingForm = JdbcHelper.getSingleResult(rs,
						RatingForm.class);
				// 判断订单明细是否删除成功和订单评价信息是否为空，不为空则删除
				if (flag > 0 && ratingForm != null) {
					flag = 0;
					ps = con.prepareStatement(deleteRF);
					ps.setInt(1, id);
					flag = ps.executeUpdate();
					con.commit();// 提交
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
	public List<IndentInfo> selectAll(String parameter, Page page) {//查询订单信息
		List<IndentInfo> infos = new ArrayList<IndentInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectAll);
			//查询条件拼接
			if (parameter != "" && !parameter.isEmpty()) {
				if (PublicUtil.isChinese(parameter)) {
					buffer.append("WHERE s.`order_status` = '" + parameter
							+ "'");
				} else {
					buffer.append(" WHERE i.`indent_num` = '" + parameter
							+ "' OR i.`indent_time` LIKE '%" + parameter + "%'");
				}
			}
			buffer.append(" GROUP BY i.`indent_id` ORDER BY i.`indent_id` DESC LIMIT ?,?");
			con = DBUtil.getConnection();
			ps = con.prepareStatement(buffer.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, IndentInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return infos;
	}

	@Override
	public long getTotalRows(String parameter) {
		StringBuilder buffer = new StringBuilder(getTotalRows);
		if (parameter != "" && !parameter.isEmpty()) {
			if (PublicUtil.isChinese(parameter)) {
				buffer.append("WHERE s.`order_status` = '" + parameter + "'");
			} else {
				buffer.append(" WHERE i.`indent_num` = '" + parameter
						+ "' OR i.`indent_time` LIKE '%" + parameter + "%'");
			}
		}
		buffer.append(" GROUP BY i.`indent_id`) a");
		//调用DaoHelper反射类的getTotalRow方法获取订单信息总条数
		long intTotalRow = DaoHelper.getTotalRow(buffer.toString());
		return intTotalRow;
	}

	@Override
	public List<IndentInfo> selectWineGre(int indentId, Page page,
			String findType) {//查询菜品信息
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IndentInfo> infos = new ArrayList<IndentInfo>();
		try {
			StringBuffer buffer = new StringBuffer(selectWineGre);
			con = DBUtil.getConnection();
			//根据findType参数查询不同菜品信息
			if ("selectWineGre".equals(findType)) {
				buffer.append(" ORDER BY w.`wineGreId` LIMIT ?,?");
				ps = con.prepareStatement(buffer.toString());
				ps.setInt(1, indentId);
				ps.setInt(2, page.getStartIndex());
				ps.setInt(3, page.getLimit());
			} else if ("findWineGre".equals(findType)) {
				ps = con.prepareStatement(buffer.toString());
				ps.setInt(1, indentId);
			}
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			infos = JdbcHelper.getResult(rs, IndentInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return infos;
	}

	@Override
	public long getTotalRows(int indentId) {
		StringBuilder buffer = new StringBuilder(getTotalRow);
		if (indentId > 0) {
			buffer.append(" WHERE d.`indent_id` = " + indentId);
		}
		//调用DaoHelper反射类的getTotalRow方法获取菜品信息总条数
		long intTotalRow = DaoHelper.getTotalRow(buffer.toString());
		return intTotalRow;
	}

	@Override
	public byte[] selectPicture(int id) {//根据id查询菜品图片
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		byte[] bytes = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectPicture);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				bytes = rs.getBytes("picture");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return bytes;
	}

	@Override
	public List<DeliveryStaff> selectPS(boolean state, Page page) {//查询配送员信息
		List<DeliveryStaff> deliveryStaffs = new ArrayList<DeliveryStaff>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(selectPS);
			ps.setBoolean(1, state);
			ps.setInt(2, page.getStartIndex());
			ps.setInt(3, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			deliveryStaffs = JdbcHelper.getResult(rs, DeliveryStaff.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return deliveryStaffs;
	}

	@Override
	public long getTotalRows(boolean state) {
		int intTotalRow = 0;
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(getPSTotalRows);
			ps.setBoolean(1, state);
			rs = ps.executeQuery();
			while (rs.next()) {
				intTotalRow = rs.getInt(1);
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
	public int insertDS(DeliveryStaff deliveryStaff) {
		//调用DaoHelper反射类的insertUpdate方法新增配送信息
		int flag = DaoHelper.insertUpdate(insertDS, deliveryStaff);
		return flag;
	}

	@Override
	public int updateDS(DeliveryStaff deliveryStaff) {
		//调用DaoHelper反射类的insertUpdate方法修改配送信息
		int flag = DaoHelper.insertUpdate(updateDS, deliveryStaff);
		return flag;
	}

	@Override
	public RatingForm findByID(int indentId) {//根据订单id查询订单评价信息
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RatingForm ratingForm = new RatingForm();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findByID);
			ps.setInt(1, indentId);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取单条数据
			ratingForm = JdbcHelper.getSingleResult(rs, RatingForm.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);//关闭con, ps, rs
		}
		return ratingForm;
	}

	@Override
	public int insertShoppCart(ShoppingCart shoppingCart) {//新增用户购物车信息
		ShoppingCart cart = new ShoppingCart();
		int flag = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM `pw_shopping_cart` WHERE userId=? AND wineGreId=?");
			ps.setInt(1, shoppingCart.getUserId());
			ps.setInt(2, shoppingCart.getWineGreId());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取单条数据
			cart = JdbcHelper.getSingleResult(rs, ShoppingCart.class);
			if (cart != null) {
				cart.setWineGreQuantity(cart.getWineGreQuantity() + 1);
				flag = DaoHelper.insertUpdate("UPDATE `pw_shopping_cart` SET wineGreId=?,userId=?,"
						+ "wineGreQuantity=? WHERE shoppingCartId=?", cart);
			} else {
				flag = DaoHelper.insertUpdate(
						"INSERT INTO `pw_shopping_cart`(wineGreId,userId,wineGreQuantity) VALUES(?,?,?)", shoppingCart);
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
	public List<ShoppingCartInfo> findShoppCart(int userId) {//查询用户购物车信息
		List<ShoppingCartInfo> shoppingCarts = new ArrayList<ShoppingCartInfo>();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findShoppCart);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			shoppingCarts = JdbcHelper.getResult(rs, ShoppingCartInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return shoppingCarts;
	}

	@Override
	public int delShoppCart(int userId, int wineGreId) {//删除购物车信息
		int flag = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("DELETE FROM `pw_shopping_cart` WHERE userId=? and wineGreId=?");
			ps.setInt(1, userId);
			ps.setInt(2, wineGreId);
			flag = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return flag;
	}

	@Override
	public int updateShoppCart(ShoppingCart shoppingCart) {
		//调用DaoHelper反射类的insertUpdate方法修改购物车信息
		int flag = DaoHelper.insertUpdate("UPDATE `pw_shopping_cart` SET wineGreQuantity=? WHERE shoppingCartId=?",
				shoppingCart);
		return flag;
	}

	@Override
	public List<UserAddress> findUserAddress(int userId) {
		List<UserAddress> addresses = new ArrayList<UserAddress>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("SELECT * FROM `pw_user_address` WHERE `userId`=?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			addresses = JdbcHelper.getResult(rs, UserAddress.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return addresses;
	}

	@Override
	public int insertUserAddress(UserAddress userAddress) {
		//调用DaoHelper反射类的insertUpdate方法新增用户地址信息
		int flag = DaoHelper.insertUpdate(insertUserAddress, userAddress);
		return flag;
	}

	@Override
	public int updateUserAddress(UserAddress userAddress) {
		//调用DaoHelper反射类的insertUpdate方法修改用户地址信息
		int flag = DaoHelper.insertUpdate(updateUserAddress, userAddress);
		return flag;
	}

	@Override
	public int delUserAddress(int id) {
		//调用DaoHelper反射类的delete方法删除用户地址信息
		int flag = DaoHelper.delete("DELETE FROM `pw_user_address` WHERE user_address_id=?", id);
		return flag;
	}

}
