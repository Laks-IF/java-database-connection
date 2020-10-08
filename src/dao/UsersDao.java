/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import model.User;

/**
 *
 * @author Novo
 */
public class UsersDao {

    private final Statement statement;

    public UsersDao(Statement statement) {
        this.statement = statement;
    }

    public User getUser(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = " + id;

        ResultSet result = statement.executeQuery(sql);

        String userName;
        int userAge;
        int userId;

        while (result.next()) {
            userName = result.getString("name");
            userAge = result.getInt("age");
            userId = result.getInt("id");

            return new User(userName, userAge, userId);
        }

        return null;
    }

    public void updateUser(int id, User newUser) throws SQLException {
        String sql = "UPDATE users " + "\n"
                + "SET name = '"
                + newUser.getName()
                + "', age = '"
                + newUser.getAge() + "'\n"
                + "WHERE id = '" + id + "'";

        statement.executeUpdate(sql);
    }

    public void createUser(User newUser) throws SQLException {
        String sql = "INSERT INTO users (name, age, id) VALUES ('"
                + newUser.getName() + "', '"
                + newUser.getAge() + "', " + randomInt(500, 20000)
                + ")";

        statement.executeUpdate(sql);
    }

    private int randomInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
