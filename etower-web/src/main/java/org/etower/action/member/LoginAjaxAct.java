package org.etower.action.member;

import static org.etower.core.manager.AuthenticationMng.AUTH_KEY;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.etower.common.security.BadCredentialsException;
import org.etower.common.security.UsernameNotFoundException;
import org.etower.common.web.RequestUtils;
import org.etower.common.web.session.SessionProvider;
import org.etower.core.domain.Authentication;
import org.etower.core.manager.AuthenticationMng;
import org.etower.web.WebErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginAjaxAct {
	
	public static final String MESSAGE = "message";
	
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private SessionProvider session;
	
	/**
	 * Ajax登陆
	 * 
	 * @param username
	 * @param password
	 * @param processUrl
	 * @param returnUrl
	 * @param message
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login_ajax.esp", method = RequestMethod.POST)
	public Map<String, Object> submit(String username, String password,String message, 
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		WebErrors errors = validateSubmit(username, password, request);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!errors.hasErrors()) {
			try {
				Authentication auth = authMng.login(username, password,
						RequestUtils.getIpAddr(request), request, response,
						session);
				String view = getView(auth.getAuthId());
				if (view != null) {
					return map;
				} else {
					model.addAttribute("auth", auth);
					return map;
				}
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
			}
		}
		errors.toModel(model);
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return map;
	}
	
	/**
	 * 获得地址
	 * 
	 * @param processUrl
	 * @param returnUrl
	 * @param authId
	 * @return
	 */
	private String getView(String authId) {
		if (!StringUtils.isBlank(authId)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append("?").append(AUTH_KEY).append("=").append(authId);
			return sb.toString();
		} else {
			return null;
		}
	}
	
	private WebErrors validateSubmit(String username, String password,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifOutOfLength(username, "username", 3, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 3, 32)) {
			return errors;
		}
		return errors;
	}
}
