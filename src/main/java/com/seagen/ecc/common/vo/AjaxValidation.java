package com.seagen.ecc.common.vo;

import java.io.Serializable;
import java.util.Map;

public class AjaxValidation  implements Serializable{
	private static final long serialVersionUID = 5382131358426784623L;
	public  static final String SUCCESS="1";
	public  static final String ERROR="-1";
	public  static final String SYS_ERROR="500";
	private String result;
	private String filed;
	private String msg;
	private Map<String,String> data;

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
