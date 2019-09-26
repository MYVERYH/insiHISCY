package com.web.dao;

import java.util.List;

import com.web.common.BaseDao;
import com.web.po.RawMaterial;
import com.web.po.RawMaterialBig;
import com.web.po.RawMaterialSmall;
import com.web.po.Unit;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public interface IRawMaterialDao extends BaseDao<RawMaterial, Integer> {

	public List<RawMaterialBig> findBig(Page page);// 查询原料大类

	public long getBigTotalRow();

	public RawMaterialBig findBigById(int id);// 根据id查询原料大类信息

	public int insert(RawMaterialBig big);// 新增原料大类信息

	public int update(RawMaterialBig big);// 修改原料大类信息

	public int delBig(int id);// 删除原料大类信息

	public List<RawMaterialSmall> findSmall(Page page, int id);// 根据原料大类id查询原料小类信息

	public long getSmallTotalRow(int id);

	public RawMaterialSmall findSmallById(int id);// 根据id查询原料小类信息

	public int insert(RawMaterialSmall small);// 新增原料小类信息

	public int update(RawMaterialSmall small);// 修改原料小类信息

	public int delSmall(int id);// 删除原料小类信息

	public List<RawMaterial> findAll(Page page, int id);// 根据原料小类id查询原料信息

	public long getTotalRow(int id);

	public List<RawMaterialBig> finBig();// 查询原料大类信息(绑定下拉框)

	public List<RawMaterialSmall> finSmall();// 查询原料小类信息(绑定下拉框)

	public List<Unit> findUnit();// 查询单位信息(绑定下拉框)
	
	public List<RawMaterialInfo> selectAll(Page page, int id);// 根据id查询原料信息

	public long getTotalRows(int id);
	
	public List<RawMaterialSmall> finSmall(int id);// 根据id查询原料小类信息
	
	public List<RawMaterialSmall> selectSmall(int id);// 根据id查询原料小类信息
	
	public List<RawMaterial> selectRawMaterial(int id);// 根据id查询原料信息

	public List<RawMaterialInfo> selectAll(Page page, String num, String name);// 多条件查询原料信息

	public long getTotalRows(String num, String name);
	
}
