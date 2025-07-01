package lk.rajaguru.web.app.bean;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.rajaguru.web.app.core.model.User;
import lk.rajaguru.web.app.core.model.UserType;
import lk.rajaguru.web.app.core.service.UserService;

import java.util.List;

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
        List<User> users = em.createNamedQuery("User.findUserByEmail", User.class).setParameter("email", email).getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @RolesAllowed({"USER","ADMIN","SUPER_ADMIN"})
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @RolesAllowed({"USER","ADMIN","SUPER_ADMIN"})
    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public boolean validate(String email, String password) {
        User user = null;
        if ((user = getUserByEmail(email)) != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
