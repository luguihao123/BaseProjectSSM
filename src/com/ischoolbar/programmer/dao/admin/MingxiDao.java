package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Mingxi;

@Repository
public interface MingxiDao {
	public Mingxi findBytime(String time);
	public int add(Mingxi mingxi);
	public List<Mingxi> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
