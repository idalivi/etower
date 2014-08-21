package org.etower.action.admin.main;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.manager.ConfigMng;
import org.etower.manager.main.EtConfigMng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EtConfigAct {

	private static final Logger log = LoggerFactory.getLogger(EtConfigAct.class);
	
	@Autowired
	private EtConfigMng manager;
	@Autowired
	private ConfigMng configMng;
	
	@RequestMapping("/config/v_system_edit.do")
	public String systemEdit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("config", manager.get());
		return "config/system_edit";
	}
	
	@RequestMapping("/config/o_system_update.do")
	public String systemUpdate(HttpServletRequest request, ModelMap model) {
		return systemEdit(request, model);
	}
	
	@RequestMapping("/config/v_login_edit.do")
	public String loginEdit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("configLogin", configMng.getConfigLogin());
		return "config/login_edit";
	}
	
	@RequestMapping("/config/o_login_update.do")
	public String loginUpdate(HttpServletRequest request, ModelMap model) {
		return loginEdit(request, model);
	}
	
	@RequestMapping("/config/v_member_edit.do")
	public String memberEdit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("memberConfig", manager.get());
		return "config/member_edit";
	}
	
	@RequestMapping("/config/o_member_update.do")
	public String memberUpdate(HttpServletRequest request, ModelMap model) {
		return memberEdit(request, model);
	}
	
	@RequestMapping("/config/v_mark_edit.do")
	public String markEdit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("markConfig", manager.get());
		return "config/mark_edit";
	}
	
	@RequestMapping("/config/o_mark_update.do")
	public String markUpdate(HttpServletRequest request, ModelMap model) {
		return markEdit(request, model);
	}
}
