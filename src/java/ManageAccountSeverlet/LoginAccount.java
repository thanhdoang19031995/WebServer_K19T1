/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageAccountSeverlet;

import DataAccess.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TrungKien đẹp trai
 */
public class LoginAccount extends HttpServlet {

    DataAccessManageAccount dataAccount = new DataAccessManageAccount();

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
            dataAccount.connectData();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            out.print(dataAccount.isAccount(email, password));
            processRequest(request, response);
            dataAccount.closeConnect();
        }

    }

}

class DataAccessManageAccount extends DataAccess {

    public String isAccount(String email, String pass) {

        String re = "";
        String select_isaccount = "select * from ManageAccount where Email like ? and Pass like ?";
        try {
            sql = select_isaccount;
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("IdUser");
                re = "" + id;
            }
            if (re.equals("")) {
                return "No";
            } else {
                return re;
            }
        } catch (Exception e) {
            re = e.toString();
            return re;
        }
    }
}
