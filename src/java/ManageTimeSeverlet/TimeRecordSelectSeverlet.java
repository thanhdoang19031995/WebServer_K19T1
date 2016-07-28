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
public class TimeRecordSelectSeverlet extends HttpServlet {

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
        String IdTime = request.getParameter("IdTime");
        String sql;
//        sql = "select R.IdTime , T.NameTag ,  R.BeginTime, R.EndTime, R.[Content] from ManageTime R, ManageTag T\n"
//                + "where R.IdTag = T.IdTag and\n"
//                + "R.IdTime =" + IdTime;
        sql="select * from ManageTime where IdTime="+IdTime;
        String result = Getdata(sql);
        response(response, result);
    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        PrintWriter out = resp.getWriter();
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
                //Retrieve by column name
                int IdTime = rs.getInt("IdTime");
                int IdUser=rs.getInt("IdUser");
                int IdTag=rs.getInt("IdTag");
                String Content = rs.getString("Content");
                Time BeginTime = rs.getTime("BeginTime");
                Time EndTime = rs.getTime("EndTime");
                Date DayEnd=rs.getDate("DateEnter");
                re = re +IdTime+";"+IdUser+";"+IdTag+";"+Content+";"+BeginTime+";"+EndTime+";"+DayEnd+"\n";
            }
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

}