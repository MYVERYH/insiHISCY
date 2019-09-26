package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.MinimumStock;
import com.web.po.SupplierPayment;
import com.web.vo.BillsInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;
import com.web.vo.RepertoryInfo;
import com.web.vo.SelectBills;
import com.web.vo.SupplierPaymentInfo;

public interface IBillDao extends BaseDao<Bills, Integer> {

	public int insert(Bills bills, List<BillsDetail> billsDetails);;// 新增单据票信息

	public int insertRepertory(Bills bills, List<BillsDetail> billsDetails);// 新增库存信息

	public int updateRepertory(Bills bills, List<BillsDetail> billsDetails, boolean b);// 修改库存信息

	public List<BillsInfo> selectCheck(SelectBills selectBills, Page page);// 查询判断单据信息

	public long getCheckTotalRow(SelectBills selectBills);

	public List<RawMaterialInfo> selectOrderMaterial(SelectBills selectBills, Page page, String findType);// 查询单据原料信息

	public long getOrderMaterialTotalRow(SelectBills selectBills, String findType);

	public List<RawMaterialInfo> selectOrderDetail(int id, Page page);// 根据单据id查询单据明细信息

	public long getOrderDetailTotalRow(int id);

	public List<BillsInfo> selectOrder(SelectBills selectBills, Page page, String findType);// 多条件查询单据信息

	public long getOrderTotalRow(SelectBills selectBills, String findType);

	public List<RawMaterialInfo> selectNowRepertory(SelectBills selectBills, Page page);// 多条件对象当前库存信息

	public long getNowRepertoryTotalRow(SelectBills selectBills);

	public List<RepertoryInfo> selectRepertory(SelectBills selectBills, Page page, String findType);// 多条件查询库存报表信息

	public long getRepertoryTotalRows(SelectBills selectBills, String findType);

	public List<BillsInfo> selectSupplier(SelectBills selectBills, Page page);// 查询供应商供货信息

	public long getSupplierTotalRow(SelectBills selectBills);

	public List<SupplierPaymentInfo> findPaymentInfo(SelectBills selectBills, Page page, String findType);// 查询供应商应付款信息

	public long getPaymentTotalRow(SelectBills selectBills, String findType);

	public int insertPayment(SupplierPayment payment);// 新增供应商付款信息

	public int insertMinimumStock(MinimumStock stock);// 新增最低库存信息

	public List<RawMaterialInfo> selectMinimumStock(SelectBills selectBills, Page page);// 查询最低库存信息

	public long getMinimumStockTotalRow(SelectBills selectBills);

}
