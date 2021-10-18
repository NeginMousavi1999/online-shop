package dao;

import model.User;
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
        String sql;
        PreparedStatement statement;
        if (connection != null) {
            if (isThisOrderExistsForThisUser(user, product)) {
                sql = "UPDATE carts SET count=count+? WHERE user_id_fk=? AND product_id_fk=? AND product_type=? AND status=\"NOT_COMPLETED\";";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, count);
                statement.setInt(2, user.getId());
                statement.setInt(3, product.getId());
                statement.setString(4, product.getTypeOfProducts().toString());

            } else {
                sql = "INSERT INTO `shop`.`carts` (`user_id_fk`, `product_id_fk`, `product_type`, `count`, `cost`, `status`) " +
                        "VALUES (?, ?, ?, ?, ?, ?);";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, user.getId());
                statement.setInt(2, product.getId());
                statement.setString(3, product.getTypeOfProducts().toString());
                statement.setInt(4, count);
                statement.setDouble(5, product.getCost());
                statement.setString(6, "NOT_COMPLETED");
            }
            statement.executeUpdate();
        }
    }

    private boolean isThisOrderExistsForThisUser(User user, Product product) throws SQLException {
        if (connection != null) {
            String sql = "SELECT * FROM carts WHERE user_id_fk=? AND product_id_fk=? AND product_type=? AND status=\"NOT_COMPLETED\"";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setString(3, product.getTypeOfProducts().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
        }
        return false;
    }

    public User createCartAndReturnWithIdIn(ResultSet resultSet) throws SQLException {
        //TODO
//        return new Cart(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
//                resultSet.getString(4), CartStatus.valueOf(resultSet.getString(5)));
        return null;
    }
}
