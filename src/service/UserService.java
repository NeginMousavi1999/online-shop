package service;

import dao.UserDao;
import model.User;

import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class UserService {
    UserDao userDao = new UserDao();

    public UserService() throws SQLException, ClassNotFoundException {
    }

    public User findUserByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }

    public void addNewUser(User user) throws SQLException {
        userDao.create(user);
    }
}
