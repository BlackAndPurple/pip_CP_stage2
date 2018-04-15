package beans;

import models.People;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Stateless(name = "SessionPeopleBeanEJB")
public class SessionPeopleBean implements IPeopleBean{
    public SessionPeopleBean() {
    }

//    @PersistenceContext(unitName = "PU")
//    private EntityManager em;

//    @PersistenceContext(unitName = "PU", type = PersistenceContextType.EXTENDED)
//    private EntityManager entityManager;

    @PersistenceUnit(unitName = "PU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    //private EntityManager em = emf.createEntityManager();

    // add person to db
//    public People add(People person){
//        EntityManager em = emf.createEntityManager();
//        return em.merge(person);
//        //need to close em
//    }

    // get person by id
    public People get(long id){
        EntityManager em = emf.createEntityManager();
        try {
            return (People)em.createQuery("select people from People people where people.person_id="+id).getSingleResult();
        }catch (Exception e){
            return null;
        }
        //need to close em
    }

    // update person
    // if there's no such person,
    // we add it as the new one
    public void update(People person){
       // add(person);
    }

    // delete person by id
    public boolean delete(long id){

        People person = get(id);
        if (person != null){
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("delete from People people where people.person_id="+id);
            query.executeUpdate();
            em.close();
            return true;
        }else return false;

    }

    //get all people
    public List<People> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select people from People people");
        List<People> people = query.getResultList();
        em.close();
        return people;
    }


    //add new person
    public void add(String name, String middleName, String surname, boolean sex, Date dateOfBirth) {
        EntityManager em = emf.createEntityManager();
        People person = new People();
        person.setPerson_id(0);
        person.setName(name);
        person.setMiddleName(middleName);
        person.setSurname(surname);
        person.setSex(sex);
        person.setDate_of_birth(dateOfBirth);
        em.persist(person);
        em.close();
    }


}
