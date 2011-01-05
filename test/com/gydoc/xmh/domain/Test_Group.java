package com.gydoc.xmh.domain;

import com.gydoc.xmh.service.GroupService;
import com.gydoc.xmh.service.LedgerService;
import com.gydoc.xmh.testutil.TestUtil;
import org.testng.annotations.Test;

/**
 *
 */
public class Test_Group {
    
    @Test
    public void testAdd() {
        Ledger l = new Ledger();
        l.setName("abc");
        AccountClassification ac = new AccountClassification();
        ac.setCode("ac root");
        ac.setName("ac root name");
        l.setAccountClassification(ac);
        LedgerService ls = (LedgerService) TestUtil.getBean("ledgerService");
        ls.add(l);
        
        Group g = new Group();
        g.setAdminGroup(true);
        g.setName("Group test 001");
        g.setLedger(l);
        GroupService gs = (GroupService) TestUtil.getBean("groupService");
        gs.add(g);
    }
    
}
