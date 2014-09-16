package org.etower.action.admin.main;

import static org.etower.common.web.Constants.MESSAGE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.etower.common.web.springmvc.MessageResolver;
import org.etower.core.domain.EtModel;
import org.etower.core.domain.EtModelItem;
import org.etower.manager.main.EtModelItemMng;
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
public class EtModelItemAct {

	private static final Logger log = LoggerFactory.getLogger(EtModelItemAct.class);
	
	@Autowired
	private EtModelMng etModelMng;
	@Autowired
	private EtModelItemMng etModelItemMng;
	@Autowired
	private SysLogMng sysLogMng;
	
	@RequestMapping("/item/v_list.do")
	public String list(Long modelId, Boolean isChannel, HttpServletRequest request, ModelMap model) {
		EtModel m = etModelMng.findById(modelId);
		List<EtModelItem> list = etModelItemMng.getList(modelId, isChannel, true);
		model.addAttribute("model", m);
		model.addAttribute("fieldList", getFieldList(list));
		model.addAttribute("modelId", modelId);
		model.addAttribute("isChannel", isChannel);
		model.addAttribute("list", list);
		if (isChannel) {
			return "item/list_channel";
		} else {
			return "item/list_content";
		}
	}
	
	@RequestMapping("/item/v_add.do")
	public String add(Long modelId, Boolean isChannel, ModelMap model) {
		EtModel m = etModelMng.findById(modelId);
		model.addAttribute("model", m);
		model.addAttribute("modelId", modelId);
		model.addAttribute("isChannel", isChannel);
		return "item/add";
	}
	
	@RequestMapping("/item/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("modelItem", etModelItemMng.findById(id));
		return "item/edit";
	}
	
	@RequestMapping("/item/o_priority.do")
	public String priority(Long[] wids, Integer[] priority, String[] label,
			Boolean[] single, Boolean[] display, Long modelId,
			Boolean isChannel, HttpServletRequest request, ModelMap model) {
		if (wids != null && wids.length > 0) {
			etModelItemMng.updatePriority(wids, priority, label, single, display);
		}
		model.addAttribute(MESSAGE, "global.success");
		return list(modelId, isChannel, request, model);
	}
	
	@RequestMapping("/item/o_save_list.do")
	public String saveList(Long modelId, Boolean isChannel, String[] fields,
			String[] labels, Integer[] dataTypes, Integer[] prioritys,
			Boolean[] singles, Boolean[] displays, HttpServletRequest request,
			ModelMap model) {
		EtModel m = etModelMng.findById(modelId);
		List<EtModelItem> itemList = getItems(m, isChannel, fields, labels,
				dataTypes, prioritys, singles, displays);
		etModelItemMng.saveList(itemList);
		log.info("save EtModelItem count={}", itemList.size());
		model.addAttribute("modelId", modelId);
		model.addAttribute("isChannel", isChannel);
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/item/o_save.do")
	public String save(EtModelItem bean, Long modelId, Boolean isChannel,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, modelId, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = etModelItemMng.save(bean, modelId);
		log.info("save EtModelItem id={}.", bean.getId());
		model.addAttribute("modelId", bean.getEtModel().getId());
		model.addAttribute("isChannel", bean.getIsChannel());
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/item/o_update.do")
	public String update(EtModelItem bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getId(), bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = etModelItemMng.update(bean);
		log.info("update EtModelItem id={}.", bean.getId());
		model.addAttribute("modelId", bean.getEtModel().getId());
		model.addAttribute("isChannel", bean.getIsChannel());
		return "redirect:v_list.do";
	}

	@RequestMapping("/item/o_delete.do")
	@ResponseBody
	public Map<String, Object> delete(Long[] ids, Integer modelId, Boolean isChannel,
			HttpServletRequest request, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			map.put("status", 0);
			map.put("msg", "操作失败，数据不存在，或者已被删除!");
			return map;
		}
		etModelItemMng.deleteByIds(ids);
		for (Long id : ids) {
			log.info("delete EtModelItem id={}", id);
			sysLogMng.operating(request, "modelItem.log.delete", "id=" + id
					+ ";name=" + etModelItemMng.findById(id).getItemLabel());
		}
		map.put("status", 1);
		map.put("msg", "操作成功！");
		return map;
	}
	
	private List<String> getFieldList(List<EtModelItem> items) {
		List<String> list = new ArrayList<String>(items.size());
		for (EtModelItem item : items) {
			list.add(item.getField());
		}
		return list;
	}
	
	private List<EtModelItem> getItems(EtModel model, boolean isChannel,
			String[] fields, String[] labels, Integer[] dataTypes,
			Integer[] prioritys, Boolean[] singles, Boolean[] displays) {
		List<EtModelItem> list = new ArrayList<EtModelItem>();
		EtModelItem item;
		for (int i = 0, len = fields.length; i < len; i++) {
			if (!StringUtils.isBlank(fields[i])) {
				item = new EtModelItem();
				item.setIsCustom(false);
				item.setEtModel(model);
				item.setIsChannel(isChannel);

				item.setField(fields[i]);
				item.setItemLabel(labels[i]);
				item.setPriority(prioritys[i]);
				item.setDataType(dataTypes[i]);
				item.setIsSingle(singles[i]);
				item.setIsDisplay(displays[i]);

				list.add(item);
			}
		}
		return list;
	}
	
	private WebErrors validateSave(EtModelItem bean, Long modelId,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (!StringUtils.isBlank(bean.getOptValue())) {
			bean.setOptValue(replaceLocaleSplit(bean.getOptValue(), request));
		}
		return errors;
	}

	private WebErrors validateEdit(Long id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Long id, EtModelItem bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (!StringUtils.isBlank(bean.getOptValue())) {
			bean.setOptValue(replaceLocaleSplit(bean.getOptValue(), request));
		}
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
		EtModelItem entity = etModelItemMng.findById(id);
		if (errors.ifNotExist(entity, EtModelItem.class, id)) {
			return true;
		}
		return false;
	}

	private String replaceLocaleSplit(String s, HttpServletRequest request) {
		String split = MessageResolver.getMessage(request,
				"modelItem.optValue.split");
		return StringUtils.replace(s, split, ",");
	}
}
