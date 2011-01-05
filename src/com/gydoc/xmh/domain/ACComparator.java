package com.gydoc.xmh.domain;

import java.util.Comparator;

/**
 *
 */
public class ACComparator implements Comparator<AccountClassification> {

    public int compare(AccountClassification o1, AccountClassification o2) {
        return o1.getCode().compareTo(o2.getCode());
    }

}
