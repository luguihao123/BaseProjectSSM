package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Chengji;

@Repository
public interface ChengjiDao {
	public Chengji findByname(String name);
	public int add(Chengji chengji);
	public List<Chengji> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
