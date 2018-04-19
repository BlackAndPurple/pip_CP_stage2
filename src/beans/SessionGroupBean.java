package beans;

import models.Group;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

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

    /**
     * Allows to get list of all the records in table.
     * @return List of groups.
     */
    public List<Group> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select group from Group group");
        List<Group> groups = query.getResultList();
        em.close();
        return groups;
    }

    /**
     * Allows to add new group account to database.
     * @param groupName       Name of the group.
     * @param groupType       Type of the group(age restrictions).
     * @return                True if add operation was successful. Otherwise false.
     */
    public boolean add(String groupName, String groupType)  {

        try{
            EntityManager em = emf.createEntityManager();
            Group group = new Group();
            group.setName(groupName);
            group.setTypeOfGroup(groupType);
            em.persist(group);
            em.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
