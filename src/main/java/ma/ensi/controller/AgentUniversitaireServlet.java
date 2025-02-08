package ma.ensi.controller.;

import ma.ensi.service.AgentUniversitaireService;
import ma.ensi.model.AgentUniversitaire;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AgentUniversitaireServlet extends HttpServlet {
    private final AgentUniversitaireService agentService = new AgentUniversitaireService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("getAll".equals(action)) {
                request.setAttribute("agents", agentService.getAllAgents());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agents.jsp");
                dispatcher.forward(request, response);
            } else if ("getById".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                AgentUniversitaire agent = agentService.getAgentById(id);
                request.setAttribute("agent", agent);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agentDetails.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("save".equals(action)) {
                String nomUtilisateur = request.getParameter("nomUtilisateur");
                String universite = request.getParameter("universite"); // Ajouté
                String motDePasse = request.getParameter("motDePasse");

                String email;
                AgentUniversitaire agent = new AgentUniversitaire(id, nomUtilisateur, email, motDePasse, nom, prenom, nomUniversite);
                agent.setNomUtilisateur(nomUtilisateur);
                agent.setUniversite(universite); // Ajouté
                agent.setMotDePasse(motDePasse);

                agentService.saveAgent(agent);
                response.sendRedirect("AgentUniversitaireServlet?action=getAll");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur avec la base de données.");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("getAll".equals(action)) {
                // Récupérer la liste des agents universitaires
                List<AgentUniversitaire> agents = agentService.getAllAgents();
                request.setAttribute("agents", agents);

                // Rediriger vers la JSP de la liste
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/listeAdminUni.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur de base de données.");
        }
    }

}
