package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Zongjie;

@Repository
public interface ZongjieDao {
	public Zongjie findByname(String name);
	public int add(Zongjie zongjie);
	public int edit(Zongjie zongjie);
	public int delete(String name);
	public List<Zongjie> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
