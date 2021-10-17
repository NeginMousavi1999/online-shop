package service;

import dao.ElectronicDeviceDao;
import dao.ProductsDao;
import dao.ReadableItemDao;
import dao.ShoeDao;
import model.products.Product;

import java.sql.ResultSet;
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
        List<List<Product>> allProducts = new ArrayList<>(); //خودم خیلی حال کردم با این ^_^
        allProducts.add(electronicDeviceDao.readAll("electronic_devices"));
        allProducts.add(shoeDao.readAll("shoes"));
        allProducts.add(readableItemDao.readAll("readable_items"));
        return allProducts;
    }


    public void reduceTheCountOfProduct(Product product, int count) throws SQLException, ClassNotFoundException {
        ProductsDao productsDao = new ProductsDao() {
            @Override
            public Product createAndReturn(ResultSet resultSet) throws SQLException {
                return null;
            }//TODO rahe behtar?
        }.reduceTheCountOfProduct(product, count);
    }
}
