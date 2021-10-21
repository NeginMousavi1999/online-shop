package service;

import dao.UserDao;
import model.person.User;

import java.sql.SQLException;
import java.util.List;

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

    public User findMentionedUser(String username, String password) throws SQLException {
        return userDao.findUser(username, password);
    }

    public int findCountOfItemsInUserCart(User user) throws SQLException {
        return cartService.findCountOfItemsByUserId(user.getId());
    }

    public CartService accessToCartService() {
        return cartService;
    }

    public List<User> returnAllUsers() throws SQLException {
        return userDao.readAll();
    }
}
