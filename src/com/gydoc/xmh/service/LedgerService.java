package com.gydoc.xmh.service;

import com.gydoc.xmh.domain.Ledger;
import java.util.List;

/**
 *
 */
public interface LedgerService {

    Ledger add(Ledger ledger);
    List<Ledger> findAllTemplates();
    String[] findAllLedgers();
    
}
