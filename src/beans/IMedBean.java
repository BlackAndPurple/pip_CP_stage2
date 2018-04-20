package beans;

import models.MedInfo;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local(SessionMedBean.class)
public interface IMedBean {
    MedInfo get(long id);
    List<MedInfo> getAll();
    boolean add(long kidId, Date dateOfCreating, int height, double weight, String inoculations, String diseases);
    boolean delete(long id);
    boolean update(long medId, long kidId, Date dateOfCreating, Integer height, Double weight, String inoculations, String diseases);
}
