package lk.rajaguru.web.app.core.service;

import jakarta.ejb.Remote;
import lk.rajaguru.web.app.core.model.User;

@Remote
public interface UserService {
    User getUser(long id);
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
