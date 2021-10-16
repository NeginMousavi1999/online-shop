package dao;

import java.sql.*;

/**
 * @author Negin Mousavi
 */
public abstract class BaseDao {
    protected Connection connection;

    public BaseDao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "123456");
    }
}