package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.LixiangDao;
import com.ischoolbar.programmer.entity.admin.Lixiang;
import com.ischoolbar.programmer.service.admin.LixiangService;

@Service
public class LixiangServiceimpl implements LixiangService {

	@Autowired
	LixiangDao lixiangDao;
	
	@Override
	public Lixiang findByname(String name) {
		// TODO Auto-generated method stub
		return lixiangDao.findByname(name);
	}

	@Override
	public int add(Lixiang lixiang) {
		// TODO Auto-generated method stub
		return lixiangDao.add(lixiang);
	}

	@Override
	public int edit(Lixiang lixiang) {
		// TODO Auto-generated method stub
		return lixiangDao.edit(lixiang);
	}

	@Override
	public List<Lixiang> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return lixiangDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return lixiangDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return lixiangDao.delete(name);
	}

}
