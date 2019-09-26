package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.po.DeliveryStaff;
import com.web.po.RatingForm;
import com.web.po.User;
import com.web.service.IOrderListService;
import com.web.service.impl.OrderListServiceImpl;
import com.web.util.PublicUtil;
import com.web.util.RequestHelper;
import com.web.vo.IndentInfo;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;

public class OrderListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IOrderListService orderListService = new OrderListServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
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
	 * 返回订单详情页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showOrderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			request.getRequestDispatcher("/jsp/OrderList.jsp").forward(request,
					response);
		} else {
			request.getRequestDispatcher("/jsp/Login.jsp").forward(request,
					response);
		}
	}

	/**
	 * 查询订单信息 parameter查询条件 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findIndent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("parameter") != null ? request
				.getParameter("parameter") : "";
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		List<IndentInfo> infos = orderListService.selectAll(parameter, page);
		if (infos != null) {
			for (IndentInfo info : infos) {
				info.setIndentTimes(info.getIndentTime());
			}
		}
		long totalRows = orderListService.getTotalRows(parameter);
		LayuiJSON<IndentInfo> layuiJSON = new LayuiJSON<IndentInfo>(0, "",
				totalRows, infos);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}

	/**
	 * 查询菜品信息 findType查询类别 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findWineGre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findType = request.getParameter("findType") != null ? request
				.getParameter("findType") : "";
		int indentId = Integer
				.parseInt(request.getParameter("indentId") != null ? request
						.getParameter("indentId") : "0");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		if ("selectWineGre".equals(findType)) {
			List<IndentInfo> infos = orderListService.selectWineGre(indentId,
					page, findType);
			long totalRows = orderListService.getTotalRows(indentId);
			LayuiJSON<IndentInfo> layuiJSON = new LayuiJSON<IndentInfo>(0, "",
					totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("findWineGre".equals(findType)) {
			List<IndentInfo> infos = orderListService.selectWineGre(indentId,
					page, findType);
			PublicUtil.jsonArrayReturn(response, infos);
		}
	}

	/**
	 * 查询菜品图片信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findPicture(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String wineGreId = request.getParameter("wineGreId") != null ? request
				.getParameter("wineGreId") : "0";
		byte[] bs = orderListService.selectPicture(Integer.parseInt(wineGreId));
		ServletOutputStream out = response.getOutputStream();// 返回图片(以二进制数组返回页面)
		out.write(bs);
		out.flush();
		out.close();
	}

	/**
	 * 根据id查询订单状态 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findIndentById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int indentId = Integer
				.parseInt(request.getParameter("indentId") != null ? request
						.getParameter("indentId") : "0");
		IndentInfo info = orderListService.findById(indentId);
		info.setIndentTimes(info.getIndentTime());
		PublicUtil.jsonObjectReturn(response, info);
	}

	/**
	 * 修改订单信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateIndent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		IndentInfo indent = RequestHelper.getSingleRequest(request,
				IndentInfo.class);
		String str = orderListService.update(indent);
		if ("修改成功".equals(str)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg(str + "!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg(str + "!");
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 查询配送员信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findDeliveryStaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean state = Boolean
				.parseBoolean(request.getParameter("state") != null ? request
						.getParameter("state") : "false");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		List<DeliveryStaff> deliveryStaffs = orderListService.selectPS(state,
				page);
		long totalRows = orderListService.getTotalRows(state);
		LayuiJSON<DeliveryStaff> layuiJSON = new LayuiJSON<DeliveryStaff>(0,
				"", totalRows, deliveryStaffs);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}

	/**
	 * 新增配送信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insertDelivery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		DeliveryStaff deliveryStaff = RequestHelper.getSingleRequest(request,
				DeliveryStaff.class);
		int flag = orderListService.insertDS(deliveryStaff);
		if (flag > 0) {
			int flags = orderListService.updateDS(deliveryStaff);
			if (flags > 0) {
				IndentInfo indent = RequestHelper.getSingleRequest(request,
						IndentInfo.class);
				String str = orderListService.update(indent);
				if ("修改成功".equals(str)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(str + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(str + "!");
				}
			}
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 根据订单id查询订单评价信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findRatingFormByID(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int indentId = Integer
				.parseInt(request.getParameter("indentId") != null ? request
						.getParameter("indentId") : "0");
		RatingForm ratingForm = orderListService.findByID(indentId);
		response.setCharacterEncoding("utf-8");
		PublicUtil.jsonObjectReturn(response, ratingForm);
	}

	/**
	 * 删除订单信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delIndent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		int indentId = Integer
				.parseInt(request.getParameter("indentId") != null ? request
						.getParameter("indentId") : "0");
		if (indentId > 0) {
			String str = orderListService.delete(indentId);
			if ("删除成功".equals(str)) {
				jsonReturn.setState(true);
				jsonReturn.setMsg(str + "!");
			} else {
				jsonReturn.setState(false);
				jsonReturn.setMsg(str + "!");
			}
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}
}
