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

	public List<RawMaterialBig> findBig(Page page);

	public long getBigTotalRow();

	public RawMaterialBig findBigById(int id);

	public int insert(RawMaterialBig big);

	public int update(RawMaterialBig big);

	public int delBig(int id);

	public List<RawMaterialSmall> findSmall(Page page, int id);

	public long getSmallTotalRow(int id);

	public RawMaterialSmall findSmallById(int id);

	public int insert(RawMaterialSmall small);

	public int update(RawMaterialSmall small);

	public int delSmall(int id);

	public List<RawMaterial> findAll(Page page, int id);

	public long getTotalRow(int id);

	public List<RawMaterialBig> finBig();

	public List<RawMaterialSmall> finSmall();

	public List<Unit> findUnit();
	
	public List<RawMaterialInfo> selectAll(Page page, int id);

	public long getTotalRows(int id);
	
	public List<RawMaterialSmall> finSmall(int id);
	
	public List<RawMaterialSmall> selectSmall(int id);
	
	public List<RawMaterial> selectRawMaterial(int id);

	public List<RawMaterialInfo> selectAll(Page page, String num, String name);

	public long getTotalRows(String num, String name);
	
}
