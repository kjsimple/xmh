package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.SpringUtil;
import com.gydoc.xmh.domain.AccountClassification;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.service.LedgerService;
import com.gydoc.xmh.widget.WizardPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 */
public class CreateLedgerStep3 extends JPanel implements WizardPanel<Ledger> {

    private List<Ledger> templateLedgers;
    private JTree tree;
    private JScrollPane treePane;
    private JList list;

    public CreateLedgerStep3() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 4, 0);
        add(new JLabel(I18NMessage.getMessage("wizard.creatLedger.ledgerTypeLabel")), c);

        String[] items = findACNames();
        list = new JList(items);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JList list = (JList) e.getSource();
                    int index = list.getSelectedIndex();
                    if (index == -1) {
                        treePane.setViewportView(null);
                    } else {
                        DefaultMutableTreeNode root = getTreeNodes(null, templateLedgers.get(index).getAccountClassification());
                        tree = new JTree(root);
                        tree.setShowsRootHandles(true);
                        tree.setRootVisible(false);
                        treePane.setViewportView(tree);
                    }
                }
            }
        });
        list.setPreferredSize(new Dimension(200, 100));
        list.setBorder(BorderFactory.createLineBorder(new Color(176, 176, 176)));
        c.gridx = 0;
        c.gridy = 1;
        add(list, c);

        treePane = new JScrollPane();
        treePane.setPreferredSize(new Dimension(200, 260));
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        add(treePane, c);
    }

    private DefaultMutableTreeNode getTreeNodes(DefaultMutableTreeNode parent, AccountClassification aac) {
        String displayName;
        if ("top".equals(aac.getCode()) || aac.getCode().length() == 1) {
            displayName = aac.getName();
        } else {
            displayName = aac.getParent().getCode() + aac.getCode() + "  " + aac.getName();
        }
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(displayName);
        if (parent != null) {
            parent.add(node);
        }
        for (AccountClassification acChild : aac.getChildren()) {
            getTreeNodes(node, acChild);
        }
        return node;
    }

    private String[] findACNames() {
        LedgerService lService = (LedgerService) SpringUtil.getBean("ledgerService");
        templateLedgers = lService.findAllTemplates();
        String[] items = new String[templateLedgers.size()];
        int i = 0;
        for (Ledger l : templateLedgers) {
            items[i++] = l.getName();
        }
        return items;
    }

    public boolean okToNext() {
        return list.getSelectedIndex() != -1;
    }

    public Ledger updateTarget(Ledger o) {
        Ledger l = (Ledger) templateLedgers.get(list.getSelectedIndex()).cloneBO();
        l.setName(o.getName());
        return l;
    }

}
