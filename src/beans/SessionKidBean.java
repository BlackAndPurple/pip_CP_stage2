package beans;

import models.Kid;
import models.Parent;
import models.ParentContacts;
import models.People;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.Kid model}.
 */
@Stateless(name = "SessionKidEJB")
public class SessionKidBean implements IKidBean{
    public SessionKidBean() {
    }

    @EJB
    private IParentBean parentBean;

    @EJB
    private IPeopleBean peopleBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get list of all the records in database.
     * @return List of kids.
     */
    public List<Kid> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select kid from Kid kid");
        List<Kid> kids = query.getResultList();
        em.close();
        return kids;
    }

    /**
     * Allows to add new kid to database.
     * @param person_id       Id of the person who we want to mark as a kid.
     * @param parent1_id      Id of this kid's parent1.
     * @param parent1_id      Id of this kid's parent2.
     * @return                True if add operation was successful. Otherwise false.
     */
    public boolean add(long person_id, long parent1_id, long parent2_id)  {

        Parent parent1 = parentBean.get(parent1_id);
        Parent parent2 = null;
        if (parent2_id != -1){
            parent2 = parentBean.get(parent2_id);
            if (parent2 == null)
                return  false;
        }
        People personKid = peopleBean.get(person_id);
        if (parent1 != null  && personKid != null){
            EntityManager em = emf.createEntityManager();
            Kid kid = new Kid();
            kid.setPerson(personKid);
            kid.setParent1(parent1);
            kid.setParent2(parent2);
            em.persist(kid);
            em.close();
            return true;
        } return false;
    }

    /**
     * Allows to get kid by id.
     * @param id  The identifier of kid record we are looking for.
     * @return    Kid with given id if found.
     */
    public Kid get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (Kid) em.createQuery("select kid from Kid kid where kid.kid_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    /**
     * Allows to delete kid with given id.
     * @param id    record's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        Kid kid = get(id);
        if (kid != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from Kid kid where kid.kid_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to update kid with given id.
     * @param kid_id      Record's identifier.
     * @param parent1_id  New id of this kid's parent1.
     * @param parent2_id  New id of this kid's parent2.
     * @return            True if add operation was successful. Otherwise false.
     */
    public boolean update(long kid_id, long person_id, long parent1_id, long parent2_id){
        Kid kid = get(kid_id);
        if (kid != null) {
            kid.setPerson(peopleBean.get(person_id));
            kid.setParent1(parentBean.get(parent1_id));
            kid.setParent2(parentBean.get(parent2_id));
            return true;
        }
        return false;

    }
}
