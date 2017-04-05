package com.seagen.ecc.utils.httpClient;

import java.util.Map;

import com.seagen.ecc.utils.JsonUtil;

public class HttpRequestBean {
	private String functionCode;
	private Map<String, String> paramList;
	public HttpRequestBean(){
		
	}
	public HttpRequestBean(String functionCode, Map<String, String> paramList) {
		super();
		this.functionCode = functionCode;
		this.paramList = paramList;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}


	public Map<String, String> getParamList() {
		return paramList;
	}

	public void setParamList(Map<String, String> paramList) {
		this.paramList = paramList;
	}

	public String toJson() {
		return JsonUtil.ojbToJsonStr(this);
	}

}
