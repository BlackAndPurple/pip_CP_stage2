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

    /**
     * Allows to get account by id.
     * @param id  The identifier of account record we are looking for.
     * @return    Contacts with given id if found.
     */
    public KidAccount get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (KidAccount) em.createQuery("select account from KidAccount account where account.account_id="+id).getSingleResult();
        }catch (Exception e){
            em.close();
            return null;
        }
    }

    /**
     * Allows to delete kid account with given id.
     * @param id    record's identifier.
     * @return      True if delete operation was successful. Otherwise false.
     */
    public boolean delete(long id){

        KidAccount account = get(id);
        if (account != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from KidAccount account where account.account_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;
    }

    /**
     * Allows to update kid Account with given id.
     * @param accountId       Record's identifier.
     * @param kidId           New kid's id.
     * @param groupId         New kid's group id.
     * @param dateOfCreating  New start of the period the kid belongs to the group.
     * @param dateOfLeaving   New end of the period the kid belongs to the group.
     * @return                True if add operation was successful. Otherwise false.
     */
    public boolean update(long accountId, long kidId, long groupId, Date dateOfCreating, Date dateOfLeaving){
        KidAccount account = get(accountId);
        Kid kid = kidBean.get(kidId);
        Group group = groupBean.get(groupId);
        if (account != null && kid != null && group != null) {
            account.setKid(kid);
            account.setGroup(group);
            account.setDate_of_creating(dateOfCreating);
            account.setDate_of_leaving(dateOfLeaving);
            return true;
        }
        return false;

    }
}
