package org.etower.core.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.math.NumberUtils;
import org.etower.common.jpa.entity.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "eo_config")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Config extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String cgfKey;
	private String cgfValue;

	public Config() {
	}

	public String getCgfKey() {
		return cgfKey;
	}

	public void setCgfKey(String cgfKey) {
		this.cgfKey = cgfKey;
	}

	public String getCgfValue() {
		return cgfValue;
	}

	public void setCgfValue(String cgfValue) {
		this.cgfValue = cgfValue;
	}

	/*
	 * ConfigLogin
	 */
	public static class ConfigLogin {
		public static String LOGIN_ERROR_INTERVAL = "login_error_interval";
		public static String LOGIN_ERROR_TIMES = "login_error_times";

		private Map<String, String> attr;

		public static ConfigLogin create(Map<String, String> map) {
			ConfigLogin configLogin = new ConfigLogin();
			configLogin.setAttr(map);
			return configLogin;
		}

		public Map<String, String> getAttr() {
			if (attr == null) {
				attr = new HashMap<String, String>();
			}
			return attr;
		}

		public void setAttr(Map<String, String> attr) {
			this.attr = attr;
		}

		public Integer getErrorInterval() {
			String interval = getAttr().get(LOGIN_ERROR_INTERVAL);
			if (NumberUtils.isDigits(interval)) {
				return Integer.parseInt(interval);
			} else {
				// 默认间隔30分钟
				return 30;
			}
		}

		public void setErrorInterval(Integer errorInterval) {
			if (errorInterval != null) {
				getAttr().put(LOGIN_ERROR_INTERVAL, errorInterval.toString());
			} else {
				getAttr().put(LOGIN_ERROR_INTERVAL, null);
			}
		}

		public Integer getErrorTimes() {
			String times = getAttr().get(LOGIN_ERROR_TIMES);
			if (NumberUtils.isDigits(times)) {
				return Integer.parseInt(times);
			} else {
				// 默认3次
				return 3;
			}
		}

		public void setErrorTimes(Integer errorTimes) {
			if (errorTimes != null) {
				getAttr().put(LOGIN_ERROR_TIMES, errorTimes.toString());
			} else {
				getAttr().put(LOGIN_ERROR_TIMES, null);
			}
		}
	}

}