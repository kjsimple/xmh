package com.gydoc.xmh.domain;

/**
 *
 */
public class Currency extends BizObject {

    private String code;
    private String name;
    private boolean isPrimary = false;

    public Currency(String code, String name, boolean primary) {
        this.code = code;
        this.name = name;
        isPrimary = primary;
    }

    public Currency() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public Object cloneBO() {
        Currency c = new Currency();
        c.setName(getName());
        c.setCode(getCode());
        
        return c;
    }
    
}
