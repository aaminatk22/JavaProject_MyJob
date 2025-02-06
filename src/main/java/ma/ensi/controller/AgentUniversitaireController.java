package ma.ensi.controller;

import ma.ensi.service.AgentUniversitaireService;
import ma.ensi.model.AgentUniversitaire;

import java.sql.SQLException;
import java.util.List;

import static java.lang.System.*;

public class AgentUniversitaireController {
    private final AgentUniversitaireService agentUniversitaireService = new AgentUniversitaireService();

    // Ajout d'un agent universitaire
    public void addAgentUniversitaire(String nom_utilisateur, String email, String mot_de_passe,
                                      String nom, String prenom, String nom_universite , String nom_etab , String type_etab) {
        AgentUniversitaire agent = new AgentUniversitaire(nom_utilisateur, email, mot_de_passe, nom, prenom, nom_universite , nom_etab , type_etab);
        agentUniversitaireService.saveAgentUniversitaire(agent);
        out.println("Agent universitaire ajouté avec succès !");
    }

    // Récupération d'un agent par ID (version corrigée)
    public void getAgentUniversitaireById(int id_utilisateur) {
        AgentUniversitaire agent = agentUniversitaireService.getAgentUniversitaireById(id_utilisateur);
        if (agent != null) {
            out.println("Agent universitaire trouvé : " + agent);
        } else {
            out.println("Aucun agent universitaire trouvé avec l'ID : " + id_utilisateur);
        }
    }

    // Récupération de tous les agents (version corrigée)
    public void getAllAgentsUniversitaires() {
        List<AgentUniversitaire> agents = agentUniversitaireService.getAllAgentsUniversitaires();
        out.println("Liste des agents universitaires : ");
        agents.forEach(out::println);
    }

    // Mise à jour d'un agent (version corrigée)
    // Remplacez setId() par setIdUtilisateur() hérité de Utilisateur
    public void updateAgentUniversitaire(int id_utilisateur, String nom_utilisateur, String email,
                                         String mot_de_passe, String nom, String prenom,
                                         String nom_universite ,String nom_etab , String type_etab) {
        AgentUniversitaire agent = new AgentUniversitaire(
                nom_utilisateur,
                email,
                mot_de_passe,
                nom,
                prenom,
                nom_universite,
                nom_etab,
                type_etab
        );
        agent.setId_utilisateur(id_utilisateur); // Correction ici
        agentUniversitaireService.updateAgentUniversitaire(agent);
        out.println("Agent universitaire mis à jour avec succès !");
    }

    // Suppression d'un agent
    public void deleteAgentUniversitaire(int id_utilisateur) {
        agentUniversitaireService.deleteAgentUniversitaire(id_utilisateur);
        out.println("Agent universitaire supprimé avec succès !");
    }
}