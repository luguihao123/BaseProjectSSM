package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Diaoyan;

@Repository
public interface DiaoyanDao {
	public Diaoyan findByname(String name);
	public int add(Diaoyan diaoyan);
	public int delete(String name);
	public int edit(Diaoyan diaoyan);
	public List<Diaoyan> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
