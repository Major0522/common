package com.seagen.ecc.common.vo;

/**
 * 标签值对象。
 * 
 * @author wutianbin
 *
 */
public class LabelValue {
	
	private Object label;
	
	private Object value;
	
	public LabelValue(Object label, Object value) {
		this.label = label;
		this.value = value;
	}
	
	public Object getLabel() {
		return label;
	}
	
	public void setLabel(Object label) {
		this.label = label;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
}
