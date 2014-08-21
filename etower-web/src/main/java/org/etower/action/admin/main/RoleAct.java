package org.etower.action.admin.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.EtRole;
import org.etower.manager.main.RoleMng;
import org.etower.manager.main.SysLogMng;
import org.etower.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleAct {

	private static final Logger log = LoggerFactory.getLogger(RoleAct.class);
	
	@Autowired
	private RoleMng roleMng;
	@Autowired
	private SysLogMng sysLogMng;
	
	@RequestMapping("/role/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<EtRole> list = roleMng.getList();
		model.addAttribute("list", list);
		
		return "role/list";
	}
	
	@RequestMapping("/role/v_add.do")
	public String add(ModelMap model) {
		return "role/add";
	}
	
	@RequestMapping("/role/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("role", roleMng.findById(id));
		return "role/edit";
	}
	
	@RequestMapping("/role/o_save.do")
	public String save(EtRole bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = roleMng.save(bean);
		log.info("save Role id={}", bean.getId());
		sysLogMng.operating(request, "role.log.save", "id=" + bean.getId()
				+ ";name=" + bean.getRoleName());
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/role/o_update.do")
	public String update(EtRole bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = roleMng.update(bean);
		log.info("update Role id={}.", bean.getId());
		sysLogMng.operating(request, "role.log.update", "id=" + bean.getId()
				+ ";name=" + bean.getRoleName());
		return list(request, model);
	}
	
	@RequestMapping("/role/o_delete.do")
	@ResponseBody
	public Map<String, Object> delete(Long[] ids,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			map.put("status", 0);
			map.put("msg", "操作失败，数据不存在，或者已被删除!");
			return map;
		}
		roleMng.deleteByIds(ids);
		for (Long id : ids) {
			log.info("delete Role id={}", id);
			sysLogMng.operating(request, "role.log.delete", "id=" + id
					+ ";name=" + roleMng.findById(id).getRoleName());
		}
		map.put("status", 1);
		map.put("msg", "操作成功！");
		return map;
	}
	
	private WebErrors validateSave(EtRole bean, HttpServletRequest request) {
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
		EtRole entity = roleMng.findById(id);
		if (errors.ifNotExist(entity, EtRole.class, id)) {
			return true;
		}
		return false;
	}
}
