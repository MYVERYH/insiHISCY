package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.po.User;
import com.web.service.IMemberService;
import com.web.service.impl.MemberServiceImpl;
import com.web.util.DBUtil;
import com.web.util.PublicUtil;
import com.web.util.RequestHelper;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.MemberInfo;
import com.web.vo.Page;

public class MemberServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IMemberService memberService = new MemberServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 根据type调用不同方法
		String type = request.getParameter("type");
		Class<?> servlet = this.getClass();
		Method[] methods = servlet.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();			
			if (methodName.equals(type)) {
				try {
					System.out.println("方法名：" + methodName);
					method.invoke(servlet.newInstance(), request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 返回会员挂失页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showMemberManagement(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			request.getRequestDispatcher("/jsp/MemberManagement.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/jsp/Login.jsp").forward(request,
					response);
		}
	}

	/**
	 * 查询会员信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String markClub = request.getParameter("markClub") != null ? request
				.getParameter("markClub") : "";
		String memberMC = request.getParameter("memberMC") != null ? request
				.getParameter("memberMC") : "";
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		List<MemberInfo> infos = memberService.findMember(markClub, memberMC, page);
		if (infos!=null) {
			for (MemberInfo info : infos) {
				info.setDelivertimeClubs(info.getDelivertimeClub());
			}
		}
		long totalRows = memberService.getTotalRows(markClub, memberMC);
		LayuiJSON<MemberInfo> layuiJSON = new LayuiJSON<MemberInfo>(
				0, "", totalRows, infos);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}
	
	/**
	 * 修改会员卡状态(挂失会员)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		MemberInfo member = RequestHelper.getSingleRequest(request, MemberInfo.class);
		String str = memberService.update(member);
		if ("修改成功".equals(str)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg("挂失成功!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg("挂失失败!");
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}
	
}
