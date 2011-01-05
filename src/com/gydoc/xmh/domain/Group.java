package com.gydoc.xmh.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Group extends BizObject {

    private String name;
    private Group parent;
    private Set<Group> children = new HashSet<Group>();
    private Set<User> users = new HashSet<User>();
    private boolean adminGroup = false;
    private Ledger ledger;

    public Object cloneBO() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Set<Group> getChildren() {
        return children;
    }

    public void setChildren(Set<Group> children) {
        this.children = children;
    }

    public void addChild(Group g) {
        getChildren().add(g);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User u) {
        getUsers().add(u);
    }

    public boolean isAdminGroup() {
        return adminGroup;
    }

    public void setAdminGroup(boolean adminGroup) {
        this.adminGroup = adminGroup;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }
    
}
