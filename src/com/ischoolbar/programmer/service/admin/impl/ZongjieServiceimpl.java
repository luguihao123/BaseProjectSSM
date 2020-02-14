package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.ZongjieDao;
import com.ischoolbar.programmer.entity.admin.Zongjie;
import com.ischoolbar.programmer.service.admin.ZongjieService;

@Service
public class ZongjieServiceimpl implements ZongjieService {

	@Autowired
	ZongjieDao zongjieDao;
	
	@Override
	public Zongjie findByname(String name) {
		// TODO Auto-generated method stub
		return zongjieDao.findByname(name);
	}

	@Override
	public int add(Zongjie zongjie) {
		// TODO Auto-generated method stub
		return zongjieDao.add(zongjie);
	}

	@Override
	public int edit(Zongjie zongjie) {
		// TODO Auto-generated method stub
		return zongjieDao.edit(zongjie);
	}

	@Override
	public List<Zongjie> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return zongjieDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return zongjieDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return zongjieDao.delete(name);
	}

}
