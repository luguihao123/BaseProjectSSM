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

import com.ischoolbar.programmer.entity.admin.Xieyi;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.XieyiService;

@RequestMapping("/admin/xieyi")
@Controller
public class XieyiController {
	@Autowired
	XieyiService xieyiService;
	
	/**
	 * 捐赠协议列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("xieyi/list");
		return model;
	}	
		
	/**
	 * 获取捐赠协议界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false) String name,
			@RequestParam(name="time",required=false) String time
			
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("time", time);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", xieyiService.findList(queryMap));
		ret.put("total",xieyiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加捐赠协议
	 * @param xieyi
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Xieyi xieyi){
		Map<String, String> ret = new HashMap<String, String>();
		if(xieyi == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的捐赠协议信息！");
			return ret;
		}
		if(StringUtils.isEmpty(xieyi.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写捐赠协议名称！");
			return ret;
		}
		if(StringUtils.isEmpty(xieyi.getJine())){
			ret.put("type", "error");
			ret.put("msg", "请填写金额！");
			return ret;
		}
		if(StringUtils.isEmpty(xieyi.getTime())){
			ret.put("type", "error");
			ret.put("msg", "请填写时间！");
			return ret;
		}
		if(StringUtils.isEmpty(xieyi.getFangshi())){
			ret.put("type", "error");
			ret.put("msg", "请填写支付方式！");
			return ret;
		}
		
		if(StringUtils.isEmpty(xieyi.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}
		
		if(xieyiService.add(xieyi) <= 0){
			ret.put("type", "error");
			ret.put("msg", "捐赠协议添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "捐赠协议添加成功！");
		return ret;
	}
}
