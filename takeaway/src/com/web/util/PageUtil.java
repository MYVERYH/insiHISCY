package com.web.util;
/**
 * 描述数据库分页信息的Java类
 * 
 */
public class PageUtil {

	/** 总共的页数 */
	public static  int  totalPage;

	/** 总共有多少记录 */
	public static int  totalCount;
	//
	/** 当前的页 */
	public static int curPage;

	/** 每页显示条目数 */
	public static int pageSize;

	// private int begin ;
	//
	// private int end;
	// begin=pageSize*(curPage-1);
	// Integer end=pageSize*curPage-1;
	// Integer pageTotal=(totalCount+pageSize-1)/pageSize;

	// /** 要排序的字段 */
	// private String orderBy;
	//
	// /** 按什么排序，只能是：asc||desc */
	// private String order;
	public static int beginIndex(int pageSize, int curPage) {
		Integer begin = pageSize * (curPage - 1);
		return begin;
	}

	public static int endIndex(int pageSize, int curPage) {
		Integer end = (pageSize * curPage) - 1;
		return end;
	}

	public static int pageTotal(int totalCount, int pageSize) {
		Integer pageTotal = (totalCount + pageSize - 1) / pageSize;
		return pageTotal;
	}

}