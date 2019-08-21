package com.web.service.impl;

import java.util.List;

import com.web.dao.IBillDao;
import com.web.dao.impl.BillDaoImpl;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.MinimumStock;
import com.web.po.SupplierPayment;
import com.web.service.IBillsService;
import com.web.vo.BillsInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;
import com.web.vo.RepertoryInfo;
import com.web.vo.SelectBills;
import com.web.vo.SupplierPaymentInfo;

public class BillsServiceImpl implements IBillsService {

	private IBillDao billDao = new BillDaoImpl();

	@Override
	public List<Bills> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bills findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(Bills t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Bills t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Bills bills, List<BillsDetail> billsDetails) {
		return billDao.insert(bills, billsDetails);
	}

	@Override
	public int insertRepertory(Bills bills, List<BillsDetail> billsDetails) {
		// TODO Auto-generated method stub
		return billDao.insertRepertory(bills, billsDetails);
	}

	@Override
	public int updateRepertory(Bills bills, List<BillsDetail> billsDetails,
			boolean b) {
		// TODO Auto-generated method stub
		return billDao.updateRepertory(bills, billsDetails, b);
	}

	@Override
	public List<BillsInfo> selectCheck(SelectBills selectBills, Page page) {
		// TODO Auto-generated method stub
		return billDao.selectCheck(selectBills, page);
	}

	@Override
	public long getCheckTotalRow(SelectBills selectBills) {
		// TODO Auto-generated method stub
		return billDao.getCheckTotalRow(selectBills);
	}

	@Override
	public List<RawMaterialInfo> selectOrderMaterial(SelectBills selectBills,
			Page page, String findType) {
		// TODO Auto-generated method stub
		return billDao.selectOrderMaterial(selectBills, page, findType);
	}

	@Override
	public long getOrderMaterialTotalRow(SelectBills selectBills,
			String findType) {
		// TODO Auto-generated method stub
		return billDao.getOrderMaterialTotalRow(selectBills, findType);
	}

	@Override
	public List<RawMaterialInfo> selectOrderDetail(int id, Page page) {
		// TODO Auto-generated method stub
		return billDao.selectOrderDetail(id, page);
	}

	@Override
	public long getOrderDetailTotalRow(int id) {
		// TODO Auto-generated method stub
		return billDao.getOrderDetailTotalRow(id);
	}

	@Override
	public List<BillsInfo> selectOrder(SelectBills selectBills, Page page,
			String findType) {
		// TODO Auto-generated method stub
		return billDao.selectOrder(selectBills, page, findType);
	}

	@Override
	public long getOrderTotalRow(SelectBills selectBills, String findType) {
		// TODO Auto-generated method stub
		return billDao.getOrderTotalRow(selectBills, findType);
	}

	@Override
	public List<RawMaterialInfo> selectNowRepertory(SelectBills selectBills,
			Page page) {
		// TODO Auto-generated method stub
		return billDao.selectNowRepertory(selectBills, page);
	}

	@Override
	public long getNowRepertoryTotalRow(SelectBills selectBills) {
		// TODO Auto-generated method stub
		return billDao.getNowRepertoryTotalRow(selectBills);
	}

	@Override
	public List<RepertoryInfo> selectRepertory(SelectBills selectBills,
			Page page, String findType) {
		// TODO Auto-generated method stub
		return billDao.selectRepertory(selectBills, page, findType);
	}

	@Override
	public long getRepertoryTotalRows(SelectBills selectBills, String findType) {
		// TODO Auto-generated method stub
		return billDao.getRepertoryTotalRows(selectBills, findType);
	}

	@Override
	public List<BillsInfo> selectSupplier(SelectBills selectBills, Page page) {
		// TODO Auto-generated method stub
		return billDao.selectSupplier(selectBills, page);
	}

	@Override
	public long getSupplierTotalRow(SelectBills selectBills) {
		// TODO Auto-generated method stub
		return billDao.getSupplierTotalRow(selectBills);
	}

	@Override
	public List<SupplierPaymentInfo> findPaymentInfo(SelectBills selectBills,
			Page page, String findType) {
		// TODO Auto-generated method stub
		return billDao.findPaymentInfo(selectBills, page, findType);
	}

	@Override
	public long getPaymentTotalRow(SelectBills selectBills, String findType) {
		// TODO Auto-generated method stub
		return billDao.getPaymentTotalRow(selectBills, findType);
	}

	@Override
	public String insertPayment(SupplierPayment payment) {
		String msg = "";
		if (billDao.insertPayment(payment) > 0) {
			msg = "新增成功";
		} else {
			msg = "新增失败";
		}
		return msg;
	}

	@Override
	public String insertMinimumStock(MinimumStock stock) {
		String msg = "";
		if (billDao.insertMinimumStock(stock) > 0) {
			msg = "新增成功";
		} else {
			msg = "新增失败";
		}
		return msg;
	}

	@Override
	public List<RawMaterialInfo> selectMinimumStock(SelectBills selectBills,
			Page page) {
		// TODO Auto-generated method stub
		return billDao.selectMinimumStock(selectBills, page);
	}

	@Override
	public long getMinimumStockTotalRow(SelectBills selectBills) {
		// TODO Auto-generated method stub
		return billDao.getMinimumStockTotalRow(selectBills);
	}

}
