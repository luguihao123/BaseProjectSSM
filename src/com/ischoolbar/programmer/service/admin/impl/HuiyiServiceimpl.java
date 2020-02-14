package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.HuiyiDao;
import com.ischoolbar.programmer.entity.admin.Huiyi;
import com.ischoolbar.programmer.service.admin.HuiyiService;

@Service
public class HuiyiServiceimpl implements HuiyiService {

	@Autowired
	HuiyiDao huiyiDao;
	
	@Override
	public Huiyi findByname(String name) {
		// TODO Auto-generated method stub
		return huiyiDao.findByname(name);
	}

	@Override
	public int add(Huiyi huiyi) {
		// TODO Auto-generated method stub
		return huiyiDao.add(huiyi);
	}

	@Override
	public List<Huiyi> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return huiyiDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return huiyiDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return huiyiDao.delete(name);
	}

}
