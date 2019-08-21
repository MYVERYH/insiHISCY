package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.po.Department;
import com.web.po.RawMaterialBig;
import com.web.po.Staff;
import com.web.po.Supplier;
import com.web.po.SupplierDetail;
import com.web.po.Warehouse;
import com.web.service.IDepartmentService;
import com.web.service.IRawMaterialService;
import com.web.service.IStaffService;
import com.web.service.ISupplierService;
import com.web.service.IWarehouseService;
import com.web.service.impl.DepartmentServiceImpl;
import com.web.service.impl.RawMaterialServiceImpl;
import com.web.service.impl.StaffServiceImpl;
import com.web.service.impl.SupplierServiceImpl;
import com.web.service.impl.WarehouseServiceImpl;
import com.web.util.DBUtil;
import com.web.util.PublicUtil;
import com.web.util.RequestHelper;
import com.web.vo.DepartmentInfo;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;
import com.web.vo.SupplierInfo;
import com.web.vo.WarehouseInfo;

public class BaseInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IRawMaterialService materialService = new RawMaterialServiceImpl();
	private IStaffService staffService = new StaffServiceImpl();
	private IWarehouseService warehouseService = new WarehouseServiceImpl();
	private IDepartmentService departmentService = new DepartmentServiceImpl();
	private ISupplierService supplierService = new SupplierServiceImpl();

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

	public void showBaseInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<RawMaterialBig> bigs = materialService.finBig();
		List<Staff> staffs = staffService.selectAll();
		request.setAttribute("staffs", staffs);
		request.setAttribute("bigs", bigs);
		request.getRequestDispatcher("/jsp/BaseInfo.jsp").forward(request,
				response);
	}

	public void findBaseInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String baseType = request.getParameter("baseType");
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		if ("findWarehouse".equals(baseType)) {
			List<WarehouseInfo> infos = warehouseService.selectAll(page);
			long totalRows = warehouseService.getTotalRows();
			LayuiJSON<WarehouseInfo> layuiJSON = new LayuiJSON<WarehouseInfo>(
					0, "", totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("findDepartment".equals(baseType)) {
			List<DepartmentInfo> departments = departmentService
					.selectAll(page);
			long totalRows = departmentService.getTotalRows();
			LayuiJSON<DepartmentInfo> layuiJSON = new LayuiJSON<DepartmentInfo>(
					0, "", totalRows, departments);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("findSupplier".equals(baseType)) {
			String supplierNum = request.getParameter("supplierNum") != null ? request
					.getParameter("supplierNum") : "";
			String supplierName = request.getParameter("supplierName") != null ? request
					.getParameter("supplierName") : "";
			List<SupplierInfo> infos = supplierService.selectAll(page,
					supplierNum, supplierName);
			long totalRows = supplierService.getTotalRows(supplierNum,
					supplierName);
			LayuiJSON<SupplierInfo> layuiJSON = new LayuiJSON<SupplierInfo>(0,
					"", totalRows, infos);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		}
	}

	public void addEidtSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String baseType = request.getParameter("baseType");
		String msg = new String();
		if ("warehouse".equals(baseType)) {
			Warehouse warehouse = RequestHelper.getSingleRequest(request,
					Warehouse.class);
			if (warehouse.getWarehouseId() == null) {
				msg = warehouseService.insert(warehouse);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			} else {
				msg = warehouseService.update(warehouse);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			}
		} else if ("department".equals(baseType)) {
			Department department = RequestHelper.getSingleRequest(request,
					Department.class);
			if (department.getDepartmentId() == null) {
				msg = departmentService.insert(department);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			} else {
				msg = departmentService.update(department);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			}
		} else if ("supplier".equals(baseType)) {
			Supplier supplier = RequestHelper.getSingleRequest(request,
					Supplier.class);
			SupplierDetail detail = RequestHelper.getSingleRequest(request,
					SupplierDetail.class);
			if (supplier.getSupplierId() == null
					&& detail.getSupplierDetailId() == null) {
				msg = supplierService.insert(supplier, detail);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			} else {
				msg = supplierService.update(supplier, detail);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			}

		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	public void delBaseInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String baseType = request.getParameter("baseType");
		String msg = new String();
		if ("department".equals(baseType)) {
			int id = Integer.parseInt(request.getParameter("departmentId")
					.toString());
			msg = departmentService.delete(id);
		} else if ("warehouse".equals(baseType)) {
			int id = Integer.parseInt(request.getParameter("warehouseId")
					.toString());
			msg = warehouseService.delete(id);
		} else if ("supplier".equals(baseType)) {
			int id = Integer.parseInt(request.getParameter("supplierId")
					.toString());
			msg = supplierService.delete(id);
		}
		if ("删除成功".equals(msg)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg(msg + "!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg(msg + "!");
		}
	}

	public void getNumber(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String baseType = request.getParameter("baseType");
		String num = new String();
		if ("warehouse".equals(baseType)) {
			List<Warehouse> warehouses = warehouseService.selectAll();
			if (warehouses.size() != 0) {
				num = warehouses.get(warehouses.size() - 1).getWarehouseNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 3) {
				num = "0" + num;
			}
			Warehouse warehouse = new Warehouse();
			warehouse.setWarehouseNum(num);
			DBUtil.jsonObjectReturn(response, warehouse);
		} else if ("supplier".equals(baseType)) {
			List<Supplier> suppliers = supplierService.selectAll();
			if (suppliers.size() != 0) {
				num = suppliers.get(suppliers.size() - 1).getSupplierNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 3) {
				num = "0" + num;
			}
			Supplier supplier = new Supplier();
			supplier.setSupplierNum(num);
			DBUtil.jsonObjectReturn(response, supplier);
		}
	}
}
