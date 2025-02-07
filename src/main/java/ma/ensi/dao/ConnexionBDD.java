package ma.ensi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/myjob?serverTimezone=UTC";
    private static final String USER = "root"; // Mets ton utilisateur MySQL
    private static final String PASSWORD = ""; // Mets ton mot de passe MySQL

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Correct pour MySQL
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion réussie !");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver MySQL non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion à la base de données !");
            e.printStackTrace();
        }
        return connection;
    }
}
