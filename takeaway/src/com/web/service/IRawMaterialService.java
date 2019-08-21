package com.web.service;

import java.util.List;

import com.web.common.BaseService;
import com.web.po.RawMaterial;
import com.web.po.RawMaterialBig;
import com.web.po.RawMaterialSmall;
import com.web.po.Unit;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public interface IRawMaterialService extends BaseService<RawMaterial, Integer> {

	public List<RawMaterialBig> findBig(Page page);

	public long getBigTotalRow();

	public RawMaterialBig findBigById(int id);

	public String insert(RawMaterialBig big);

	public String update(RawMaterialBig big);

	public String delBig(int id);

	public List<RawMaterialSmall> findSmall(Page page, int id);

	public long getSmallTotalRow(int id);

	public RawMaterialSmall findSmallById(int id);

	public String insert(RawMaterialSmall small);

	public String update(RawMaterialSmall small);

	public String delSmall(int id);

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
