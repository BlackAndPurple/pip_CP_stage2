package controllers;

import beans.IPeopleBean;
import beans.SessionPeopleBean;
import models.People;

import javax.ejb.EJB;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetController extends HttpServlet {

    @EJB
    private IPeopleBean peopleBean;

    private void responseBlank(HttpServletResponse resp, String content) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>" + "<head><title>result</title>" +
                "</head><body >");
        out.println("<a href=\"../\">Back</a><br>");
        out.println(content);
        out.println("</body></html>");
        out.close();
    }

    private String makeContentFromList(List ResultList){
        String retString = "";
        for (Object o : ResultList)
            retString += (o + "<br>");
        return retString;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Получаем список пользователей
        List<People> ResultList = peopleBean.getAll();

        responseBlank(resp, makeContentFromList(ResultList));

        // добавляем полученный список в request,
        // который отправится на jsp
        //req.setAttribute("users", allUser);

        // отправляем request на jsp
        //req.getRequestDispatcher("/list.jsp").forward(req, resp);

    }
}
