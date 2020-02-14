package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.XieyiDao;
import com.ischoolbar.programmer.entity.admin.Xieyi;
import com.ischoolbar.programmer.service.admin.XieyiService;

@Service
public class XieyiServiceimpl implements XieyiService {
    
	@Autowired
	XieyiDao xieyiDao;
	
	@Override
	public Xieyi findByname(String name) {
		// TODO Auto-generated method stub
		return xieyiDao.findByname(name);
	}

	@Override
	public int add(Xieyi xieyi) {
		// TODO Auto-generated method stub
		return xieyiDao.add(xieyi);
	}

	@Override
	public List<Xieyi> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return xieyiDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return xieyiDao.getTotal(queryMap);
	}

}
