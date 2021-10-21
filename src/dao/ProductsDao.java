package dao;

import model.enums.BrandOfDevice;
import model.enums.TypeOfReadableItem;
import model.enums.TypeOfShoe;
import model.products.ElectronicDevice;
import model.products.Product;
import model.products.ReadableItem;
import model.products.Shoe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public abstract class ProductsDao extends BaseDao {
    public ProductsDao() throws ClassNotFoundException, SQLException {
    }

    public List<Product> readAll(String tableName) throws SQLException {
        List<Product> products = new ArrayList<>();
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s ;", tableName));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(createAndReturn(resultSet, tableName));
            }
        }
        return products;
    }

    public Product createAndReturn(ResultSet resultSet, String tableName) throws SQLException {
        switch (tableName) {
            case "electronic_devices":
                return new ElectronicDevice(resultSet.getInt(1), resultSet.getInt(3), resultSet.getDouble(2),
                        BrandOfDevice.valueOf(resultSet.getString(4)));

            case "shoes":
                return new Shoe(resultSet.getInt(1), resultSet.getInt(3), resultSet.getDouble(2), resultSet.getInt(4),
                        resultSet.getString(5), TypeOfShoe.valueOf(resultSet.getString(6)));

            case "readable_items":
                return new ReadableItem(resultSet.getInt(1), resultSet.getInt(3), resultSet.getDouble(2), resultSet.getInt(4),
                        TypeOfReadableItem.valueOf(resultSet.getString(5)));
        }
        return null;
    }

    public void reduceTheCountOfProduct(Product product, int countToReduce) throws SQLException {
        if (connection != null) {
            String sql = String.format("UPDATE %s SET count = count - %o WHERE id = ?;", product.getTypeOfProducts().toString().toLowerCase(),
                    countToReduce);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
    }

    public void increaseTheCountOfProduct(Product product, int countToIncrease) throws SQLException {
        if (connection != null) {
            String sql = String.format("UPDATE %s SET count = count + %o WHERE id = ?;", product.getTypeOfProducts().toString().toLowerCase(),
                    countToIncrease);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        }
    }

    public Product findById(String tableName, int id) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE id=?;", tableName));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return createAndReturn(resultSet, tableName.toLowerCase());
        }
        return null;
    }

    public void delete(String tableName, int id) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("DELETE FROM %s WHERE id=?;", tableName));
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}