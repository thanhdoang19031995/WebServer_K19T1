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
public class TagServlet extends HttpServlet {

    DataAccessManageTag ManageTag = new DataAccessManageTag();

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ManageTag.connectData();
            String IdUser = request.getParameter("IdUser");
            int userId = Integer.parseInt(IdUser);
            out.print(ManageTag.isTagIdUser(userId));
            processRequest(request, response);
            ManageTag.closeConnect();
        }
    }
}

class DataAccessManageTag extends DataAccess {

    public String isTagIdUser(int IdUser) {
        String re = "";
        String select_istag = "select T.IdTag , T.NameTag from  ManageTag T, [ManageAccount] U \n"
                + " where T.IdUser = U.IdUser \n"
                + "and U.IdUser = " + IdUser + "order by T.IdTag DESC";
        try {
            sql = select_istag;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("IdTag");
                String name = rs.getString("NameTag");
                re = re + id + ";" + name + "\n";
            }
            return re.trim();
        } catch (Exception e) {
            re = e.toString();
            return re;
        }
    }
}
