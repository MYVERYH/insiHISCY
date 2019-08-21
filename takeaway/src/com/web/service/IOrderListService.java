package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.DeliveryStaff;
import com.web.po.RatingForm;
import com.web.vo.IndentInfo;
import com.web.vo.Page;

public interface IOrderListService extends BaseService<IndentInfo, Integer> {

	public List<IndentInfo> selectAll(String parameter, Page page);

	public long getTotalRows(String parameter);
	
	public List<IndentInfo> selectWineGre(int indentId, Page page, String findType);
	
	public long getTotalRows(int indentId);
	
	public byte[] selectPicture(int id);
	
	public List<DeliveryStaff> selectPS(boolean state, Page page);
	
	public long getTotalRows(boolean state);
	
	public int insertDS(DeliveryStaff deliveryStaff);
	
	public int updateDS(DeliveryStaff deliveryStaff);
	
	public RatingForm findByID(int indentId);

}
