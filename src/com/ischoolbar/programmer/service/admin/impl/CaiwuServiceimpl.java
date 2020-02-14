package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.CaiwuDao;
import com.ischoolbar.programmer.entity.admin.Caiwu;
import com.ischoolbar.programmer.service.admin.CaiwuService;

@Service
public class CaiwuServiceimpl implements CaiwuService {

	@Autowired
	CaiwuDao caiwuDao;
	
	@Override
	public Caiwu findByname(String name) {
		// TODO Auto-generated method stub
		return caiwuDao.findByname(name);
	}

	@Override
	public int add(Caiwu caiwu) {
		// TODO Auto-generated method stub
		return caiwuDao.add(caiwu);
	}

	@Override
	public int edit(Caiwu caiwu) {
		// TODO Auto-generated method stub
		return caiwuDao.edit(caiwu);
	}

	@Override
	public List<Caiwu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return caiwuDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return caiwuDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return caiwuDao.delete(name);
	}

}
