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

@WebServlet("/RecruteurServlet")
public class RecruteurServlet extends HttpServlet {
    private RecruteurService recruteurService;

    @Override
    public void init() {
        recruteurService = new RecruteurService();
    }

    // Récupérer la liste des recruteurs
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recruteur> recruteurs = recruteurService.getAllRecruteurs();
            request.setAttribute("recruteurs", recruteurs);
            request.getRequestDispatcher("views/admin/gestionRecruteur.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des recruteurs", e);
        }
    }

    // Ajouter un recruteur
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nomUtilisateur = request.getParameter("nomUtilisateur");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String entreprise = request.getParameter("entreprise");

            Recruteur recruteur = new Recruteur(0, nomUtilisateur, email, motDePasse, nom, prenom, entreprise);
            recruteurService.saveRecruteur(recruteur);

            response.sendRedirect("RecruteurServlet");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'ajout du recruteur", e);
        }
    }

    // Modifier un recruteur
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nomUtilisateur = request.getParameter("nomUtilisateur");
            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String entreprise = request.getParameter("entreprise");

            Recruteur recruteur = new Recruteur(id, nomUtilisateur, email, motDePasse, nom, prenom, entreprise);
            recruteurService.updateRecruteur(recruteur);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la mise à jour du recruteur", e);
        }
    }

    // Supprimer un recruteur
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            recruteurService.deleteRecruteur(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la suppression du recruteur", e);
        }
    }
}
