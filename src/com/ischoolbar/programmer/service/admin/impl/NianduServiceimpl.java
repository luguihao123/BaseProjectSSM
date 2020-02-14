package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.NianduDao;
import com.ischoolbar.programmer.entity.admin.Niandu;
import com.ischoolbar.programmer.service.admin.NianduService;

@Service
public class NianduServiceimpl implements NianduService {

	@Autowired
	NianduDao nianduDao;
	
	@Override
	public Niandu findByname(String name) {
		// TODO Auto-generated method stub
		return nianduDao.findByname(name);
	}

	@Override
	public int add(Niandu niandu) {
		// TODO Auto-generated method stub
		return nianduDao.add(niandu);
	}

	@Override
	public int edit(Niandu niandu) {
		// TODO Auto-generated method stub
		return nianduDao.edit(niandu);
	}

	@Override
	public List<Niandu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return nianduDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return nianduDao.getTotal(queryMap);
	}

}
