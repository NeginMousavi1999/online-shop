package service;

import dao.CartDao;
import model.User;
import model.products.Product;

import java.sql.SQLException;

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
        reduceTheCountOfAvailableProduct(product);
    }

    public void reduceTheCountOfAvailableProduct(Product product) throws SQLException, ClassNotFoundException {
        productService.reduceTheCountOfProduct(product);
    }

    public void increaseTheCountOfAvailableProduct(Product product){

    }
}
