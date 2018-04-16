package beans;

import models.Parent;
import javax.ejb.Local;
import java.util.List;

@Local(SessionParentBean.class)
public interface IParentBean {

    List<Parent> getAll();
    boolean add(long personId);
    boolean delete(long id);
    Parent get(long id);
    boolean update(long parent_id, long person_id);
}
