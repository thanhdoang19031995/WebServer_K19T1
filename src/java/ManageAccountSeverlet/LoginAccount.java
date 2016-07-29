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

    DataAccess data = new DataAccess();

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
        String Password = request.getParameter("Password");
        String sql;
        sql = "if '" + Email + "' in (select U.Email from [ManageAccount] U)\n"
                + "begin\n"
                + "	if '" + Password + "' like (select U.[Pass] from [ManageAccount] U where U.Email like '" + Email + "')\n"
                + "	begin\n"
                + "		select 'Ok;'+ CONVERT(varchar(10), U.IdUser) as Result from [ManageAccount] U where U.Email = '" + Email + "'\n"
                + "	end\n"
                + "	else\n"
                + "	begin\n"
                + "		select 'No'as Result\n"
                + "	end\n"
                + "end\n"
                + "else\n"
                + "begin\n"
                + "	select 'Account is not exist'as Result\n"
                + "end";
        String result = data.getDataSQL(sql);
        response(response, result);
    }

    private void response(HttpServletResponse response, String msg)
            throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
    }
}
