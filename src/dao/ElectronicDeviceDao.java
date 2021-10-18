package dao;

import model.products.ElectronicDevice;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class ElectronicDeviceDao extends ProductsDao {
    public ElectronicDeviceDao() throws ClassNotFoundException, SQLException {
    }

    public void create(ElectronicDevice electronicDevice) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`electronic_devices` (`cost`, `count`, `brand`) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, electronicDevice.getCost());
            statement.setDouble(2, electronicDevice.getCost());
            statement.setString(3, electronicDevice.getBrandOfDevice().toString());
            statement.executeUpdate();
        }
    }
}
