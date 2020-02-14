package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Caiwu;
import com.ischoolbar.programmer.entity.admin.Chengji;

     @Service
	 public interface ChengjiService {
    	 public Chengji findByname(String name);
    		public int add(Chengji chengji);
    		public List<Chengji> findList(Map<String, Object> queryMap);
    		public int getTotal(Map<String, Object> queryMap);
}
