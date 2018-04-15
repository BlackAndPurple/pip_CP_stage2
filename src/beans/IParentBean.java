package beans;

import models.Parent;
import javax.ejb.Local;
import java.util.List;

@Local(SessionParentBean.class)
public interface IParentBean {

    public List<Parent> getAll();
    public boolean add(long personId);
    public boolean delete(long id);
    public Parent get(long id);
}
