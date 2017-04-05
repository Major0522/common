package com.seagen.ecc.common.vo;

/**
 * 接口信息对象。
 * 
 * @author wutianbin
 *
 */
public class InterfaceObj {
    protected String id;
    protected String className;
	
    public InterfaceObj() {
        super();
    }
    
    public InterfaceObj(String id, String className) {
		super();
		this.id = id;
		this.className = className;
	}

	public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "{ id:'" + id + "', " 
                + "className:'" + className + "' }";
    }

}
