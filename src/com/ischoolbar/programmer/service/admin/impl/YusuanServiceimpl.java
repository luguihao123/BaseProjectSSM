package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.YusuanDao;
import com.ischoolbar.programmer.entity.admin.Yusuan;
import com.ischoolbar.programmer.service.admin.YusuanService;

@Service
public class YusuanServiceimpl implements YusuanService {

	@Autowired
	YusuanDao yusuanDao;
	
	@Override
	public Yusuan findByYongtu(String yongtu) {
		// TODO Auto-generated method stub
		return yusuanDao.findByYongtu(yongtu);
	}

	@Override
	public int add(Yusuan yusuan) {
		// TODO Auto-generated method stub
		return yusuanDao.add(yusuan);
	}

	@Override
	public int edit(Yusuan yusuan) {
		// TODO Auto-generated method stub
		return yusuanDao.edit(yusuan);
	}

	@Override
	public int delete(String yongtu) {
		// TODO Auto-generated method stub
		return yusuanDao.delete(yongtu);
	}

	@Override
	public List<Yusuan> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return yusuanDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return yusuanDao.getTotal(queryMap);
	}

}
