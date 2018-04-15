package beans;

import models.Parent;
import models.People;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Bean implementing CRUD api for {@link models.Parent model}.
 */
@Stateless(name = "SessionParentEJB")
public class SessionParentBean implements IParentBean{
    public SessionParentBean() {
    }

    @EJB
    private IPeopleBean peopleBean;

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


    /**
     * Allows to add new parent to database.
     * @param personId       Id of the person we want to mark as a parent.
     * @return               True if add operation was successful. Otherwise false.
     */
    public boolean add(long personId) {

        People person = peopleBean.get(personId);
        if (person != null){
            EntityManager em = emf.createEntityManager();
            Parent parent = new Parent();
            parent.setPerson(person);
            parent.setPerson_id();
            em.persist(parent);
            em.close();
            return true;
        } return false;
    }

    /**
     * Allows to delete parent with given id.
     * @param id    parent's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        Parent parent = get(id);
        if (parent != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from Parent parent where parent.parent_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to get parent by id.
     * @param id  The identifier of parent we are looking for.
     * @return    Parent with given id if found.
     */
    public Parent get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (Parent) em.createQuery("select parent from Parent parent where parent.parent_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }
}
