package com.tarena.elts.service;
/**
 * 自定义异常
 * 不是描述运行过程中出现的错误
 * 而是描述一个逻辑上的错误
 * 此异常能描述用户输入的ID和密码错误
 */
public class IdOrPasswordException extends Exception{
	private static final long serialVersionUID = 1L;

	public IdOrPasswordException() {
		super();
	}

	public IdOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdOrPasswordException(String message) {
		super(message);
	}

	public IdOrPasswordException(Throwable cause) {
		super(cause);
	}
}