package dao;

import model.Address;
import model.Cart;
import model.User;
import model.enums.CartStatus;
import model.products.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class CartDao extends BaseDao {
    public CartDao() throws ClassNotFoundException, SQLException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        int count = 0;
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carts where user_id_fk=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                count++;
        }
        return count;
    }

    public void create(User user, Product product, int count) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`carts` (`user_id_fk`, `product_id_fk`, `product_type`, `count`, `cost`, `status`) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, product.getId());
            statement.setString(3, product.getTypeOfProducts().toString());
            statement.setInt(4, count);
            statement.setDouble(5, product.getCost());
            statement.setString(6, "NOT_COMPLETED");
            statement.executeUpdate();
        }
    }

    public User createCartAndReturnWithIdIn(ResultSet resultSet) throws SQLException {
        //TODO
//        return new Cart(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
//                resultSet.getString(4), CartStatus.valueOf(resultSet.getString(5)));
        return null;
    }
}
