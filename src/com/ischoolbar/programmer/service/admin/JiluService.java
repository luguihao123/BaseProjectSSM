package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.ischoolbar.programmer.entity.admin.Huiyi;
import com.ischoolbar.programmer.entity.admin.Jilu;

     @Service
	 public interface JiluService {
    	    public Jilu findByname(String name);
    		public int add(Jilu jilu);
    		public int delete(String name);
    		public List<Jilu> findList(Map<String, Object> queryMap);
    		public int getTotal(Map<String, Object> queryMap);
    		
}
