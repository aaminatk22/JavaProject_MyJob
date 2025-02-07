package ma.ensi.service;

import ma.ensi.dao.adminagentuniversitairedao;
import ma.ensi.dao.ConnexionBDD;
import ma.ensi.model.adminAgentUniversitaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AdminAgentUniversitaireService {
    private final adminagentuniversitairedao agentDAO = new adminagentuniversitairedao();

    // Correction de la méthode update (retrait du static)
    public void updateAgentUniversitaire(adminAgentUniversitaire agent) {
        try {
            agentDAO.updateAgent(agent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAgentUniversitaire(adminAgentUniversitaire agent) throws SQLException {
        agentDAO.saveAgent(agent); // Appel du DAO pour enregistrer en base de données
    }



    public void deleteAgentUniversitaire(int id) {
        try {
            adminagentuniversitairedao.deleteAgentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    public adminAgentUniversitaire getAgentUniversitaireByNomUtilisateur(String nom_utilisateur) throws SQLException {
        return agentDAO.findByNomUtilisateur(nom_utilisateur); // Correction de l'appel
    }


    // Méthodes existantes à garder
    public void updateAgent(adminAgentUniversitaire agent) throws SQLException {
        adminagentuniversitairedao.updateAgent(agent);
    }

    public void deleteAgent(int id) throws SQLException {
        agentDAO.deleteAgentById(id);
    }
    public List<String> getAllUniversites() {
        List<String> universites = new ArrayList<>();
        String sql = "SELECT DISTINCT nom_universite FROM utilisateur";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                universites.add(rs.getString("nom_universite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universites;
    }

    public List<String> getAllEtablissements() {
        List<String> etablissements = new ArrayList<>();
        String sql = "SELECT DISTINCT NomEtab FROM utilisateur";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                etablissements.add(rs.getString("NomEtab"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etablissements;
    }



}
