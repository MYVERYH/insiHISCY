package com.web.service.impl;

import java.util.List;

import com.web.dao.IMemberDao;
import com.web.dao.impl.MemberDaoImpl;
import com.web.service.IMemberService;
import com.web.vo.MemberInfo;
import com.web.vo.Page;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memberDao = new MemberDaoImpl();

	@Override
	public List<MemberInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberInfo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(MemberInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(MemberInfo info) {
		String msg = "";
		if (memberDao.update(info) > 0) {
			msg = "修改成功";
		} else {
			msg = "修改失败";
		}
		return msg;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberInfo> findMember(String num, String name, Page page) {
		// TODO Auto-generated method stub
		return memberDao.findMember(num, name, page);
	}

	@Override
	public long getTotalRows(String num, String name) {
		// TODO Auto-generated method stub
		return memberDao.getTotalRows(num, name);
	}

}
