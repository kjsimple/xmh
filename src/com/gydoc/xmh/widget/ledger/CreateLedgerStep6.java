package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.widget.WizardPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class CreateLedgerStep6 extends JPanel implements WizardPanel<Ledger> {

    public CreateLedgerStep6() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 20, 0, 0);
        c.anchor = GridBagConstraints.WEST;
        add(new JLabel(I18NMessage.getMessage("ledger.creatLedger.finalStep.desc")), c);
    }

    public boolean okToNext() {
        return true;
    }

    public Ledger updateTarget(Ledger o) {
        return o;
    }
    
}