package com.gydoc.xmh.widget.user;

import com.gydoc.xmh.AppMain;
import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.widget.XMHDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/**
 *
 */
public class ManageUserDialog extends XMHDialog {
    
    public ManageUserDialog() {
        init();
    }

    public ManageUserDialog(Frame owner, boolean modal) {
        super(owner, modal);
        init();
    }

    private void init() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle(I18NMessage.getMessage("user.manage.dialog.title"));
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        JPanel userAndGroup = new JPanel();
        userAndGroup.setLayout(new GridBagLayout());
        userAndGroup.setBorder(BorderFactory.createTitledBorder(I18NMessage.getMessage("usermanage.dialog.userandgroup")));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.weighty = 1;
        c.insets = new Insets(8, 4, 8, 4);
        c.fill = GridBagConstraints.BOTH;
        add(userAndGroup, c);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        add(buttonsPanel, c);

        JButton addUser = new JButton(I18NMessage.getMessage("button.adduser"));
        addUser.setMnemonic('U');
        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				new AddUserDialog(AppMain.getInstance().getFrame(), true).setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        buttonsPanel.add(addUser, c);

        JButton addGroup = new JButton(I18NMessage.getMessage("button.addgroup"));
        addGroup.setMnemonic('G');
        addGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddGroupDialog dialog = new AddGroupDialog(ManageUserDialog.this, true);
                dialog.setVisible(true);
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        buttonsPanel.add(addGroup, c);

        JTree groupTree = new JTree();
        JScrollPane groupPane = new JScrollPane();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        groupPane.setViewportView(groupTree);
        userAndGroup.add(groupPane, c);
    }
    
}
