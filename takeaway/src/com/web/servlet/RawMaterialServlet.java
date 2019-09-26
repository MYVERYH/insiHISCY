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
import com.web.util.PublicUtil;
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		Class<?> servlet = this.getClass();//获取当前Class类(即RawMaterialServlet.class)
		Method[] methods = servlet.getDeclaredMethods();//获取servlet下面所有方法
		for (Method method : methods) {
			String methodName = method.getName();//获取方法名称
			if (methodName.equals(type)) {
				try {
					System.out.println("方法名：" + methodName);
					method.invoke(servlet.newInstance(), request, response);//调用方法
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 返回原料设置页面 units单位信息(绑定下拉框)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showSetMaterial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Unit> units = rawMaterialService.findUnit();
		request.setAttribute("units", units);
		request.getRequestDispatcher("/jsp/SetRawMaterial.jsp").forward(request, response);
	}

	/**
	 * 查询原料信息 rawMaterialType原料类别 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void tabMaterialType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page page = new Page();
		String rawMaterialType = request.getParameter("rawMaterialType");
		if ("rawMaterialBig".equals(rawMaterialType)) {// 查询原料大类信息
			page = RequestHelper.getSingleRequest(request, Page.class);
			page.setStartIndex(page.getPage(), page.getLimit());
			List<RawMaterialBig> bigs = rawMaterialService.findBig(page);
			long totalRow = rawMaterialService.getBigTotalRow();
			if (bigs.size() == 0 && totalRow == 0) {//当原料大类信息为空时，默认设置一条空数据
				RawMaterialBig big = new RawMaterialBig();
				big.setRawMaterialBigNum("");
				big.setRawMaterialBigName("");
				bigs.add(big);
			}
			LayuiJSON<RawMaterialBig> layuiJSON = new LayuiJSON<RawMaterialBig>(0, "", totalRow, bigs);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {// 查询原料小类
			page = RequestHelper.getSingleRequest(request, Page.class);
			page.setStartIndex(page.getPage(), page.getLimit());
			int id = Integer.parseInt(request.getParameter("rawMaterialBigId").toString());
			List<RawMaterialSmall> smalls = rawMaterialService.findSmall(page, id);
			long totalRow = rawMaterialService.getSmallTotalRow(id);
			if (smalls.size() == 0 && totalRow == 0) {//当该原料大类下面的原料小类为空时，默认设置一条空数据
				RawMaterialSmall small = new RawMaterialSmall();
				small.setRawMaterialSmallNum("");
				small.setRawMaterialSmallName("");
				smalls.add(small);
			}
			LayuiJSON<RawMaterialSmall> layuiJSON = new LayuiJSON<RawMaterialSmall>(0, "", totalRow, smalls);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("rawMaterial".equals(rawMaterialType)) {// 查询原料信息
			page = RequestHelper.getSingleRequest(request, Page.class);
			page.setStartIndex(page.getPage(), page.getLimit());
			int id = Integer.parseInt(request.getParameter("rawMaterialSmallId").toString());
			List<RawMaterial> rawMaterials = rawMaterialService.findAll(page, id);
			long totalRow = rawMaterialService.getTotalRow(id);
			if (rawMaterials.size() == 0 && totalRow == 0) {//当该原料小类下面的原料信息为空时，默认设置一条空数据
				RawMaterial rawMaterial = new RawMaterial();
				rawMaterial.setRawMaterialNum("");
				rawMaterial.setRawMaterialName("");
				rawMaterial.setRawMaterialPrice(null);
				rawMaterial.setUnitName("");
				rawMaterial.setPinyinCode("");
				rawMaterials.add(rawMaterial);
			}
			LayuiJSON<RawMaterial> layuiJSON = new LayuiJSON<RawMaterial>(0, "", totalRow, rawMaterials);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		}

	}

	/**
	 * 新增或修改原料信息 rawMaterialType原料类别 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEidtSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String rawMaterialType = request.getParameter("rawMaterialType");
		String msg = new String();
		if ("rawMaterialBig".equals(rawMaterialType)) {//新增或修改原料大类信息
			RawMaterialBig big = RequestHelper.getSingleRequest(request, RawMaterialBig.class);
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
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {//新增或修改原料小类信息
			RawMaterialSmall small = RequestHelper.getSingleRequest(request, RawMaterialSmall.class);
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
		} else if ("rawMaterial".equals(rawMaterialType)) {//新增或修改原料信息
			RawMaterial rawMaterial = RequestHelper.getSingleRequest(request, RawMaterial.class);
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
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 根据id查询原料信息(修改回显原料大类、原料小类、原料信息) rawMaterialType原料类别
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findTypeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rawMaterialType = request.getParameter("rawMaterialType");
		if ("rawMaterialBig".equals(rawMaterialType)) {// 查询原料大类
			int id = Integer.parseInt(request.getParameter("rawMaterialBigId").toString());
			RawMaterialBig big = rawMaterialService.findBigById(id);
			PublicUtil.jsonObjectReturn(response, big);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {// 查询原料小类
			int id = Integer.parseInt(request.getParameter("rawMaterialSmallId").toString());
			RawMaterialSmall small = rawMaterialService.findSmallById(id);
			PublicUtil.jsonObjectReturn(response, small);
		} else if ("rawMaterial".equals(rawMaterialType)) {// 查询原料信息
			int id = Integer.parseInt(request.getParameter("rawMaterialId").toString());
			RawMaterial rawMaterial = rawMaterialService.findById(id);
			PublicUtil.jsonObjectReturn(response, rawMaterial);
		}
	}

	/**
	 * 删除原料信息 rawMaterialType原料类别 jsonReturn返回执行状态和提示信息
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delRawMaterialType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String rawMaterialType = request.getParameter("rawMaterialType");
		String msg = new String();
		if ("rawMaterialBig".equals(rawMaterialType)) {// 删除原料大类
			int id = Integer.parseInt(request.getParameter("rawMaterialBigId").toString());
			msg = rawMaterialService.delBig(id);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {// 删除原料小类
			int id = Integer.parseInt(request.getParameter("raw_material_small_id").toString());
			msg = rawMaterialService.delSmall(id);
		} else if ("rawMaterial".equals(rawMaterialType)) {// 删除原料
			int id = Integer.parseInt(request.getParameter("raw_material_id").toString());
			msg = rawMaterialService.delete(id);
		}
		if ("删除成功".equals(msg)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg(msg + "!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg(msg + "!");
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 自动生成编号 rawMaterialType原料类别 num变量，自动生成的编号 PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getNumber(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rawMaterialType = request.getParameter("rawMaterialType");
		String num = new String();
		if ("rawMaterialBig".equals(rawMaterialType)) {// 生成原料大类编号
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
			PublicUtil.jsonObjectReturn(response, big);
		} else if ("rawMaterialSmall".equals(rawMaterialType)) {// 生成原料小类编号
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
			PublicUtil.jsonObjectReturn(response, small);
		} else if ("rawMaterial".equals(rawMaterialType)) {// 生成原料编号
			List<RawMaterial> rawMaterials = rawMaterialService.selectAll();
			if (rawMaterials.size() != 0) {
				num = rawMaterials.get(rawMaterials.size() - 1).getRawMaterialNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 5) {
				num = "0" + num;
			}
			RawMaterial rawMaterial = new RawMaterial();
			rawMaterial.setRawMaterialNum(num);
			PublicUtil.jsonObjectReturn(response, rawMaterial);
		}
	}

}
