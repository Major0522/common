package com.seagen.ecc.common.vo;

/**
 * 接口信息对象扩展。
 * 
 * @author wutianbin
 *
 */
public class InterfaceObjEx extends InterfaceObj {
	
    protected boolean isRemote;
    protected String desc;
    
    public InterfaceObjEx() {
        super();
    }
    
    public InterfaceObjEx(String id, String className) {
		super(id, className);
	}

    /**
     * 是否是远程对象。
     * 
     * @return
     */
    public boolean getIsRemote() {
        return isRemote;
    }
    
    public void setIsRemote(boolean isRemote) {
        this.isRemote = isRemote;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    @Override
    public String toString() {
        return "{ id:'" + id + "', " 
                + "className:'" + className + "' }";
    }

}
