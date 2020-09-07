package proj;

import dao.ConnectionFactory;
import dao.ConnectionConfig;

import java.sql.Connection;
import java.sql.ResultSet;
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

            // Query de exemplo, é só um Foo Bar, não tem quase
            // nada nesse DB remoto, só tem um registro na tabela users:
            // nome: Douglas
            // idade: 25
            // id: 1
            // Mesmo banco que usei lá no outro trabalho
            String sql = "SELECT * FROM users";

            ResultSet result = statement.executeQuery(sql);

            // iterate through the java resultset
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");

                // Print the results
                System.out.format(
                        "ID: " + id + "\n"
                        + "Name: " + name + "\n"
                        + "age: " + age + "\n"
                );
                System.out.println("========================");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Exception" + e.toString());
        }
    }

}
