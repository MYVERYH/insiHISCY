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

import com.alibaba.fastjson.JSONArray;
import com.web.po.Bills;
import com.web.po.BillsDetail;
import com.web.po.Check;
import com.web.po.Department;
import com.web.po.MaterialRequirement;
import com.web.po.Order;
import com.web.po.RawMaterial;
import com.web.po.RawMaterialBig;
import com.web.po.RawMaterialSmall;
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
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.OrderInfo;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class EntryOrderServlet extends HttpServlet {

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
		Class<?> servletClass = this.getClass();// 获取当前ServletClass类(即EntryOrderServlet.class)
		Method[] methods = servletClass.getDeclaredMethods();// //获取servletClass类里面所有方法，即方法数组
		for (Method method : methods) {// foreach循环遍历每一个方法
			String methodName = method.getName();// 获取方法名称
			if (methodName.equals(type)) {// 判断方法名称是否和页面type值相同
				try {
					System.out.println("方法名：" + methodName);
					// 调用方法：servletClass.newInstance()实例化方法(相当于new 一个对象)，
					// request、response调用方法需要的参数
					method.invoke(servletClass.newInstance(), request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/***
	 * 返回单据录入页面和准备下拉框绑定的数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showEntryOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Supplier> suppliers = supplierService.selectAll();
		List<Staff> staffs = staffService.selectAll();
		List<Warehouse> warehouses = warehouseService.selectAll();
		List<Department> departments = departmentService.selectAll();
		List<RawMaterialBig> bigs = rawMaterialService.finBig();
		List<Unit> units = rawMaterialService.findUnit();
		request.setAttribute("suppliers", suppliers);
		request.setAttribute("staffs", staffs);
		request.setAttribute("warehouses", warehouses);
		request.setAttribute("departments", departments);
		request.setAttribute("bigs", bigs);
		request.setAttribute("units", units);
		request.getRequestDispatcher("/jsp/EntryOrder.jsp").forward(request,
				response);
	}

	/***
	 * 查询单据下的原料信息 findType查询类型信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findMaterial(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String findType = request.getParameter("findType") != null ? request
				.getParameter("findType") : "";
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		List<RawMaterialInfo> infos = new ArrayList<RawMaterialInfo>();
		long totalRows = 0;
		if ("findMaterial".equals(findType)) {// 查询供应商供应所有原料信息
			int supplierId = Integer.parseInt(request
					.getParameter("supplierId") != null ? request
					.getParameter("supplierId") : "0");
			infos = rawMaterialService.selectAll(page, supplierId);
			totalRows = rawMaterialService.getTotalRows(supplierId);
		} else if ("findMaterial2".equals(findType)) {// 查询采购单原料信息
			int purchaseOrdersId = Integer.parseInt(request
					.getParameter("purchaseOrdersId") != null ? request
					.getParameter("purchaseOrdersId") : "0");
			infos = orderService.findMaterial(page, purchaseOrdersId);
			totalRows = orderService.getMaterialRow(purchaseOrdersId);
		} else if ("findPSMaterial".equals(findType)) {// 查询配送单原料信息
			int dispatchingOrdersId = Integer.parseInt(request
					.getParameter("dispatchingOrdersId") != null ? request
					.getParameter("dispatchingOrdersId") : "0");
			int warehouseId = Integer.parseInt(request
					.getParameter("warehouseId") != null ? request
					.getParameter("warehouseId") : "0");
			infos = orderService.findPSMaterial(page, dispatchingOrdersId);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					BigDecimal bigDecimal = new BigDecimal(
							info.getRawMaterialAmount());
					info.setTotalPrice(bigDecimal.multiply(info
							.getRawMaterialPrice()));// 计算当前原料总金额
					info.setReportAmount(orderService.getRepertoryAmount(
							warehouseId, info.getRawMaterialId()));// 查询原料库存数量
					info.setOldAmount(info.getRawMaterialAmount());// 记录当前原料配送数量
				}
			}
			totalRows = orderService.getPSMaterialRow(dispatchingOrdersId);
		} else if ("findRKMaterial".equals(findType)) {// 查询入库单原料信息
			int godownOrdersId = Integer.parseInt(request
					.getParameter("godownOrdersId") != null ? request
					.getParameter("godownOrdersId") : "0");
			int warehouseId = Integer.parseInt(request
					.getParameter("warehouseId") != null ? request
					.getParameter("warehouseId") : "0");
			infos = orderService.findRKMaterial(page, godownOrdersId);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					BigDecimal bigDecimal = new BigDecimal(
							info.getRawMaterialAmount());
					info.setTotalPrice(bigDecimal.multiply(info
							.getRawMaterialPrice()));// 计算当前原料总金额
					info.setReportAmount(orderService.getRepertoryAmount(
							warehouseId, info.getRawMaterialId()));// 查询原料库存数量
					info.setOldAmount(info.getRawMaterialAmount());// 记录当前原料入库数量
				}
			}
			totalRows = orderService.getRKMaterialRow(godownOrdersId);
		} else if ("findMaterialRequirement".equals(findType)) {// 查询部门原料需求信息
			int departmentId = Integer.parseInt(request
					.getParameter("departmentId") != null ? request
					.getParameter("departmentId") : "0");
			int warehouseId = Integer.parseInt(request
					.getParameter("warehouseId") != null ? request
					.getParameter("warehouseId") : "0");
			infos = departmentService.findRequirement(page, departmentId);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					info.setReportAmount(orderService.getRepertoryAmount(
							warehouseId, info.getRawMaterialId()));// 查询原料库存数量
				}
			}
			totalRows = departmentService.getTotalRow(departmentId);
		} else if ("findLLMaterial".equals(findType)) {// 查询领料单原料信息
			int stocksRequisitionId = Integer.parseInt(request
					.getParameter("stocksRequisitionId") != null ? request
					.getParameter("stocksRequisitionId") : "0");
			infos = orderService.findLLMaterial(page, stocksRequisitionId);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					BigDecimal bigDecimal = new BigDecimal(
							info.getRawMaterialAmount());
					info.setTotalPrice(bigDecimal.multiply(info
							.getRawMaterialPrice()));// 计算当前原料总金额
					info.setOldAmount(info.getRawMaterialAmount());// 记录当前原料领料数量
				}
			}
			totalRows = orderService.getLLMaterialRow(stocksRequisitionId);
		} else if ("findWarehouse".equals(findType)) {// 查询库存原料信息
			int warehouseId = Integer.parseInt(request
					.getParameter("warehouseId") != null ? request
					.getParameter("warehouseId") : "0");
			infos = orderService.findRepertory(page, warehouseId);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					info.setReportAmount(orderService.getRepertoryAmount(
							warehouseId, info.getRawMaterialId()));// 查询原料库存数量
				}
			}
			totalRows = orderService.getCKMaterialRow(warehouseId);
		} else if ("findMaterial4".equals(findType)) {// 查询所有原料信息
			String num = request.getParameter("rawMaterialNum") != null ? request
					.getParameter("rawMaterialNum") : "";
			String name = request.getParameter("rawMaterialName") != null ? request
					.getParameter("rawMaterialName") : "";
			infos = rawMaterialService.selectAll(page, num, name);
			totalRows = rawMaterialService.getTotalRows(num, name);
		} else if ("findCheck".equals(findType)) {// 查询盘点原料信息
			int warehouseId = Integer.parseInt(request
					.getParameter("warehouseId") != null ? request
					.getParameter("warehouseId") : "0");
			infos = orderService.findCheck(page, warehouseId);
			if (infos != null) {
				for (RawMaterialInfo info : infos) {
					info.setReportAmount(orderService.getRepertoryAmount(
							warehouseId, info.getRawMaterialId()));// 查询原料库存数量
					info.setStockAmount(info.getRawMaterialPrice(),
							info.getReportAmount());// 计算库存金额
					info.setCheckAmount(info.getRawMaterialPrice(),
							info.getInventoryCount());// 计算盘点金额
					info.setRawMaterialAmount(info.getInventoryCount());// 盘点数
					info.setNumberOfProfitAndLoss(info.getReportAmount()
							- info.getInventoryCount());// 计算盈亏数
					info.setProfitAndLossAmount(info.getRawMaterialPrice(),
							info.getNumberOfProfitAndLoss());// 计算盈亏金额
				}
			}
			totalRows = orderService.getCheckRow(warehouseId);
		}
		LayuiJSON<RawMaterialInfo> layuiJSON = new LayuiJSON<RawMaterialInfo>(
				0, "", totalRows, infos);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}

	/**
	 * 绑定下拉框信息 optionType绑定类型 PublicUtil工具类，用于response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void smallOption(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String optionType = request.getParameter("optionType") != null ? request
				.getParameter("optionType") : "";
		if ("finSmall".equals(optionType)) {// 根据供应商id查询原料小类信息
			int supplierId = Integer.parseInt(request
					.getParameter("supplierId") != null ? request
					.getParameter("supplierId") : "0");
			List<RawMaterialSmall> smalls = rawMaterialService
					.finSmall(supplierId);
			PublicUtil.jsonArrayReturn(response, smalls);
		} else if ("selectSmall".equals(optionType)) {// 根据原料大类id查询原料小类信息
			int rawMaterialBigId = Integer.parseInt(request
					.getParameter("rawMaterialBigId") != null ? request
					.getParameter("rawMaterialBigId") : "0");
			List<RawMaterialSmall> smalls = rawMaterialService
					.selectSmall(rawMaterialBigId);
			PublicUtil.jsonArrayReturn(response, smalls);
		} else if ("selectRawMaterial".equals(optionType)) {// 根据原料小类id查询原料信息
			int rawMaterialSmallId = Integer.parseInt(request
					.getParameter("rawMaterialSmallId") != null ? request
					.getParameter("rawMaterialSmallId") : "0");
			List<RawMaterial> rawMaterials = rawMaterialService
					.selectRawMaterial(rawMaterialSmallId);
			PublicUtil.jsonArrayReturn(response, rawMaterials);
		}
	}

	/***
	 * 新增单据信息 bills 单据信息 billsDetails 单据明细集合 order (采购、配送、入库、退货、领料、领退、调拨、盘点)单据信息
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addBills(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String billsType = request.getParameter("billsType") != null ? request
				.getParameter("billsType") : "";
		int staffId = Integer
				.parseInt(request.getParameter("staffID") != null ? request
						.getParameter("staffID") : "0");
		Bills bills = RequestHelper.getSingleRequest(request, Bills.class);// 通过反射获取对象数据
		if (staffId > 0) {
			bills.setStaffId(staffId);
		}
		List<BillsDetail> billsDetails = JSONArray.parseArray(
				request.getParameter("tableInfo"), BillsDetail.class);// 获取对象数组
		for (BillsDetail billsDetail : billsDetails) {
			if (!"".equals(billsDetail.getRemark())
					&& !billsDetail.getRemark().isEmpty()) {
				// 解决中文乱码问题
				billsDetail.setRemark(RequestHelper.getNewString(billsDetail
						.getRemark().trim()));
			}
		}
		String msg = new String();
		int billsId = billsService.insert(bills, billsDetails);
		if (billsId > 0) {
			Order order = RequestHelper.getSingleRequest(request, Order.class);
			order.setBillsId(billsId);
			if ("purchaseOrders".equals(billsType)) {// 新增采购单
				msg = orderService.insertPurchaseOrders(order);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg("落单成功!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增采购单失败!");
				}
			} else if ("dispatchingOrders".equals(billsType)) {// 新增配送单
				msg = orderService.insertDispatchingOrders(order);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg("落单成功!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增配送单失败!");
				}
			} else if ("godownOrders".equals(billsType)) {// 新增入库单
				msg = orderService.insertGodownOrders(order);
				if ("新增成功".equals(msg)) {
					// 更新库存
					int flag = billsService
							.insertRepertory(bills, billsDetails);
					if (flag > 0) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("落单成功!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg("更新库存失败!");
					}
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增入库单失败!");
				}
			} else if ("creditOrders".equals(billsType)) {// 新增退货单
				msg = orderService.insertCreditOrders(order);
				if ("新增成功".equals(msg)) {
					// 更新库存
					int flag = billsService.updateRepertory(bills,
							billsDetails, true);
					if (flag > 0) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("落单成功!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg("更新库存失败!");
					}
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增退货单失败!");
				}
			} else if ("stocksRequisition".equals(billsType)) {// 新增领料单
				msg = orderService.insertStocksRequisition(order, billsDetails);
				if ("新增成功".equals(msg)) {
					// 更新库存
					int flag = billsService.updateRepertory(bills,
							billsDetails, true);
					if (flag > 0) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("落单成功!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg("更新库存失败!");
					}
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增领料单失败!");
				}
			} else if ("pickingCreditOrders".equals(billsType)) {// 新增领料退货单
				msg = orderService.insertPickingCreditOrders(order,
						billsDetails);
				if ("新增成功".equals(msg)) {
					// 更新库存
					int flag = billsService.updateRepertory(bills,
							billsDetails, false);
					if (flag > 0) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("落单成功!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg("更新库存失败!");
					}
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增领料单失败!");
				}
			} else if ("warehouseTransferOrder".equals(billsType)) {// 新增仓库调拨单
				int warehouseId = Integer.parseInt(request
						.getParameter("warehouseID") != null ? request
						.getParameter("warehouseID") : "0");
				order.setWarehouseId(warehouseId);
				msg = orderService.insertWarehouseTransferOrder(order, bills,
						billsDetails);
				if ("新增成功".equals(msg)) {
					bills.setWarehouseId(warehouseId);
					int flag = billsService.updateRepertory(bills,
							billsDetails, true);
					if (flag > 0) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("落单成功!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg("更新库存失败!");
					}
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增仓库调拨单失败!");
				}
			} else if ("repertoryCheck".equals(billsType)) {// 新增盘点单
				msg = orderService.insertRepertoryCheck(order);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg("落单成功!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("新增盘点单失败!");
				}
			}
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg("新增单据失败!");
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/***
	 * 查询单据信息 orderType单据类型，用于判断查询单据信息 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findOrderInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderType = request.getParameter("orderType") != null ? request
				.getParameter("orderType") : "";
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		long totalRows = 0;
		if ("findPurchase".equals(orderType)) {// 查询采购单
			orderInfos = orderService.findPurchase(page);
			totalRows = orderService.getPurchaseRow();
		} else if ("findDispatching".equals(orderType)) {// 查询配送单
			orderInfos = orderService.findDispatching(page);
			totalRows = orderService.getDispatchingRow();
		} else if ("findGodownOrders".equals(orderType)) {// 查询入库单
			orderInfos = orderService.findGodownOrders(page);
			totalRows = orderService.getGodownOrdersRow();
		} else if ("findStocksRequisition".equals(orderType)) {// 查询领料单
			orderInfos = orderService.findStocksRequisition(page);
			totalRows = orderService.getStocksRequisitionRow();
		}
		if (orderInfos != null) {// 判断单据信息是否为空
			for (OrderInfo orderInfo : orderInfos) {
				// 转换时间格式yyyy-MM-dd
				orderInfo.setBillsEntryTimes(orderInfo.getBillsEntryTime());
			}
		}
		LayuiJSON<OrderInfo> layuiJSON = new LayuiJSON<OrderInfo>(0, "",
				totalRows, orderInfos);
		PublicUtil.jsonObjectReturn(response, layuiJSON);
	}

	/**
	 * 新增或修改信息 addType新增类型，用于判断新增修改或删除信息 msg变量是返回提示信息
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEidtSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String addType = request.getParameter("addType") != null ? request
				.getParameter("addType") : "";
		String msg = new String();
		if (addType != null) {
			if ("addCheck".equals(addType)) {// 新增盘点信息
				Check check = RequestHelper.getSingleRequest(request,
						Check.class);
				Check check2 = orderService.selectCheck(check.getWarehouseId(),
						check.getRawMaterialId());
				if (check2 != null) {// 判断盘点信息是否为空
					check.setCheckId(check2.getCheckId());
					msg = orderService.updateCheck(check);
					if ("修改成功".equals(msg)) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("已更新该原料盘点信息");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg(msg);
					}
				} else {
					msg = orderService.insertCheck(check);
					if ("新增成功".equals(msg)) {
						jsonReturn.setState(true);
						jsonReturn.setMsg("已成功添加该原料信息");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg(msg);
					}
				}
			} else if ("delCheck".equals(addType)) {// 删除盘点信息
				int checkId = Integer
						.parseInt(request.getParameter("checkId") != null ? request
								.getParameter("checkId") : "0");
				msg = orderService.delCheck(checkId);
				if ("删除成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg);
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg);
				}
			}
		} else {
			// 新增部门原料需求信息
			MaterialRequirement requirement = RequestHelper.getSingleRequest(
					request, MaterialRequirement.class);
			if (requirement.getMaterialRequirementId() != null) {
				msg = departmentService.update(requirement);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg);
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg);
				}
			} else {
				msg = departmentService.insert(requirement);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg);
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg);
				}
			}
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/***
	 * 查询库存原料数量 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getReportAmount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		int warehouseId = Integer
				.parseInt(request.getParameter("warehouseId") != null ? request
						.getParameter("warehouseId") : "0");
		int rawMaterialId = Integer.parseInt(request
				.getParameter("rawMaterialId") != null ? request
				.getParameter("rawMaterialId") : "0");
		int reportAmount = orderService.getRepertoryAmount(warehouseId,
				rawMaterialId);
		jsonReturn.setSum(reportAmount);
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

}
