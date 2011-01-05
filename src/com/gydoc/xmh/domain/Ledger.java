package com.gydoc.xmh.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class Ledger extends BizObject {

    private String name;
    private short level;
    private short c1Part1Len;
    private short c1Part2Len;
    private short c2Len;
    private short c3Len;
    private short c4Len;
    private short c5Len;
    private short c6Len;
    private short c7Len;
    private short c8Len;
    private short c9Len;
    private short isTemplate = 0;
    private boolean isTplt;
    private Currency primaryCurrency;
    private Set<Currency> currencies = new HashSet<Currency>();
    private Set<User> users = new HashSet<User>();
    private Set<Group> groups = new HashSet<Group>();
    private AccountClassification accountClassification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public short getC1Part1Len() {
        return c1Part1Len;
    }

    public void setC1Part1Len(short c1Part1Len) {
        this.c1Part1Len = c1Part1Len;
    }

    public short getC1Part2Len() {
        return c1Part2Len;
    }

    public void setC1Part2Len(short c1Part2Len) {
        this.c1Part2Len = c1Part2Len;
    }

    public short getC2Len() {
        return c2Len;
    }

    public void setC2Len(short c2Len) {
        this.c2Len = c2Len;
    }

    public short getC3Len() {
        return c3Len;
    }

    public void setC3Len(short c3Len) {
        this.c3Len = c3Len;
    }

    public short getC4Len() {
        return c4Len;
    }

    public void setC4Len(short c4Len) {
        this.c4Len = c4Len;
    }

    public short getC5Len() {
        return c5Len;
    }

    public void setC5Len(short c5Len) {
        this.c5Len = c5Len;
    }

    public short getC6Len() {
        return c6Len;
    }

    public void setC6Len(short c6Len) {
        this.c6Len = c6Len;
    }

    public short getC7Len() {
        return c7Len;
    }

    public void setC7Len(short c7Len) {
        this.c7Len = c7Len;
    }

    public short getC8Len() {
        return c8Len;
    }

    public void setC8Len(short c8Len) {
        this.c8Len = c8Len;
    }

    public short getC9Len() {
        return c9Len;
    }

    public void setC9Len(short c9Len) {
        this.c9Len = c9Len;
    }

    public short getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(short template) {
        isTemplate = template;
    }

    public boolean isTplt() {
        return getIsTemplate() == 1;
    }

    public void setTplt(boolean tplt) {
        setIsTemplate(tplt ? (short) 1 : (short) 0);
    }

    public AccountClassification getAccountClassification() {
        return accountClassification;
    }

    public void setAccountClassification(AccountClassification accountClassification) {
        this.accountClassification = accountClassification;
        accountClassification.setLedger(this);
    }

    public Object cloneBO() {
        Ledger l = new Ledger();
        l.setName(getName());
        l.setC1Part1Len(getC1Part1Len());
        l.setC1Part2Len(getC1Part2Len());
        l.setC2Len(getC2Len());
        l.setC3Len(getC3Len());
        l.setC4Len(getC4Len());
        l.setC5Len(getC5Len());
        l.setC6Len(getC6Len());
        l.setC7Len(getC7Len());
        l.setC8Len(getC8Len());
        l.setC9Len(getC9Len());
        AccountClassification ac = getAccountClassification();
        if (ac != null) {
            l.setAccountClassification((AccountClassification) ac.cloneBO());
        }
        
        return l;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
    
}
