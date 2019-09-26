package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Supplier;
import com.web.po.SupplierDetail;
import com.web.vo.Page;
import com.web.vo.SupplierInfo;

public interface ISupplierDao extends BaseDao<Supplier, Integer> {

	public List<SupplierInfo> selectAll(Page page, String supplierNum, String supplierName);//多条件查询供应商信息

	public long getTotalRows(String supplierNum, String supplierName);
	
	public SupplierInfo selectById(int supplierId);//根据供应商id查询供应商信息
	
	public int insert(Supplier supplier, SupplierDetail detail);//新增供应商信息
	
	public int update(Supplier supplier, SupplierDetail detail);//修改供应商信息
	
	public int delSupplier(int id);//删除供应商信息
	
}
