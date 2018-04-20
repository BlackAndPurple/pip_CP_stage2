package controllers;

import beans.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteController extends HttpServlet implements Respondent{

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

    @EJB
    private IStaffBean staffBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        String id = req.getParameter("id");
        boolean success = false;
        switch (pathArr[2]) {
            case "people":
                success = peopleBean.delete(Long.parseLong(id));
                break;
            case "parent":
                success = parentBean.delete(Long.parseLong(id));
                break;
            case "contacts":
                success = contactsBean.delete(Long.parseLong(id));
                break;
            case "kid":
                success = kidBean.delete(Long.parseLong(id));
                break;
            case "account":
                success = accountBean.delete(Long.parseLong(id));
                break;
            case "group":
                success = groupBean.delete(Long.parseLong(id));
                break;
            case "med":
                success = medBean.delete(Long.parseLong(id));
                break;
            case "staff":
                success = staffBean.delete(Long.parseLong(id));
                break;
        }

        if (success)
            responseBlank(resp, "Record has been deleted!");
        else responseBlank(resp, "There's no record with such id");
    }

}
