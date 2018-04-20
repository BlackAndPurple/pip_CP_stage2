package beans;

import models.Kid;
import models.MedInfo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.MedInfo model}.
 */
@Stateless(name = "SessionMedEJB")
public class SessionMedBean implements IMedBean
{
    public SessionMedBean() {
    }

    @EJB
    private IKidBean kidBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get medinfo by id.
     * @param id  The identifier of med record we are looking for.
     * @return    medinfo with given id if found.
     */
    public MedInfo get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (MedInfo) em.createQuery("select med from MedInfo med where med.med_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    /**
     * Allows to get list of all the records in table.
     * @return List of medInfos.
     */
    public List<MedInfo> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select med from MedInfo med");
        List<MedInfo> medInfos = query.getResultList();
        em.close();
        return medInfos;
    }

    /**
     * Allows to add new medInfo to database.
     * @param kidId             Kid's id.
     * @param dateOfCreating    Date of medical check.
     * @param height            Kid's height.
     * @param weight            Kid's weight.
     * @param inoculations      Information about new inoculations if there are any.
     * @param diseases          Information about current diseases if there are any.
     * @return                  True if add operation was successful. Otherwise false.
     */
    public boolean add(long kidId, Date dateOfCreating, int height, double weight, String inoculations, String diseases)  {

        Kid kid = kidBean.get(kidId);
        if (kid != null){
            EntityManager em = emf.createEntityManager();
            MedInfo medInfo = new MedInfo();
            medInfo.setKid(kid);
            medInfo.setHeight(height);
            medInfo.setWeight(weight);
            medInfo.setInoculations(inoculations);
            medInfo.setCurrentDeseases(diseases);
            if (dateOfCreating != null)
                medInfo.setDate_of_creating(dateOfCreating);
            else medInfo.setDate_of_creating(new Date());
            em.persist(medInfo);
            em.close();
            return true;
        } return false;
    }

    /**
     * Allows to delete medInfo with given id.
     * @param id    record's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        MedInfo medInfo = get(id);
        if (medInfo != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from MedInfo medInfo where medInfo.med_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to update medinfo with given id.
     * @param medId             Record's id.
     * @param kidId             New kid's id.
     * @param dateOfCreating    New date of medical check.
     * @param height            New kid's height.
     * @param weight            New kid's weight.
     * @param inoculations      New information about new inoculations if there are any.
     * @param diseases          New information about current diseases if there are any.
     * @return                  True if add operation was successful. Otherwise false.
     */
    public boolean update(long medId, long kidId, Date dateOfCreating, Integer height, Double weight, String inoculations, String diseases){
        Kid kid = kidBean.get(kidId);
        MedInfo medInfo = get(medId);
        if (medInfo != null && kid != null) {
            medInfo.setKid(kid);
            if (dateOfCreating != null)
                medInfo.setDate_of_creating(dateOfCreating);
            else medInfo.setDate_of_creating(new Date());
            medInfo.setHeight(height);
            medInfo.setWeight(weight);
            medInfo.setInoculations(inoculations);
            medInfo.setCurrentDeseases(diseases);
            return true;
        }
        return false;

    }
}
