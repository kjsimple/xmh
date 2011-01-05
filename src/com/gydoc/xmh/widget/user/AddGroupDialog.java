package com.gydoc.xmh.widget.user;

import com.gydoc.xmh.AppMain;
import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.SpringUtil;
import com.gydoc.xmh.domain.Group;
import com.gydoc.xmh.service.GroupService;
import com.gydoc.xmh.widget.XMHDialog;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 */
public class AddGroupDialog extends XMHDialog {
    
    private JTextField groupNameField;

    public AddGroupDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    @Override
    protected void initSize() {
        setSize(320, 110);
    }

    @Override
    protected void dialogInit() {
        super.dialogInit();
        init();
    }

    private void init() {
        setResizable(false);
        setLayout(new GridBagLayout());
        setTitle(I18NMessage.getMessage("addgroup.dialog.title"));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel(I18NMessage.getMessage("addgroup.dialog.groupnamelabel")), c);
        
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        groupNameField = new JTextField("", 30);
        add(groupNameField, c);
        
        JPanel buttonsPanel = new JPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(buttonsPanel, c);
        
        buttonsPanel.setLayout(new GridBagLayout());
        JButton addButton = new JButton(I18NMessage.getMessage("button.add"));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String groupName = groupNameField.getText();
                if (groupName == null || groupName.equals("")) {
                    // todo
                    return ;
                }
                GroupService gs = (GroupService) SpringUtil.getBean("groupService");
                Group g = new Group();
                g.setName(groupNameField.getText());
                g.setLedger(AppMain.getInstance().getUser().getLedger());
                gs.add(g);
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        buttonsPanel.add(addButton, c);
        setDefaultButton(addButton);
        
        JButton cancelButton = new JButton(I18NMessage.getMessage("button.cancel"));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escapePressed();
            }
        });
        c.gridx = 1;
        buttonsPanel.add(cancelButton, c);
    }

    @Override
    public void escapePressed() {
        dispose();
    }

}
