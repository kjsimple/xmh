package com.gydoc.xmh.standalone.dsimpl;

import com.gydoc.xmh.SQLUtil;
import com.gydoc.xmh.datasource.DataServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import org.apache.derby.drda.NetworkServerControl;

/**
 * 
 */
public class DerbyDataServer implements DataServer {

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private boolean running = false;
    private static String userPassword = "60118791lrigasidog";
    private static String dbStart = "jdbc:derby:xmh_db;user=xmh;password=" + userPassword + ";bootPassword=godisagirl19781106";
    private static String dbStartWithCreate = dbStart + ";dataEncryption=true;create=true";
    private static String userNamePropertyName = "derby.user.xmh";

    public void start() throws Exception {
        System.setProperty("derby.system.home", "conf/db");

        Class.forName(driver).newInstance();
        if (!dbExist()) {
            initDB();
        } else {
            startDB();
        }
        NetworkServerControl server = new NetworkServerControl();
        server.start(null);
        running = true;
    }

    private void startDB() throws SQLException {
        Connection conn  = DriverManager.getConnection(dbStart);
        conn.close();
    }

    private void initDB() throws SQLException {
        Connection conn  = DriverManager.getConnection(dbStartWithCreate);
        initDB(conn);
        conn.close();
    }

    private void initDB(Connection conn) throws SQLException {
        try {
            setDataBaseProperty("derby.connection.requireAuthentication", "true", conn);
            setDataBaseProperty("derby.authentication.provider", "BUILTIN", conn);
            setDataBaseProperty(userNamePropertyName, userPassword, conn);
            setDataBaseProperty("derby.database.propertiesOnly", "true", conn);
			SQLUtil.executeBatch(conn, getClass().getClassLoader().getResourceAsStream("db/derby/init.sql"));
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    private void setDataBaseProperty(String name, String value, Connection conn) throws SQLException {
        Statement stat = conn.createStatement();
        stat.executeUpdate(String.format("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('%1$s', '%2$s')", name, value));
        ResultSet rs = stat.executeQuery(String.format("VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY('%1$s')", name));
        rs.next();
        String v = rs.getString(1);
        if (userNamePropertyName.equals(name) && v != null) {
            return ;
        }
        if (v == null || !value.toUpperCase().equals(v.toUpperCase())) {
            String msg = String.format("Could not set database level property \"%1$s\".", name);
            throw new SQLException(msg);
        }
    }

    private boolean dbExist() throws SQLException {
        try {
            Connection conn  = DriverManager.getConnection(dbStart);
            conn.close();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 40000 && ("XJ004".equals(e.getSQLState()))) {
                return false;
            }
            throw e;
        }
    }

    public void stop() throws Exception {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");

            // To shut down a specific database only, but keep the
            // engine running (for example for connecting to other
            // databases), specify a database in the connection URL:
            //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
        } catch (SQLException se) {
            if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
                // we got the expected exception

                // Note that for single database shutdown, the expected
                // SQL state is "08006", and the error code is 45000.
            } else {
                // if the error code or SQLState is different, we have
                // an unexpected exception (shutdown failed)
                se.printStackTrace();
            }
        }
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

}
