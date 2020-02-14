package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Caiwu;
import com.ischoolbar.programmer.entity.admin.Fankui;

     @Service
	 public interface FankuiService {
    	 public Fankui findByname(String name);
    		public int add(Fankui fankui);
    		public int edit(Fankui fankui);
    		public int delete(String name);
    		public List<Fankui> findList(Map<String, Object> queryMap);
    		public int getTotal(Map<String, Object> queryMap);
}
