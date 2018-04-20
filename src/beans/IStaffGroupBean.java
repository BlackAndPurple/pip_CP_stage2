package beans;

import models.StaffGroup;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local(SessionStaffGroupBean.class)
public interface IStaffGroupBean {
    List<StaffGroup> getAll();
    StaffGroup get(long id);
    boolean add(long staffId, long groupID, Date since, Date until);
    boolean delete(long id);
    boolean update(long id, long staffId, long groupID, Date since, Date until);
}
