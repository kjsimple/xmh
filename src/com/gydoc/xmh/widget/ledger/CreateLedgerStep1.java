package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.widget.WizardPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class CreateLedgerStep1 extends JPanel implements WizardPanel<Ledger> {

    public CreateLedgerStep1() {
        init();
    }

    private void init() {
        add(new JLabel(I18NMessage.getMessage("createledger.step1.instruction")));
    }

    public boolean okToNext() {
        return true;
    }

    public Ledger updateTarget(Ledger o) {
        return o;
    }

}
