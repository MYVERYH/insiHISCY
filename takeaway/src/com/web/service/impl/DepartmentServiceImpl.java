package com.web.service.impl;

import java.util.List;

import com.web.dao.IDepartmentDao;
import com.web.dao.impl.DepartmentDaoImpl;
import com.web.po.Department;
import com.web.po.MaterialRequirement;
import com.web.service.IDepartmentService;
import com.web.vo.DepartmentInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class DepartmentServiceImpl implements IDepartmentService {

	private IDepartmentDao departmentDao = new DepartmentDaoImpl();

	@Override
	public List<Department> selectAll() {
		// TODO Auto-generated method stub
		return departmentDao.selectAll();
	}

	@Override
	public Department findById(int id) {
		// TODO Auto-generated method stub
		return departmentDao.findById(id);
	}

	@Override
	public String insert(Department department) {
		String str = "";
		if (departmentDao.insert(department) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(Department department) {
		String str = "";
		if (departmentDao.update(department) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delete(int id) {
		String str = "";
		if (departmentDao.delete(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<DepartmentInfo> selectAll(Page page) {
		// TODO Auto-generated method stub
		return departmentDao.selectAll(page);
	}

	@Override
	public long getTotalRows() {
		// TODO Auto-generated method stub
		return departmentDao.getTotalRows();
	}

	@Override
	public List<RawMaterialInfo> findRequirement(Page page, int departmentId) {
		// TODO Auto-generated method stub
		return departmentDao.findRequirement(page, departmentId);
	}

	@Override
	public long getTotalRow(int departmentId) {
		// TODO Auto-generated method stub
		return departmentDao.getTotalRow(departmentId);
	}

	@Override
	public String insert(MaterialRequirement requirement) {
		String str = "";
		if (departmentDao.insert(requirement) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(MaterialRequirement requirement) {
		String str = "";
		if (departmentDao.update(requirement) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

}
