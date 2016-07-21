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
public class EditTimeRecordSeverlet extends HttpServlet {

   DataAccess ManageTime = new DataAccess();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String IdTime = request.getParameter("IdTime");
        String IdTag = request.getParameter("IdTag");
        String DateEnter = request.getParameter("DateEnter");
        String BeginTime = request.getParameter("BeginTime");
        String EndTime = request.getParameter("EndTime");
        String Content = request.getParameter("Content");
        String reContent = Content.replace('+', ' ');
        String sql;
        sql = "update ManageTime\n"
                + "set IdTag = " + IdTag + ",\n"
                + "[DateEnter] = '" + DateEnter + "',\n"
                + "BeginTime ='" + BeginTime + "',\n"
                + "EndTime = '" + EndTime + "',\n"
                + "[Content] = '" + reContent + "'\n"
                + "where IdTime =" + IdTime;
        
        String result = ManageTime.setDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(msg);
    }
}
