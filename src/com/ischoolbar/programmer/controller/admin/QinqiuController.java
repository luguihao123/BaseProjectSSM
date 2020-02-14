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

import com.ischoolbar.programmer.entity.admin.Qinqiu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.QinqiuService;

@RequestMapping("/admin/qinqiu")
@Controller
public class QinqiuController {
	@Autowired
	QinqiuService qinqiuService;
	
	/**
	 * 财务报告列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("qinqiu/list");
		return model;
	}	
		
	/**
	 * 获取财务报告界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false) String name,
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
		ret.put("rows", qinqiuService.findList(queryMap));
		ret.put("total",qinqiuService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加财务报告
	 * @param qinqiu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Qinqiu qinqiu){
		Map<String, String> ret = new HashMap<String, String>();
		if(qinqiu == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财务信息！");
			return ret;
		}
		if(StringUtils.isEmpty(qinqiu.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写请求方！");
			return ret;
		}
		if(StringUtils.isEmpty(qinqiu.getQinqiu())){
			ret.put("type", "error");
			ret.put("msg", "请填写请求内容！");
			return ret;
		}
		if(qinqiuService.add(qinqiu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "电话请求添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "电话请求添加成功！");
		return ret;
	}

	/**
	 * 删除电话请求
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的电话请求！");
			return ret;
		}
		
		if(qinqiuService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "电话请求删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "电话请求删除成功！");
		return ret;
	}		
}
