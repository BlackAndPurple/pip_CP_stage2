package controllers;

import beans.*;
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

public class AddController extends HttpServlet implements Respondent{

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

    @EJB
    private IStaffGroupBean staffGroupBean;


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
//                    if ((dateOfBirth != null) && !dateOfBirth.equals("")) {
//                        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//                        try {
//                            date = format.parse(dateOfBirth);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    date = parseDate(dateOfBirth);
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
                case "kid":
                    id = req.getParameter("person_id");
                    String parent1_id = req.getParameter("parent1_id");
                    String parent2_id = req.getParameter("parent2_id");
                    if (parent2_id.equals(""))
                        kidBean.add(Long.parseLong(id), Long.parseLong(parent1_id), -1);
                    else
                        kidBean.add(Long.parseLong(id), Long.parseLong(parent1_id), Long.parseLong(parent2_id));
                    break;
                case "account":
                    String kidId = req.getParameter("kid_id");
                    String groupId = req.getParameter("group_id");
                    String dateOfCreating = req.getParameter("date_of_creating");
                    String dateOfLeaving = req.getParameter("date_of_leaving");
                    if (!accountBean.add(Long.parseLong(kidId), Long.parseLong(groupId), parseDate(dateOfCreating), parseDate(dateOfLeaving)))
                        throw new Exception();
                    break;
                case "group":
                    String groupName = req.getParameter("group_name");
                    String groupType = req.getParameter("group_type");
                    if (!groupBean.add(groupName, groupType))
                        throw new Exception();
                    break;
                case "med":
                    kidId = req.getParameter("kid_id");
                    date = parseDate(req.getParameter("date_of_creating"));
                    String height = req.getParameter("height");
                    String weight = req.getParameter("weight");
                    String inoculations = req.getParameter("inoculations");
                    String diseases = req.getParameter("diseases");
                    if (!medBean.add(Long.parseLong(kidId), date, Integer.parseInt(height), Double.parseDouble(weight), inoculations, diseases))
                        throw new Exception();
                    break;
                case "staff":
                    id = req.getParameter("person_id");
                    String function = req.getParameter("function");
                    String experience = req.getParameter("experience");
                    if (!staffBean.add(Long.parseLong(id), function, experience))
                        throw new Exception();
                    break;
                case "sg":
                    id = req.getParameter("staff_id");
                    groupId = req.getParameter("group_id");
                    Date since = parseDate(req.getParameter("since"));
                    Date until = parseDate(req.getParameter("until"));
                    if (!staffGroupBean.add(Long.parseLong(id), Long.parseLong(groupId), since, until))
                        throw new Exception();
                    break;
            }

        }catch (Exception e){
            e.printStackTrace();
            responseBlank(resp, "Unable to add record");
        }

        responseBlank(resp, "Record has been added!");

    }
}
