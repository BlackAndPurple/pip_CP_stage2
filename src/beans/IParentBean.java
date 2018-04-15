package beans;

import models.Parent;
import javax.ejb.Local;
import java.util.List;

@Local(SessionParentBean.class)
public interface IParentBean {

    public List<Parent> getAll();
}
