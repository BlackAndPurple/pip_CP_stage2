package beans;

import models.Kid;

import javax.ejb.Local;
import java.util.List;

@Local(SessionKidBean.class)
public interface IKidBean {

    List<Kid> getAll();
    boolean add(long person_id, long parent1_id, long parent2_id);
    boolean delete(long id);
    boolean update(long kid_id, long person_id, long parent1_id, long parent2_id);
    public Kid get(long id);
}
