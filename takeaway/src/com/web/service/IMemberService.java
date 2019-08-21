package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.vo.MemberInfo;
import com.web.vo.Page;

public interface IMemberService extends BaseService<MemberInfo, Integer> {

	public List<MemberInfo> findMember(String num, String name, Page page);

	public long getTotalRows(String num, String name);
}
