package com.gydoc.xmh.widget.login;

import com.gydoc.xmh.AppMain;
import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.SpringUtil;
import com.gydoc.xmh.conf.UIConfig;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.domain.User;
import com.gydoc.xmh.service.LedgerService;
import com.gydoc.xmh.service.UserService;
import com.gydoc.xmh.widget.LedgerComboboxAdapter;
import com.gydoc.xmh.widget.XMHDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 */
public class LoginDialog extends XMHDialog {

    private JComboBox ledgersList;
    private JTextField userNameField;
    private JPasswordField passwordField;

    public LoginDialog() {
        init();
    }

    private void init() {
        setTitle(I18NMessage.getMessage("login.dialog.title"));
        setName("XMHLoginDialog");
        UIConfig conf = AppMain.getInstance().getUiConfig();
        setSize(conf.getLdWidth(), conf.getLdHeight());
        if (conf.getLdPosX() < 0 || conf.getLdPosY() < 0) {
            setLocationRelativeTo(null);
        } else {
            setLocation(conf.getLdPosX(), conf.getLdPosY());
        }
        setResizable(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        add(new JLabel(I18NMessage.getMessage("login.username.label")), c);

        c.gridx = 1;
        c.gridy = 0;
        Insets fieldInsets = new Insets(0, 4, 2, 0);
        c.insets = fieldInsets;
        c.anchor = GridBagConstraints.WEST;
        userNameField = new JTextField("", 20);
        userNameField.setName("loginUserName");
        add(userNameField, c);

        c.gridx = 0;
        c.gridy = 1;
        Insets labelInsets = new Insets(0, 0, 0, 0);
        c.insets = labelInsets;
        c.anchor = GridBagConstraints.EAST;
        add(new JLabel(I18NMessage.getMessage("login.password.label")), c);

        c.gridx = 1;
        c.insets = fieldInsets;
        c.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField("", 20);
        add(passwordField, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = labelInsets;
        c.anchor = GridBagConstraints.EAST;
        add(new JLabel(I18NMessage.getMessage("login.dialog.ledgerlabel")), c);

        c.gridx = 1;
        c.insets = fieldInsets;
        c.anchor = GridBagConstraints.WEST;
        ledgersList = new JComboBox(new String[] {"abc", "123"});
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Ledger none = new Ledger();
        none.setName(I18NMessage.getMessage("login.dialog.ledger.none.name"));
        model.addElement(new LedgerComboboxAdapter(none));
        LedgerService ls = (LedgerService) SpringUtil.getBean("ledgerService");
        String[] ledgers = ls.findAllLedgers();
        for (String l : ledgers) {
            model.addElement(l);
        }
        ledgersList.setModel(model);
        ledgersList.setEditable(false);
        add(ledgersList, c);

        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = 2;
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        add(buttonsPanel, c);
        
        JButton loginButton = new JButton(I18NMessage.getMessage("login.loginbutton.text"));
        loginButton.setName("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.insets = labelInsets;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        buttonsPanel.add(loginButton, c);
        JButton cancelButton = new JButton(I18NMessage.getMessage("button.cancel"));
        cancelButton.setName("LoginCancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escapePressed();
            }
        });
        c.gridx = 1;
        buttonsPanel.add(cancelButton, c);
        
        setDefaultButton(loginButton);
    }

    private void login() {
        Ledger selectedLedger = ((LedgerComboboxAdapter) ledgersList.getSelectedItem()).getLedger();
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());

        UserService us = (UserService) SpringUtil.getBean("userService");
        User user = us.login(userName, password, selectedLedger.getId());
        if (user == null) {
            JOptionPane.showMessageDialog(this, I18NMessage.getMessage("login.wronguserorpass"));
            return ;
        }
        AppMain.getInstance().setUser(user);
        if (us.isSuperUserName(user.getName())) {
            AppMain.getInstance().setLedger4Admin(getSelectedLedger());
        }
        dispose();
    }

    @Override
    public void dispose() {
        UIConfig conf = AppMain.getInstance().getUiConfig();
        conf.setLdPosX(getX());
        conf.setLdPosY(getY());
        conf.setLdWidth(getWidth());
        conf.setLdHeight(getHeight());
        super.dispose();
    }

    @Override
    public void escapePressed() {
        dispose();
        System.exit(10000);
    }

    public Ledger getSelectedLedger() {
        Object o= ledgersList.getModel().getElementAt(ledgersList.getSelectedIndex());
        return ((LedgerComboboxAdapter) o).getLedger();
    }
    
}
