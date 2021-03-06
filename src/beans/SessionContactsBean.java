package beans;

import models.Parent;
import models.ParentContacts;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.ParentContacts model}.
 */
@Stateless(name = "SessionContactsEJB")
public class SessionContactsBean implements IContactsBean{
    public SessionContactsBean() {
    }

    @EJB
    private IParentBean parentBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get list of all the records in database.
     * @return List of parent contacts.
     */
    public List<ParentContacts> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select contacts from ParentContacts contacts");
        List<ParentContacts> contacts = query.getResultList();
        em.close();
        return contacts;
    }

    /**
     * Allows to add new contacts to database.
     * @param parentId        Id of the person whose contacts we are going to add.
     * @param homeAddress     Information about home address.
     * @param job             Information about parent's job.
     * @param jobPhoneNumber  Information about parent's job phone number.
     * @param cellPhoneNumber Information about parent's cell phone number.
     * @return                True if add operation was successful. Otherwise false.
     */
    public boolean add(long parentId, String homeAddress, String job, String jobPhoneNumber, String cellPhoneNumber) {

        Parent parent = parentBean.get(parentId);
        if (parent != null){
            EntityManager em = emf.createEntityManager();
            ParentContacts contacts = new ParentContacts();
            contacts.setParent(parent);
            contacts.setHomeAddress(homeAddress);
            contacts.setJob(job);
            contacts.setCellphoneNumber(cellPhoneNumber);
            contacts.setJobPhoneNumber(jobPhoneNumber);
            contacts.setDate_of_creating(new Date());
            em.persist(contacts);
            em.close();
            return true;
        } return false;
    }

    /**
     * Allows to delete contacts with given id.
     * @param id    record's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        ParentContacts contacts = get(id);
        if (contacts != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from ParentContacts contacts where contacts.contacts_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to get contacts by id.
     * @param id  The identifier of contacts record we are looking for.
     * @return    Contacts with given id if found.
     */
    public ParentContacts get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (ParentContacts) em.createQuery("select contacts from ParentContacts contacts where contacts.contacts_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    /**
     * Allows to update contacts with given id.
     * @param contacts_id     Record's identifier.
     * @param parent_id       New person's id.
     * @param homeAddress     Information about home address.
     * @param job             Information about parent's job.
     * @param jobPhoneNumber  Information about parent's job phone number.
     * @param cellPhoneNumber Information about parent's cell phone number.
     * @return                True if add operation was successful. Otherwise false.
     */
    public boolean update(long contacts_id, long parent_id, String homeAddress, String job, String jobPhoneNumber, String cellPhoneNumber){
        ParentContacts contacts = get(contacts_id);
        if (contacts != null) {
            contacts.setHomeAddress(homeAddress);
            contacts.setJob(job);
            contacts.setJobPhoneNumber(jobPhoneNumber);
            contacts.setCellphoneNumber(cellPhoneNumber);
            contacts.setParent(parentBean.get(parent_id));
            return true;
        }
        return false;

    }
}
