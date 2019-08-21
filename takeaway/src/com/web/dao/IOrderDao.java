package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.Check;
import com.web.po.Order;
import com.web.vo.OrderInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public interface IOrderDao extends BaseDao<Order, Integer> {

	public int insertPurchaseOrders(Order order);

	public List<OrderInfo> findPurchase(Page page);

	public long getPurchaseRow();

	public List<RawMaterialInfo> findMaterial(Page page, int id);

	public long getMaterialRow(int id);

	public int insertDispatchingOrders(Order order);

	public List<OrderInfo> findDispatching(Page page);

	public long getDispatchingRow();
	
	public List<RawMaterialInfo> findPSMaterial(Page page, int id);

	public long getPSMaterialRow(int id);
	
	public double getRepertoryAmount(int warehouseId, int rawMaterialId);
	
	public int insertGodownOrders(Order order);

	public List<OrderInfo> findGodownOrders(Page page);

	public long getGodownOrdersRow();
	
	public List<RawMaterialInfo> findRKMaterial(Page page, int id);

	public long getRKMaterialRow(int id);
	
	public int insertCreditOrders(Order order);
	
	public int insertStocksRequisition(Order order, List<BillsDetail> billsDetails);

	public List<OrderInfo> findStocksRequisition(Page page);

	public long getStocksRequisitionRow();
	
	public List<RawMaterialInfo> findLLMaterial(Page page, int id);

	public long getLLMaterialRow(int id);
	
	public int insertPickingCreditOrders(Order order, List<BillsDetail> billsDetails);
	
	public List<RawMaterialInfo> findRepertory(Page page, int id);

	public long getCKMaterialRow(int id);
	
	public int insertWarehouseTransferOrder(Order order, Bills bills, List<BillsDetail> billsDetails);

	public List<RawMaterialInfo> findCheck(Page page, int id);

	public long getCheckRow(int id);
	
	public Check selectCheck(int warehouseId, int rawMaterialId);
	
	public int insertCheck(Check check);
	
	public int updateCheck(Check check);
	
	public int delCheck(int id);
	
	public int insertRepertoryCheck(Order order);
	
}
