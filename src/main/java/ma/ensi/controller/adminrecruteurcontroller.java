package ma.ensi.controller;

import ma.ensi.model.adminrecruteur;
import ma.ensi.dao.adminrecruteurdao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminrecruteurservlet")
class adminrecruteurcontroller extends HttpServlet {

    private adminrecruteurdao recruteurDao;

    @Override
    public void init() throws ServletException {
        recruteurDao = new adminrecruteurdao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            // Retrieve recruiter by ID and display the edit form
            int id = Integer.parseInt(request.getParameter("id"));
            adminrecruteur recruteur = recruteurDao.getRecruteurById(id);
            request.setAttribute("recruteur", recruteur);
            request.getRequestDispatcher("/editRecruteur.jsp").forward(request, response);
        } else {
            // Retrieve all recruiters
            List<adminrecruteur> recruteurs = recruteurDao.getAllRecruteurs(); // Correct method to fetch all recruiters
            request.setAttribute("adminrecruteurs", recruteurs);
            request.getRequestDispatcher("/listRecruteurs.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            // Edit recruiter
            int id = Integer.parseInt(request.getParameter("id"));
            String nom_utilisateur = request.getParameter("nom_utilisateur");
            String email = request.getParameter("email");
            String mot_de_passe = request.getParameter("mot_de_passe");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String entreprise = request.getParameter("entreprise");
            String poste = request.getParameter("poste");

            adminrecruteur recruteur = new adminrecruteur(id, nom_utilisateur, email, mot_de_passe, "recruteur", nom, prenom, entreprise, poste);
            boolean isUpdated = recruteurDao.updateRecruteur(recruteur);

            if (isUpdated) {
                response.sendRedirect("adminrecruteurservlet");
            } else {
                request.setAttribute("errorMessage", "Failed to update recruiter.");
                request.getRequestDispatcher("/editRecruteur.jsp").forward(request, response);
            }
        } else if ("delete".equals(action)) {
            // Delete recruiter
            int id = Integer.parseInt(request.getParameter("id"));
            boolean isDeleted = recruteurDao.deleteRecruteur(id);

            if (isDeleted) {
                response.sendRedirect("adminrecruteurservlet");
            } else {
                request.setAttribute("errorMessage", "Failed to delete recruiter.");
                request.getRequestDispatcher("/listRecruteurs.jsp").forward(request, response);
            }
        }
    }
}
