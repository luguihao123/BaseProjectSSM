package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Caiwu;

@Repository
public interface CaiwuDao {
	public Caiwu findByname(String name);
	public int add(Caiwu caiwu);
	public int edit(Caiwu caiwu);
	public int delete(String name);
	public List<Caiwu> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
