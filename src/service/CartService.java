package service;

import dao.ProductsSoldDao;

import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class CartService {
    ProductsSoldDao productsSoldDao = new ProductsSoldDao();

    public CartService() throws SQLException, ClassNotFoundException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        return productsSoldDao.findCountOfItemsByUserId(id);
    }
}
