package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.widget.WizardPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 */
public class CreateLedgerStep5 extends JPanel implements WizardPanel<Ledger> {

    private JTextField[] fields = new JTextField[9];
    private JTextField levelNumField;
    private static final int COLUMNS = 5;

    public CreateLedgerStep5() {
        fields[0] = new JTextField("4", COLUMNS);
        fields[0].setDocument(new IntegerOnlyDocument());
        fields[0].setEditable(false);
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 4, 0);
        c.gridwidth = 2;
        add(new JLabel(I18NMessage.getMessage("ledger.classification.structure.title")), c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        add(new JLabel(I18NMessage.getMessage("ledger.classification.structure.levelNumber.label")), c);

        c.gridx = 1;
        c.gridy = 1;
        levelNumField = new JTextField("", COLUMNS);
        add(levelNumField, c);

        for (int i = 1; i < 10; i++) {
            c.gridx = 0;
            c.gridy++;
            add(new JLabel(I18NMessage.getMessage("ledger.classification.structure.levelLength.label", new Object[] {i})), c);

            c.gridx = 1;
            if (i >= 2) {
                fields[i - 1] = new JTextField("", COLUMNS);
                fields[i - 1].setDocument(new IntegerOnlyDocument());
            }
            add(fields[i - 1], c);
        }
    }

    public short getLevel() {
        return Short.parseShort(levelNumField.getText());
    }

    public short getC1Len() {
        return Short.parseShort(fields[0].getText());
    }

    public short getC2Len() {
        return Short.parseShort(fields[1].getText());
    }

    public short getC3Len() {
        return Short.parseShort(fields[2].getText());
    }

    public short getC4Len() {
        return Short.parseShort(fields[3].getText());
    }

    public short getC5Len() {
        return Short.parseShort(fields[4].getText());
    }

    public short getC6Len() {
        return Short.parseShort(fields[5].getText());
    }

    public short getC7Len() {
        return Short.parseShort(fields[6].getText());
    }

    public short getC8Len() {
        return Short.parseShort(fields[7].getText());
    }

    public short getC9Len() {
        return Short.parseShort(fields[8].getText());
    }

    public boolean okToNext() {
        return true;
    }

    public Ledger updateTarget(Ledger o) {
        o.setLevel(getLevel());
        short level = o.getLevel();
        if (level >= 2) {
            o.setC2Len(getC2Len());
        }
        if (level >= 3) {
            o.setC3Len(getC3Len());
        }
        if (level >= 4) {
            o.setC4Len(getC4Len());
        }
        if (level >= 5) {
            o.setC5Len(getC5Len());
        }
        if (level >= 6) {
            o.setC6Len(getC6Len());
        }
        if (level >= 7) {
            o.setC7Len(getC7Len());
        }
        if (level >= 8) {
            o.setC8Len(getC8Len());
        }
        if (level >= 9) {
            o.setC9Len(getC9Len());
        }
        return o;
    }
    
    private static class IntegerOnlyDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            try {
                Integer.parseInt(str);
            } catch (Exception e) {
                return ;
            }
            super.insertString(offs, str, a);
        }
    }
    
}