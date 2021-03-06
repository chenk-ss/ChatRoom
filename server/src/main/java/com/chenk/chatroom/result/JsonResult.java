package com.chenk.chatroom.result;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
	private static final long serialVersionUID = -4699713095477151086L;

	/**
	 * 数据
	 */
	private T data;
	/**
	 * 信息
	 */
	private String message;
	/**
	 * 是否成功
	 */
	private boolean success;

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public JsonResult() {
		super();
		this.success = true;
		this.message = "";
	}

	public JsonResult(T data, String message, boolean success) {
		this.data = data;
		this.message = message;
		this.success = success;
	}

	public JsonResult(T data, String message) {
		this.data = data;
		this.message = message;
		this.success = true;
	}

	public JsonResult(T data) {
		this.data = data;
		this.success = true;
	}
}
