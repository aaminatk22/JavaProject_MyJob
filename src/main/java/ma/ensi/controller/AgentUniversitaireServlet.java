package ma.ensi.controller;

import ma.ensi.service.AgentUniversitaireService;
import ma.ensi.model.AgentUniversitaire;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class AgentUniversitaireServlet extends HttpServlet {
    private final AgentUniversitaireService agentService = new AgentUniversitaireService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        RequestDispatcher dispatcher = null;

        try {
            if ("getAll".equals(action)) {
                // 1. Récupérer la liste des agents
                List<AgentUniversitaire> agents = agentService.getAllAgentsUniversitaires();
                request.setAttribute("agents", agents);

                // 2. Récupérer les universités et établissements
                List<String> universites = agentService.getAllUniversites();
                List<String> etablissements = agentService.getAllEtablissements();
                request.setAttribute("universites", universites);
                request.setAttribute("etablissements", etablissements);

                // 3. Redirection vers ListeDesUniversite.jsp
                dispatcher = request.getRequestDispatcher("/views/admin/ListeDesUniversite.jsp");

            } else if ("getById".equals(action)) {
                // 1. Récupérer un agent par ID
                int id = Integer.parseInt(request.getParameter("id"));
                AgentUniversitaire agent = agentService.getAgentUniversitaireById(id);
                request.setAttribute("agent", agent);

                // 2. Redirection vers ListeDesAdminUni.jsp
                dispatcher = request.getRequestDispatcher("/views/admin/ListeDesAdminUni.jsp");

            } else {
                // Action non reconnue
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non supportée");
                return;
            }

            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            log("ID invalide : " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Format ID incorrect");
        } catch (Exception e) {
            log("Erreur serveur : " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("save".equals(action)) {
                // 1. Récupérer les données du formulaire
                String nom_utilisateur = request.getParameter("nom_utilisateur");
                String email = request.getParameter("email");
                String mot_de_passe = request.getParameter("mot_de_passe");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String nom_universite = request.getParameter("nom_universite");
                String nom_etab = request.getParameter("universite");
                String type_etab = request.getParameter("secteur");

                // 2. Créer et sauvegarder l'agent
                AgentUniversitaire agent = new AgentUniversitaire(
                        nom_utilisateur, email, mot_de_passe, nom, prenom, nom_universite, nom_etab, type_etab
                );
                agentService.saveAgentUniversitaire(agent);

                // 3. Redirection après sauvegarde
                response.sendRedirect(request.getContextPath() + "/AgentUniversitaireServlet?action=getAll");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non supportée");
            }

        } catch (Exception e) {
            log("Erreur interne : " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur interne");
        }
    }
}
