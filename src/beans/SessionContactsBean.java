package beans;

import models.Parent;
import models.ParentContacts;
import models.People;

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
     * Allows to add new parent to database.
     * @param parentId       Id of the person we want to mark as a parent.
     * @return               True if add operation was successful. Otherwise false.
     */
    public boolean add(long parentId, String homeAddress, String job, String jobPhoneNumber, String cellPhoneNumber) {

        Parent parent = parentBean.get(parentId);
        if (parent != null){
            EntityManager em = emf.createEntityManager();
            ParentContacts contacts = new ParentContacts();
            ///parent.setPerson(person);
            //parent.setPerson_id();
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

}
