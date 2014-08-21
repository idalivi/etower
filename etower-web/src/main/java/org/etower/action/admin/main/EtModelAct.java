package org.etower.action.admin.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.EtModel;
import org.etower.core.domain.EtRole;
import org.etower.manager.main.EtModelMng;
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
public class EtModelAct {
	private static final Logger log = LoggerFactory.getLogger(EtModelAct.class);

	@Autowired
	private EtModelMng etModelMng;
	@Autowired
	private SysLogMng sysLogMng;
	
	@RequestMapping("/model/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<EtModel> list = etModelMng.getList();
		model.addAttribute("list", list);
		return "model/list";
	}
	
	@RequestMapping("/model/v_add.do")
	public String add(ModelMap model) {
		return "model/add";
	}
	
	@RequestMapping("/model/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("model", etModelMng.findById(id));
		return "model/edit";
	}
	
	@RequestMapping("/model/o_save.do")
	public String save(EtModel bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = etModelMng.save(bean);
		log.info("save Model id={}", bean.getId());
		sysLogMng.operating(request, "model.log.save", "id=" + bean.getId()
				+ ";name=" + bean.getModelName());
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/model/o_update.do")
	public String update(EtModel bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = etModelMng.update(bean);
		log.info("update Model id={}.", bean.getId());
		sysLogMng.operating(request, "model.log.update", "id=" + bean.getId()
				+ ";name=" + bean.getModelName());
		return list(request, model);
	}
	
	@RequestMapping("/model/o_delete.do")
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
		etModelMng.deleteByIds(ids);
		for (Long id : ids) {
			log.info("delete Model id={}", id);
			sysLogMng.operating(request, "model.log.delete", "id=" + id
					+ ";name=" + etModelMng.findById(id).getModelName());
		}
		map.put("status", 1);
		map.put("msg", "操作成功！");
		return map;
	}
	
	private WebErrors validateSave(EtModel bean, HttpServletRequest request) {
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
		EtModel entity = etModelMng.findById(id);
		if (errors.ifNotExist(entity, EtRole.class, id)) {
			return true;
		}
		return false;
	}
}
