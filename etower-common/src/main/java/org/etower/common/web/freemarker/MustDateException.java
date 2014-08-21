package org.etower.common.web.freemarker;

import freemarker.template.TemplateModelException;

/**
 * 非日期参数异常
 * 
 * @author dawei.li
 *
 */
@SuppressWarnings("serial")
public class MustDateException extends TemplateModelException {
	
	public MustDateException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a date.");
	}
	
}
