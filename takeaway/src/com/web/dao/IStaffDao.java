package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.Position;
import com.web.po.Staff;
import com.web.vo.Page;
import com.web.vo.StaffInfo;

public interface IStaffDao extends BaseDao<Staff, Integer> {
	
	public List<StaffInfo> findAll(Page page);//查询员工信息
	
	public long getTotalRow();
	
	public List<Position> findAll();//查询职位信息(绑定下拉框)
	
}
