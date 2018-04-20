package beans;

import models.People;
import models.Staff;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.Staff model}.
 */
@Stateless(name = "SessionStaffEJB")
public class SessionStaffBean implements IStaffBean{
    public SessionStaffBean() {
    }

    @EJB
    IPeopleBean peopleBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get list of all the records in table.
     * @return List of staff.
     */
    public List<Staff> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select staff from Staff staff");
        List<Staff> staff = query.getResultList();
        em.close();
        return staff;
    }

    /**
     * Allows to add new staff to database.
     * @param personId          Kid's id.
     * @param function          Function the staff 	accomplishes.
     * @param experience        Working experience;
     * @return                  True if add operation was successful. Otherwise false.
     */
    public boolean add(long personId, String function, String experience)  {

        People person = peopleBean.get(personId);
        if (person != null){
            EntityManager em = emf.createEntityManager();
            Staff staff = new Staff();
            staff.setPerson(person);
            staff.setFunction(function);
            staff.setExperience(experience);
            em.persist(staff);
            em.close();
            return true;
        } return false;
    }

    /**
     * Allows to delete staff with given id.
     * @param id    Record's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        Staff staff = get(id);
        if (staff != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from Staff staff where staff.staff_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to get staff by id.
     * @param id  The identifier of med record we are looking for.
     * @return    Staff with given id if found.
     */
    public Staff get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (Staff) em.createQuery("select staff from Staff staff where staff.staff_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
