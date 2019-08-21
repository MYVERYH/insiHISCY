package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.WineGre;
import com.web.po.WineGreBig;
import com.web.po.WineGreSmall;
import com.web.vo.Page;

public interface IWineGreDao extends BaseDao<WineGre, Integer> {

	public List<WineGreBig> findBig(Page page);

	public long count();

	public int insert(WineGreBig big);

	public WineGreBig findByID(int id);

	public int update(WineGreBig big);

	public int deleteBig(int id);

	public List<WineGreSmall> findSmalll(Page page, int id);

	public long getTotalRows(int id);

	public int insert(WineGreSmall small);

	public WineGreSmall findBYID(int id);

	public int update(WineGreSmall small);

	public int deleteSmall(int id);

	public List<WineGre> selectAll(Page page, int id);

	public long getTotalRow(int id);
	
	public List<WineGreBig> bigNumber();
	
	public List<WineGreSmall> smallNumber();
	
	public byte[] findPicture(int id);

}
