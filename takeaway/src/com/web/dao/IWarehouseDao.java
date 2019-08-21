package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Warehouse;
import com.web.vo.Page;
import com.web.vo.WarehouseInfo;

public interface IWarehouseDao extends BaseDao<Warehouse, Integer> {
	
	public List<WarehouseInfo> selectAll(Page page);
	
	public long getTotalRows();

}
