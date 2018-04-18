package beans;

import models.KidAccount;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local(SessionAccountBean.class)
public interface IAccountBean {

    List<KidAccount> getAll();
    boolean add(long kid_id, long group_id, Date dateOfCreating, Date dateOfLeaving);
}
