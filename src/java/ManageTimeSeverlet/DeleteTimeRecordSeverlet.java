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
public class DeleteTimeRecordSeverlet extends HttpServlet {

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
        String IdTime = request.getParameter("IdTime");
        String sql;
        sql = "delete ManageTime\n" + "where IdTime =" + IdTime;
        String result = ManageTime.setDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
    }

}
