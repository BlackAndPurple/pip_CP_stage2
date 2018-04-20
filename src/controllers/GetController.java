package controllers;

import beans.*;
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

    @EJB
    private IKidBean kidBean;

    @EJB
    private IAccountBean accountBean;

    @EJB
    private IGroupBean groupBean;

    @EJB
    private IMedBean medBean;


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
                break;
            case "/parent":
                ResultList = parentBean.getAll();
                break;
            case "/contacts":
                ResultList = contactsBean.getAll();
                break;
            case "/kid":
                ResultList = kidBean.getAll();
                break;
            case "/account":
                ResultList = accountBean.getAll();
                break;
            case "/group":
                ResultList = groupBean.getAll();
                break;
            case "/med":
                ResultList = medBean.getAll();
                break;
        }

        responseBlank(resp, makeContentFromList(ResultList));


    }
}
