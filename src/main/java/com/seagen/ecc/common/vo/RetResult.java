package com.seagen.ecc.common.vo;

import java.io.Serializable;

import com.seagen.ecc.common.Ret;

/**
 * 
 * 返回结果
 * 
 * @author kuangjianbo
 * 
 * @param <T>
 */
public class RetResult<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RetResult() {
        super();
    }

    public RetResult(String ret, String detail) {
        super();
        this.ret = ret;
        this.detail = detail;
    }

    public RetResult(String ret, T model) {
        super();
        this.ret = ret;
        this.model = model;
    }

    public RetResult(String ret, String detail, String field) {
        super();
        this.ret = ret;
        this.detail = detail;
        this.field = field;
    }

    public RetResult(String ret, String detail, T model) {
        super();
        this.ret = ret;
        this.detail = detail;
        this.model = model;
    }

    public RetResult(String ret, String detail, String field, T model) {
        super();
        this.ret = ret;
        this.detail = detail;
        this.field = field;
        this.model = model;
    }

    /**
     * 操作结果描述<br>
     * {@link Ret#OK} (10000)表示成功,否则失败<br>
     */
    private String ret;

    /**
     * 如果返回不成功,会有具体描述
     */
    private String detail;

    /**
     * 如果返回不成功,涉及的字段或者数据
     */
    private String field;

    /**
     * 返回的具体数据
     */
    private T model;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "RetResult [ret=" + ret + ", detail=" + detail + ", field=" + field + ", model=" + model + "]";
    }

}
