package dao;

import model.products.Shoe;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ShoeDao extends ProductsDao {
    public ShoeDao() throws ClassNotFoundException, SQLException {
    }

    public void create(Shoe shoe) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`shoes` (`cost`, `count`, `size`, `color`, `type`)" +
                    " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, shoe.getCost());
            statement.setDouble(2, shoe.getCount());
            statement.setInt(3, shoe.getSizeOfShoe());
            statement.setString(4, shoe.getColor());
            statement.setString(5, shoe.getTypeOfShoe().toString());
            statement.executeUpdate();
        }
    }
}
