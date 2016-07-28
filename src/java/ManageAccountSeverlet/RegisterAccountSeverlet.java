/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageAccountSeverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TrungKien
 */
public class RegisterAccountSeverlet extends HttpServlet {

    //public static String sInsert="insert into ManageAccount (Email, Pass) values(?,?)";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Email = request.getParameter("Email");
        String Pass = request.getParameter("Pass");
        String sql;
        sql = "if '" + Email + "' in (select U.Email from [ManageAccount] U)\n"
                + "begin\n"
                + "	update [ManageAccount]\n"
                + "	set [Pass] = '" + Pass + "'\n"
                + "	where Email like '" + Email + "'\n"
                + "end\n"
                + "else\n"
                + "begin\n"
                + "	insert into [ManageAccount]\n"
                + "	values ('" + Email + "', '" + Pass + "')\n"
                + "end";
//        sql1 = "select * from ManageAccount where Email=" + Email;
        String result = setDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(msg);
    }

    public String setDataSQL(String sql) {
        String re = "";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://cmu.vanlanguni.edu.vn:1433;databaseName=thanhdoang", "thanhdoang", "19031995");

            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            re = "Ok";
        } catch (SQLException | ClassNotFoundException se) {
            //Handle errors for JDBC
            se.getMessage();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return re;
    }
}
