package beans;

import models.ParentContacts;

import javax.ejb.Local;
import java.util.List;

@Local(SessionContactsBean.class)
public interface IContactsBean {

    public List<ParentContacts> getAll();
}
