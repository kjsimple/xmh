package com.gydoc.xmh.domain;

import java.io.Serializable;

/**
 *
 */
public abstract class BizObject implements Serializable, BOCloneable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
