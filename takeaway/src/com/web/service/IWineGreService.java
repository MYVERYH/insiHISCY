package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.WineGre;
import com.web.po.WineGreBig;
import com.web.po.WineGreSmall;
import com.web.vo.Page;

public interface IWineGreService extends BaseService<WineGre, Integer> {

	public List<WineGreBig> findBig(Page page);

	public long count();

	public String insert(WineGreBig big);
	
	public WineGreBig findByID(int id);

	public String update(WineGreBig big);

	public String deleteBig(int id);

	public List<WineGreSmall> findSmalll(Page page, int id);

	public long getTotalRows(int id);

	public String insert(WineGreSmall small);
	
	public WineGreSmall findBYID(int id);

	public String update(WineGreSmall small);

	public String deleteSmall(int id);

	public List<WineGre> selectAll(Page page, int id);

	public long getTotalRow(int id);
	
	public List<WineGreBig> bigNumber();
	
	public List<WineGreSmall> smallNumber();
	
	public byte[] findPicture(int id);
	
	public List<WineGre> selectAll(Page page);
	
	public long getTotalRows();
	
	public int findSumById(int id);
	
	public List<WineGre> findHotWineGres();

}
