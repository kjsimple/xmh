package com.gydoc.xmh.domain;

/**
 *
 */
public class User extends BizObject {

    private String name;
    private String password;
    private Ledger ledger;
    private Group group;

    public Object cloneBO() {
        User u = new User();
        if (ledger != null) {
            u.setLedger((Ledger) ledger.cloneBO());
        }
        u.setName(getName());
        return u;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
}
