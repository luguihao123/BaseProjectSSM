package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Huiyi;

@Repository
public interface HuiyiDao {
	public Huiyi findByname(String name);
	public int add(Huiyi huiyi);
	public int delete(String name);
	public List<Huiyi> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
