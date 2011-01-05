package com.gydoc.xmh.domain;

import java.util.Set;
import java.util.TreeSet;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

/**
 *
 */
public class Test_ACComparator {
    
    @Test
    public void testCompare() {
        String[] orderStrings = new String[] {"001", "111", "167", "267", "489", "720", "890"};
        AccountClassification ac01 = new AccountClassification();
        ac01.setCode(orderStrings[4]);
        AccountClassification ac02 = new AccountClassification();
        ac02.setCode(orderStrings[6]);
        AccountClassification ac03 = new AccountClassification();
        ac03.setCode(orderStrings[0]);
        AccountClassification ac04 = new AccountClassification();
        ac04.setCode(orderStrings[2]);
        AccountClassification ac05 = new AccountClassification();
        ac05.setCode(orderStrings[3]);
        AccountClassification ac06 = new AccountClassification();
        ac06.setCode(orderStrings[1]);
        AccountClassification ac07 = new AccountClassification();
        ac07.setCode(orderStrings[5]);
        
        Set<AccountClassification> acs = new TreeSet<AccountClassification>(new ACComparator());
        acs.add(ac01);
        acs.add(ac02);
        acs.add(ac03);
        acs.add(ac04);
        acs.add(ac05);
        acs.add(ac06);
        acs.add(ac07);
        
        int i = 0;
        assertEquals(7, acs.size());
        for (AccountClassification ac : acs) {
            assertEquals(ac.getCode(), orderStrings[i++]);
        }
    }
    
}
