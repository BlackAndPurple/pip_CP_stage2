package beans;

import models.ParentContacts;

import javax.ejb.Local;
import java.util.List;

@Local(SessionContactsBean.class)
public interface IContactsBean {

    List<ParentContacts> getAll();
    boolean add(long parentId, String homeAddress, String job, String jobPhoneNumber, String cellPhoneNumber);
    boolean delete(long id);
    ParentContacts get(long id);
    boolean update(long contacts_id, long parent_id, String homeAddress, String job, String jobPhoneNumber, String cellPhoneNumber);
    }
