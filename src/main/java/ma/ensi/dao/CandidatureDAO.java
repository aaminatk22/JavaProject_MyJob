package ma.ensi.dao;

import ma.ensi.model.Candidature;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
