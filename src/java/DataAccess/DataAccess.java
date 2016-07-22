/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.*;

/**
 *
 * @author TrungKien
 * @KienDeptrai
 */
public class DataAccess {
// nguyen demo
    protected Connection conn = null;
    protected Statement stmt = null;
    protected ResultSet rs = null;
    protected PreparedStatement pst = null;
    protected String sql = "";
    protected String USER = "thanhdoang";
    protected String PASS = "19031995";
    protected String DB_URL = "jdbc:sqlserver://cmu.vanlanguni.edu.vn";
    protected String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public DataAccess() {
        connectData();
    }//construct

    public void connectData() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("Connect Successfull");
        } catch (Exception d) {
            d.getMessage();
        }
    }

    public boolean closeConnect() {
        try {
            rs.close();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception d) {
            return false;
        }
    }//close connect

    public String setDataSQL(String sql) {
        String re = "";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            re = "SUCCESSFUL";
        } catch (SQLException se) {
            //Handle errors for JDBC
            re = se.toString();
        } catch (Exception e) {
            //Handle errors for Class.forName
            re = e.toString();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                re = se.toString();
            }
        }
        return re;
    }

    public String getDataSQL(String sql) {
        String re = "";
        //ResultSet rs;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String result = rs.getString("Result");
                re = re + result + "\n";
            }
        } catch (SQLException se) {
            se.getMessage();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.getMessage();

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.getMessage();
            }
        }
        return re.trim();
    }
}//end class
