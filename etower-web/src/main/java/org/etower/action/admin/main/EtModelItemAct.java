package org.etower.action.admin.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.etower.core.domain.EtModel;
import org.etower.core.domain.EtModelItem;
import org.etower.manager.main.EtModelItemMng;
import org.etower.manager.main.EtModelMng;

@Controller
public class EtModelItemAct {

	private static final Logger log = LoggerFactory.getLogger(EtModelItemAct.class);
	
	@Autowired
	private EtModelMng etModelMng;
	@Autowired
	private EtModelItemMng etModelItemMng;
	
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
	
	private List<String> getFieldList(List<EtModelItem> items) {
		List<String> list = new ArrayList<String>(items.size());
		for (EtModelItem item : items) {
			list.add(item.getField());
		}
		return list;
	}
}
