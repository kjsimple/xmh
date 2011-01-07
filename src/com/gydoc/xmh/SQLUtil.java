package com.gydoc.xmh;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SQLUtil {

	private SQLUtil() {

	}

	public static void executeBatch(Connection conn, InputStream sqlFileStream) throws SQLException, IOException {
		executeBatch(conn, sqlFileStream, "\\@\\@\\$\\$\\%\\%;");
	}

	public static void executeBatch(Connection conn, InputStream sqlFileStream, String delimiter) throws SQLException, IOException {
		try {
            conn.setAutoCommit(false);
            StringBuilder sb = new StringBuilder();
            InputStreamReader isr = new InputStreamReader(sqlFileStream, "UTF-8");
            int bufferSize = 1024 * 1024;
            char[] buffer = new char[bufferSize];
            int index;
            while ((index = isr.read(buffer, 0, bufferSize)) != -1) {
                sb.append(buffer, 0, index);
            }
            isr.close();
            Statement stat = conn.createStatement();
            String sqlsStr = sb.toString();
            String[] sqls = sqlsStr.split(delimiter);
            for (String s : sqls) {
                if (s.trim().equals("")) {
                    continue ;
                }
                stat.addBatch(s);
            }
            stat.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
	}

}
