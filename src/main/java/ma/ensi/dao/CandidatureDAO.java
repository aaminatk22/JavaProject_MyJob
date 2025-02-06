package ma.ensi.dao;

import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {


    public void saveCandidature(Candidature candidature) throws SQLException {
        String query = "INSERT INTO candidature (id_annonce, id_utilisateur, date_soumission, statut) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, candidature.getIdAnnonce());
            ps.setInt(2, candidature.getIdUtilisateur());
            ps.setObject(3, candidature.getDateSoumission());
            ps.setString(4, candidature.getStatut());


            ps.executeUpdate();
        }
    }

    public List<Candidature> findByAnnonce(int idAnnonce) {
        List<Candidature> candidatures = new ArrayList<>();

        String sql = "SELECT * FROM candidature WHERE id_annonce = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idAnnonce);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Candidature candidature = new Candidature();
                candidature.setIdCandidature(resultSet.getInt("id_candidature"));
                candidature.setIdAnnonce(resultSet.getInt("id_annonce"));
                candidature.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                candidature.setDateSoumission(resultSet.getDate("date_soumission").toLocalDate());
                candidature.setStatut(resultSet.getString("statut"));

                candidatures.add(candidature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatures;
    }
}