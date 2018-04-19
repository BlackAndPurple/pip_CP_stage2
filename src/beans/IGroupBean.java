package beans;

import models.Group;

import javax.ejb.Local;
import java.util.List;

@Local(SessionGroupBean.class)
public interface IGroupBean {

    Group get(long id);
    List<Group> getAll();
    boolean add(String groupName, String groupType);
    boolean delete(long id);
    boolean update(long groupId, String groupName, String groupType);
}
