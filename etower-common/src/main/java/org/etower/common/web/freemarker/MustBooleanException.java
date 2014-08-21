package org.etower.common.web.freemarker;

import freemarker.template.TemplateModelException;

/**
 * 非布尔参数异常
 * 
 * @author dawei.li
 *
 */
@SuppressWarnings("serial")
public class MustBooleanException extends TemplateModelException {

	public MustBooleanException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a boolean.");
	}
	
}
