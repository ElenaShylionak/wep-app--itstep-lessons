//Регистрация

package org.example.servlets;

import org.example.util.AppConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html"); //interface, он определяет объект,
        // который получает запросы от клиента и отправляет им любому ресурсу
        requestDispatcher.forward( req, resp); //принимает два значения
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        final String password = req.getParameter("pwd");

        //Метод trim() – возвращает копию строки с пропущенными начальными и конечными пробелами,
        // другими словами метод позволяет в Java удалить пробелы в начале и конце строки.
        if(email.trim().equalsIgnoreCase(AppConstants.DUMMY_USER_EMAIL) && password.equals(AppConstants.DUMMY_USER_PWD)){
            RequestDispatcher rd = req.getRequestDispatcher("welcome");
            rd.forward(req, resp);
        } else {
            // include (мы можем включить содержимое другого сервлета в текущий сервлет).
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            resp.getWriter().println("<b>Bad credentials</b>");
            rd.include(req, resp);
        }

    }
}
