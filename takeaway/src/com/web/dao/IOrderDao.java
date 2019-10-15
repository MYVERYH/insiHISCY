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

	public int insertPurchaseOrders(Order order);// 新增采购订货单信息

	public List<OrderInfo> findPurchase(Page page);// 查询采购订货单信息

	public long getPurchaseRow();

	public List<RawMaterialInfo> findMaterial(Page page, int id);// 根据采购订货单id查询采购原料信息

	public long getMaterialRow(int id);

	public int insertDispatchingOrders(Order order);// 新增配送单信息	

	public List<OrderInfo> findDispatching(Page page);// 查询配送单信息

	public long getDispatchingRow();
	
	public List<RawMaterialInfo> findPSMaterial(Page page, int id);// 根据配送单id查询配送原料信息

	public long getPSMaterialRow(int id);
	
	public int getRepertoryAmount(int warehouseId, int rawMaterialId);// 根据仓库id和原料id查询库存数量
	
	public int insertGodownOrders(Order order);// 查询入库单信息

	public List<OrderInfo> findGodownOrders(Page page);// 查询入库单信息

	public long getGodownOrdersRow();
	
	public List<RawMaterialInfo> findRKMaterial(Page page, int id);// 查询入库原料信息

	public long getRKMaterialRow(int id);
	
	public int insertCreditOrders(Order order);// 查询退货单信息
	
	public int insertStocksRequisition(Order order, List<BillsDetail> billsDetails);// 新增领料单信息

	public List<OrderInfo> findStocksRequisition(Page page);// 查询领料单信息

	public long getStocksRequisitionRow();
	
	public List<RawMaterialInfo> findLLMaterial(Page page, int id);// 查询领料单原料信息

	public long getLLMaterialRow(int id);
	
	public int insertPickingCreditOrders(Order order, List<BillsDetail> billsDetails);// 新增领料退货单信息
	
	public List<RawMaterialInfo> findRepertory(Page page, int id);// 查询库存原料信息

	public long getCKMaterialRow(int id);
	
	public int insertWarehouseTransferOrder(Order order, Bills bills, List<BillsDetail> billsDetails);// 新增仓库调拔单

	public List<RawMaterialInfo> findCheck(Page page, int id);// 查询盘点信息

	public long getCheckRow(int id);
	
	public Check selectCheck(int warehouseId, int rawMaterialId);// 根据仓库id和原料id查询盘点信息
	
	public int insertCheck(Check check);// 查询盘点信息
	
	public int updateCheck(Check check);// 修改盘点信息
	
	public int delCheck(int id);// 删除盘点信息
	
	public int insertRepertoryCheck(Order order);// 新增库存盘点单信息
	
}
