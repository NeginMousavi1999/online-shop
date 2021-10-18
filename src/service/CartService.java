package service;

import dao.CartDao;
import model.Cart;
import model.User;
import model.products.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CartService {
    CartDao cartDao = new CartDao();
    ProductService productService = new ProductService();

    public CartService() throws SQLException, ClassNotFoundException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        return cartDao.findCountOfItemsByUserId(id);
    }

    public void addNewProductForThisUser(User user, Product product, int count) throws SQLException, ClassNotFoundException {
        cartDao.create(user, product, count);
        reduceTheCountOfAvailableProduct(product, count);
    }

    public void reduceTheCountOfAvailableProduct(Product product, int count) throws SQLException, ClassNotFoundException {
        productService.reduceTheCountOfProduct(product, count);
    }

    public void increaseTheCountOfAvailableProduct(Product product) {

    }

    public List<Cart> showUserNotCompletedCart(User user) throws SQLException {
        return cartDao.showNotCompletedCart(user);
    }
}
