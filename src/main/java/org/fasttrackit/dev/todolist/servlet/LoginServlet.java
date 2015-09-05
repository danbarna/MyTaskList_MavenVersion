package org.fasttrackit.dev.todolist.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by condor on 04/04/15.
 * FastTrackIT, 2015
 */
public class LoginServlet extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) {


        // citesc useri si parole din db
        // le scriu intr-o lista
        // compar user si parola cu ceea ce vine din ui

        // daca sunt egale atunci scriu pe sesiune un unsername si un id si il trimit la lista de taskuri a sa
        // altfel revin la pagina de login la infinit

        final String dbuser="ionel";
        final String dbpassword="anaaremere";
        final String dbuser1="florian";
        final String dbpassword1="florian";

        String userui=request.getParameter("userui");
        String pwdui=request.getParameter("pwdui");

        boolean found=false;
        int userid=-1;

        if(userui.equals(dbuser) && pwdui.equals(dbpassword)) {
            found=true;
            userid=341;
        }
        if(userui.equals(dbuser1) && pwdui.equals(dbpassword1)) {
            found=true;
            userid=200;
        }


        if(found) {

            HttpSession s = request.getSession(true);
            s.setAttribute("username",userui);
            s.setAttribute("userid", userid);

            String success="/todolist.html";
            RequestDispatcher d = getServletContext().getRequestDispatcher(success);
            try {
                d.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {

            System.out.println("nu ai nimerit");
            String success="/login.html";
            RequestDispatcher d = getServletContext().getRequestDispatcher(success);
            try {
                d.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        System.out.println("service called...");


    }
}
