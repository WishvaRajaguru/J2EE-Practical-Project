package lk.rajaguru.web.app.bean;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.service.UserService;

@Stateless
public class UserSessionBean implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        return em.createNamedQuery("User.findUserByEmail", User.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }
}
