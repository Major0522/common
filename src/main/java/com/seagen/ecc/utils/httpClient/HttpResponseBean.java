package com.seagen.ecc.utils.httpClient;

public class HttpResponseBean<T> {
	private String status;
	private String errorMsg;
	private T data ;
	public HttpResponseBean() {
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
