package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Chengji;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.ChengjiService;

@RequestMapping("/admin/chengji")
@Controller
public class ChengjiController {
	@Autowired
	ChengjiService chengjiService;
	
	/**
	 * 财务报告列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("chengjiList", chengjiService.findList(queryMap));
		model.setViewName("chengji/list");
		return model;
	}	
		
	/**
	 * 获取财务报告界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="people",required=false) String people,
			@RequestParam(name="fujian",required=false) String fujian
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("fujian", fujian);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", chengjiService.findList(queryMap));
		ret.put("total",chengjiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加财务报告
	 * @param chengji
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Chengji chengji){
		Map<String, String> ret = new HashMap<String, String>();
		if(chengji == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的成绩信息！");
			return ret;
		}
		if(StringUtils.isEmpty(chengji.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写员工名称！");
			return ret;
		}
		if(StringUtils.isEmpty(chengji.getTime())){
			ret.put("type", "error");
			ret.put("msg", "请填写时间段！");
			return ret;
		}
		if(StringUtils.isEmpty(chengji.getChengji())){
			ret.put("type", "error");
			ret.put("msg", "请填写员工考核成绩！");
			return ret;
		}
		
		if(chengjiService.add(chengji) <= 0){
			ret.put("type", "error");
			ret.put("msg", "员工考核成绩添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "员工考核成绩添加成功！");
		return ret;
	}
}
