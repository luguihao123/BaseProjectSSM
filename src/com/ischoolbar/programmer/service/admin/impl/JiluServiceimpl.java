package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.JiluDao;
import com.ischoolbar.programmer.entity.admin.Jilu;
import com.ischoolbar.programmer.service.admin.JiluService;

@Service
public class JiluServiceimpl implements JiluService {

	@Autowired
	JiluDao jiluDao;
	
	@Override
	public Jilu findByname(String name) {
		// TODO Auto-generated method stub
		return jiluDao.findByname(name);
	}

	@Override
	public int add(Jilu jilu) {
		// TODO Auto-generated method stub
		return jiluDao.add(jilu);
	}

	@Override
	public List<Jilu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return jiluDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return jiluDao.getTotal(queryMap);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return jiluDao.delete(name);
	}

}
