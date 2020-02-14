package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Qinqiu;

/**
 * qinqiu”√ªßdao
 * @author llq
 *
 */
@Repository
public interface QinqiuDao {
	public Qinqiu findByQinqiuname(String name);
	public int add(Qinqiu qinqiu);
	public int delete(String name);
	public List<Qinqiu> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
