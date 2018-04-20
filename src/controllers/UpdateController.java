package controllers;

import beans.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateController extends HttpServlet implements Respondent{

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



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String[] pathArr = path.split("/");
        try {
            boolean success = false;
            String personId = null;
            String parentId = null;
            String kidId = null;
            String groupId = null;
            switch (pathArr[2]) {
                case "person":
                    personId = req.getParameter("id");
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
                    success = peopleBean.update(Long.parseLong(personId), name, middleName, surname, Boolean.parseBoolean(sex), date);
                    break;
                case "parent":
                    personId = req.getParameter("person_id");
                    parentId = req.getParameter("parent_id");
                    success = parentBean.update(Long.parseLong(parentId), Long.parseLong(personId));
                    break;
                case "contacts":
                    String contactsId = req.getParameter("contacts_id");
                    parentId = req.getParameter("parent_id");
                    String address = req.getParameter("address");
                    String job = req.getParameter("job");
                    String jobPhoneNumber = req.getParameter("job_phone_number");
                    String cellPhoneNumber = req.getParameter("cell_phone_number");
                    success = contactsBean.update(Long.parseLong(contactsId), Long.parseLong(parentId), address, job, jobPhoneNumber, cellPhoneNumber);
                    break;
                case "kid":
                    kidId = req.getParameter("kid_id");
                    personId = req.getParameter("person_id");
                    String parent1Id = req.getParameter("parent1_id");
                    String parent2Id = req.getParameter("parent2_id");
                    success = kidBean.update(Long.parseLong(kidId), Long.parseLong(personId), Long.parseLong(parent1Id), Long.parseLong(parent2Id));
                    break;
                case "account":
                    String accountId = req.getParameter("account_id");
                    kidId = req.getParameter("kid_id");
                    groupId = req.getParameter("group_id");
                    Date since = parseDate(req.getParameter("date_of_creating"));
                    Date until = parseDate(req.getParameter("date_of_leaving"));
                    success = accountBean.update(Long.parseLong(accountId), Long.parseLong(kidId), Long.parseLong(groupId), since, until);
                    break;
                case "group":
                    groupId = req.getParameter("group_id");
                    String groupName = req.getParameter("group_name");
                    String groupType = req.getParameter("group_type");
                    success = groupBean.update(Long.parseLong(groupId), groupName, groupType);
                    break;
                case "med":
                    String medId = req.getParameter("med_id");
                    kidId = req.getParameter("kid_id");
                    date = parseDate(req.getParameter("date_of_creating"));
                    Integer height = req.getParameter("height").equals("") ? null : Integer.parseInt(req.getParameter("height"));
                    Double weight = req.getParameter("height").equals("") ? null : Double.parseDouble(req.getParameter("height"));
                   // String height = req.getParameter("height");
                    //String weight = req.getParameter("weight");
                    String inoculations = req.getParameter("inoculations");
                    String diseases = req.getParameter("diseases");
                    success = medBean.update(Long.parseLong(medId), Long.parseLong(kidId), date, height, weight, inoculations, diseases);
                    break;
            }
            if (!success)
                throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
            responseBlank(resp, "Unable to update record");
        }

        responseBlank(resp, "Record has been updated!");

    }
}
