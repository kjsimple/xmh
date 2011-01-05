package com.gydoc.xmh.domain;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

/**
 *
 */
public class Test_AccountClassification {
    
    @Test
    public void testCloneBO() {
        AccountClassification ori = new AccountClassification();
        ori.setId((long) 1);
        ori.setCode("test001 code");
        ori.setName("test001 name");
        ori.setLedger(new Ledger());
        ori.setParent(null);
        
        AccountClassification c1 = new AccountClassification();
        c1.setId((long) 2);
        c1.setCode("c1");
        c1.setName("c1 name");
        ori.addChild(c1);
        
        AccountClassification c2 = new AccountClassification();
        c2.setId((long) 3);
        c2.setCode("c2");
        c2.setName("c2 name");
        ori.addChild(c2);
        
        AccountClassification c21 = new AccountClassification();
        c21.setId((long) 4);
        c21.setCode("c21");
        c21.setName("c21 name");
        c2.addChild(c21);
        
        AccountClassification cloned = (AccountClassification) ori.cloneBO();
        assertNull(cloned.getId());
        assertEquals(ori.getCode(), cloned.getCode());
        assertEquals(ori.getName(), cloned.getName());
        assertNull(cloned.getLedger());
        assertNull(cloned.getParent());
        assertEquals(2, cloned.getChildren().size());
        
        int index = 0;
        for (AccountClassification ac : cloned.getChildren()) {
            index++;
            String code = null;
            String name = null;
            if (index == 1) {
                code = "c1";
                name = "c1 name";
                assertEquals(0, ac.getChildren().size());
            } else if (index == 2) {
                code = "c2";
                name = "c2 name";
                assertEquals(1, ac.getChildren().size());
                for (AccountClassification ac001 : ac.getChildren()) {
                    verifyAC(ac001, "c21", "c21 name", ac);
                }
            }
            verifyAC(ac, code, name, cloned);
        }
    }

    private void verifyAC(AccountClassification ac, String code, String name, AccountClassification parent) {
        assertNull(ac.getId());
        assertEquals(code, ac.getCode());
        assertEquals(name, ac.getName());
        assertNull(ac.getLedger());
        assertSame(parent, ac.getParent());
    }

}
