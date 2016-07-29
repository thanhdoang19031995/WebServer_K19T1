/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageTagSeverlet;

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
public class EditTagSeverlet extends HttpServlet {

     DataAccess ManageTag = new DataAccess();
     
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
        String NameTag = request.getParameter("NameTag");
        String reNameTag = NameTag.replace('+', ' ');
        String sql;
        sql = "update ManageTag\n" +"set NameTag = '"+reNameTag+"'\n" +"where IdTag =" + IdTag;
        String result = ManageTag.setDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
    }

}
