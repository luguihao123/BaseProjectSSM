package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Diaoyan;

     @Service
	 public interface DiaoyanService {
		public Diaoyan findByname(String name);
		public int add(Diaoyan diaoyan);
		public int edit(Diaoyan diaoyan);
		public int delete(String name);
		public List<Diaoyan> findList(Map<String, Object> queryMap);
		public int getTotal(Map<String, Object> queryMap);
}
