package ma.ensi.dao;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnexionBDD {
    public static void main(String[] args) {
        try {
            // Attempt to establish a connection
            Connection connection = ConnexionBDD.getConnection();
            if (connection != null) {
                System.out.println("Connection successful!");

                // Fetching annonces from the database
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM annonce")) {

                    System.out.println("Fetching annonces...");
                    while (resultSet.next()) {
                        int idAnnonce = resultSet.getInt("id_annonce");
                        String titre = resultSet.getString("titre");
                        String typeAnnonce = resultSet.getString("type_annonce");
                        String description = resultSet.getString("description");
                        String datePublication = resultSet.getString("date_publication");
                        int idUtilisateur = resultSet.getInt("id_utilisateur");

                        System.out.println("ID: " + idAnnonce);
                        System.out.println("Titre: " + titre);
                        System.out.println("Type: " + typeAnnonce);
                        System.out.println("Description: " + description);
                        System.out.println("Date: " + datePublication);
                        System.out.println("Utilisateur ID: " + idUtilisateur);
                        System.out.println("-------------");
                    }
                } catch (SQLException e) {
                    System.err.println("Error while fetching annonces: " + e.getMessage());
                }

                // Close the connection
                connection.close();
            } else {
                System.out.println("Connection failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/


import ma.ensi.model.Annonce;

import java.util.List;

public class TestAnnonceDAO {
    public static void main(String[] args) {
        AnnonceDAO annonceDAO = new AnnonceDAO();

        // Test getAllAnnonces()
        List<Annonce> annonces = annonceDAO.getAllAnnonces();
        for (Annonce annonce : annonces) {
            System.out.println("ID: " + annonce.getIdAnnonce());
            System.out.println("Titre: " + annonce.getTitre());
            System.out.println("Type: " + annonce.getTypeAnnonce());
            System.out.println("Description: " + annonce.getDescription());
            System.out.println("Date: " + annonce.getDatePublication());
            System.out.println("User ID: " + annonce.getIdUtilisateur());
        }
    }
}
