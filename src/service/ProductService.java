package service;

import dao.ElectronicDeviceDao;
import dao.ProductsDao;
import dao.ReadableItemDao;
import dao.ShoeDao;
import model.products.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public class ProductService {
    ReadableItemDao readableItemDao = new ReadableItemDao();
    ShoeDao shoeDao = new ShoeDao();
    ElectronicDeviceDao electronicDeviceDao = new ElectronicDeviceDao();

    public ProductService() throws SQLException, ClassNotFoundException {
    }

    public List<List<Product>> returnAllProducts() throws SQLException {
        List<List<Product>> allProducts = new ArrayList<>();
        allProducts.add(electronicDeviceDao.readAll("electronic_devices"));
        allProducts.add(shoeDao.readAll("shoes"));
        allProducts.add(readableItemDao.readAll("readable_items"));
        return allProducts;
    }


    public void reduceTheCountOfProduct(Product product, int count) throws SQLException, ClassNotFoundException {
        returnProductsDao().reduceTheCountOfProduct(product, count);
    }

    public void increaseTheCountOfProduct(Product product, int count) throws SQLException, ClassNotFoundException {
        returnProductsDao().increaseTheCountOfProduct(product, count);
    }

    public Product findProductById(String tableName, int id) throws SQLException, ClassNotFoundException {
        return returnProductsDao().findById(tableName, id);
    }

    public ProductsDao returnProductsDao() throws SQLException, ClassNotFoundException {
        return new ProductsDao() {
        };
    }
}
