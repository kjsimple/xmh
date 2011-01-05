package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.widget.WizardPanel;
import com.gydoc.xmh.widget.UIConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 *
 */
public class CreateLedgerStep2 extends JPanel implements WizardPanel<Ledger> {

    private JTextField ledgerNameField;

    public CreateLedgerStep2() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, UIConstants.LINE_SPACE, 0);
        add(new JLabel(I18NMessage.getMessage("wizard.creatLedger.ledgerNameLabel")), c);

        c.gridx = 0;
        c.gridy = 1;
        ledgerNameField = new JTextField("", 30);
        add(ledgerNameField, c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel(I18NMessage.getMessage("wizard.creatLedger.ledgerNameDescLabel")), c);
        
        c.gridx = 0;
        c.gridy = 3;
        
        JTextArea textArea = new JTextArea("", 5, 30);
        JScrollPane wrap = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        add(wrap, c);
    }

    public JTextField getLedgerNameField() {
        return ledgerNameField;
    }

    public boolean okToNext() {
        String name = ledgerNameField.getText();
        if (name != null && !name.trim().equals("")) {
            return true;
        }
        return false;
    }

    public Ledger updateTarget(Ledger o) {
        o.setName(ledgerNameField.getText());
        return o;
    }

}
