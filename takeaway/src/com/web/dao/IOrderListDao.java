package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.DeliveryStaff;
import com.web.po.RatingForm;
import com.web.po.ShoppingCart;
import com.web.po.UserAddress;
import com.web.vo.IndentInfo;
import com.web.vo.Page;
import com.web.vo.ShoppingCartInfo;

public interface IOrderListDao extends BaseDao<IndentInfo, Integer> {

	public List<IndentInfo> selectAll(String parameter, Page page);//多条件查询订单信息

	public long getTotalRows(String parameter);

	public List<IndentInfo> selectWineGre(int indentId, Page page,
			String findType);//根据订单id查询菜品信息

	public long getTotalRows(int indentId);

	public byte[] selectPicture(int id);//查询二进制图片信息

	public List<DeliveryStaff> selectPS(boolean state, Page page);//查询配送员信息
	
	public long getTotalRows(boolean state);
	
	public int insertDS(DeliveryStaff deliveryStaff);//新增配送信息
	
	public int updateDS(DeliveryStaff deliveryStaff);//修改配送信息
	
	public RatingForm findByID(int indentId);//查询评价信息
	
	public int insertShoppCart(ShoppingCart shoppingCart);//新增购物车信息
	
	public List<ShoppingCartInfo> findShoppCart(int userId);//查询用户购物车信息
	
	public int delShoppCart(int userId, int wineGreId);//删除用户购物车信息
	
	public int updateShoppCart(ShoppingCart shoppingCart);//修改用户购物车信息
	
	public List<UserAddress> findUserAddress(int userId);//根据用户id查询用户地址信息
 
}
