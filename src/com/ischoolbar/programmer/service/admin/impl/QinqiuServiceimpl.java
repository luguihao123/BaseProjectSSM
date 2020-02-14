package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.QinqiuDao;
import com.ischoolbar.programmer.entity.admin.Qinqiu;
import com.ischoolbar.programmer.service.admin.QinqiuService;

@Service
public class QinqiuServiceimpl implements QinqiuService {

	@Autowired
	QinqiuDao qinqiuDao;
	
	@Override
	public Qinqiu findByQinqiuname(String name) {
		// TODO Auto-generated method stub
		return qinqiuDao.findByQinqiuname(name);
	}

	@Override
	public int add(Qinqiu qinqiu) {
		// TODO Auto-generated method stub
		return qinqiuDao.add(qinqiu);
	}

	@Override
	public int delete(String name) {
		// TODO Auto-generated method stub
		return qinqiuDao.delete(name);
	}

	@Override
	public List<Qinqiu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return qinqiuDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return qinqiuDao.getTotal(queryMap);
	}

}
