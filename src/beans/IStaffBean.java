package beans;

import models.Staff;

import javax.ejb.Local;
import java.util.List;

@Local(SessionStaffBean.class)
public interface IStaffBean {
    List<Staff> getAll();
    boolean add(long personId, String function, String experience);
    boolean delete(long id);
    Staff get(long id);
    boolean update(long staffId, long personId, String function, String experience);
}
