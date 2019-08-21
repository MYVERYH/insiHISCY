package com.web.service.impl;

import java.util.List;

import com.web.dao.IRawMaterialDao;
import com.web.dao.impl.RawMaterialDaoImpl;
import com.web.po.RawMaterial;
import com.web.po.RawMaterialBig;
import com.web.po.RawMaterialSmall;
import com.web.po.Unit;
import com.web.service.IRawMaterialService;
import com.web.vo.Page;
import com.web.vo.RawMaterialInfo;

public class RawMaterialServiceImpl implements IRawMaterialService {

	private IRawMaterialDao rawMaterialDao = new RawMaterialDaoImpl();

	@Override
	public List<RawMaterial> selectAll() {
		// TODO Auto-generated method stub
		return rawMaterialDao.selectAll();
	}

	@Override
	public RawMaterial findById(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.findById(id);
	}

	@Override
	public String insert(RawMaterial rawMaterial) {
		String str = "";
		if (rawMaterialDao.insert(rawMaterial) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(RawMaterial rawMaterial) {
		String str = "";
		if (rawMaterialDao.update(rawMaterial) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delete(int id) {
		String str = "";
		if (rawMaterialDao.delete(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<RawMaterialBig> findBig(Page page) {
		// TODO Auto-generated method stub
		return rawMaterialDao.findBig(page);
	}

	@Override
	public long getBigTotalRow() {
		// TODO Auto-generated method stub
		return rawMaterialDao.getBigTotalRow();
	}

	@Override
	public RawMaterialBig findBigById(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.findBigById(id);
	}

	@Override
	public String insert(RawMaterialBig big) {
		String str = "";
		if (rawMaterialDao.insert(big) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(RawMaterialBig big) {
		String str = "";
		if (rawMaterialDao.update(big) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delBig(int id) {
		String str = "";
		if (rawMaterialDao.delBig(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<RawMaterialSmall> findSmall(Page page, int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.findSmall(page, id);
	}

	@Override
	public long getSmallTotalRow(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.getSmallTotalRow(id);
	}

	@Override
	public RawMaterialSmall findSmallById(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.findSmallById(id);
	}

	@Override
	public String insert(RawMaterialSmall small) {
		String str = "";
		if (rawMaterialDao.insert(small) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(RawMaterialSmall small) {
		String str = "";
		if (rawMaterialDao.update(small) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delSmall(int id) {
		String str = "";
		if (rawMaterialDao.delSmall(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<RawMaterial> findAll(Page page, int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.findAll(page, id);
	}

	@Override
	public long getTotalRow(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.getTotalRow(id);
	}

	@Override
	public List<RawMaterialBig> finBig() {
		// TODO Auto-generated method stub
		return rawMaterialDao.finBig();
	}

	@Override
	public List<RawMaterialSmall> finSmall() {
		// TODO Auto-generated method stub
		return rawMaterialDao.finSmall();
	}

	@Override
	public List<Unit> findUnit() {
		// TODO Auto-generated method stub
		return rawMaterialDao.findUnit();
	}

	@Override
	public List<RawMaterialInfo> selectAll(Page page, int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.selectAll(page, id);
	}

	@Override
	public long getTotalRows(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.getTotalRows(id);
	}

	@Override
	public List<RawMaterialSmall> finSmall(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.finSmall(id);
	}

	@Override
	public List<RawMaterialSmall> selectSmall(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.selectSmall(id);
	}

	@Override
	public List<RawMaterial> selectRawMaterial(int id) {
		// TODO Auto-generated method stub
		return rawMaterialDao.selectRawMaterial(id);
	}

	@Override
	public List<RawMaterialInfo> selectAll(Page page, String num, String name) {
		// TODO Auto-generated method stub
		return rawMaterialDao.selectAll(page, num, name);
	}

	@Override
	public long getTotalRows(String num, String name) {
		// TODO Auto-generated method stub
		return rawMaterialDao.getTotalRows(num, name);
	}

}
