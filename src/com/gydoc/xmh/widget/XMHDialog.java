package com.gydoc.xmh.widget;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/**
 *
 */
public abstract class XMHDialog extends JDialog {

    public XMHDialog() {
        this((Frame)null, false);
    }

    public XMHDialog(Frame owner) {
        this(owner, false);
    }

    public XMHDialog(Frame owner, boolean modal) {
        this(owner, null, modal);
    }

    public XMHDialog(Frame owner, String title) {
        this(owner, title, false);
    }

    public XMHDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public XMHDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public XMHDialog(Dialog owner) {
        super(owner);
    }

    public XMHDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    public XMHDialog(Dialog owner, String title) {
        super(owner, title);
    }

    public XMHDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public XMHDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public XMHDialog(Window owner) {
        super(owner);
    }

    public XMHDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    public XMHDialog(Window owner, String title) {
        super(owner, title);
    }

    public XMHDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    public XMHDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }
    
    public abstract void escapePressed();

    @Override
    protected void dialogInit() {
        super.dialogInit();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                escapePressed();
            }
        });
        initSize();
        initPosition();
    }

    protected void initSize() {

    }

    protected void initPosition() {
        setLocationRelativeTo(null);
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = super.createRootPane();
        KeyStroke escapeStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                escapePressed();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(escapeStroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", actionListener);
        return rootPane;
    }
    
    public void setDefaultButton(JButton button) {
        getRootPane().setDefaultButton(button);
    }
    
}
