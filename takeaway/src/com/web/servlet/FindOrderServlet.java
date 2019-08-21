package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.po.Department;
import com.web.po.Staff;
import com.web.po.Supplier;
import com.web.po.Unit;
import com.web.po.Warehouse;
import com.web.service.IBillsService;
import com.web.service.IDepartmentService;
import com.web.service.IOrderService;
import com.web.service.IRawMaterialService;
import com.web.service.IStaffService;
import com.web.service.ISupplierService;
import com.web.service.IWarehouseService;
import com.web.service.impl.BillsServiceImpl;
import com.web.service.impl.DepartmentServiceImpl;
import com.web.service.impl.OrderServiceImpl;
import com.web.service.impl.RawMaterialServiceImpl;
import com.web.service.impl.StaffServiceImpl;
import com.web.service.impl.SupplierServiceImpl;
import com.web.service.impl.WarehouseServiceImpl;
import com.web.util.PublicUtil;
import com.web.util.RequestHelper;
import com.web.vo.BillsInfo;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;
import com.web.vo.SelectBills;

public class FindOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ISupplierService supplierService = new SupplierServiceImpl();
	private IStaffService staffService = new StaffServiceImpl();
	private IWarehouseService warehouseService = new WarehouseServiceImpl();
	private IDepartmentService departmentService = new DepartmentServiceImpl();
	private IRawMaterialService rawMaterialService = new RawMaterialServiceImpl();
	private IOrderService orderService = new OrderServiceImpl();
	private IBillsService billsService = new BillsServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
	 * 返回单据查询页面和准备下拉框绑定数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showFindOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Supplier> suppliers = supplierService.selectAll();
		List<Staff> staffs = staffService.selectAll();
		List<Warehouse> warehouses = warehouseService.selectAll();
		List<Department> departments = departmentService.selectAll();
		List<Unit> units = rawMaterialService.findUnit();
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("staffs", staffs);
		request.setAttribute("warehouses", warehouses);
		request.setAttribute("departments", departments);
		request.setAttribute("units", units);
		request.getRequestDispatcher("/jsp/FindOrder.jsp").forward(request,
				response);
	}

	/***
	 * 查询单据信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findType = request.getParameter("findType");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		SelectBills selectBills = RequestHelper.getSingleRequest(request,
				SelectBills.class);
		List<BillsInfo> billsInfos = new ArrayList<BillsInfo>();
		long totalRows = 0;
		if ("findCheckOrder".equals(findType)) {//查询盘点单据信息
			billsInfos = billsService.selectCheck(selectBills, page);
			totalRows = billsService.getCheckTotalRow(selectBills);
		} else {
			billsInfos = billsService.selectOrder(selectBills, page, findType);
			totalRows = billsService.getOrderTotalRow(selectBills, findType);
		}
		if (billsInfos != null) {
			for (BillsInfo info : billsInfos) {
				info.setBillsEntryTimes(info.getBillsEntryTime());
			}
		}
		LayuiJSON<BillsInfo> layuiJSON = new LayuiJSON<BillsInfo>(0, "",
				totalRows, billsInfos);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}

	/***
	 * 查询原料信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findMaterial(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findType = request.getParameter("findType");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		SelectBills selectBills = RequestHelper.getSingleRequest(request,
				SelectBills.class);
		List<RawMaterialInfo> materialInfos = new ArrayList<RawMaterialInfo>();
		long totalRows = 0;
		if ("findMaterial1".equals(findType)) {
			materialInfos = billsService.selectOrderMaterial(selectBills, page,
					findType);
			for (RawMaterialInfo info : materialInfos) {
				info.setReportAmount(orderService.getRepertoryAmount(
						info.getWarehouseId(), info.getRawMaterialId()));
				info.setNumberOfProfitAndLoss(info.getReportAmount()
						- info.getRawMaterialAmount());
				info.setProfitAndLossAmount(info.getRawMaterialPrice(),
						info.getNumberOfProfitAndLoss());
			}
			totalRows = billsService.getOrderMaterialTotalRow(selectBills,
					findType);
		} else if ("findMaterials".equals(findType)) {
			int billsId = Integer
					.parseInt(request.getParameter("billsId") != null ? request
							.getParameter("billsId") : "0");
			materialInfos = billsService.selectOrderDetail(billsId, page);
			totalRows = billsService.getOrderDetailTotalRow(billsId);
		} else {
			materialInfos = billsService.selectOrderMaterial(selectBills, page,
					findType);
			for (RawMaterialInfo info : materialInfos) {
				BigDecimal bigDecimal = new BigDecimal(
						info.getRawMaterialAmount());
				info.setTotalPrice(bigDecimal.multiply(info
						.getRawMaterialPrice()));
			}
			totalRows = billsService.getOrderMaterialTotalRow(selectBills,
					findType);
		}
		LayuiJSON<RawMaterialInfo> layuiJSON = new LayuiJSON<RawMaterialInfo>(
				0, "", totalRows, materialInfos);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}
}
