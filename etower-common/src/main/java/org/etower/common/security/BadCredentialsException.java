package org.etower.common.security;

/**
 * 认证信息错误异常。如：密码错误。
 * 
 * @author dawei.li
 *
 */
@SuppressWarnings("serial")
public class BadCredentialsException extends AuthenticationException {

	public BadCredentialsException() {
	}

	public BadCredentialsException(String msg) {
		super(msg);
	}

}
