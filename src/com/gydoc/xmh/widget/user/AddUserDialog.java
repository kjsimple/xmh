package com.gydoc.xmh.widget.user;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.UIConstant;
import com.gydoc.xmh.widget.XMHDialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 */
public class AddUserDialog extends XMHDialog {

	public AddUserDialog(Frame owner, boolean modal) {
		super(owner, modal);
		init();
	}

	private void init() {
		setSize(240, 320);
        setLocationRelativeTo(null);
        setTitle(I18NMessage.getMessage("user.add.dialog.title"));
        setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(UIConstant.DIALOG_TOP_PADDING, 0, 0, 0);
		add(new JLabel(), c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, UIConstant.DIALOG_LEFT_PADDING, UIConstant.FIELD_PADDING, UIConstant.LABEL_RIGHT_PADDING);
		add(new JLabel(I18NMessage.getMessage("login.username.label")), c);

		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.insets = new Insets(0, 0, UIConstant.FIELD_PADDING, UIConstant.DIALOG_RIGHT_PADDING);
		add(new JTextField("", 20), c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.insets = new Insets(0, UIConstant.DIALOG_LEFT_PADDING, UIConstant.FIELD_PADDING, UIConstant.LABEL_RIGHT_PADDING);
		add(new JLabel(I18NMessage.getMessage("login.password.label")), c);

		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, UIConstant.FIELD_PADDING, UIConstant.DIALOG_RIGHT_PADDING);
		add(new JTextField(""), c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.insets = new Insets(0, UIConstant.DIALOG_LEFT_PADDING, UIConstant.FIELD_PADDING, UIConstant.LABEL_RIGHT_PADDING);
		add(new JLabel(I18NMessage.getMessage("login.repeatpassword.label")), c);

		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, UIConstant.FIELD_PADDING, UIConstant.DIALOG_RIGHT_PADDING);
		add(new JTextField(""), c);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 0, 0, 0);
		add(buttonPanel, c);
		GridBagConstraints cc = new GridBagConstraints();
		buttonPanel.add(new JButton(I18NMessage.getMessage("button.ok")), cc);
		cc.gridx = 1;
		buttonPanel.add(new JButton(I18NMessage.getMessage("button.cancel")), cc);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.insets = new Insets(UIConstant.DIALOG_BOTTOM_PADDING, 0, 0, 0);
		add(new JLabel(), c);

		pack();
		setResizable(false);
	}

}
