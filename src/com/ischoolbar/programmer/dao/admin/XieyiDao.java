package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Xieyi;

@Repository
public interface XieyiDao {
	public Xieyi findByname(String name);
	public int add(Xieyi xieyi);
	public List<Xieyi> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
