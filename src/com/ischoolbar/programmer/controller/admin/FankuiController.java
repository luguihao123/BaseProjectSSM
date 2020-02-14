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

import com.ischoolbar.programmer.entity.admin.Fankui;
import com.ischoolbar.programmer.entity.admin.Fankui;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.FankuiService;

@RequestMapping("/admin/fankui")
@Controller
public class FankuiController {
	@Autowired
	FankuiService fankuiService;
	
	/**
	 * 捐赠反馈记录列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("fankui/list");
		return model;
	}	
		
	/**
	 * 获取捐赠反馈记录界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="time",required=false) String time,
			@RequestParam(name="name",required=false) String name
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("time", time);
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", fankuiService.findList(queryMap));
		ret.put("total",fankuiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加捐赠反馈记录
	 * @param fankui
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Fankui fankui){
		Map<String, String> ret = new HashMap<String, String>();
		if(fankui == null){
			ret.put("type", "error");
			ret.put("msg", "请填写捐赠反馈记录！");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写捐赠人！");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getTime())){
			ret.put("type", "error");
			ret.put("msg", "请填写时间！");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getXinxi())){
			ret.put("type", "error");
			ret.put("msg", "请填写已反馈的信息！");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getFname())){
			ret.put("type", "error");
			ret.put("msg", "请填写反馈人！");
			return ret;
		}
		
		if(fankuiService.add(fankui) <= 0){
			ret.put("type", "error");
			ret.put("msg", "捐赠反馈记录添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "捐赠反馈记录添加成功！");
		return ret;
	}
	
	/**
	 * 修改捐赠反馈
	 */
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Fankui fankui){
		Map<String, String> ret = new HashMap<String, String>();
		if(fankui == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的捐赠反馈信息！");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getTime())){
			ret.put("type", "error");
			ret.put("msg", "请填写时间！");
			return ret;
		}
		if(StringUtils.isEmpty(fankui.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写捐赠人！");
			return ret;
		}
		if(fankuiService.edit(fankui) <= 0){
			ret.put("type", "error");
			ret.put("msg", "捐赠反馈修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "捐赠反馈修改成功！");
		return ret;
	}
	
	/**
	 * 删除捐赠反馈
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的捐赠反馈记录！");
			return ret;
		}
		if(fankuiService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "捐赠反馈删除成功！");
		return ret;
	}
}
