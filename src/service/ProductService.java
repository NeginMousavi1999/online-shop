package service;

import dao.ElectronicDeviceDao;
import dao.ProductsDao;
import dao.ReadableItemDao;
import dao.ShoeDao;
import model.products.ElectronicDevice;
import model.products.Product;
import model.products.ReadableItem;
import model.products.Shoe;

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

        List<Product> products = new ArrayList<>();
        products.addAll(electronicDeviceDao.readAll("electronic_devices"));
        products.addAll(shoeDao.readAll("shoes"));
        products.addAll(readableItemDao.readAll("readable_items"));

        return products;
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

    public void addNewElectronicProduct(ElectronicDevice electronicDevice) throws SQLException {
        electronicDeviceDao.create(electronicDevice);
    }

    public void addNewReadableProduct(ReadableItem readableItem) throws SQLException {
        readableItemDao.create(readableItem);
    }

    public void addNewShoeProduct(Shoe shoe) throws SQLException {
        shoeDao.create(shoe);
    }

    public void removeElectronicDevice(ElectronicDevice electronicDevice) throws SQLException {
        electronicDeviceDao.delete("electronic_device", electronicDevice.getId());
    }

    public void removeReadableItem(ReadableItem readableItem) throws SQLException {
        readableItemDao.delete("readable_items", readableItem.getId());
    }

    public void removeShoe(Shoe shoe) throws SQLException {
        shoeDao.delete("shoes", shoe.getId());
    }
}
