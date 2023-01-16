//знакомство с сервлетом, тестовый сервлет

package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


//Параметр типа HttpServletRequest инкапсулирует всю информацию о запросе
//Параметр типа HttpServletResponse позволяет управлять ответом.
//С помощью вызова response.setContentType("text/html") устанавливается тип ответа

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8"); //устанавливается тип ответа
        PrintWriter writer = resp.getWriter(); //объект PrintWriter, через который можно отправить какой-то определенный ответ пользователю
        writer.println( "<h2 > Hello from Servlet. Time" + new Date()+"</h2>" ); //это будет наш ответ
        writer.println("<a href='http://localhost:8081/web-app/'> home </a>"); //и добавим ссылку
    }
}