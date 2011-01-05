package com.gydoc.xmh;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 */
public class Test {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        final JFrame jf = new JFrame();
        jf.setName("123");
        jf.setLayout(new FlowLayout());
        JTextField field = new JTextField("", 6);
        field.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                try {
                    Integer.parseInt(str);
                } catch (Exception e) {
                    return ;
                }
                super.insertString(offs, str, a);
            }
        });
        field.setText("55");
        
        jf.add(field);

        JButton defaultButton = new JButton("Close");
        defaultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jf, "test");
                System.exit(0);
            }
        });
        jf.add(defaultButton);
        jf.add(new JButton("MM"));
        jf.getRootPane().setDefaultButton(new JButton("as"));
        
        jf.pack();
        jf.setVisible(true);
    }

}
