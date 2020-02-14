package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Jilu;

@Repository
public interface JiluDao {
	public Jilu findByname(String name);
	public int add(Jilu jilu);
	public int delete(String name);
	public List<Jilu> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
