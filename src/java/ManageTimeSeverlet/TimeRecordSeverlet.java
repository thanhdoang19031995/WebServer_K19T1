/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageTimeSeverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TrungKien
 */
public class TimeRecordSeverlet extends HttpServlet {

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
        String IdUser = request.getParameter("IdUser");
        String sql;
        sql = "select R.IdTime , T.NameTag , R.BeginTime, R.EndTime,R.DateEnter, R.[Content] from ManageTime R, ManageTag T\n"
                + "where R.IdTag = T.IdTag and\n"
                + "R.IdUser =" + IdUser + "order by R.IdTime DESC";
        String result = Getdata(sql);
        response(response, result.trim());
    }

    private void response(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
    }

    public String Getdata(String sql) {
        String re = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://cmu.vanlanguni.edu.vn:1433;databaseName=thanhdoang", "thanhdoang", "19031995");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int IdTime = rs.getInt("IdTime");
                String NameTag = rs.getString("NameTag");
                Date DateEnter = rs.getDate("DateEnter");
                Time BeginTime = rs.getTime("BeginTime");
                Time EndTime = rs.getTime("EndTime");
                String Content = rs.getString("Content");
                re = re + IdTime + ";" + NameTag + ";" + BeginTime + ";" + EndTime + ";" + DateEnter + ";" + Content + "\n";
            }
        } catch (SQLException | ClassNotFoundException e) {
            re = e.getMessage();
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
                re = e.getMessage();
            }
        }
        return re;
    }

}
