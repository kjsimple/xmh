package com.gydoc.xmh.widget;

import com.gydoc.xmh.domain.Ledger;

/**
 *
 */
public class LedgerComboboxAdapter {

    private Ledger ledger;

    public LedgerComboboxAdapter(Ledger ledger) {
        this.ledger = ledger;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public String toString() {
        return ledger.getName();
    }

}
