package com.web.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.web.po.WineGre;
import com.web.po.WineGreBig;
import com.web.po.WineGreSmall;
import com.web.service.IWineGreService;
import com.web.service.impl.WineGreServiceImpl;
import com.web.util.PublicUtil;
import com.web.util.RequestHelper;
import com.web.vo.JsonReturn;
import com.web.vo.LayuiJSON;
import com.web.vo.Page;

public class SetTablesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 6; // 6MB

	private IWineGreService wineService = new WineGreServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
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
		} else {
			addEidtWineGre(request, response);
		}
	}

	/**
	 * 查询酒菜相关信息
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findWineGres(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page page = RequestHelper.getSingleRequest(request, Page.class);
		page.setStartIndex(page.getPage(), page.getLimit());
		String wineGreType = request.getParameter("wineGreType");
		if ("wineGreBig".equals(wineGreType)) {//查询酒菜大类
			List<WineGreBig> wineGreBigs = wineService.findBig(page);
			long totalRow = wineService.count();
			if (wineGreBigs.size() == 0 && totalRow == 0) {//当数据为空时，默认设置一条空数据
				WineGreBig wineGreBig = new WineGreBig();
				wineGreBig.setWineGreBigNum("");
				wineGreBig.setWineGreBigName("");
				wineGreBigs.add(wineGreBig);
				totalRow = 1;
			}
			LayuiJSON<WineGreBig> layuiJSON = new LayuiJSON<WineGreBig>(0, "", totalRow, wineGreBigs);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("wineGreSmall".equals(wineGreType)) {//查询酒菜小类
			int wineGreBigId = Integer.parseInt(request.getParameter("wineGreBigId").toString());
			List<WineGreSmall> wineGreSmalls = wineService.findSmalll(page, wineGreBigId);
			long totalRow = wineService.getTotalRows(wineGreBigId);
			if (wineGreSmalls.size() == 0 && totalRow == 0) {//当数据为空时，默认设置一条空数据
				WineGreSmall wineGreSmall = new WineGreSmall();
				wineGreSmall.setWineGreSmallNum("");
				wineGreSmall.setWineGreSmallName("");
				wineGreSmalls.add(wineGreSmall);
				totalRow = 1;
			}
			LayuiJSON<WineGreSmall> layuiJSON = new LayuiJSON<WineGreSmall>(0, "", totalRow, wineGreSmalls);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		} else if ("wineGre".equals(wineGreType)) {//查询酒菜信息
			int wineGreSmallId = Integer.parseInt(request.getParameter("wineGreSmallId").toString());
			List<WineGre> wineGres = wineService.selectAll(page, wineGreSmallId);
			long totalRow = wineService.getTotalRow(wineGreSmallId);
			if (wineGres.size() == 0 && totalRow == 0) {//当数据为空时，默认设置一条空数据
				WineGre wineGre = new WineGre();
				wineGre.setWineGreNum("");
				wineGre.setWineGreName("");
				wineGres.add(wineGre);
				totalRow = 1;
			}
			LayuiJSON<WineGre> layuiJSON = new LayuiJSON<WineGre>(0, "", totalRow, wineGres);
			PublicUtil.jsonObjectReturn(response, layuiJSON);
		}
	}

	/**
	 * 新增或修改酒菜大类、酒菜小类信息
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEidtSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String wineGreType = request.getParameter("wineGreType");
		String str = new String();
		if ("wineGreBig".equals(wineGreType)) {//新增或修改酒菜大类
			WineGreBig wineGreBig = RequestHelper.getSingleRequest(request, WineGreBig.class);
			if (wineGreBig.getWineGreBigId() == null) {
				str = wineService.insert(wineGreBig);
				if ("新增成功".equals(str)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(str + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(str + "!");
				}
			} else {
				str = wineService.update(wineGreBig);
				if ("修改成功".equals(str)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(str + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(str + "!");
				}
			}
		} else if ("wineGreSmall".equals(wineGreType)) {//新增或修改酒菜小类
			WineGreSmall wineGreSmall = RequestHelper.getSingleRequest(request, WineGreSmall.class);
			if (wineGreSmall.getWineGreSmallId() == null) {
				str = wineService.insert(wineGreSmall);
				if ("新增成功".equals(str)) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(str + "!");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(str + "!");
				}
			} else {
				str = wineService.update(wineGreSmall);
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
	 * 根据id查询酒菜信息(修改数据回显)
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findTypeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wineGreType = request.getParameter("wineGreType");
		if ("wineGreBig".equals(wineGreType)) {//查询酒菜大类
			int id = Integer.parseInt(request.getParameter("wineGreBigId").toString());
			WineGreBig wineGreBig = wineService.findByID(id);
			PublicUtil.jsonObjectReturn(response, wineGreBig);
		} else if ("wineGreSmall".equals(wineGreType)) {//查询酒菜小类
			int id = Integer.parseInt(request.getParameter("wineGreSmallId").toString());
			WineGreSmall wineGreSmall = wineService.findBYID(id);
			PublicUtil.jsonObjectReturn(response, wineGreSmall);
		} else if ("wineGre".equals(wineGreType)) {//查询酒菜信息
			int id = Integer.parseInt(request.getParameter("wineGreId").toString());
			WineGre wineGre = wineService.findById(id);
			PublicUtil.jsonObjectReturn(response, wineGre);
		}
	}

	/**
	 * 删除酒菜信息
	 * PublicUtil工具类，response响应返回页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delWineGreType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String wineGreType = request.getParameter("wineGreType");
		String str = new String();
		if ("wineGreBig".equals(wineGreType)) {//删除酒菜大类
			int id = Integer.parseInt(request.getParameter("wineGreBigId").toString());
			str = wineService.deleteBig(id);
		} else if ("wineGreSmall".equals(wineGreType)) {//删除酒菜小类
			int id = Integer.parseInt(request.getParameter("wineGreSmallId").toString());
			str = wineService.deleteSmall(id);
		} else if ("wineGre".equals(wineGreType)) {//删除酒菜信息
			int id = Integer.parseInt(request.getParameter("wineGreId").toString());
			str = wineService.delete(id);
		}
		if ("删除成功".equals(str)) {
			jsonReturn.setState(true);
			jsonReturn.setMsg(str + "!");
		} else {
			jsonReturn.setState(false);
			jsonReturn.setMsg(str + "!");
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 自动生成编号
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getNumber(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String wineGreType = request.getParameter("wineGreType");
		String num = new String();
		if ("wineGreBig".equals(wineGreType)) {//删除酒菜大类编号
			List<WineGreBig> wineGreBigs = wineService.bigNumber();
			if (wineGreBigs.size() != 0) {
				num = wineGreBigs.get(wineGreBigs.size() - 1).getWineGreBigNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 2) {
				num = "0" + num;
			}
			WineGreBig wineGreBig = new WineGreBig();
			wineGreBig.setWineGreBigNum(num);
			PublicUtil.jsonObjectReturn(response, wineGreBig);
		} else if ("wineGreSmall".equals(wineGreType)) {//删除酒菜小类编号
			List<WineGreSmall> wineGreSmalls = wineService.smallNumber();
			if (wineGreSmalls.size() != 0) {
				num = wineGreSmalls.get(wineGreSmalls.size() - 1).getWineGreSmallNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 3) {
				num = "0" + num;
			}
			WineGreSmall wineGreSmall = new WineGreSmall();
			wineGreSmall.setWineGreSmallNum(num);
			PublicUtil.jsonObjectReturn(response, wineGreSmall);
		} else if ("wineGre".equals(wineGreType)) {//删除酒菜编号
			List<WineGre> wineGres = wineService.selectAll();
			if (wineGres.size() != 0) {
				num = wineGres.get(wineGres.size() - 1).getWineGreNum();
				num = String.valueOf(Integer.parseInt(num) + 1);
			} else {
				num = String.valueOf(1);
			}
			while (num.length() < 4) {
				num = "0" + num;
			}
			WineGre wineGre = new WineGre();
			wineGre.setWineGreNum(num);
			PublicUtil.jsonObjectReturn(response, wineGre);
		}
	}

	/**
	 * 多媒体新增或修改酒菜信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEidtWineGre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		WineGre wineGre = new WineGre();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		// 创建文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		upload.setHeaderEncoding("utf-8");
		// 开始解析请求信息
		try {
			// 解析请求的内容提取文件数据
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
			if (items != null && items.size() > 0) {
				// 对所请求信息进行判断
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) {
						String fileName = item.getName();
						if (!"".equals(fileName) && !fileName.isEmpty()) {
							System.out.println("文件名称:" + fileName);
							byte[] bs = item.get();
							wineGre.setPicture(bs);
						}
					} else {
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						if ("wineGreId".equals(name)) {
							if (!"".equals(value) && !value.isEmpty()) {
								wineGre.setWineGreId(Integer.parseInt(value));
							}
						} else if ("wineGreSmallId".equals(name)) {
							wineGre.setWineGreSmallId(Integer.parseInt(value));
						} else if ("wineGreNum".equals(name)) {
							wineGre.setWineGreNum(value);
						} else if ("wineGreName".equals(name)) {
							wineGre.setWineGreName(value);
						} else if ("wineGrePrice".equals(name)) {
							wineGre.setWineGrePrice(Double.parseDouble(value));
						} else if ("bigPrice".equals(name)) {
							wineGre.setBigPrice(Double.parseDouble(value));
						} else if ("smallPrice".equals(name)) {
							wineGre.setSmallPrice(Double.parseDouble(value));
						} else if ("memberPrice".equals(name)) {
							wineGre.setMemberPrice(Double.parseDouble(value));
						} else if ("isDiscount".equals(name)) {
							if ("1".equals(value)) {
								wineGre.setIsDiscount(true);
							} else {
								wineGre.setIsDiscount(false);
							}
						}
					}
				}
				String str = new String();
				if (wineGre.getWineGreId() == null) {
					str = wineService.insert(wineGre);
					if ("新增成功".equals(str)) {
						jsonReturn.setState(true);
						jsonReturn.setMsg(str + "!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg(str + "!");
					}
				} else {
					str = wineService.update(wineGre);
					if ("修改成功".equals(str)) {
						jsonReturn.setState(true);
						jsonReturn.setMsg(str + "!");
					} else {
						jsonReturn.setState(false);
						jsonReturn.setMsg(str + "!");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PublicUtil.jsonObjectReturn(response, jsonReturn);
	}

	/**
	 * 查询菜品图片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void rtPicture(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("wineGreId").toString() != null
				? request.getParameter("wineGreId").toString() : "0");
		byte[] bs = wineService.findPicture(id);
		ServletOutputStream out = response.getOutputStream();// 返回图片(以二进制数组返回页面)
		out.write(bs);
		out.flush();
		out.close();
	}

}
