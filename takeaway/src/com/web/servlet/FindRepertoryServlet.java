package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.po.MinimumStock;
import com.web.po.RawMaterialBig;
import com.web.po.Staff;
import com.web.po.Supplier;
import com.web.po.SupplierPayment;
import com.web.po.Warehouse;
import com.web.service.IBillsService;
import com.web.service.IRawMaterialService;
import com.web.service.IStaffService;
import com.web.service.ISupplierService;
import com.web.service.IWarehouseService;
import com.web.service.impl.BillsServiceImpl;
import com.web.service.impl.RawMaterialServiceImpl;
import com.web.service.impl.StaffServiceImpl;
import com.web.service.impl.SupplierServiceImpl;
import com.web.service.impl.WarehouseServiceImpl;
import com.web.util.PublicUtil;
import com.web.util.RequestHelper;
import com.web.vo.BillsInfo;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;
import com.web.vo.RepertoryInfo;
import com.web.vo.SelectBills;
import com.web.vo.SupplierPaymentInfo;

public class FindRepertoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IWarehouseService warehouseService = new WarehouseServiceImpl();
	private IRawMaterialService rawMaterialService = new RawMaterialServiceImpl();
	private ISupplierService supplierService = new SupplierServiceImpl();
	private IStaffService staffService = new StaffServiceImpl();
	private IBillsService billsService = new BillsServiceImpl();

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
	 * 显示库存查询页面 suppliers、bigs、staffs、warehouses为下拉框准备数据
	 * 
	 * */
	public void showFindRepertory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Supplier> suppliers = supplierService.selectAll();
		List<RawMaterialBig> bigs = rawMaterialService.finBig();
		List<Staff> staffs = staffService.selectAll();
		List<Warehouse> warehouses = warehouseService.selectAll();
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("staffs", staffs);
		request.setAttribute("warehouses", warehouses);
		request.setAttribute("bigs", bigs);
		request.getRequestDispatcher("/jsp/FindRepertory.jsp").forward(request,
				response);
	}

	/***
	 * 查询库存数据， findType查询表格类型， findNowRepertory为当前库存查询，
	 * findInOrOutRepertory为库存进销报表查询， page分页对象，
	 * page.setStartIndex(page.getPage(), page.getLimit());计算分页开始页
	 * selectBills查询条件对象，和page对象都是通过RequestHelper反射类的getSingleRequest方法获取页面传输值
	 * totalRows获取数据总条数
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findRepertory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findType = request.getParameter("findType");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		SelectBills selectBills = RequestHelper.getSingleRequest(request,
				SelectBills.class);
		long totalRows = 0;
		if ("findNowRepertory".equals(findType)) {
			List<RawMaterialInfo> infos = billsService.selectNowRepertory(
					selectBills, page);
			totalRows = billsService.getNowRepertoryTotalRow(selectBills);
			LayuiJSON<RawMaterialInfo> layuiJSON = new LayuiJSON<RawMaterialInfo>(
					0, "", totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("findInOrOutRepertory".equals(findType)
				|| "findRepertoryDetail".equals(findType)) {
			List<RepertoryInfo> infos = billsService.selectRepertory(
					selectBills, page, findType);
			totalRows = billsService.getRepertoryTotalRows(selectBills,
					findType);
			LayuiJSON<RepertoryInfo> layuiJSON = new LayuiJSON<RepertoryInfo>(
					0, "", totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("findRepertoryAlarm".equals(findType)) {
			List<RawMaterialInfo> infos = billsService.selectMinimumStock(
					selectBills, page);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					info.setTitleInfo(info.getMinimumQuantity(),
							info.getRawMaterialQuantity());
				}
			}
			totalRows = billsService.getMinimumStockTotalRow(selectBills);
			LayuiJSON<RawMaterialInfo> layuiJSON = new LayuiJSON<RawMaterialInfo>(
					0, "", totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		}
	}

	/**
	 * 供应商供货信息查询
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findSupplier(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findType = request.getParameter("findType");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		SelectBills selectBills = RequestHelper.getSingleRequest(request,
				SelectBills.class);
		long totalRows = 0;
		if ("findSupplierInfo".equals(findType)) {
			List<BillsInfo> infos = billsService.selectSupplier(selectBills,
					page);
			for (BillsInfo info : infos) {
				if (info.getBillsEntryTime() != null
						&& info.getRawMaterialAmount() > 0
						&& info.getRawMaterialPrice() != null) {
					BigDecimal bigDecimal = new BigDecimal(
							info.getRawMaterialAmount());
					info.setBillsEntryTimes(info.getBillsEntryTime());
					info.setTotalPrice(bigDecimal.multiply(info
							.getRawMaterialPrice()));
				}
			}
			totalRows = billsService.getSupplierTotalRow(selectBills);
			LayuiJSON<BillsInfo> layuiJSON = new LayuiJSON<BillsInfo>(0, "",
					totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else {
			List<SupplierPaymentInfo> infos = billsService.findPaymentInfo(
					selectBills, page, findType);
			if (infos != null) {
				for (SupplierPaymentInfo info : infos) {
					if (info.getPaymentDate() != null) {
						info.setPaymentDates(info.getPaymentDate());
					}
				}
			}
			totalRows = billsService.getPaymentTotalRow(selectBills, findType);
			LayuiJSON<SupplierPaymentInfo> layuiJSON = new LayuiJSON<SupplierPaymentInfo>(
					0, "", totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		}
	}

	/**
	 * 新增供应商应付款记录 jsonReturn对象可设置状态和提示文本 payment表单对象(vo)装新增的表格数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addPayment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		SupplierPayment payment = RequestHelper.getSingleRequest(request,
				SupplierPayment.class);
		String msg = billsService.insertPayment(payment);
		if ("新增成功".equals(msg)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg("付款成功");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg("付款失败");
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 新增最低库存报警 jsonReturn对象可设置状态和提示文本 stock表单对象(vo)装新增的表格数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addeditMinimumStock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		MinimumStock stock = RequestHelper.getSingleRequest(request,
				MinimumStock.class);		
		String msg = billsService.insertMinimumStock(stock);
		if ("新增成功".equals(msg)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg("设置成功");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg("设置失败");
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

}
