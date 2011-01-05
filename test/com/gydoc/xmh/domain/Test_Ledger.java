package com.gydoc.xmh.domain;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

/**
 *
 */
public class Test_Ledger {
    
    @Test
    public void testCloneBO() {
        Ledger ori = new Ledger();
        ori.setId((long) 1);
        ori.setName("test 001");
        ori.setC1Part1Len((short) 1);
        ori.setC1Part2Len((short) 3);
        ori.setC2Len((short) 9);
        ori.setC3Len((short) 2);
        ori.setC4Len((short) 3);
        ori.setC5Len((short) 4);
        ori.setC6Len((short) 5);
        ori.setC7Len((short) 6);
        ori.setC8Len((short) 7);
        ori.setC9Len((short) 8);
        
        AccountClassification ac = new AccountClassification();
        ac.setId((long) 1);
        ac.setCode("ac root");
        ac.setName("ac root name");
        
        AccountClassification ac001 = new AccountClassification();
        ac001.setId((long) 2);
        ac001.setCode("ac child1");
        ac001.setName("ac child1 name");
        ac.addChild(ac001);
        ori.setAccountClassification(ac);
        
        Ledger l = (Ledger) ori.cloneBO();
        assertNull(l.getId());
        assertEquals("test 001", l.getName());
        assertEquals(1, l.getC1Part1Len());
        assertEquals(3, l.getC1Part2Len());
        assertEquals(9, l.getC2Len());
        assertEquals(2, l.getC3Len());
        assertEquals(3, l.getC4Len());
        assertEquals(4, l.getC5Len());
        assertEquals(5, l.getC6Len());
        assertEquals(6, l.getC7Len());
        assertEquals(7, l.getC8Len());
        assertEquals(8, l.getC9Len());
        
        AccountClassification lac = l.getAccountClassification();
        verifyAC(lac, "ac root", "ac root name");
        
        for (AccountClassification lacC : lac.getChildren()) {
            verifyAC(lacC, "ac child1", "ac child1 name");
        }
    }

    private void verifyAC(AccountClassification lacC, String code, String name) {
        assertNull(lacC.getId());
        assertEquals(code, lacC.getCode());
        assertEquals(name, lacC.getName());
    }

}
