/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exe_Z.lib;

import Exe_Z.db.jdbc.DbManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class ZConnection {

    private Connection connection;
    private final int timeOut;

    public ZConnection(int timeOut) {
        this.timeOut = timeOut;
    }

    public Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                try (Statement st = connection.createStatement()) {
                    st.setQueryTimeout(timeOut);
                    st.execute("SELECT 1");
                    return connection;
                } catch (SQLException ex) {
                    closeQuietly(connection);
                    connection = null;
                }
            }
            connection = DbManager.getInstance().getConnection();
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }
}
