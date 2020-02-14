package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.DiaoyanDao;
import com.ischoolbar.programmer.entity.admin.Diaoyan;
import com.ischoolbar.programmer.service.admin.DiaoyanService;

@Service
public class DiaoyanServiceimpl implements DiaoyanService {

	@Autowired
	DiaoyanDao diaoyanDao;
	
	@Override
	public Diaoyan findByname(String name) {
		// TODO Auto-generated method stub
		return diaoyanDao.findByname(name);
	}

	@Override
	public int add(Diaoyan diaoyan) {
		// TODO Auto-generated method stub
		return diaoyanDao.add(diaoyan);
	}

	@Override
	public int edit(Diaoyan diaoyan) {
		// TODO Auto-generated method stub
		return diaoyanDao.edit(diaoyan);
	}

	@Override
	public List<Diaoyan> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return diaoyanDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return diaoyanDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return diaoyanDao.delete(name);
	}

}
