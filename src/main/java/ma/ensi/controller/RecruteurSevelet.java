package ma.ensi.controller;

import ma.ensi.model.Recruteur;
import ma.ensi.service.RecruteurService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recruteurs")
public class RecruteurServlet extends HttpServlet {
    private final RecruteurService recruteurService = new RecruteurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recruteur> recruteurs = recruteurService.getAllRecruteurs();
            request.setAttribute("recruteurs", recruteurs);
            request.getRequestDispatcher("/views/admin/recruteurs.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors du chargement des recruteurs.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomUtilisateur = request.getParameter("nomUtilisateur");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String entreprise = request.getParameter("entreprise");

            Recruteur recruteur = new Recruteur(0, nomUtilisateur, email, motDePasse, "recruteur", nom, prenom, entreprise, null);
            recruteurService.saveRecruteur(recruteur);

            response.sendRedirect("/recruteurs");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'ajout du recruteur.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
            String nomUtilisateur = request.getParameter("nomUtilisateur");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String entreprise = request.getParameter("entreprise");

            Recruteur recruteur = new Recruteur(idUtilisateur, nomUtilisateur, email, motDePasse, "recruteur", nom, prenom, entreprise, null);
            recruteurService.updateRecruteur(recruteur);

            response.sendRedirect("/recruteurs");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la modification du recruteur.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));
            recruteurService.deleteRecruteur(idUtilisateur);
            response.sendRedirect("/recruteurs");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la suppression du recruteur.");
        }
    }
}
