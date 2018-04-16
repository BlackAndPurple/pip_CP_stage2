package controllers;

import beans.IContactsBean;
import beans.IParentBean;
import beans.IPeopleBean;
import models.People;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddControlller extends HttpServlet implements Respondent{

    @EJB
    private IPeopleBean peopleBean;

    @EJB
    private IParentBean parentBean;

    @EJB
    private IContactsBean contactsBean;


//    @PersistenceUnit
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        String id = null;
        try {
            switch (pathArr[2]) {
                case "person":
                    id = req.getParameter("id");
                    String name = req.getParameter("name");
                    String middleName = req.getParameter("middle_name");
                    String surname = req.getParameter("surname");
                    String sex = req.getParameter("sex");
                    String dateOfBirth = req.getParameter("date_of_birth");
                    Date date = null;
                    if ((dateOfBirth != null) && !dateOfBirth.equals("")) {
                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        try {
                            date = format.parse(dateOfBirth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    peopleBean.add(name, middleName, surname, Boolean.parseBoolean(sex), date);
                    break;
                case "parent":
                    id = req.getParameter("id");
                    parentBean.add(Long.parseLong(id));
                    break;
                case "contacts":
                    id = req.getParameter("parent_id");
                    String homeAddress = req.getParameter("address");
                    String job = req.getParameter("job");
                    String jobPhoneNumber = req.getParameter("job_phone_number");
                    String cellPhoneNumber = req.getParameter("cell_phone_number");
                    contactsBean.add(Long.parseLong(id), homeAddress, job, jobPhoneNumber, cellPhoneNumber);
                    break;
            }

        }catch (Exception e){
            responseBlank(resp, "Unable to add record");
        }

        responseBlank(resp, "Record has been added!");

    }
}
