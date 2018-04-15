package controllers;

import beans.IContactsBean;
import beans.IParentBean;
import beans.IPeopleBean;
import beans.SessionPeopleBean;
import models.Parent;
import models.People;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetController extends HttpServlet implements Respondent{

    @EJB
    private IPeopleBean peopleBean;

    @EJB
    private IParentBean parentBean;

    @EJB
    private IContactsBean contactsBean;


    private String makeContentFromList(List ResultList){
        String retString = "";
        for (Object o : ResultList)
            retString += (o + "<br>");
        return retString;
        //need to change to StringBuilder
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<?> ResultList = null;

        switch (req.getPathInfo()) {
            case "/":
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
                break;
            case "/people":
                ResultList = peopleBean.getAll();
                //responseBlank(resp, makeContentFromList(ResultList));
                break;
            case "/parent":
                ResultList = parentBean.getAll();
                //responseBlank(resp, makeContentFromList(ResultList));
                break;
            case "/contacts":
                ResultList = contactsBean.getAll();
                //responseBlank(resp, makeContentFromList(ResultList));
                break;
        }

        responseBlank(resp, makeContentFromList(ResultList));


    }
}
