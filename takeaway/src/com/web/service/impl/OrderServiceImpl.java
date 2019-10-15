package com.web.service.impl;

import java.util.List;

import com.web.dao.IOrderDao;
import com.web.dao.impl.OrderDaoImpl;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.Check;
import com.web.po.Order;
import com.web.service.IOrderService;
import com.web.vo.OrderInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class OrderServiceImpl implements IOrderService {

	private IOrderDao orderDao = new OrderDaoImpl();

	@Override
	public List<Order> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertPurchaseOrders(Order order) {
		String str = "";
		if (orderDao.insertPurchaseOrders(order) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public List<OrderInfo> findPurchase(Page page) {
		// TODO Auto-generated method stub
		return orderDao.findPurchase(page);
	}

	@Override
	public long getPurchaseRow() {
		// TODO Auto-generated method stub
		return orderDao.getPurchaseRow();
	}

	@Override
	public List<RawMaterialInfo> findMaterial(Page page, int id) {
		// TODO Auto-generated method stub
		return orderDao.findMaterial(page, id);
	}

	@Override
	public long getMaterialRow(int id) {
		// TODO Auto-generated method stub
		return orderDao.getMaterialRow(id);
	}

	@Override
	public String insertDispatchingOrders(Order order) {
		String str = "";
		if (orderDao.insertDispatchingOrders(order) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public List<OrderInfo> findDispatching(Page page) {
		// TODO Auto-generated method stub
		return orderDao.findDispatching(page);
	}

	@Override
	public long getDispatchingRow() {
		// TODO Auto-generated method stub
		return orderDao.getDispatchingRow();
	}

	@Override
	public List<RawMaterialInfo> findPSMaterial(Page page, int id) {
		// TODO Auto-generated method stub
		return orderDao.findPSMaterial(page, id);
	}

	@Override
	public long getPSMaterialRow(int id) {
		// TODO Auto-generated method stub
		return orderDao.getPSMaterialRow(id);
	}

	@Override
	public int getRepertoryAmount(int warehouseId, int rawMaterialId) {
		// TODO Auto-generated method stub
		return orderDao.getRepertoryAmount(warehouseId, rawMaterialId);
	}

	@Override
	public String insertGodownOrders(Order order) {
		String str = "";
		if (orderDao.insertGodownOrders(order) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public List<OrderInfo> findGodownOrders(Page page) {
		// TODO Auto-generated method stub
		return orderDao.findGodownOrders(page);
	}

	@Override
	public long getGodownOrdersRow() {
		// TODO Auto-generated method stub
		return orderDao.getGodownOrdersRow();
	}

	@Override
	public List<RawMaterialInfo> findRKMaterial(Page page, int id) {
		// TODO Auto-generated method stub
		return orderDao.findRKMaterial(page, id);
	}

	@Override
	public long getRKMaterialRow(int id) {
		// TODO Auto-generated method stub
		return orderDao.getRKMaterialRow(id);
	}

	@Override
	public String insertCreditOrders(Order order) {
		String str = "";
		if (orderDao.insertCreditOrders(order) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String insertStocksRequisition(Order order, List<BillsDetail> billsDetails) {
		String str = "";
		if (orderDao.insertStocksRequisition(order, billsDetails) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public List<OrderInfo> findStocksRequisition(Page page) {
		// TODO Auto-generated method stub
		return orderDao.findStocksRequisition(page);
	}

	@Override
	public long getStocksRequisitionRow() {
		// TODO Auto-generated method stub
		return orderDao.getStocksRequisitionRow();
	}

	@Override
	public List<RawMaterialInfo> findLLMaterial(Page page, int id) {
		// TODO Auto-generated method stub
		return orderDao.findLLMaterial(page, id);
	}

	@Override
	public long getLLMaterialRow(int id) {
		// TODO Auto-generated method stub
		return orderDao.getLLMaterialRow(id);
	}

	@Override
	public String insertPickingCreditOrders(Order order, List<BillsDetail> billsDetails) {
		String str = "";
		if (orderDao.insertPickingCreditOrders(order, billsDetails) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public List<RawMaterialInfo> findRepertory(Page page, int id) {
		// TODO Auto-generated method stub
		return orderDao.findRepertory(page, id);
	}

	@Override
	public long getCKMaterialRow(int id) {
		// TODO Auto-generated method stub
		return orderDao.getCKMaterialRow(id);
	}

	@Override
	public String insertWarehouseTransferOrder(Order order, Bills bills, List<BillsDetail> billsDetails) {
		String str = "";
		if (orderDao.insertWarehouseTransferOrder(order, bills, billsDetails) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public List<RawMaterialInfo> findCheck(Page page, int id) {
		// TODO Auto-generated method stub
		return orderDao.findCheck(page, id);
	}

	@Override
	public long getCheckRow(int id) {
		// TODO Auto-generated method stub
		return orderDao.getCheckRow(id);
	}

	@Override
	public String insertCheck(Check check) {
		String str = "";
		if (orderDao.insertCheck(check) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String updateCheck(Check check) {
		String str = "";
		if (orderDao.updateCheck(check) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delCheck(int id) {
		String str = "";
		if (orderDao.delCheck(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public Check selectCheck(int warehouseId, int rawMaterialId) {
		// TODO Auto-generated method stub
		return orderDao.selectCheck(warehouseId, rawMaterialId);
	}

	@Override
	public String insertRepertoryCheck(Order order) {
		String str = "";
		if (orderDao.insertRepertoryCheck(order) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

}
