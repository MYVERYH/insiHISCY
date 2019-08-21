package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Supplier;
import com.web.po.SupplierDetail;
import com.web.vo.Page;
import com.web.vo.SupplierInfo;

public interface ISupplierDao extends BaseDao<Supplier, Integer> {

	public List<SupplierInfo> selectAll(Page page, String supplierNum, String supplierName);

	public long getTotalRows(String supplierNum, String supplierName);
	
	public SupplierInfo selectById(int supplierId);
	
	public int insert(Supplier supplier, SupplierDetail detail);
	
	public int update(Supplier supplier, SupplierDetail detail);
	
	public int delSupplier(int id);
	
}
