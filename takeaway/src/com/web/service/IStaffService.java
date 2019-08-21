package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.Position;
import com.web.po.Staff;
import com.web.vo.Page;
import com.web.vo.StaffInfo;

public interface IStaffService extends BaseService<Staff, Integer> {

	public List<StaffInfo> findAll(Page page);

	public long getTotalRow();

	public List<Position> findAll();
	
}
