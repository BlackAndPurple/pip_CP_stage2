package beans;

import models.People;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

//@Local
@Local(SessionPeopleBean.class)
public interface IPeopleBean {

    public People add(People person);

    public People get(long id);

    public void update(People person);

    public void delete(long id);

    public List<People> getAll();

    public void addPerson(String name, String middleName, String surname, boolean sex, Date dateOfBirth);

}
