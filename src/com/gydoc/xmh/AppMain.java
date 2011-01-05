package com.gydoc.xmh;

import com.gydoc.xmh.conf.UIConfig;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.domain.User;
import com.gydoc.xmh.widget.ledger.CreateLedgerWizard;
import com.gydoc.xmh.widget.login.LoginDialog;
import com.gydoc.xmh.widget.user.ChangePasswordDialog;
import com.gydoc.xmh.widget.user.ManageUserDialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/**
 *
 */
public class AppMain {

    private UIConfig uiConfig = new UIConfig();
    private JFrame mainFrame;
    private File conf = new File("conf/xmh.cfg");
    private static AppMain instance;
    private User user;
    private Ledger ledger4Admin;

    public static void main(String[] args) {
        instance = new AppMain();
        instance.start(args);
    }

    public static AppMain getInstance() {
        return instance;
    }

    public JFrame getFrame() {
        return this.mainFrame;
    }

    private void start(String[] args) {
        setUpLookAndFeel();
        setUpSpring();
        try {
            setUpUIConfig();
            login();
        } catch (Exception e) {
            System.exit(-1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(conf));
                    oos.writeObject(uiConfig);
                    oos.close();
                } catch (Exception e) {
                    // todo
                    e.printStackTrace();
                }
            }
        });

        setUpFrame();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Window mf = e.getWindow();
                uiConfig.setPositionX(mf.getX());
                uiConfig.setPositionY(mf.getY());
                uiConfig.setWindowHeight(mf.getHeight());
                uiConfig.setWindowWidth(mf.getWidth());
            }
        });
        mainFrame.setVisible(true);
    }

    private JMenuBar createMenus() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(I18NMessage.getMessage("menu.file"));
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem fileNewACMenu = new JMenuItem(I18NMessage.getMessage("menu.file.newAC"));
        fileNewACMenu.setMnemonic(KeyEvent.VK_A);
        fileNewACMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new CreateLedgerWizard();
                dialog.setVisible(true);
            }
        });
        fileMenu.add(fileNewACMenu);
        
        fileMenu.addSeparator();
        JMenuItem fileExitMenu = new JMenuItem(I18NMessage.getMessage("menu.file.exit"));
        fileExitMenu.setMnemonic(KeyEvent.VK_X);
        fileExitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });
        fileMenu.add(fileExitMenu);
        menuBar.add(fileMenu);
        
        JMenu manageMenu = new JMenu(I18NMessage.getMessage("menu.manage"));
        manageMenu.setMnemonic(KeyEvent.VK_M);
        
        if (isAdmin()) {
            JMenuItem manageUserMenu = new JMenuItem(I18NMessage.getMessage("menu.manage.user"));
            manageUserMenu.setMnemonic(KeyEvent.VK_U);
            manageUserMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ManageUserDialog dialog = new ManageUserDialog(getFrame(), true);
                    dialog.setVisible(true);
                }
            });
            manageMenu.add(manageUserMenu);
        }

        JMenuItem manageChangePass = new JMenuItem(I18NMessage.getMessage("menu.manage.changepass"));
        manageChangePass.setMnemonic(KeyEvent.VK_P);
        manageChangePass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePasswordDialog dialog = new ChangePasswordDialog(getFrame(), true);
                dialog.setVisible(true);
            }
        });
        manageMenu.add(manageChangePass);
        menuBar.add(manageMenu);
        
        return menuBar;
    }

    private void login() throws InterruptedException {
        JDialog loginDialog = new LoginDialog();
        loginDialog.setVisible(true);
        while (user == null) {
            Thread.sleep(1000);
        }
    }

    private void setUpSpring() {
        SpringUtil.getSpringContext();
    }

    private void setUpLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // this should never happen.
        }
    }

    private void setUpFrame() {
        mainFrame = new JFrame();
        mainFrame.setLayout(new FlowLayout());
        ImageIcon icon = new ImageIcon("images/xmh.png");
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setJMenuBar(createMenus());
        mainFrame.setName("XMH Main");
        mainFrame.setSize(uiConfig.getWindowWidth(), uiConfig.getWindowHeight());
        mainFrame.setLocation(uiConfig.getPositionX(), uiConfig.getPositionY());
    }

    private void setUpUIConfig() throws IOException, ClassNotFoundException {
        if (!conf.exists()) {
            uiConfig = new UIConfig();
            uiConfig.setPositionX(100);
            uiConfig.setPositionY(100);
            uiConfig.setWindowHeight(600);
            uiConfig.setWindowWidth(800);
            
            uiConfig.setLdWidth(320);
            uiConfig.setLdHeight(160);
            uiConfig.setLdPosX(-1);
            uiConfig.setLdPosY(-1);
        } else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(conf));
            uiConfig = (UIConfig) ois.readObject();
            ois.close();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UIConfig getUiConfig() {
        return uiConfig;
    }

    public Ledger getLedger4Admin() {
        return ledger4Admin;
    }

    public void setLedger4Admin(Ledger ledger4Admin) {
        this.ledger4Admin = ledger4Admin;
    }
    
    public boolean isAdmin() {
        return ledger4Admin != null;
    }
    
}
