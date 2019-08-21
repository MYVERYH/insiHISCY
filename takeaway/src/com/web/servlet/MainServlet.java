package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.po.User;
import com.web.service.IUserService;
import com.web.service.impl.UserServiceImpl;
import com.web.util.DBUtil;
import com.web.util.ASECode.AESUtils;
import com.web.util.validateimage.GifCaptcha;
import com.web.vo.JsonReturn;

public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Key = ")O[NB]6,YF}+efcaj{+oESb9d8>Z'e9M";

	private IUserService userService = new UserServiceImpl();

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

	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置验证码图片的宽高和验证码的长度
		GifCaptcha captcha = new GifCaptcha(96, 38, 4);
		ServletOutputStream out = response.getOutputStream();
		// captcha会产生一个二进制数组，将其全部输出到ServletOutputStream中
		String keyString = captcha.out(out);
		out.flush();
		out.close();
		request.getSession().setAttribute("randomString", keyString);
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String validateCode = request.getParameter("validateCode");
		String rememberMe = request.getParameter("rememberMe");
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("randomString").toString()
					.equalsIgnoreCase(validateCode)) {
				User user = userService.findByUserMCAndPassword(userName,
						AESUtils.encrypt(userPassword, Key).toString().trim());
				if (user != null) {
					session.setAttribute("user", user);
					if (rememberMe != null) {
						Cookie ck_userName = new Cookie("userName", userName);
						Cookie ck_Password = new Cookie("userPassword",
								userPassword);
						ck_userName.setMaxAge(24 * 60 * 60 * 7);
						ck_Password.setMaxAge(24 * 60 * 60 * 7);
						ck_userName.setPath("/takeaway/");// 创建cookie的路径
						ck_Password.setPath("/takeaway/");
						response.addCookie(ck_userName);
						response.addCookie(ck_Password);
					} else {
						// 删除所有Cookie，即清空Cookie
						Cookie[] cookies = request.getCookies();
						for (int i = 0; i < cookies.length; i++) {// 循环遍历每一个Cookie
							Cookie cookie = new Cookie(cookies[i].getName(),
									null);
							cookie.setMaxAge(0);
							cookie.setPath("/takeaway/");// 根据创建cookie的路径进行填写
							response.addCookie(cookie);
						}
					}
					jsonReturn.setState(true);
					jsonReturn.setMsg("登录成功！");
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg("用户名或密码错误!");
				}
			} else {
				jsonReturn.setState(false);
				jsonReturn.setMsg("验证码错误!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}

	public void logOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/jsp/Login.jsp").forward(request,
				response);
	}

	public void onMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reception = request.getParameter("Reception");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (Integer.parseInt(reception) != 0) {
				request.getRequestDispatcher("/jsp/Main.jsp").forward(request,
						response);
			} else {
				request.getRequestDispatcher("/jsp/FrontDesk.jsp").forward(
						request, response);
			}
		} else {
			request.getRequestDispatcher("/jsp/Login.jsp").forward(request,
					response);
		}
	}

	public void iFrame(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");
		if ("EmployeeData".equals(content)) {
			request.getRequestDispatcher(
					"/servlet/StaffServlet?type=showStaffInfo").forward(request,
					response);
		} else if ("SetTables".equals(content)) {
			request.getRequestDispatcher("/jsp/SetTables.jsp").forward(request,
					response);
		} else if ("UpdatePassword".equals(content)) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/jsp/EditUser.jsp").forward(request,
					response);
		} else if ("SetMaterial".equals(content)) {
			request.getRequestDispatcher(
					"/servlet/RawMaterialServlet?type=showSetMaterial").forward(
					request, response);
		} else if ("ManOrder".equals(content)) {
			request.getRequestDispatcher(
					"/servlet/EntryOrderServlet?type=showEntryOrder").forward(
					request, response);
		} else if ("BaseInfo".equals(content)) {
			request.getRequestDispatcher(
					"/servlet/BaseInfoServlet?type=showBaseInfo").forward(request,
					response);
		} else if ("FindOrder".equals(content)) {
			request.getRequestDispatcher(
					"/servlet/FindOrderServlet?type=showFindOrder").forward(
					request, response);
		} else if ("FindRepertory".equals(content)) {
			request.getRequestDispatcher(
					"/servlet/FindRepertoryServlet?type=showFindRepertory")
					.forward(request, response);
		}
	}

	public void editUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JsonReturn jsonReturn = new JsonReturn();
		String userName = request.getParameter("userName");
		String oldPassword = request.getParameter("oldPassword");
		String userPassword = request.getParameter("userPassword");
		if (userName != null && oldPassword != null) {
			User user = userService.findByUserMCAndPassword(userName, AESUtils
					.encrypt(oldPassword, Key).toString().trim());
			/*
			 * User user = userService.findByUserMCAndPassword(userName,
			 * oldPassword);
			 */
			if (user != null) {
				User user2 = new User();
				user2.setUserId(user.getUserId());
				user2.setUserName(userName);
				user2.setUserPassword(AESUtils.encrypt(userPassword, Key)
						.trim());
				String str = userService.update(user2);
				if (str != null) {
					jsonReturn.setState(true);
					jsonReturn.setMsg(str);
				} else {
					jsonReturn.setState(false);
					jsonReturn.setMsg(str);
				}
			} else {
				jsonReturn.setState(false);
				jsonReturn.setMsg("原密码错误！");
			}
		}
		DBUtil.jsonObjectReturn(response, jsonReturn);
	}

}
