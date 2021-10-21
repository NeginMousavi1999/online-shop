package dao;

import model.enums.UserRole;
import model.person.Address;
import model.person.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                new Address(resultSet.getString(4)), UserRole.valueOf(resultSet.getString(5)));
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

    public User findUser(String username, String password) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUserAndReturn(resultSet); //reusing code :)
            }
        }
        return null;
    }

    public List<User> readAll() throws SQLException {
        List<User> users = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM users;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(createUserAndReturn(resultSet));
            }
        }
        return users;

    }
}
