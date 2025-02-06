package ma.ensi.service;

import ma.ensi.dao.AgentUniversitaireDAO;
import ma.ensi.model.AgentUniversitaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentUniversitaireService {
    private final AgentUniversitaireDAO agentDAO = new AgentUniversitaireDAO();

    // Correction de la méthode update (retrait du static)
    public void updateAgentUniversitaire(AgentUniversitaire agent) {
        try {
            agentDAO.updateAgent(agent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAgentUniversitaire(AgentUniversitaire agent) {
        try {
            agentDAO.saveAgent(agent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAgentUniversitaire(int id) {
        try {
            agentDAO.deleteAgentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AgentUniversitaire getAgentUniversitaireById(int id) {
        try {
            return agentDAO.findAgentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null en cas d'erreur
        }
    }

    public List<AgentUniversitaire> getAllAgentsUniversitaires() {
        try {
            return agentDAO.findAllAgents();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retourne une liste vide en cas d'erreur
        }
    }

    // Méthodes existantes à garder
    public void updateAgent(AgentUniversitaire agent) throws SQLException {
        agentDAO.updateAgent(agent);
    }

    public void deleteAgent(int id) throws SQLException {
        agentDAO.deleteAgentById(id);
    }
    public List<String> getAllUniversites() {
        List<String> universites = new ArrayList<>();
        String sql = "SELECT DISTINCT nom_universite FROM agents_universitaires";

        DatabaseMetaData Database = null;
        try (Connection conn = Database.getConnection();
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
        String sql = "SELECT DISTINCT nom_etab FROM agents_universitaires";

        DatabaseMetaData Database = null;
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                etablissements.add(rs.getString("nom_etab"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etablissements;
    }


}
