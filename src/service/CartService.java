package service;

import dao.CartDao;

import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class CartService {
    CartDao cartDao = new CartDao();

    public CartService() throws SQLException, ClassNotFoundException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        return cartDao.findCountOfItemsByUserId(id);
    }
}
