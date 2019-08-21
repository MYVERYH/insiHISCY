package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterEconding implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");//设置servlet获取页面编码格式
		response.setCharacterEncoding("utf-8");//设置页面获取servlet编码格式		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 获取初始化参数
	    String encoding = config.getInitParameter("Encoding"); 
	    // 输出初始化参数
	    System.out.println("编码格式: " + encoding); 

	}

}
