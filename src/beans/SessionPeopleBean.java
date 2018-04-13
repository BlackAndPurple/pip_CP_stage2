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

    @PersistenceUnit
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private EntityManager em = emf.createEntityManager();

    // add person to db
    public People add(People person){
        return em.merge(person);
    }

    // get person by id
    public People get(long id){
        return em.find(People.class, id);
    }

    // update person
    // if there's no such person,
    // we add it as the new one
    public void update(People person){
        add(person);
    }

    // delete person by id
    public boolean delete(long id){
        if (get(id) != null){
            em.remove(get(id));
            return true;
        }else return false;

    }

    //get all people
    public List<People> getAll(){
        //TypedQuery<People> namedQuery = em.createNamedQuery("People.getAll", People.class);
        Query query = em.createQuery("Select people from People people");
        return query.getResultList();
    }

    public void add(String name, String middleName, String surname, boolean sex, Date dateOfBirth) {
        EntityManager em = emf.createEntityManager();
        People person = new People();
        person.setName(name);
        person.setMiddleName(middleName);
        person.setSurname(surname);
        person.setSex(sex);
        person.setDate_of_birth(dateOfBirth);
        em.persist(person);
        em.close();
    }


}
