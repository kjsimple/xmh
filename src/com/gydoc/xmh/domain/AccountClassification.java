package com.gydoc.xmh.domain;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class AccountClassification extends BizObject {

    private String code;
    private String name;
    private AccountClassification parent;
    private Set<AccountClassification> children = new TreeSet<AccountClassification>(new ACComparator());
    private Ledger ledger;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AccountClassification> getChildren() {
        return children;
    }

    public void setChildren(Set<AccountClassification> children) {
        this.children = children;
    }

    public AccountClassification getParent() {
        return parent;
    }

    public void setParent(AccountClassification parent) {
        this.parent = parent;
    }

    public void addChild(AccountClassification child) {
        getChildren().add(child);
        child.setParent(this);
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public Object cloneBO() {
        AccountClassification root = new AccountClassification();
        root.setCode(getCode());
        root.setName(getName());
        for (AccountClassification ac : children) {
            root.addChild((AccountClassification) ac.cloneBO());
        }
        return root;
    }
    
}
