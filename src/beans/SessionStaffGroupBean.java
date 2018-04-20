package beans;

import models.Staff;
import models.StaffGroup;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.StaffGroup model}.
 */
@Stateless(name = "SessionStaffGroupEJB")
public class SessionStaffGroupBean implements IStaffGroupBean{
    public SessionStaffGroupBean() {
    }

    @EJB
    private IStaffBean staffBean;

    @EJB
    private IGroupBean groupBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get list of all the records in table.
     * @return List of staff-group.
     */
    public List<StaffGroup> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select staffGroup from StaffGroup staffGroup");
        List<StaffGroup> staffGroup = query.getResultList();
        em.close();
        return staffGroup;
    }

    /**
     * Allows to get staff-group connection by id.
     * @param id  The identifier of staff-group we are looking for.
     * @return    Staff-group with given id if found.
     */
    public StaffGroup get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (StaffGroup) em.createQuery("select s from StaffGroup s where s.id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    /**
     * Allows to delete staff-group connection with given id.
     * @param id    Record's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        StaffGroup staffGroup = get(id);
        if (staffGroup != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from StaffGroup s where s.id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to add new staff-group connection to database.
     * @param staffId           Id of the staff we want to connect with a group.
     * @param groupID           Id of the group where the staff works.
     * @param since             Start of period of time staff works in the group.
     * @param until             End of period of time staff works in the group.
     * @return                  True if add operation was successful. Otherwise false.
     */
    public boolean add(long staffId, long groupID, Date since, Date until)  {

        Staff staff = staffBean.get(staffId);
        if (staff != null){
            EntityManager em = emf.createEntityManager();
            StaffGroup staffGroup = new StaffGroup();
            staffGroup.setStaff(staff);
            staffGroup.setGroup(groupBean.get(groupID));
            if (since != null)
                staffGroup.setDate_of_beginning(since);
            else staffGroup.setDate_of_beginning(new Date());
            staffGroup.setDate_of_end(until);
            em.persist(staffGroup);
            em.close();
            return true;
        } return false;
    }

    /**
     * Allows to update staff-group connection with given id.
     * @param id                Record's id.
     * @param staffId           Id of the staff we want to connect with a group.
     * @param groupID           Id of the group where the staff works.
     * @param since             Start of period of time staff works in the group.
     * @param until             End of period of time staff works in the group.
     * @return                  True if add operation was successful. Otherwise false.
     */
    public boolean update(long id, long staffId, long groupID, Date since, Date until){
        Staff staff = staffBean.get(staffId);
        StaffGroup staffGroup = get(id);
        if (staff != null && staffGroup != null) {
            staffGroup.setStaff(staff);
            staffGroup.setGroup(groupBean.get(groupID));
            if (since != null)
                staffGroup.setDate_of_beginning(since);
            else staffGroup.setDate_of_beginning(new Date());
            staffGroup.setDate_of_end(until);
            return true;
        }
        return false;

    }
}
