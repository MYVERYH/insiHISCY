package com.web.service.impl;

import java.util.List;

import com.web.dao.IWarehouseDao;
import com.web.dao.impl.WarehouseDaoImpl;
import com.web.po.Warehouse;
import com.web.service.IWarehouseService;
import com.web.vo.Page;
import com.web.vo.WarehouseInfo;

public class WarehouseServiceImpl implements IWarehouseService {

	private IWarehouseDao warehouseDao = new WarehouseDaoImpl();

	@Override
	public List<Warehouse> selectAll() {
		// TODO Auto-generated method stub
		return warehouseDao.selectAll();
	}

	@Override
	public Warehouse findById(int id) {
		// TODO Auto-generated method stub
		return warehouseDao.findById(id);
	}

	@Override
	public String insert(Warehouse warehouse) {
		String str = "";
		if (warehouseDao.insert(warehouse) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(Warehouse warehouse) {
		String str = "";
		if (warehouseDao.update(warehouse) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delete(int id) {
		String str = "";
		if (warehouseDao.delete(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<WarehouseInfo> selectAll(Page page) {
		// TODO Auto-generated method stub
		return warehouseDao.selectAll(page);
	}

	@Override
	public long getTotalRows() {
		// TODO Auto-generated method stub
		return warehouseDao.getTotalRows();
	}

}
