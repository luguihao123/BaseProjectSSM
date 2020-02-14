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
 * ����Ԥ����������
 * @author llq
 *
 */
@RequestMapping("/admin/yusuan")
@Controller
public class YusuanController {
	
	@Autowired
	YusuanService yusuanService;
	
	/**
	 * ����Ԥ���б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("yusuan/list");
		return model;
	}
	
	/**
	 * ��ȡ����Ԥ���б�
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
	 * ��Ӳ���Ԥ��
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Yusuan yusuan){
		Map<String, String> ret = new HashMap<String, String>();
		if(yusuan == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ���û���Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getYongtu())){
			ret.put("type", "error");
			ret.put("msg", "����д����Ԥ�㣡");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getJine())){
			ret.put("type", "error");
			ret.put("msg", "����д��");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getFujian())){
			ret.put("type", "error");
			ret.put("msg", "����д������");
			return ret;
		}

		if(yusuanService.add(yusuan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����Ԥ�����ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "����Ԥ����ӳɹ���");
		return ret;
	}
	
	/**
	 * �޸Ĳ���Ԥ��
	 * @param yusuan
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Yusuan yusuan){
		Map<String, String> ret = new HashMap<String, String>();
		if(yusuan == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ�Ĳ���Ԥ����Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getYongtu())){
			ret.put("type", "error");
			ret.put("msg", "����д��;��");
			return ret;
		}
		if(StringUtils.isEmpty(yusuan.getJine())){
			ret.put("type", "error");
			ret.put("msg", "����д��");
			return ret;
		}
		if(yusuanService.edit(yusuan) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����Ԥ���޸�ʧ��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "����Ԥ���޸ĳɹ���");
		return ret;
	}
	
	/**
	 * ɾ������Ԥ��
	 * @param ids
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String yongtu){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(yongtu)){
			ret.put("type", "error");
			ret.put("msg", "ѡ��Ҫɾ�������ݣ�");
			return ret;
		}
		if(yusuanService.delete(yongtu) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����Ԥ��ɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "����Ԥ��ɾ���ɹ���");
		return ret;
	}
}
