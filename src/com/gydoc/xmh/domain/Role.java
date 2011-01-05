package com.gydoc.xmh.domain;

/**
 *
 */
public class Role extends BizObject {

    private String name;

    public Object cloneBO() {
        Role r = new Role();
        return r;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
