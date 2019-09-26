package com.web.service.impl;

import java.util.List;

import com.web.dao.ISupplierDao;
import com.web.dao.impl.SupplierDaoImpl;
import com.web.po.Supplier;
import com.web.po.SupplierDetail;
import com.web.service.ISupplierService;
import com.web.vo.Page;
import com.web.vo.SupplierInfo;

public class SupplierServiceImpl implements ISupplierService {

	private ISupplierDao supplierDao = new SupplierDaoImpl();

	@Override
	public List<Supplier> selectAll() {
		// TODO Auto-generated method stub
		return supplierDao.selectAll();
	}

	@Override
	public Supplier findById(int id) {
		// TODO Auto-generated method stub
		return supplierDao.findById(id);
	}

	@Override
	public String insert(Supplier supplier) {
		String str = "";
		if (supplierDao.insert(supplier) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(Supplier supplier) {
		String str = "";
		if (supplierDao.update(supplier) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delete(int id) {
		String str = "";
		if (supplierDao.delete(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<SupplierInfo> selectAll(Page page, String supplierNum, String supplierName) {
		// TODO Auto-generated method stub
		return supplierDao.selectAll(page, supplierNum, supplierName);
	}

	@Override
	public long getTotalRows(String supplierNum, String supplierName) {
		// TODO Auto-generated method stub
		return supplierDao.getTotalRows(supplierNum, supplierName);
	}

	@Override
	public SupplierInfo selectById(int supplierId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(Supplier supplier, SupplierDetail detail) {
		String str = "";
		if (supplierDao.insert(supplier, detail) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(Supplier supplier, SupplierDetail detail) {
		String str = "";
		if (supplierDao.update(supplier, detail) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delSupplier(int id) {
		String str = "";
		if (supplierDao.delSupplier(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

}
