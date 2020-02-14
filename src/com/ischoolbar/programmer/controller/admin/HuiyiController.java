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

import com.ischoolbar.programmer.entity.admin.Huiyi;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.HuiyiService;

@RequestMapping("/admin/huiyi")
@Controller
public class HuiyiController {
	@Autowired
	HuiyiService huiyiService;
	
	/**
	 * 会议记录列表界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("huiyi/list");
		return model;
	}	
		
	/**
	 * 获取会议记录界面
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name,
			@RequestParam(name="time",required=false) String time,
			@RequestParam(name="faqiren",required=false) String faqiren,
			@RequestParam(name="canyuren",required=false) String canyuren,
			@RequestParam(name="neirong",required=false) String neirong
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("time", time);
		queryMap.put("faqiren",faqiren);
		queryMap.put("canyuren",canyuren);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", huiyiService.findList(queryMap));
		ret.put("total",huiyiService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加会议记录
	 * @param huiyi
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Huiyi huiyi){
		Map<String, String> ret = new HashMap<String, String>();
		if(huiyi == null){
			ret.put("type", "error");
			ret.put("msg", "请填写会议记录！");
			return ret;
		}
		if(StringUtils.isEmpty(huiyi.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写会议名称！");
			return ret;
		}
		if(StringUtils.isEmpty(huiyi.getTime())){
			ret.put("type", "error");
			ret.put("msg", "请填写会议时间！");
			return ret;
		}
		if(StringUtils.isEmpty(huiyi.getFaqiren())){
			ret.put("type", "error");
			ret.put("msg", "请填写发起人！");
			return ret;
		}
		if(StringUtils.isEmpty(huiyi.getCanyuren())){
			ret.put("type", "error");
			ret.put("msg", "请填写参与人员！");
			return ret;
		}
		
		if(huiyiService.add(huiyi) <= 0){
			ret.put("type", "error");
			ret.put("msg", "会议记录添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "会议记录添加成功！");
		return ret;
	}
	
	/**
	 * 删除会议记录
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String name){
		Map<String, String> ret = new HashMap<String, String>();
		if(name==null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的会议记录！");
			return ret;
		}
		if(huiyiService.delete(name) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
		}	
		ret.put("type", "success");
		ret.put("msg", "该会议记录删除成功！");
		return ret;
	}
}
