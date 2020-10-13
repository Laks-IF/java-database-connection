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
import shared.Utils;

/**
 *
 * @author Novo
 */
public class UsersDao {

    private final Statement statement;

    public UsersDao(Statement statement) {
        this.statement = statement;
    }

    private boolean userAlreadyExists(int id) throws SQLException {
        User userToDelete = this.getUser(id);

        return userToDelete != null;
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

    public void updateUser(int id, User newUser) throws SQLException, Exception {
        if (!this.userAlreadyExists(id)) {
            throw new Exception("Este usuário: " + id + ", não existe");
        }

        String sql = "UPDATE users " + "\n"
                + "SET name = '"
                + newUser.getName()
                + "', age = '"
                + newUser.getAge() + "'\n"
                + "WHERE id = '" + id + "'";

        statement.executeUpdate(sql);
    }

    public void createUser(User newUser, boolean useImplicityId) throws SQLException {
        int id = useImplicityId ? newUser.getId() : Utils.randomInt(500, 20000);

        String sql = "INSERT INTO users (name, age, id) VALUES ('"
                + newUser.getName() + "', '"
                + newUser.getAge() + "', " + id
                + ")";

        statement.executeUpdate(sql);
    }

    public void deleteUser(int id) throws SQLException, Exception {
        if (!this.userAlreadyExists(id)) {
            throw new Exception("Este usuário: " + id + ", não existe");
        }

        String sql = "DELETE FROM users" + " WHERE id = " + id;

        statement.executeUpdate(sql);
    }

}
