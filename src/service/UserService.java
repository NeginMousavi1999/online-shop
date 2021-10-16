package service;

import dao.UserDao;
import model.User;

import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class UserService {
    UserDao userDao = new UserDao();
    CartService cartService = new CartService();

    public UserService() throws SQLException, ClassNotFoundException {
    }

    public User findUserByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }

    public void addNewUser(User user) throws SQLException {
        userDao.create(user);
    }

    public boolean isPassCorrect(User user) throws SQLException {
        return userDao.isPassCorrect(user);
    }

    public int findCountOfItemsInUserCart(User user) {
        return cartService.findCountOfItemsByUserId(user.getId());
    }

    public void addNewProductForThisUser(User user) throws SQLException {
        userDao.create(user);
    }
}
