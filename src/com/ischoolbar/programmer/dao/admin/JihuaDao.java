package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Jihua;

import net.sf.jsqlparser.statement.delete.Delete;

@Repository
public interface JihuaDao {
	public Jihua findByname(String name);
	
	public int add(Jihua jihua);
	
	public int edit(Jihua jihua);
	
	public int delete(String name);
	
	public int review(String state);
	
	public List<Jihua> findList(Map<String, Object> queryMap);
	
	public int getTotal(Map<String, Object> queryMap);
}
