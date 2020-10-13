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
        
        // Como a ideia é criar as conexões, o CRUD e tudo mais
        // Não fiz muita coisa aqui na classe Main pra ilustrar bem
        // Mas é possível ver todas as alterações usando o PHPMyAdmin
        // desse banco de dados aqui. Esse BDD tá disponível nessa URL:
        // https://remotemysql.com/phpmyadmin/
        // Username: xoMuXbAysR
        // Senha: L3XulXHzPC
        // Pode usar os métodos da classe UsersDao a vontade:
        // .createUser(...) e conferir lá na URL. O mesmo
        // para os outros métodos, valeu

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
            usersDao.createUser(demoUser, false);
            System.out.println("Usuário inserido: " + demoUser);

            System.out.println("\n");

            System.out.println("Exemplo de atualização de usuário");
            usersDao.updateUser(1, new User("Paulo", 29));
            System.out.println("Atualização concluída");
            
            System.out.println("\n");

            System.out.println("Exemplo de remoção de usuário");
            usersDao.deleteUser(1);
            System.out.println("Remoção concluída");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Exception" + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tentativa de acessar um usuário com ID inválido");
        }
    }

}
