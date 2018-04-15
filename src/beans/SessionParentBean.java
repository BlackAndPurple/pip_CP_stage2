package beans;

import models.Parent;
import models.People;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.Parent model}.
 */
@Stateless(name = "SessionParentEJB")
public class SessionParentBean implements IParentBean{
    public SessionParentBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get list of all the records in database.
     * @return List of parents.
     */
    public List<Parent> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select parent from Parent parent");
        List<Parent> parents = query.getResultList();
        em.close();
        return parents;
    }
}
