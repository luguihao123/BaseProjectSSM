package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Lixiang;

@Repository
public interface LixiangDao {
	public Lixiang findByname(String name);
	public int add(Lixiang lixiang);
	public int edit(Lixiang lixiang);
	public int delete(String name);
	public List<Lixiang> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
