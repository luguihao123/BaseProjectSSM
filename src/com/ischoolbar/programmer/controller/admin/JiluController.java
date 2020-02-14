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

import com.ischoolbar.programmer.entity.admin.Jilu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.JiluService;

@RequestMapping("/admin/jilu")
@Controller
public class JiluController {
	@Autowired
	JiluService jiluService;
	
	/**
	 * 项目实施记录列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("jilu/list");
		return model;
	}	
		
	/**
	 * 获取项目实施记录界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="xiepeople",required=false) String xiepeople
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("xiepeople", xiepeople);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", jiluService.findList(queryMap));
		ret.put("total",jiluService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加项目实施记录
	 * @param jilu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Jilu jilu){
		Map<String, String> ret = new HashMap<String, String>();
		if(jilu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写项目实施记录！");
			return ret;
		}
		if(StringUtils.isEmpty(jilu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写实施记录名称！");
			return ret;
		}
		if(StringUtils.isEmpty(jilu.getXiepeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(jilu.getShijian())){
			ret.put("type", "error");
			ret.put("msg", "请填写事件！");
			return ret;
		}
		if(StringUtils.isEmpty(jilu.getZuopeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写实施人员！");
			return ret;
		}
		
		if(jiluService.add(jilu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "项目实施记录添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "项目实施记录添加成功！");
		return ret;
	}
	
	/**
	 * 删除项目实施记录
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的项目实施记录！");
			return ret;
		}
		if(jiluService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "该项目实施记录删除成功！");
		return ret;
	}
}
