package org.etower.common.web.freemarker;

import freemarker.template.TemplateModelException;

/**
 * 非字符串参数异常
 * 
 * @author dawei.li
 *
 */
@SuppressWarnings("serial")
public class MustStringException extends TemplateModelException {

	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}

}
