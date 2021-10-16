package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Negin Mousavi
 */
public class UserDao extends BaseDao {
    public UserDao() throws ClassNotFoundException, SQLException {
    }

    public User findByUsername(String username) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserAndReturn(resultSet);
            }
        }
        return null;
    }

    public User createUserAndReturn(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString(2), resultSet.getString(3));
    }

    public void create(User user) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO `shop`.`users` (`username`, `password`) VALUES (?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        }
    }

    public boolean isPassCorrect(User user) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where username=? and password=?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        return false;
    }
}
