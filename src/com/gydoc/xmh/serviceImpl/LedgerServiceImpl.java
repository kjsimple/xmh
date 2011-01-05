package com.gydoc.xmh.serviceImpl;

import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.service.LedgerService;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileFilter;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation= Propagation.REQUIRED)
public class LedgerServiceImpl extends ServiceBase implements LedgerService {

    public Ledger add(Ledger ledger) {
        getCS().save(ledger.getAccountClassification());
        getCS().save(ledger);
        return ledger;
    }

    public List<Ledger> findAllTemplates() {
        Query q = getCS().createQuery("from AccountClassification as ac inner join ac.ledger as l with l.isTemplate=1");
        List result = q.list();
        List<Ledger> ledgers = new LinkedList<Ledger>();
        for (Object o : result) {
            if (o instanceof Object[]) {
                Object[] os = (Object[]) o;
                for (Object r : os) {
                    if (r instanceof Ledger) {
                        ledgers.add((Ledger) r);
                    }
                }
            }
        }
        return ledgers;
    }

    public String[] findAllLedgers() {
        File derbyHome = new File(System.getProperty("derby.system.home"));
        
        if (!derbyHome.exists()) {
            return new String[0];
        }
        
        File[] dbs = derbyHome.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        String[] values = new String[dbs.length];
        for (int i = 0; i < dbs.length; i++) {
            values[i] = dbs[i].getName();
        }
        return values;
    }

}
