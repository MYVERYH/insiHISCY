package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.vo.MemberInfo;
import com.web.vo.Page;

public interface IMemberDao extends BaseDao<MemberInfo, Integer> {
	
	public List<MemberInfo> findMember(String num, String name, Page page);//查询会员信息

	public long getTotalRows(String num, String name);
	
}
