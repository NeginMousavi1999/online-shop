package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ProductsSoldDao extends BaseDao{
    public ProductsSoldDao() throws ClassNotFoundException, SQLException {
    }

    public int findCountOfItemsByUserId(int id) throws SQLException {
        int count = 0;
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products_sold where user_id_fk=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                count++;
        }
        return count;
    }
}
