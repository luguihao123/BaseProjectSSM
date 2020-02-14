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

import com.ischoolbar.programmer.entity.admin.Niandu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.NianduService;

@RequestMapping("/admin/niandu")
@Controller
public class NianduController {
	@Autowired
	NianduService nianduService;
	
	/**
	 * 年度工作报告列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("nianduList", nianduService.findList(queryMap));
		model.setViewName("niandu/list");
		return model;
	}	
		
	/**
	 * 获取年度工作报告界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="people",required=false) String people,
			@RequestParam(name="beizhu",required=false) String beizhu,
			@RequestParam(name="fujian",required=false) String fujian
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("beizhu", beizhu);
		queryMap.put("fujian", fujian);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", nianduService.findList(queryMap));
		ret.put("total",nianduService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加年度工作报告
	 * @param niandu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Niandu niandu){
		Map<String, String> ret = new HashMap<String, String>();
		if(niandu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(niandu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写年度工作报告的名称！");
			return ret;
		}
		if(StringUtils.isEmpty(niandu.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(niandu.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填加附件！");
			return ret;
		}
		
		if(nianduService.add(niandu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "年度工作报告添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "年度工作报告添加成功！");
		return ret;
	}
	
	/**
	 * 修改工作计划
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Niandu niandu){
		Map<String, String> ret = new HashMap<String, String>();
		if(niandu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(niandu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写年度工作报告的名称！");
			return ret;
		}
		if(StringUtils.isEmpty(niandu.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(niandu.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(nianduService.edit(niandu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "年度工作报告修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "年度工作报告修改成功！");
		return ret;
	}
	
	
		
}
