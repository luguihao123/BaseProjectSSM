package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.ChengjiDao;
import com.ischoolbar.programmer.entity.admin.Chengji;
import com.ischoolbar.programmer.service.admin.ChengjiService;

@Service
public class ChengjiServiceimpl implements ChengjiService {

	@Autowired
	ChengjiDao chengjiDao;
	
	@Override
	public Chengji findByname(String name) {
		// TODO Auto-generated method stub
		return chengjiDao.findByname(name);
	}

	@Override
	public int add(Chengji chengji) {
		// TODO Auto-generated method stub
		return chengjiDao.add(chengji);
	}

	@Override
	public List<Chengji> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return chengjiDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return chengjiDao.getTotal(queryMap);
	}

}
