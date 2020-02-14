package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.JihuaDao;
import com.ischoolbar.programmer.entity.admin.Jihua;
import com.ischoolbar.programmer.service.admin.JihuaService;

@Service
public class JihuaServiceimpl implements JihuaService {

	@Autowired
	JihuaDao jihuaDao;
	
	@Override
	public Jihua findByname(String name) {
		// TODO Auto-generated method stub
		return jihuaDao.findByname(name);
	}

	@Override
	public int add(Jihua jihua) {
		// TODO Auto-generated method stub
		return jihuaDao.add(jihua);
	}

	@Override
	public int edit(Jihua jihua) {
		// TODO Auto-generated method stub
		return jihuaDao.edit(jihua);
	}

	@Override
	public List<Jihua> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return jihuaDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return jihuaDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return jihuaDao.delete(name);
	}

	@Override
	public int review(String state) {
		// TODO Auto-generated method stub
		return jihuaDao.review(state);
	}

}
