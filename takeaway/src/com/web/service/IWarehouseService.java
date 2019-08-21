package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.Warehouse;
import com.web.vo.Page;
import com.web.vo.WarehouseInfo;

public interface IWarehouseService extends BaseService<Warehouse, Integer> {

	public List<WarehouseInfo> selectAll(Page page);

	public long getTotalRows();

}
