package org.etower.action.admin.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.EtSite;
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
public class SiteConfigAct {

	private static final Logger log = LoggerFactory.getLogger(FtpAct.class);
	
	@Autowired
	private FtpMng ftpMng;
	@Autowired
	private SysLogMng logMng;
	@Autowired
	private EtSiteMng siteMng;
	
	@RequestMapping("/site_config/v_base_edit.do")
	public String baseEdit(HttpServletRequest request, ModelMap model) {
		EtSite site = SysUtils.getSite(request);
		List<Ftp> ftpList = ftpMng.getList();
		model.addAttribute("ftpList", ftpList);
		model.addAttribute("etSite", site);
		return "site_config/base_edit";
	}
	
	@RequestMapping("/site_config/o_base_update.do")
	public String baseUpdate(EtSite bean, Long uploadFtpId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateBaseUpdate(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		EtSite site = SysUtils.getSite(request);
		bean.setId(site.getId());
		bean = siteMng.update(bean, uploadFtpId);
		model.addAttribute("message", "global.success");
		log.info("update Site success. id={}", site.getId());
		logMng.operating(request, "siteconf.log.updateBase", null);
		return baseEdit(request, model);
	}
	
	private WebErrors validateBaseUpdate(EtSite bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}
}
