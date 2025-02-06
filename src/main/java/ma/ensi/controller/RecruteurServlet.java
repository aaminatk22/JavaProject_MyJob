package ma.ensi.controller;

import ma.ensi.model.Recruteur;
import ma.ensi.service.RecruteurService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recruteurs-Servlet")
public class RecruteurServlet extends HttpServlet {
    private RecruteurService recruteurService;

    @Override
    public void init() {
        recruteurService = new RecruteurService();
    }

    // Récupérer la liste des recruteurs
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recruteur> recruteurs = recruteurService.getAllRecruteurs();
        request.setAttribute("recruteurs", recruteurs);
        request.getRequestDispatcher("/views/admin/gestionRecruteur.jsp").forward(request, response);


    }

    // Ajouter ou modifier un recruteur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_utilisateur = request.getParameter("idUtilisateur") != null ? Integer.parseInt(request.getParameter("idUtilisateur")) : 0;
        String nom_utilisateur = request.getParameter("nomUtilisateur");
        String email = request.getParameter("email");
        String mot_de_passe = request.getParameter("motDePasse");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String entreprise = request.getParameter("entreprise");

        Recruteur recruteur = new Recruteur(id_utilisateur, nom_utilisateur, email, mot_de_passe, "recruteur", nom, prenom);

        if (id_utilisateur == 0) {
            recruteurService.saveRecruteur(recruteur);
        } else {
            recruteurService.updateRecruteur(recruteur);
        }

        response.sendRedirect("/recruteurs");
    }

    // Supprimer un recruteur
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_utilisateur"));
        recruteurService.deleteRecruteur(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
