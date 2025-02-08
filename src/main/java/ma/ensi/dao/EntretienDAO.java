package ma.ensi.dao;

import ma.ensi.model.Entretien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntretienDAO {

    public void saveEntretien(Entretien entretien) throws SQLException {
        String query = "INSERT INTO entretien (id_candidature, date_entretien, heure_entretien, lieu, statut) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, entretien.getIdCandidature());
            ps.setDate(2, Date.valueOf(entretien.getDateEntretien()));
            ps.setTime(3, Time.valueOf(entretien.getHeureEntretien()));
            ps.setString(4, entretien.getLieu());
            ps.setString(5, entretien.getStatut());
            ps.executeUpdate();
        }
    }

    public List<Entretien> getEntretiensByCandidatureId(int idCandidature) throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String query = "SELECT * FROM entretien WHERE id_candidature = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idCandidature);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entretien entretien = new Entretien();
                entretien.setIdEntretien(rs.getInt("id_entretien"));
                entretien.setIdCandidature(rs.getInt("id_candidature"));
                entretien.setDateEntretien(rs.getDate("date_entretien").toLocalDate());
                entretien.setHeureEntretien(rs.getTime("heure_entretien").toLocalTime());
                entretien.setLieu(rs.getString("lieu"));
                entretien.setStatut(rs.getString("statut"));
                entretiens.add(entretien);
            }
        }
        return entretiens;
    }
}
