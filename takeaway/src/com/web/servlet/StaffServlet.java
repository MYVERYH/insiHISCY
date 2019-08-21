package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.po.Department;
import com.web.po.Position;
import com.web.po.Staff;
import com.web.service.IDepartmentService;
import com.web.service.IStaffService;
import com.web.service.impl.DepartmentServiceImpl;
import com.web.service.impl.StaffServiceImpl;
import com.web.util.DBUtil;
import com.web.util.RequestHelper;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;
import com.web.vo.StaffInfo;

public class StaffServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IDepartmentService departmentService = new DepartmentServiceImpl();
	private IStaffService staffService = new StaffServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解决乱码
		response.setContentType("text/html;charset=utf-8");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	/***
	 * 返回员工管理页面并准备下拉框绑定数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showStaffInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Department> departments = departmentService.selectAll();
		List<Position> positions = staffService.findAll();
		request.setAttribute("departments", departments);
		request.setAttribute("positions", positions);
		request.getRequestDispatcher("/jsp/EmployeeData.jsp").forward(request,
				response);
	}

	/***
	 * 查询员工信息数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void tabStaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		List<StaffInfo> staffInfos = staffService.findAll(page);
		long totalRow = staffService.getTotalRow();
		LayuiJSON<StaffInfo> layuiJSON = DBUtil.layuiReturn(null, totalRow,
				staffInfos);
		DBUtil.jsonObjectReturn(response, layuiJSON);
	}

	/***
	 * 新增或修改员工信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEidtSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		Staff staff = RequestHelper.getSingleRequest(request, Staff.class);
		String str = new String();
		if (staff.getStaffId() == null) {
			str = staffService.insert(staff);
			if ("新增成功".equals(str)) {
				jsonReturn.setState(true);
				jsonReturn.setMsg(str + "!");
			} else {
				jsonReturn.setState(false);
				jsonReturn.setMsg(str + "!");
			}
		} else {
			str = staffService.update(staff);
			if ("修改成功".equals(str)) {
				jsonReturn.setState(true);
				jsonReturn.setMsg(str + "!");
			} else {
				jsonReturn.setState(false);
				jsonReturn.setMsg(str + "!");
			}
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}

	/***
	 * 数据回显员工信息
	 * 根据员工id查询单条数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("staffID");
		Staff staff = staffService.findById(Integer.parseInt(id));
		DBUtil.jsonObjectReturn(response, staff);
	}

	/***
	 * 删除员工信息
	 * 根据员工id删除
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delStaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String id = request.getParameter("staffId");
		String str = staffService.delete(Integer.parseInt(id));
		if ("删除成功".equals(str)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg(str + "!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg(str + "!");
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}

}
