package com.web.service;

import java.util.List;

import com.web.common.BaseService;
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

public interface IBillsService extends BaseService<Bills, Integer> {

	public int insert(Bills bills, List<BillsDetail> billsDetails);

	public int insertRepertory(Bills bills, List<BillsDetail> billsDetails);

	public int updateRepertory(Bills bills, List<BillsDetail> billsDetails,
			boolean b);

	public List<BillsInfo> selectCheck(SelectBills selectBills, Page page);

	public long getCheckTotalRow(SelectBills selectBills);

	public List<RawMaterialInfo> selectOrderMaterial(SelectBills selectBills,
			Page page, String findType);

	public long getOrderMaterialTotalRow(SelectBills selectBills,
			String findType);

	public List<RawMaterialInfo> selectOrderDetail(int id, Page page);

	public long getOrderDetailTotalRow(int id);

	public List<BillsInfo> selectOrder(SelectBills selectBills, Page page,
			String findType);

	public long getOrderTotalRow(SelectBills selectBills, String findType);

	public List<RawMaterialInfo> selectNowRepertory(SelectBills selectBills,
			Page page);

	public long getNowRepertoryTotalRow(SelectBills selectBills);

	public List<RepertoryInfo> selectRepertory(SelectBills selectBills,
			Page page, String findType);

	public long getRepertoryTotalRows(SelectBills selectBills, String findType);

	public List<BillsInfo> selectSupplier(SelectBills selectBills, Page page);

	public long getSupplierTotalRow(SelectBills selectBills);
	
	public List<SupplierPaymentInfo> findPaymentInfo(SelectBills selectBills, Page page, String findType);
	
	public long getPaymentTotalRow(SelectBills selectBills, String findType);
	
	public String insertPayment(SupplierPayment payment);
	
	public String insertMinimumStock(MinimumStock stock);
	
	public List<RawMaterialInfo> selectMinimumStock(SelectBills selectBills, Page page);

	public long getMinimumStockTotalRow(SelectBills selectBills);

}
