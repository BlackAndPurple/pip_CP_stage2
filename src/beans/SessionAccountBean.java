package beans;


import models.Group;
import models.Kid;
import models.KidAccount;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Bean implementing CRUD api for {@link models.KidAccount model}.
 */
@Stateless(name = "SessionAccountEJB")
public class SessionAccountBean implements IAccountBean{
    public SessionAccountBean() {
    }

    @EJB
    private IKidBean kidBean;

    @EJB
    private IGroupBean groupBean;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    /**
     * Allows to get list of all the records in database.
     * @return List of kid accounts.
     */
    public List<KidAccount> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select account from KidAccount account");
        List<KidAccount> accounts = query.getResultList();
        em.close();
        return accounts;
    }

    /**
     * Allows to add new kid account to database.
     * @param kid_id          Id of the kid whose account we want to create.
     * @param group_id        Id of the group this kid belongs to.
     * @param dateOfCreating  Start of the period the kid belongs to the group.
     * @param dateOfLeaving   End of the period the kid belongs to the group.
     * @return                True if add operation was successful. Otherwise false.
     */
    public boolean add(long kid_id, long group_id, Date dateOfCreating, Date dateOfLeaving)  {

        Kid kid = kidBean.get(kid_id);
        Group group = groupBean.get(group_id);

        if (kid != null && group != null){
            EntityManager em = emf.createEntityManager();
            KidAccount account = new KidAccount();
            account.setKid(kid);
            //kid.getKidAccounts().add()
            account.setGroup(group);
            if (dateOfCreating != null)
                account.setDate_of_creating(dateOfCreating);
            else account.setDate_of_creating(new Date());
            account.setDate_of_leaving(dateOfLeaving);
            em.persist(account);
            em.close();
            return true;
        }
        return false;
    }
}
