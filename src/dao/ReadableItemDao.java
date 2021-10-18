package dao;

import model.products.ReadableItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ReadableItemDao extends ProductsDao {
    public ReadableItemDao() throws ClassNotFoundException, SQLException {
    }

    public void create(ReadableItem readableItem) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`readable_items` (`cost`, `count`, `count_of_pages`, `type`)" +
                    " VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, readableItem.getCost());
            statement.setDouble(2, readableItem.getCost());
            statement.setInt(3, readableItem.getCountOfPages());
            statement.setString(4, readableItem.getTypeOfReadableItem().toString());
            statement.executeUpdate();
        }
    }
}
