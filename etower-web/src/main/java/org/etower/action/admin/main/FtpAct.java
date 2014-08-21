package org.etower.action.admin.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.etower.common.web.CookieUtils;
import org.etower.core.domain.Ftp;
import org.etower.core.manager.FtpMng;
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
public class FtpAct {

	private static final Logger log = LoggerFactory.getLogger(FtpAct.class);

	@Autowired
	private FtpMng ftpMng;
	@Autowired
	private SysLogMng sysLogMng;

	@RequestMapping("/ftp/v_list.do")
	public String list(
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			HttpServletRequest request, ModelMap model) {
		Page<Ftp> page = ftpMng.getPage(pageNo, CookieUtils.getPageSize(request), sortType);
		model.addAttribute("pagination", page);
		model.addAttribute("pageNo", page.getNumber());
		return "ftp/list";
	}

	@RequestMapping("/ftp/v_add.do")
	public String add(ModelMap model) {
		return "ftp/add";
	}

	@RequestMapping("/ftp/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("ftp", ftpMng.findById(id));
		return "ftp/edit";
	}

	@RequestMapping("/ftp/o_save.do")
	public String save(Ftp bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = ftpMng.save(bean);
		log.info("save Ftp id={}", bean.getId());
		sysLogMng.operating(request, "ftp.log.save", "id=" + bean.getId()
				+ ";name=" + bean.getFtpName());
		return "redirect:v_list.do";
	}

	@RequestMapping("/ftp/o_update.do")
	public String update(
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			Ftp bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = ftpMng.update(bean);
		log.info("update Ftp id={}.", bean.getId());
		sysLogMng.operating(request, "ftp.log.update", "id=" + bean.getId()
				+ ";name=" + bean.getFtpName());
		return list(sortType, pageNo, request, model);
	}

	@RequestMapping("/ftp/o_delete.do")
	@ResponseBody
	public Map<String, Object> delete(Long[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			map.put("status", 0);
			map.put("msg", "操作失败，数据不存在，或者已被删除!");
			return map;
		}
		ftpMng.deleteByIds(ids);
		for (Long id : ids) {
			log.info("delete Ftp id={}", id);
			sysLogMng.operating(request, "ftp.log.delete", "id=" + id
					+ ";name=" + ftpMng.findById(id).getFtpName());
		}
		map.put("status", 1);
		map.put("msg", "操作成功！");
		return map;
	}

	private WebErrors validateSave(Ftp bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
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
		Ftp entity = ftpMng.findById(id);
		if (errors.ifNotExist(entity, Ftp.class, id)) {
			return true;
		}
		return false;
	}
}
