package org.etower.action.admin;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.EtSite;
import org.etower.core.domain.EtUser;
import org.etower.core.domain.Resource;
import org.etower.manager.main.EtSiteMng;
import org.etower.manager.main.ResourceMng;
import org.etower.web.AdminContextInterceptor;
import org.etower.web.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeAct {

	@Autowired
	private EtSiteMng etSiteMng;
	@Autowired
	private ResourceMng resourceMng;
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, ModelMap model) {
		// 需要获得站点列表
		List<EtSite> siteList = (List<EtSite>) etSiteMng.getList();
		List<Resource> topMenu = resourceMng.findByParentId((long) 0);
		EtSite site = SysUtils.getSite(request);
		EtUser user = SysUtils.getUser(request);
		model.addAttribute("topMenu", topMenu);
		model.addAttribute("siteList", siteList);
		model.addAttribute("site", site);
		model.addAttribute("siteParam", AdminContextInterceptor.SITE_PARAM);
		model.addAttribute("user", user);
		return "index";
	}

	@RequestMapping("/left.do")
	public String left(HttpServletRequest request, ModelMap model) {
		// 获取点击的菜单ID
		String menuid = request.getParameter("menuid");
		// 初始化menuid为空，则赋值-1
		if(menuid == null || menuid.equals("-1")) {
			menuid = "-1";
			model.addAttribute("rootNode", "-1");
		}
		// 根据ID获取子菜单
		List<Resource> firstNode = resourceMng.findByParentId(Long.valueOf(menuid));
		List<Resource> allNode = resourceMng.getList();
		model.addAttribute("firstNode", firstNode);
		model.addAttribute("allNode", allNode);
		return "left";
	}

	@RequestMapping("/right.do")
	public String right(HttpServletRequest request, ModelMap model) {
		EtSite site = SysUtils.getSite(request);
		EtUser user = SysUtils.getUser(request);
		String version = site.getDomain(); //?????
		Properties props = System.getProperties();
		Runtime runtime = Runtime.getRuntime();
		long freeMemoery = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long usedMemory = totalMemory - freeMemoery;
		long maxMemory = runtime.maxMemory();
		long useableMemory = maxMemory - totalMemory + freeMemoery;
		model.addAttribute("props", props);
		model.addAttribute("freeMemoery", freeMemoery);
		model.addAttribute("totalMemory", totalMemory);
		model.addAttribute("usedMemory", usedMemory);
		model.addAttribute("maxMemory", maxMemory);
		model.addAttribute("useableMemory", useableMemory);
		model.addAttribute("version", version);
		model.addAttribute("user", user);
//		model.addAttribute("flowMap", etStatisticSvc.getWelcomeSiteFlowData(site.getSiteId()));
		return "right";
	}

}
