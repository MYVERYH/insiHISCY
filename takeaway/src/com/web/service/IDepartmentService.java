package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.Department;
import com.web.po.MaterialRequirement;
import com.web.vo.DepartmentInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public interface IDepartmentService extends BaseService<Department, Integer> {

	public List<DepartmentInfo> selectAll(Page page);

	public long getTotalRows();

	public List<RawMaterialInfo> findRequirement(Page page, int departmentId);

	public long getTotalRow(int departmentId);
	
	public String insert(MaterialRequirement requirement);
	
	public String update(MaterialRequirement requirement);
	
}
