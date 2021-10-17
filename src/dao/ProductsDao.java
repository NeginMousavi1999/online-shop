package dao;

import model.products.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
public abstract class ProductsDao extends BaseDao{
    public ProductsDao() throws ClassNotFoundException, SQLException {
    }

    public List<Product> readAll(String tableName) throws SQLException {
        List<Product> products = new ArrayList<>();
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s ;", tableName));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(createAndReturn(resultSet));
            }
        }
        return products;
    }

    public abstract Product createAndReturn(ResultSet resultSet) throws SQLException;

}
