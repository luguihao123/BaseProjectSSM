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
import com.ischoolbar.programmer.entity.admin.Yusuan;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.YusuanService;

/**
 * 财务预算管理控制器
 * @author llq
 *
 */
@RequestMapping("/admin/yusuan")
@Controller
public class YusuanController {
	
	@Autowired
	YusuanService yusuanService;
	
	/**
	 * 财务预算列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("yusuan/list");
		return model;
	}
	
	/**
	 * 获取财务预算列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="yongtu",required=false) String yongtu,
			@RequestParam(name="jine",required=false) String jine
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("yongtu", yongtu);
		queryMap.put("jine", jine);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", yusuanService.findList(queryMap));
		ret.put("total", yusuanService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加财务预算
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Yusuan yusuan){
		Map<String, String> ret = new HashMap<String, String>();
		if(yusuan == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的用户信息！");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getYongtu())){
			ret.put("type", "error");
			ret.put("msg", "请填写财务预算！");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getJine())){
			ret.put("type", "error");
			ret.put("msg", "请填写金额！");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "请填写附件！");
			return ret;
		}

		if(yusuanService.add(yusuan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "财务预算添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财务预算添加成功！");
		return ret;
	}
	
	/**
	 * 修改财务预算
	 * @param yusuan
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Yusuan yusuan){
		Map<String, String> ret = new HashMap<String, String>();
		if(yusuan == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的财务预算信息！");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getYongtu())){
			ret.put("type", "error");
			ret.put("msg", "请填写用途！");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getJine())){
			ret.put("type", "error");
			ret.put("msg", "请填写金额！");
			return ret;
		}
		if(yusuanService.edit(yusuan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "财务预算修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财务预算修改成功！");
		return ret;
	}
	
	/**
	 * 删除财务预算
	 * @param ids
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String yongtu){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(yongtu)){
			ret.put("type", "error");
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		if(yusuanService.delete(yongtu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "财务预算删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "财务预算删除成功！");
		return ret;
	}
}
