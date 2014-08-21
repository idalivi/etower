package org.etower.action.front;

import static org.etower.Constants.TPLDIR_INDEX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.etower.core.domain.EtSite;
import org.etower.core.web.front.URLHelper;
import org.etower.core.web.front.URLHelper.PageInfo;
import org.etower.web.FrontUtils;
import org.etower.web.SysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DynamicPageAct {

	private static final Logger log = LoggerFactory
			.getLogger(DynamicPageAct.class);

	/**
	 * 首页模板名称
	 */
	public static final String TPL_INDEX = "tpl.index";
	public static final String GROUP_FORBIDDEN = "login.groupAccessForbidden";

	/**
	 * TOMCAT的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		EtSite site = SysUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_INDEX, TPL_INDEX);
	}

	/**
	 * WEBLOGIC的默认路径
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index.ehtml", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request, ModelMap model) {
		return index(request, model);
	}

	/**
	 * 动态页入口
	 */
	@RequestMapping(value = "/**/*.*", method = RequestMethod.GET)
	public String dynamic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// 尽量不要携带太多参数，多使用标签获取数据。
		// 目前已知的需要携带翻页信息。
		// 获得页号和翻页信息吧。
		int pageNo = URLHelper.getPageNo(request);
		String[] params = URLHelper.getParams(request);
		PageInfo info = URLHelper.getPageInfo(request);
		String[] paths = URLHelper.getPaths(request);
		int len = paths.length;
		System.out.println(len);
		return null;
//		if (len == 1) {
//			// 单页
//			return channel(paths[0], pageNo, params, info, request, response,
//					model);
//		} else if (len == 2) {
//			if (paths[1].equals(INDEX)) {
//				// 栏目页
//				return channel(paths[0], pageNo, params, info, request,
//						response, model);
//			} else {
//				// 内容页
//				try {
//					Integer id = Integer.parseInt(paths[1]);
//					return content(id, pageNo, params, info, request, response,
//							model);
//				} catch (NumberFormatException e) {
//					log.debug("Content id must String: {}", paths[1]);
//					return FrontUtils.pageNotFound(request, response, model);
//				}
//			}
//		} else {
//			log.debug("Illegal path length: {}, paths: {}", len, paths);
//			return FrontUtils.pageNotFound(request, response, model);
//		}
	}

}
