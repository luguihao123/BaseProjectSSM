package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.MingxiDao;
import com.ischoolbar.programmer.entity.admin.Mingxi;
import com.ischoolbar.programmer.service.admin.MingxiService;

@Service
public class MingxiServiceimpl implements MingxiService {

	@Autowired
	MingxiDao mingxiDao;
	
	@Override
	public Mingxi findBytime(String time) {
		// TODO Auto-generated method stub
		return mingxiDao.findBytime(time);
	}

	@Override
	public int add(Mingxi mingxi) {
		// TODO Auto-generated method stub
		return mingxiDao.add(mingxi);
	}

	@Override
	public List<Mingxi> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return mingxiDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return mingxiDao.getTotal(queryMap);
	}

}
