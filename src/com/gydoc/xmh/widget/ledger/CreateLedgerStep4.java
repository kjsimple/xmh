package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.domain.Currency;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.widget.WizardPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 */
public class CreateLedgerStep4 extends JPanel implements WizardPanel<Ledger> {

    private JTextField currencyCodeField;
    private JTextField currencyNameField;

    public CreateLedgerStep4() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 4, 0);
        add(new JLabel(I18NMessage.getMessage("ledger.currency.standard.title")), c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(new JLabel(I18NMessage.getMessage("ledger.currency.standard.label")), c);

        c.gridx = 1;
        c.gridy = 1;
        c.insets.right = 4;
        currencyCodeField = new JTextField("", 5);
        add(currencyCodeField, c);

        c.gridx = 2;
        c.gridy = 1;
        currencyNameField = new JTextField("", 15);
        add(currencyNameField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        add(new JLabel(I18NMessage.getMessage("ledger.currency.standard.desc")), c);
    }

    public JTextField getCurrencyCodeField() {
        return currencyCodeField;
    }

    public JTextField getCurrencyNameField() {
        return currencyNameField;
    }

    public boolean okToNext() {
        String code = currencyCodeField.getText();
        String name = currencyNameField.getText();

        return code != null && name != null && !code.equals("") && !name.equals("");
    }

    public Ledger updateTarget(Ledger o) {
        Currency c = new Currency();
        c.setName(currencyNameField.getText());
        c.setCode(currencyCodeField.getText());
        c.setPrimary(true);
        o.getCurrencies().add(c);
        return o;
    }

}