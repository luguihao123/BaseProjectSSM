package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Yusuan;

/**
 * ≤∆ŒÒ‘§À„dao
 * @author llq
 *
 */
@Repository
public interface YusuanDao {
	public Yusuan findByYongtu(String yongtu);
	public int add(Yusuan yusuan);
	public int edit(Yusuan yusuan);
	public int delete(String yongtu);
	public List<Yusuan> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
