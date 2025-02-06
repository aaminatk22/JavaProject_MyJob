package ma.ensi.dao;

import ma.ensi.model.Candidature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ma.ensi.dao.ConnexionBDD;

public class CandidatureDAO {
    public void saveCandidature(Candidature candidature) throws SQLException {
        String query = "INSERT INTO candidature (id_annonce, id_utilisateur, date_soumission, statut, message_candidat) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, candidature.getIdAnnonce());
            ps.setInt(2, candidature.getIdUtilisateur());
            ps.setObject(3, candidature.getDateSoumission());
            ps.setString(4, candidature.getStatut());
            ps.setString(5, candidature.getMessageCandidat());

            ps.executeUpdate();
        }
    }


        // Compter le nombre total de candidatures pour un recruteur
        public int countTotalCandidaturesByRecruiter(int idUtilisateur) {
            int count = 0;
            try (Connection connection = ConnexionBDD.getConnection()) {
                String sql = "SELECT COUNT(*) AS total FROM candidature c " +
                        "JOIN annonce a ON c.id_annonce = a.id_annonce " +
                        "WHERE a.id_utilisateur = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUtilisateur);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = resultSet.getInt("total");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return count;
        }

        // Compter le nombre de candidatures acceptées
        public int countAcceptedCandidaturesByRecruiter(int idUtilisateur) {
            int count = 0;
            try (Connection connection = ConnexionBDD.getConnection()) {
                String sql = "SELECT COUNT(*) AS total FROM candidature c " +
                        "JOIN annonce a ON c.id_annonce = a.id_annonce " +
                        "WHERE a.id_utilisateur = ? AND c.status = 'accepted'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUtilisateur);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = resultSet.getInt("total");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return count;
        }

        // Compter le nombre de candidatures rejetées
        public int countRejectedCandidaturesByRecruiter(int idUtilisateur) {
            int count = 0;
            try (Connection connection = ConnexionBDD.getConnection()) {
                String sql = "SELECT COUNT(*) AS total FROM candidature c " +
                        "JOIN annonce a ON c.id_annonce = a.id_annonce " +
                        "WHERE a.id_utilisateur = ? AND c.status = 'rejected'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, idUtilisateur);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = resultSet.getInt("total");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return count;
        }
    }


