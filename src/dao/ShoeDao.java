package dao;

import model.enums.TypeOfShoe;
import model.products.Shoe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ShoeDao extends ProductsDao {
    public ShoeDao() throws ClassNotFoundException, SQLException {
    }

    @Override
    public Shoe createAndReturn(ResultSet resultSet) throws SQLException { //TODO
        return new Shoe(resultSet.getInt(1), resultSet.getInt(3), resultSet.getDouble(2), resultSet.getInt(4),
                resultSet.getString(5), TypeOfShoe.valueOf(resultSet.getString(6)));
    }

    public void create(Shoe shoe) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`shoes` (`cost`, `count`, `size`, `color`, `type`)" +
                    " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, shoe.getCost());
            statement.setDouble(2, shoe.getCost());
            statement.setInt(3, shoe.getSizeOfShoe());
            statement.setString(4, shoe.getColor());
            statement.setString(5, shoe.getTypeOfShoe().toString());
            statement.executeUpdate();
        }
    }
}
