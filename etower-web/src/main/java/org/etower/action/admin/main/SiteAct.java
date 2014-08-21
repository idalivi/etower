package org.etower.action.admin.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.core.domain.Ftp;
import org.etower.core.manager.FtpMng;
import org.etower.manager.main.EtSiteMng;
import org.etower.manager.main.SysLogMng;
import org.etower.web.SysUtils;
import org.etower.web.WebErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteAct {

	private static final Logger log = LoggerFactory.getLogger(SiteAct.class);

	@Autowired
	private EtSiteMng etSiteMng;
	@Autowired
	private FtpMng ftpMng;
	@Autowired
	private SysLogMng sysLogMng;

	@RequestMapping("/site/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<EtSite> list = etSiteMng.getList();
		model.addAttribute("list", list);

		return "site/list";
	}

	@RequestMapping("/site/v_add.do")
	public String add(ModelMap model) {
		List<Ftp> ftpList = ftpMng.getList();
		model.addAttribute("ftpList", ftpList);

		return "site/add";
	}
	
	@RequestMapping("/site/v_edit.do")
	public String edit(Long id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<Ftp> ftpList = ftpMng.getList();
		model.addAttribute("ftpList", ftpList);
		model.addAttribute("site", etSiteMng.findById(id));
		return "site/edit";
	}

	@RequestMapping("/site/o_save.do")
	public String save(EtSite bean, Long ftpId, HttpServletRequest request,
			ModelMap model) throws IOException {
		EtSite site = SysUtils.getSite(request);
		EtUser user = SysUtils.getUser(request);
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		etSiteMng.save(site, user, bean, ftpId);
		log.info("save Site id={}", bean.getId());
		sysLogMng.operating(request, "site.log.save", "id=" + bean.getId()
				+ ";name=" + bean.getSiteName());
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/site/o_update.do")
	public String update(EtSite bean, Long ftpId, HttpServletRequest request,
			ModelMap model) throws IOException {
		WebErrors errors = validateUpdate(bean.getId(), ftpId, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		etSiteMng.update(bean, ftpId);
		log.info("update Site id={}", bean.getId());
		sysLogMng.operating(request, "site.log.update", "id=" + bean.getId()
				+ ";name=" + bean.getSiteName());
		return "redirect:v_list.do";
	}

	private WebErrors validateSave(EtSite bean, HttpServletRequest request) {
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
	
	private WebErrors validateUpdate(Long id, Long ftpId, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		if (vldFtpExist(ftpId, errors)) {
			return errors;
		}
		return errors;
	}
	
	private boolean vldFtpExist(Long ftpId, WebErrors errors) {
		if (ftpId == null) {
			return false;
		}
		Ftp entity = ftpMng.findById(ftpId);
		return errors.ifNotExist(entity, Ftp.class, ftpId);
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
		EtSite entity = etSiteMng.findById(id);
		if (errors.ifNotExist(entity, EtSite.class, id)) {
			return true;
		}
		return false;
	}
}
