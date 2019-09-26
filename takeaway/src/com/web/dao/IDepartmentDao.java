package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Department;
import com.web.po.MaterialRequirement;
import com.web.vo.DepartmentInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public interface IDepartmentDao extends BaseDao<Department, Integer> {

	public List<DepartmentInfo> selectAll(Page page);//查询部门信息

	public long getTotalRows();
	
	public List<RawMaterialInfo> findRequirement(Page page, int departmentId);//查询部门原料需求信息

	public long getTotalRow(int departmentId);
	
	public int insert(MaterialRequirement requirement);//新增部门原料需求信息
	
	public int update(MaterialRequirement requirement);//修改部门原料需求信息

}
