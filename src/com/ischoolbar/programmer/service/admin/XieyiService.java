package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.ischoolbar.programmer.entity.admin.Xieyi;

     @Service
	 public interface XieyiService {
    	    public Xieyi findByname(String name);
    		public int add(Xieyi xieyi);
    		public List<Xieyi> findList(Map<String, Object> queryMap);
    		public int getTotal(Map<String, Object> queryMap);
}
