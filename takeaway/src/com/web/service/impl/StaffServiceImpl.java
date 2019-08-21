package com.web.service.impl;

import java.util.List;

import com.web.dao.IStaffDao;
import com.web.dao.impl.StaffDaoImpl;
import com.web.po.Position;
import com.web.po.Staff;
import com.web.service.IStaffService;
import com.web.vo.Page;
import com.web.vo.StaffInfo;

public class StaffServiceImpl implements IStaffService {

	private IStaffDao staffDao = new StaffDaoImpl();

	@Override
	public List<Staff> selectAll() {
		// TODO Auto-generated method stub
		return staffDao.selectAll();
	}

	@Override
	public Staff findById(int id) {
		// TODO Auto-generated method stub
		return staffDao.findById(id);
	}

	@Override
	public String insert(Staff staff) {
		String str = "";
		if (staffDao.insert(staff) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(Staff staff) {
		String str = "";
		if (staffDao.update(staff) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delete(int id) {
		String str = "";
		if (staffDao.delete(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<StaffInfo> findAll(Page page) {
		// TODO Auto-generated method stub
		return staffDao.findAll(page);
	}

	@Override
	public long getTotalRow() {
		// TODO Auto-generated method stub
		return staffDao.getTotalRow();
	}

	@Override
	public List<Position> findAll() {
		// TODO Auto-generated method stub
		return staffDao.findAll();
	}

}
