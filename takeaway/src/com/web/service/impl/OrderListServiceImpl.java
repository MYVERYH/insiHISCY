package com.web.service.impl;

import java.util.List;

import com.web.dao.IOrderListDao;
import com.web.dao.impl.OrderListDaoImpl;
import com.web.po.DeliveryStaff;
import com.web.po.RatingForm;
import com.web.po.ShoppingCart;
import com.web.po.UserAddress;
import com.web.service.IOrderListService;
import com.web.vo.IndentInfo;
import com.web.vo.Page;
import com.web.vo.ShoppingCartInfo;

public class OrderListServiceImpl implements IOrderListService {

	private IOrderListDao orderListDao = new OrderListDaoImpl();

	@Override
	public List<IndentInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndentInfo findById(int id) {
		// TODO Auto-generated method stub
		return orderListDao.findById(id);
	}

	@Override
	public String insert(IndentInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(IndentInfo info) {
		String msg = "";
		if (orderListDao.update(info) > 0) {
			msg = "修改成功";
		} else {
			msg = "修改失败";
		}
		return msg;
	}

	@Override
	public String delete(int id) {
		String msg = "";
		if (orderListDao.delete(id) > 0) {
			msg = "删除成功";
		} else {
			msg = "删除失败";
		}
		return msg;
	}

	@Override
	public List<IndentInfo> selectAll(String parameter, Page page) {
		// TODO Auto-generated method stub
		return orderListDao.selectAll(parameter, page);
	}

	@Override
	public long getTotalRows(String parameter) {
		// TODO Auto-generated method stub
		return orderListDao.getTotalRows(parameter);
	}

	@Override
	public List<IndentInfo> selectWineGre(int indentId, Page page, String findType) {
		// TODO Auto-generated method stub
		return orderListDao.selectWineGre(indentId, page, findType);
	}

	@Override
	public long getTotalRows(int indentId) {
		// TODO Auto-generated method stub
		return orderListDao.getTotalRows(indentId);
	}

	@Override
	public byte[] selectPicture(int id) {
		// TODO Auto-generated method stub
		return orderListDao.selectPicture(id);
	}

	@Override
	public List<DeliveryStaff> selectPS(boolean state, Page page) {
		// TODO Auto-generated method stub
		return orderListDao.selectPS(state, page);
	}

	@Override
	public long getTotalRows(boolean state) {
		// TODO Auto-generated method stub
		return orderListDao.getTotalRows(state);
	}

	@Override
	public int insertDS(DeliveryStaff deliveryStaff) {
		// TODO Auto-generated method stub
		return orderListDao.insertDS(deliveryStaff);
	}

	@Override
	public int updateDS(DeliveryStaff deliveryStaff) {
		// TODO Auto-generated method stub
		return orderListDao.updateDS(deliveryStaff);
	}

	@Override
	public RatingForm findByID(int indentId) {
		// TODO Auto-generated method stub
		return orderListDao.findByID(indentId);
	}

	@Override
	public String insertShoppCart(ShoppingCart shoppingCart) {
		String msg = "";
		if (orderListDao.insertShoppCart(shoppingCart) > 0) {
			msg = "新增成功";
		} else {
			msg = "新增失败";
		}
		return msg;
	}

	@Override
	public List<ShoppingCartInfo> findShoppCart(int userId) {
		// TODO Auto-generated method stub
		return orderListDao.findShoppCart(userId);
	}

	@Override
	public String delShoppCart(int userId, int wineGreId) {
		String msg = "";
		if (orderListDao.delShoppCart(userId, wineGreId) > 0) {
			msg = "删除成功";
		} else {
			msg = "删除失败";
		}
		return msg;
	}

	@Override
	public String updateShoppCart(ShoppingCart shoppingCart) {
		String msg = "";
		if (orderListDao.updateShoppCart(shoppingCart) > 0) {
			msg = "修改成功";
		} else {
			msg = "修改失败";
		}
		return msg;
	}

	@Override
	public List<UserAddress> findUserAddress(int userId) {
		// TODO Auto-generated method stub
		return orderListDao.findUserAddress(userId);
	}

}
