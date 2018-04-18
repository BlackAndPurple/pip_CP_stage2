package beans;

import models.Group;

import javax.ejb.Local;

@Local(SessionGroupBean.class)
public interface IGroupBean {

    Group get(long id);
}
