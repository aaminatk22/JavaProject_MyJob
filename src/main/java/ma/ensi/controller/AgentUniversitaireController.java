package ma.ensi.controller;

import ma.ensi.model.AgentUniversitaire;
import ma.ensi.service.AgentUniversitaireService;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.*;

public class AgentUniversitaireController {
    private final AgentUniversitaireService agentUniversitaireService = new AgentUniversitaireService();

    // Add a new university agent
    public void addAgentUniversitaire(String nomUtilisateur, String email, String motDePasse, String nom, String prenom, String nomUniversite) {
        AgentUniversitaire agent = new AgentUniversitaire(nomUtilisateur, email, motDePasse, nom, prenom, nomUniversite);
        agentUniversitaireService.saveAgentUniversitaire(agent);
        out.println("Agent universitaire ajouté avec succès !");
    }

    // Get university agent by ID
    public void getAgentUniversitaireById(int id) {
        try {
            AgentUniversitaire agent = agentUniversitaireService.getAgentUniversitaireById(id);
            if (agent != null) {
                out.println("Agent universitaire trouvé : " + agent);
            } else out.println("Aucun agent universitaire trouvé avec l'ID : " + id);
        } catch (SQLException e) {
            err.println("Erreur lors de la récupération de l'agent universitaire : " + e.getMessage());
        }
    }

    // Get all university agents
    public void getAllAgentsUniversitaires() {
        try {
            List<AgentUniversitaire> agents = agentUniversitaireService.getAllAgentsUniversitaires();
            out.println("Liste des agents universitaires : ");
            agents.forEach(out::println);
        } catch (SQLException e) {
            err.println("Erreur lors de la récupération des agents universitaires : " + e.getMessage());
        }
    }

    // Update university agent information
    public void updateAgentUniversitaire(int id, String nomUtilisateur, String email, String motDePasse, String nom, String prenom, String nomUniversite) {
        AgentUniversitaire agent = new AgentUniversitaire(id, nomUtilisateur, email, motDePasse, nom, prenom, nomUniversite);
        try {
            agentUniversitaireService.updateAgentUniversitaire(agent);
            out.println("Agent universitaire mis à jour avec succès !");
        } catch (SQLException e) {
            err.println("Erreur lors de la mise à jour de l'agent universitaire : " + e.getMessage());
        }
    }

    // Delete a university agent by ID
    public void deleteAgentUniversitaire(int id) {
        agentUniversitaireService.deleteAgentUniversitaire(id);
        out.println("Agent universitaire supprimé avec succès !");
    }
}
