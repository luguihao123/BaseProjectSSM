package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Jihua;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.JihuaService;

@RequestMapping("/admin/jihua")
@Controller
public class JihuaController {
	@Autowired
	JihuaService jihuaService;
	
	/**
	 * 工作计划列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("jihua/list");
		return model;
	}	
		
	/**
	 * 获取工作计划界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="people",required=false) String people
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("people", people);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", jihuaService.findList(queryMap));
		ret.put("total",jihuaService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加工作计划
	 * @param jihua
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Jihua jihua){
		Map<String, String> ret = new HashMap<String, String>();
		if(jihua == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写工作计划的名称！");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填加附件！");
			return ret;
		}
		
		if(jihuaService.add(jihua) <= 0){
			ret.put("type", "error");
			ret.put("msg", "工作计划添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "工作计划添加成功！");
		return ret;
	}
	
	/**
	 * 修改工作计划
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Jihua jihua){
		Map<String, String> ret = new HashMap<String, String>();
		if(jihua == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写工作计划的名称！");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getPeople())){
			ret.put("type", "error");
			ret.put("msg", "请填写撰写人！");
			return ret;
		}
		if(StringUtils.isEmpty(jihua.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(jihuaService.edit(jihua) <= 0){
			ret.put("type", "error");
			ret.put("msg", "工作计划修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "工作计划修改成功！");
		return ret;
	}
	
	/**
	 * 删除工作计划
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的工作计划！");
			return ret;
		}
		if(jihuaService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "该工作计划删除成功！");
		return ret;
	}
	
	/**
	 * 审核工作计划
	 */
	
	@RequestMapping(value="/review",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> review(String state){
		Map<String, String> ret = new HashMap<String, String>();
	    jihuaService.review(state);
		ret.put("msg", "状态修改成功！");
		return ret;
		/**if(jihuaService.review(state) <= 0){
			ret.put("type", "error");
			ret.put("msg", "该审核操作失败，请联系管理员");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "状态修改成功！");
		return ret;*/
	   }
}
   
