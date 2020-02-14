package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Yusuan;

@Service
public interface YusuanService {
	public Yusuan findByYongtu(String yongtu);
	public int add(Yusuan yusuan);
	public int edit(Yusuan yusuan);
	public int delete(String yongtu);
	public List<Yusuan> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
