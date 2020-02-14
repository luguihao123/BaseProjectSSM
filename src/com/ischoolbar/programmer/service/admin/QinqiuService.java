package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Qinqiu;

@Service
public interface QinqiuService {
	public Qinqiu findByQinqiuname(String name);
	public int add(Qinqiu qinqiu);
	public int delete(String name);
	public List<Qinqiu> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
