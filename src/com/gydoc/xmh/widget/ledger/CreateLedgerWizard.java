package com.gydoc.xmh.widget.ledger;

import com.gydoc.xmh.AppMain;
import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.SpringUtil;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.service.LedgerService;
import com.gydoc.xmh.widget.WizardPanel;
import com.gydoc.xmh.widget.XMHDialog;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 */
public class CreateLedgerWizard extends XMHDialog {

    private int currentStep = 0;
    private List<JPanel> steps = new ArrayList<JPanel>();
    private JButton cancel = new JButton(I18NMessage.getMessage("button.cancel"));
    private JButton previous = new JButton(I18NMessage.getMessage("button.previous"));
    private JButton next = new JButton(I18NMessage.getMessage("button.next"));
    private JButton finishButton = new JButton(I18NMessage.getMessage("button.finish"));
    private Component lastComponent = null;
    private int height = 395;
    private int width = 495;
    private JPanel buttons;
    private Ledger target = new Ledger();

    public CreateLedgerWizard() {
        super(AppMain.getInstance().getFrame(), "Create Ledger", true);
        init();
    }

    @Override
    public void escapePressed() {
        cancel.doClick();
    }

    private void init() {
        setSize(width, height);
        setResizable(false);
        steps.add(new CreateLedgerStep1());
        steps.add(new CreateLedgerStep2());
        steps.add(new CreateLedgerStep3());
        steps.add(new CreateLedgerStep4());
        steps.add(new CreateLedgerStep5());
        steps.add(new CreateLedgerStep6());

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints c = getMainPanelConstrains();
        add(steps.get(currentStep), c);

        buttons = new JPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 10, 4);
        c.fill = GridBagConstraints.HORIZONTAL;
        addButtons(buttons);
        add(buttons, c);
        
        setLocationRelativeTo(AppMain.getInstance().getFrame());
    }

    private GridBagConstraints getMainPanelConstrains() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        return c;
    }

    private void addButtons(final JPanel buttons) {
        GridBagLayout layout = new GridBagLayout();
        buttons.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        buttons.add(new JLabel(), c);

        c.gridx = 1;
        c.weightx = 0;
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markLastComponent();
                if (currentStep == (steps.size() - 1)) {
                    buttons.remove(finishButton);
                    buttons.add(next, getC());
                }
                currentStep--;
                changeMainPanel();
            }
        });
        checkButtons();
        buttons.add(previous, c);

        c.gridx = 2;
        c.weightx = 0;
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel wp = steps.get(currentStep);
                if (wp instanceof WizardPanel) {
                    if (!((WizardPanel) wp).okToNext()) {
                        return ;
                    }
                    target = (Ledger) ((WizardPanel) wp).updateTarget(target);
                }

                markLastComponent();
                currentStep++;
                changeMainPanel();
            }
        });
        buttons.add(next, c);

        c.gridx = 3;
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateLedgerWizard.this.dispose();
            }
        });
        buttons.add(cancel, c);

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finish();
            }
        });
    }

    private GridBagConstraints getC() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        return c;
    }

    private void finish() {
        LedgerService service = (LedgerService) SpringUtil.getBean("ledgerService");
        service.add(target);
    }

    private void markLastComponent() {
        lastComponent = steps.get(currentStep);
    }

    private void changeMainPanel() {
        remove(lastComponent);
        add(steps.get(currentStep), getMainPanelConstrains());
        checkButtons();
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void checkButtons() {
        if (steps.size() == 0) {
            previous.setEnabled(false);
            next.setEnabled(false);
            return ;
        }
        if (currentStep == 0) {
            next.setEnabled(true);
            previous.setEnabled(false);
        }
        if (currentStep > 0) {
            previous.setEnabled(true);
        }
        if (currentStep == (steps.size() - 1)) {
            buttons.remove(next);
            buttons.add(finishButton, getC());
        } else {
            next.setEnabled(true);
        }
    }

}
