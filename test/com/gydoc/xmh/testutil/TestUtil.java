package com.gydoc.xmh.testutil;

import com.gydoc.xmh.SpringUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 *
 */
public class TestUtil {

    private static String[] tables = new String[] {"ledger", "ledgerusers", "xmhUser", "accountclassification", "LedgerCurrencies", "currency"};

    @BeforeSuite
    public void beforeSuite() throws Exception {
        String userDir = System.getProperty("user.dir");
        System.setProperty("user.dir", userDir + File.separator + "xmh_testing");
        File f = new File(System.getProperty("user.dir"));
        if (f.exists()) {
            if (!delTree(f)) {
                System.out.println("Can not delete old configuration files, ignore the testing.");
                System.exit(-1);
            }
        }
    }
    
    @AfterSuite
    public void afterSuite() throws Exception {
        
    }
    
    private boolean delTree(File f) {
        if (!f.exists()) {
            return false;
        }
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File child : files) {
                if (!delTree(child)) {
                    return false;
                }
            }
        }
        return f.delete();
    }

    public static Object getBean(String id) {
        return SpringUtil.getSpringContext().getBean(id);
    }

    public static void cleanAllTables() throws SQLException {
        for (String t : tables) {
            cleanTable(t);
        }
    }
    
    public static void cleanTable(String s) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:xmh_db;user=xmh;password=60118791lrigasidog");
        Statement stat = conn.createStatement();
        if ("xmhUser".equals(s)) {
            stat.executeUpdate("DELETE FROM xmhUser where name <> '_sys'");
        } else {
            stat.executeUpdate("DELETE FROM " + s);
        }
    }
}
