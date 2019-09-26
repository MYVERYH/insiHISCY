package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.WineGre;
import com.web.po.WineGreBig;
import com.web.po.WineGreSmall;
import com.web.vo.Page;

public interface IWineGreDao extends BaseDao<WineGre, Integer> {

	public List<WineGreBig> findBig(Page page);//查询酒菜大类

	public long count();

	public int insert(WineGreBig big);//新增酒菜大类

	public WineGreBig findByID(int id);//根据id查询酒菜大类

	public int update(WineGreBig big);//修改酒菜大类

	public int deleteBig(int id);//删除酒菜大类

	public List<WineGreSmall> findSmalll(Page page, int id);//根据酒菜大类id查询酒菜小类

	public long getTotalRows(int id);

	public int insert(WineGreSmall small);//新增酒菜小类

	public WineGreSmall findBYID(int id);//根据id查询酒菜小类

	public int update(WineGreSmall small);//修改酒菜小类

	public int deleteSmall(int id);//删除酒菜小类

	public List<WineGre> selectAll(Page page, int id);//根据酒菜小类id查询酒菜

	public long getTotalRow(int id);
	
	public List<WineGreBig> bigNumber();//查询酒菜大类编号
	
	public List<WineGreSmall> smallNumber();//查询酒菜小类编号
	
	public byte[] findPicture(int id);//根据菜品id查询图片
	
	public List<WineGre> selectAll(Page page);//外卖主页查询菜品
	
	public long getTotalRows();
	
	public int findSumById(int id);//根据菜品id查询菜品已卖总数
	
	public List<WineGre> findHotWineGres();//查询热门菜品

}
