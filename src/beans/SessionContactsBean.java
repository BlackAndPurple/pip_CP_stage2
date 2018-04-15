package beans;

import models.ParentContacts;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.ParentContacts model}.
 */
@Stateless(name = "SessionContactsEJB")
public class SessionContactsBean implements IContactsBean{
    public SessionContactsBean() {
    }

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

}
