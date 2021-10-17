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

    public List<Object> returnAllProducts() throws SQLException {
        List<Object> allProducts = new ArrayList<>();
        allProducts.add(electronicDeviceDao.readAll("electronic_devices"));
        allProducts.add(shoeDao.readAll("shoes"));
        allProducts.add(readableItemDao.readAll("readable_items"));
        return allProducts;
    }
}
