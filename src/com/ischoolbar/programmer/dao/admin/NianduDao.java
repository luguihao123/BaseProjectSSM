package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Niandu;

@Repository
public interface NianduDao {
	public Niandu findByname(String name);
	public int add(Niandu niandu);
	public int edit(Niandu niandu);
	public List<Niandu> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
