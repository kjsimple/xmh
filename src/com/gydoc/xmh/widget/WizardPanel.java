package com.gydoc.xmh.widget;

/**
 *
 */
public interface WizardPanel<T> {
    
    boolean okToNext();
    
    T updateTarget(T o);

}
