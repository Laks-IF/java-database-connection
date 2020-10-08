package proj;

import dao.ConnectionFactory;
import dao.ConnectionConfig;
import dao.UsersDao;

import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Proj {

    public static void main(String[] args) {
        // Configuração do banco de dados remoto online
        // Depois configuro um localmente
        // https://remotemysql.com/
        ConnectionConfig config = new ConnectionConfig(
                "remotemysql.com",
                "xoMuXbAysR",
                "L3XulXHzPC",
                "3306",
                "xoMuXbAysR"
        );

        try {
            Connection connection = ConnectionFactory.getConnection(config);
            Statement statement = ConnectionFactory.getStatement(connection);

            System.out.println("Successful connected to MySQL Database");

            System.out.println("\n");
            
            UsersDao usersDao = new UsersDao(statement);

            User userFetched = usersDao.getUser(1);

            System.out.println("Exemplo de busca de usuário:");
            System.out.println(userFetched);

            System.out.println("\n");
            
            System.out.println("Exemplo de inserção de usuário:");
            User demoUser = new User("Clodosvaldo", 56);
            usersDao.createUser(demoUser);
            System.out.println("Usuário inserido: " + demoUser);
            
            System.out.println("\n");

            System.out.println("Exemplo de atualização de usuário");
            usersDao.updateUser(1, new User("Paulo", 29));
            System.out.println("Atualização concluída");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Exception" + e.toString());
        }
    }

}
