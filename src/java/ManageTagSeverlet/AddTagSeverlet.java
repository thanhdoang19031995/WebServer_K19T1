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
public class AddTagSeverlet extends HttpServlet {

    DataAccess ManageTag = new DataAccess();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String IdUser = request.getParameter("IdUser");
        String NameTag = request.getParameter("NameTag");
        String rename = NameTag.replace('+', ' ');
        String sql;
        sql = "insert into ManageTag\n" + "values (" + IdUser + ",'" + rename + "')";
        String result = ManageTag.setDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
    }

}
