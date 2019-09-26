package com.web.service.impl;

import java.util.List;

import com.web.dao.IWineGreDao;
import com.web.dao.impl.WineGreDaoImpl;
import com.web.po.WineGre;
import com.web.po.WineGreBig;
import com.web.po.WineGreSmall;
import com.web.service.IWineGreService;
import com.web.vo.Page;

public class WineGreServiceImpl implements IWineGreService {

	private IWineGreDao wineGreDao = new WineGreDaoImpl();

	@Override
	public List<WineGre> selectAll() {
		// TODO Auto-generated method stub
		return wineGreDao.selectAll();
	}

	@Override
	public WineGre findById(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.findById(id);
	}

	@Override
	public String insert(WineGre wineGre) {
		String str = "";
		if (wineGreDao.insert(wineGre) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public String update(WineGre wineGre) {
		String str = "";
		if (wineGreDao.update(wineGre) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String delete(int id) {
		String str = "";
		if (wineGreDao.delete(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<WineGreBig> findBig(Page page) {
		// TODO Auto-generated method stub
		return wineGreDao.findBig(page);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return wineGreDao.count();
	}

	@Override
	public String insert(WineGreBig big) {
		String str = "";
		if (wineGreDao.insert(big) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public WineGreBig findByID(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.findByID(id);
	}

	@Override
	public String update(WineGreBig big) {
		String str = "";
		if (wineGreDao.update(big) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String deleteBig(int id) {
		String str = "";
		if (wineGreDao.deleteBig(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<WineGreSmall> findSmalll(Page page, int id) {
		// TODO Auto-generated method stub
		return wineGreDao.findSmalll(page, id);
	}

	@Override
	public long getTotalRows(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.getTotalRows(id);
	}

	@Override
	public String insert(WineGreSmall small) {
		String str = "";
		if (wineGreDao.insert(small) > 0) {
			str = "新增成功";
		} else {
			str = "新增失败";
		}
		return str;
	}

	@Override
	public WineGreSmall findBYID(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.findBYID(id);
	}

	@Override
	public String update(WineGreSmall small) {
		String str = "";
		if (wineGreDao.update(small) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		return str;
	}

	@Override
	public String deleteSmall(int id) {
		String str = "";
		if (wineGreDao.deleteSmall(id) > 0) {
			str = "删除成功";
		} else {
			str = "删除失败";
		}
		return str;
	}

	@Override
	public List<WineGre> selectAll(Page page, int id) {
		// TODO Auto-generated method stub
		return wineGreDao.selectAll(page, id);
	}

	@Override
	public long getTotalRow(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.getTotalRow(id);
	}

	@Override
	public List<WineGreBig> bigNumber() {
		// TODO Auto-generated method stub
		return wineGreDao.bigNumber();
	}

	@Override
	public List<WineGreSmall> smallNumber() {
		// TODO Auto-generated method stub
		return wineGreDao.smallNumber();
	}

	@Override
	public byte[] findPicture(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.findPicture(id);
	}

	@Override
	public List<WineGre> selectAll(Page page) {
		// TODO Auto-generated method stub
		return wineGreDao.selectAll(page);
	}

	@Override
	public long getTotalRows() {
		// TODO Auto-generated method stub
		return wineGreDao.getTotalRows();
	}

	@Override
	public int findSumById(int id) {
		// TODO Auto-generated method stub
		return wineGreDao.findSumById(id);
	}

	@Override
	public List<WineGre> findHotWineGres() {
		// TODO Auto-generated method stub
		return wineGreDao.findHotWineGres();
	}
	
}
