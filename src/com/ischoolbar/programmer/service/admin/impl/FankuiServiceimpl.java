package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.FankuiDao;
import com.ischoolbar.programmer.entity.admin.Fankui;
import com.ischoolbar.programmer.service.admin.FankuiService;

@Service
public class FankuiServiceimpl implements FankuiService {

	@Autowired
	FankuiDao fankuiDao;
	
	@Override
	public Fankui findByname(String name) {
		// TODO Auto-generated method stub
		return fankuiDao.findByname(name);
	}

	@Override
	public int add(Fankui fankui) {
		// TODO Auto-generated method stub
		return fankuiDao.add(fankui);
	}

	@Override
	public List<Fankui> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return fankuiDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return fankuiDao.getTotal(queryMap);
	}

	@Override
	public int edit(Fankui fankui) {
		// TODO Auto-generated method stub
		return fankuiDao.edit(fankui);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return fankuiDao.delete(name);
	}

}
