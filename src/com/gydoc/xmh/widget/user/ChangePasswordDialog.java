package com.gydoc.xmh.widget.user;

import com.gydoc.xmh.AppMain;
import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.SpringUtil;
import com.gydoc.xmh.domain.User;
import com.gydoc.xmh.service.UserService;
import com.gydoc.xmh.widget.XMHDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 */
public class ChangePasswordDialog extends XMHDialog {

    public ChangePasswordDialog(Frame owner, boolean modal) {
        super(owner, modal);
        init();
    }

    public ChangePasswordDialog(Frame owner) {
        super(owner);
        init();
    }

    public ChangePasswordDialog() {
        init();
    }

    private void init() {
        setTitle(I18NMessage.getMessage("user.manage.dialog.title"));
        setSize(320, 167);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        final GridBagConstraints c = new GridBagConstraints();
        JLabel currentPassLabel = new JLabel(I18NMessage.getMessage("changepassword.currentpass.label"));
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        add(currentPassLabel, c);

        final JPasswordField cPass = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        add(cPass, c);

        JLabel newPassLabel = new JLabel(I18NMessage.getMessage("changepassword.newpass.label"));
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        add(newPassLabel, c);

        final JPasswordField nPass = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        add(nPass, c);

        JLabel rPassLabel = new JLabel(I18NMessage.getMessage("changepassword.repeatpass.label"));
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        add(rPassLabel, c);

        final JPasswordField rPass = new JPasswordField("", 20);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        add(rPass, c);

        JPanel buttonsPanel = new JPanel();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(buttonsPanel, c);

        buttonsPanel.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        JButton okButton = new JButton(I18NMessage.getMessage("button.ok"));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cP = new String(cPass.getPassword());
                String nP = new String(nPass.getPassword());
                String rP = new String(rPass.getPassword());

                User u = AppMain.getInstance().getUser();
                if (cP.equals("") || !u.getPassword().equals(cP)) {
                    JOptionPane.showMessageDialog(ChangePasswordDialog.this, I18NMessage.getMessage("changepassword.currentpass.notvalid"));
                    return ;
                }
                if (nP.equals("") || rP.equals("") || !nP.equals(rP)) {
                    JOptionPane.showMessageDialog(ChangePasswordDialog.this, I18NMessage.getMessage("changepassword.npassenotqualrpass"));
                    return ;
                }
                if (nP.equals(cP)) {
                    JOptionPane.showMessageDialog(ChangePasswordDialog.this, I18NMessage.getMessage("changepassword.cpassequalnpass"));
                    return ;
                }

                UserService us = (UserService) SpringUtil.getBean("userService");

                u.setPassword(new String(nPass.getPassword()));
                us.updateUser(u);
                AppMain.getInstance().setUser(u);
                JOptionPane.showMessageDialog(ChangePasswordDialog.this, I18NMessage.getMessage("changepassword.success.message"));
                escapePressed();
            }
        });
        buttonsPanel.add(okButton);

        c.gridx = 1;
        c.gridy = 0;
        JButton cancelButton = new JButton(I18NMessage.getMessage("button.cancel"));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escapePressed();
            }
        });
        buttonsPanel.add(cancelButton);
        setDefaultButton(okButton);
    }

}
