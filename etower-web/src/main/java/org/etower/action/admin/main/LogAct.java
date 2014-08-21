package org.etower.action.admin.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.etower.common.web.CookieUtils;
import org.etower.common.web.Servlets;
import org.etower.core.domain.EtLog;
import org.etower.manager.main.SysLogMng;
import org.etower.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogAct {

	private static final Logger log = LoggerFactory.getLogger(LogAct.class);

	@Autowired
	private SysLogMng sysLogMng;

	@RequestMapping("/log/v_list_operating.do")
	public String listOperating(
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<EtLog> page = sysLogMng.getPage(SysLogMng.OPERATING, searchParams,
				pageNo, CookieUtils.getPageSize(request), "auto");

		model.addAttribute("pagination", page);
		model.addAttribute("pageNo", page.getNumber());
		// 将搜索条件循环addAttribute，用于分页，Key中"."替换成"_"，防止freemarker误解析
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			model.addAttribute("search_" + entry.getKey().replace(".", "_"),
					entry.getValue());
		}
		return "log/list_operating";
	}

	@RequestMapping("/log/v_list_login_success.do")
	public String listLoginSuccess(
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<EtLog> page = sysLogMng.getPage(SysLogMng.LOGIN_SUCCESS,
				searchParams, pageNo, CookieUtils.getPageSize(request), "auto");

		model.addAttribute("pagination", page);
		// 将搜索条件循环addAttribute，用于分页，Key中"."替换成"_"，防止freemarker误解析
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			model.addAttribute("search_" + entry.getKey().replace(".", "_"),
					entry.getValue());
		}
		return "log/list_login_success";
	}

	@RequestMapping("/log/v_list_login_failure.do")
	public String listLoginFailure(
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		Page<EtLog> page = sysLogMng.getPage(SysLogMng.LOGIN_FAILURE,
				searchParams, pageNo, CookieUtils.getPageSize(request), "auto");

		model.addAttribute("pagination", page);
		// 将搜索条件循环addAttribute，用于分页，Key中"."替换成"_"，防止freemarker误解析
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			model.addAttribute("search_" + entry.getKey().replace(".", "_"),
					entry.getValue());
		}
		return "log/list_login_failure";
	}
	
	@RequestMapping("/log/o_delete.do")
	@ResponseBody
	public Map<String, Object> deleteOperating(Long[] ids,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			map.put("status", 0);
			map.put("msg", "操作失败，数据不存在，或者已被删除!");
			return map;
		}
		sysLogMng.deleteByIds(ids);
		for (Long id : ids) {
			log.info("delete SysLog id={}", id);
		}
		map.put("status", 1);
		map.put("msg", "操作成功！");
		return map;
	}
	
	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Long id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Long id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		EtLog entity = sysLogMng.findById(id);
		if (errors.ifNotExist(entity, EtLog.class, id)) {
			return true;
		}
		return false;
	}

}
