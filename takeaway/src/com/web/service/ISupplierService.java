package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.Supplier;
import com.web.po.SupplierDetail;
import com.web.vo.Page;
import com.web.vo.SupplierInfo;

public interface ISupplierService extends BaseService<Supplier, Integer> {

	public List<SupplierInfo> selectAll(Page page, String supplierNum, String supplierName);

	public long getTotalRows(String supplierNum, String supplierName);

	public SupplierInfo selectById(int supplierId);

	public String insert(Supplier supplier, SupplierDetail detail);

	public String update(Supplier supplier, SupplierDetail detail);

	public String delSupplier(int id);

}
