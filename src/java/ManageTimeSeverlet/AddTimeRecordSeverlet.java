/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageTimeSeverlet;

import DataAccess.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TrungKien
 */
public class AddTimeRecordSeverlet extends HttpServlet {

    DataAccess ManageTime = new DataAccess();

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
        String IdTag = request.getParameter("IdTag");
        String IdUser = request.getParameter("IdUser");
        String DateEnter = request.getParameter("DateEnter");
        String BeginTime = request.getParameter("BeginTime");
        String EndTime = request.getParameter("EndTime");
        String Content = request.getParameter("Content");
        String reContent = Content.replace('+', ' ');
        String sql;
        sql = "insert into ManageTime\n"
                + "values (" + IdUser + "," + IdTag + ",'" + reContent + "'"
                + ",'" + BeginTime + "','" + EndTime + "','" + DateEnter + "')";

        String result = ManageTime.setDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
    }

}
