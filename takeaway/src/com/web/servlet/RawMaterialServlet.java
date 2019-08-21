package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.po.RawMaterial;
import com.web.po.RawMaterialBig;
import com.web.po.RawMaterialSmall;
import com.web.po.Unit;
import com.web.service.IRawMaterialService;
import com.web.service.impl.RawMaterialServiceImpl;
import com.web.util.DBUtil;
import com.web.util.RequestHelper;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;

public class RawMaterialServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IRawMaterialService rawMaterialService = new RawMaterialServiceImpl();

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

	public void showSetMaterial(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Unit> units = rawMaterialService.findUnit();
		request.setAttribute("units", units);
		request.getRequestDispatcher("/jsp/SetRawMaterial.jsp").forward(
				request, response);
	}

	public void tabMaterialType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Page page = new Page();		
		String rawMaterialType = request.getParameter("rawMaterialType");
		if ("rawMaterialBig".equals(rawMaterialType)) {
			page = RequestHelper.getSingleRequest(request, Page.class);
			page.setStartIndex(page.getPage(), page.getLimit());
			List<RawMaterialBig> bigs = rawMaterialService.findBig(page);
			long totalRow = rawMaterialService.getBigTotalRow();
			if (bigs.size() == 0 && totalRow == 0) {
				RawMaterialBig big = new RawMaterialBig();
				big.setRawMaterialBigNum("");
				big.setRawMaterialBigName("");
				bigs.add(big);
			}
			LayuiJSON<RawMaterialBig> layuiJSON = new LayuiJSON<RawMaterialBig>(
					0, "", totalRow, bigs);
			DBUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {
			page = RequestHelper.getSingleRequest(request, Page.class);
			page.setStartIndex(page.getPage(), page.getLimit());
			int id = Integer.parseInt(request.getParameter("rawMaterialBigId")
					.toString());
			List<RawMaterialSmall> smalls = rawMaterialService.findSmall(page,
					id);
			long totalRow = rawMaterialService.getSmallTotalRow(id);
			if (smalls.size() == 0 && totalRow == 0) {
				RawMaterialSmall small = new RawMaterialSmall();
				small.setRawMaterialSmallNum("");
				small.setRawMaterialSmallName("");
				smalls.add(small);
			}
			LayuiJSON<RawMaterialSmall> layuiJSON = new LayuiJSON<RawMaterialSmall>(
					0, "", totalRow, smalls);
			DBUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("rawMaterial".equals(rawMaterialType)) {
			page = RequestHelper.getSingleRequest(request, Page.class);
			page.setStartIndex(page.getPage(), page.getLimit());
			int id = Integer.parseInt(request
					.getParameter("rawMaterialSmallId").toString());
			List<RawMaterial> rawMaterials = rawMaterialService.findAll(page,
					id);
			long totalRow = rawMaterialService.getTotalRow(id);
			if (rawMaterials.size() == 0 && totalRow == 0) {
				RawMaterial rawMaterial = new RawMaterial();
				rawMaterial.setRawMaterialNum("");
				rawMaterial.setRawMaterialName("");
				rawMaterial.setRawMaterialPrice(null);
				rawMaterial.setUnitName("");
				rawMaterial.setPinyinCode("");
				rawMaterials.add(rawMaterial);
			}
			LayuiJSON<RawMaterial> layuiJSON = new LayuiJSON<RawMaterial>(0,
					"", totalRow, rawMaterials);
			DBUtil.jsonObjectReturn(response, layuiJSON);
		}

	}

	public void addEidtSave(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String rawMaterialType = request.getParameter("rawMaterialType");
		String msg = new String();
		if ("rawMaterialBig".equals(rawMaterialType)) {
			RawMaterialBig big = RequestHelper.getSingleRequest(request,
					RawMaterialBig.class);
			if (big.getRawMaterialBigId() == null) {
				msg = rawMaterialService.insert(big);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			} else {
				msg = rawMaterialService.update(big);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			}
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {
			RawMaterialSmall small = RequestHelper.getSingleRequest(request,
					RawMaterialSmall.class);
			if (small.getRawMaterialSmallId() == null) {
				msg = rawMaterialService.insert(small);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			} else {
				msg = rawMaterialService.update(small);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			}
		} else if ("rawMaterial".equals(rawMaterialType)) {
			RawMaterial rawMaterial = RequestHelper.getSingleRequest(request,
					RawMaterial.class);
			if (rawMaterial.getRawMaterialId() == null) {
				msg = rawMaterialService.insert(rawMaterial);
				if ("新增成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			} else {
				msg = rawMaterialService.update(rawMaterial);
				if ("修改成功".equals(msg)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(msg + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(msg + "!");
				}
			}
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}

	public void findTypeById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rawMaterialType = request.getParameter("rawMaterialType");
		if ("rawMaterialBig".equals(rawMaterialType)) {
			int id = Integer.parseInt(request.getParameter("rawMaterialBigId")
					.toString());
			RawMaterialBig big = rawMaterialService.findBigById(id);
			DBUtil.jsonObjectReturn(response, big);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {
			int id = Integer.parseInt(request
					.getParameter("rawMaterialSmallId").toString());
			RawMaterialSmall small = rawMaterialService.findSmallById(id);
			DBUtil.jsonObjectReturn(response, small);
		} else if ("rawMaterial".equals(rawMaterialType)) {
			int id = Integer.parseInt(request.getParameter("rawMaterialId")
					.toString());
			RawMaterial rawMaterial = rawMaterialService.findById(id);
			DBUtil.jsonObjectReturn(response, rawMaterial);
		}
	}

	public void delRawMaterialType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String rawMaterialType = request.getParameter("rawMaterialType");
		String msg = new String();
		if ("rawMaterialBig".equals(rawMaterialType)) {
			int id = Integer.parseInt(request.getParameter("rawMaterialBigId")
					.toString());
			msg = rawMaterialService.delBig(id);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {
			int id = Integer.parseInt(request.getParameter(
					"raw_material_small_id").toString());
			msg = rawMaterialService.delSmall(id);
		} else if ("rawMaterial".equals(rawMaterialType)) {
			int id = Integer.parseInt(request.getParameter("raw_material_id")
					.toString());
			msg = rawMaterialService.delete(id);
		}
		if ("删除成功".equals(msg)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg(msg + "!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg(msg + "!");
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}

	public void getNumber(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String rawMaterialType = request.getParameter("rawMaterialType");
		String num = new String();
		if ("rawMaterialBig".equals(rawMaterialType)) {
			List<RawMaterialBig> bigs = rawMaterialService.finBig();
			if (bigs.size() != 0) {
				num = bigs.get(bigs.size() - 1).getRawMaterialBigNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 3) {
				num = "0" + num;
			}
			RawMaterialBig big = new RawMaterialBig();
			big.setRawMaterialBigNum(num);
			DBUtil.jsonObjectReturn(response, big);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {
			List<RawMaterialSmall> smalls = rawMaterialService.finSmall();
			if (smalls.size() != 0) {
				num = smalls.get(smalls.size() - 1).getRawMaterialSmallNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 4) {
				num = "0" + num;
			}
			RawMaterialSmall small = new RawMaterialSmall();
			small.setRawMaterialSmallNum(num);
			DBUtil.jsonObjectReturn(response, small);
		} else if ("rawMaterial".equals(rawMaterialType)) {
			List<RawMaterial> rawMaterials = rawMaterialService.selectAll();
			if (rawMaterials.size() != 0) {
				num = rawMaterials.get(rawMaterials.size() - 1)
						.getRawMaterialNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 5) {
				num = "0" + num;
			}
			RawMaterial rawMaterial = new RawMaterial();
			rawMaterial.setRawMaterialNum(num);
			DBUtil.jsonObjectReturn(response, rawMaterial);
		}
	}

}
