package service;

import dao.ElectronicDeviceDao;
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

    public List<Product> returnAllProducts() throws SQLException {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add((Product) electronicDeviceDao.readAll("electronic_devices"));
        allProducts.add((Product) shoeDao.readAll("shoes"));
        allProducts.add((Product) readableItemDao.readAll("readable_items"));
        return allProducts;
    }
}
