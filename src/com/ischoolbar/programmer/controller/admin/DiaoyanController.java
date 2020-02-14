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

import com.ischoolbar.programmer.entity.admin.Diaoyan;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.DiaoyanService;

@RequestMapping("/admin/diaoyan")
@Controller
public class DiaoyanController {
	@Autowired
	DiaoyanService diaoyanService;
	
	/**
	 * 需求调研报告列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("diaoyan/list");
		return model;
	}	
		
	/**
	 * 获取需求调研报告界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false) String name,
			@RequestParam(name="people",required=false) String people
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", diaoyanService.findList(queryMap));
		ret.put("total",diaoyanService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加需求调研报告
	 * @param diaoyan
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Diaoyan diaoyan){
		Map<String, String> ret = new HashMap<String, String>();
		if(diaoyan == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的需求调研报告信息！");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写需求调研报告名称！");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(diaoyanService.add(diaoyan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "需求调研报告添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "需求调研报告添加成功！");
		return ret;
	}
	
	/**
	 * 修改需求调研报告
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Diaoyan diaoyan){
		Map<String, String> ret = new HashMap<String, String>();
		if(diaoyan == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的需求调研报告信息！");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写需求调研报告名称！");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(diaoyan.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(diaoyanService.edit(diaoyan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "财务报告修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财务报告修改成功！");
		return ret;
	}
	
	/**
	 * 删除需求调研报告
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的需求调研报告！");
			return ret;
		}
		if(diaoyanService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "需求调研报告删除成功！");
		return ret;
	}
}
