package beans;

import models.Group;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@Stateless(name = "SessionGroupEJB")
public class SessionGroupBean implements IGroupBean{
    public SessionGroupBean() {
    }

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get group by id.
     * @param id  The identifier of group record we are looking for.
     * @return    group with given id if found.
     */
    public Group get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (Group) em.createQuery("select group from Group group where group.group_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
