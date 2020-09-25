/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.data;

import javax.naming.Context;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/**
 *
 * @author Leo
 */
public class Dao {

    private static String url = "jdbc:mysql://localhost:3306/soup";
    private static String user = "root";
    private static String pass = "";

    public static java.sql.Connection getConnection() throws Exception{
        java.sql.Connection c = null;
        try {
            String DATASOURCE_CONTEXT = "java:comp/env/jdbc/mydatabase";
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                c = datasource.getConnection();
            }
        } catch (Exception ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
