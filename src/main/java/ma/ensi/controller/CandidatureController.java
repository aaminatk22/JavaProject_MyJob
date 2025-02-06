package ma.ensi.controller;

import ma.ensi.model.Annonce;
import ma.ensi.model.Candidature;
import ma.ensi.model.Utilisateur;
import ma.ensi.service.AnnonceService;
import ma.ensi.service.CandidatureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/postuler")
public class CandidatureController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AnnonceService annonceService = new AnnonceService();
    private CandidatureService candidatureService = new CandidatureService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les paramètres du formulaire
        String idAnnonceParam = request.getParameter("idAnnonce");
        String idUtilisateurParam = request.getParameter("idUtilisateur");

// Validation des paramètres
        if (idAnnonceParam == null || idUtilisateurParam == null) {
            // Set an error message if the parameters are missing
            HttpSession session = request.getSession();
            session.setAttribute("message", "Les paramètres sont manquants.");
            response.sendRedirect("views/candidat/annonces.jsp"); // Redirect to correct page
            return;
        }

        try {
            // Parse the integers from the parameters
            int idAnnonce = Integer.parseInt(idAnnonceParam);
            int idUtilisateur = Integer.parseInt(idUtilisateurParam);

            HttpSession session = request.getSession();

            // Créer une nouvelle candidature
            Candidature candidature = new Candidature();
            candidature.setIdAnnonce(idAnnonce);
            candidature.setIdUtilisateur(idUtilisateur);
            candidature.setDateSoumission(LocalDate.now());
            candidature.setStatut("En attente"); // Statut par défaut

            // Sauvegarder la candidature
            candidatureService.saveCandidature(candidature);

            session.setAttribute("message", "Votre candidature a été envoyée avec succès !");
        } catch (NumberFormatException e) {
            // Handle invalid number format exception
            HttpSession session = request.getSession();
            session.setAttribute("message", "Erreur de format de numéro.");
        } catch (Exception e) {
            // Handle any other exception
            HttpSession session = request.getSession();
            session.setAttribute("message", "Erreur lors de la soumission de votre candidature.");
        }

// Rediriger vers la page annonces.jsp avec le message
        response.sendRedirect("views/candidat/annonces.jsp");

    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Ensure the user is logged in
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Retrieve user details
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        // Check if the user is a recruiter
        if (!"recruteur".equalsIgnoreCase(role)) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Get the 'idAnnonce' parameter from the request
        String idAnnonceParam = request.getParameter("idAnnonce");

        // Validate the 'idAnnonce' parameter
        if (idAnnonceParam == null || idAnnonceParam.isEmpty()) {
            session.setAttribute("message", "ID de l'annonce manquant.");
            response.sendRedirect(request.getContextPath() + "/views/recruteur/RecruiterSpace.jsp");
            return;
        }

        try {
            // Parse the 'idAnnonce' parameter
            int idAnnonce = Integer.parseInt(idAnnonceParam);

            // Fetch candidatures for the specific announcement
            List<Candidature> candidatures = candidatureService.getCandidaturesByAnnonce(idAnnonce);

            // Set the candidatures list as a request attribute
            request.setAttribute("candidatures", candidatures);

            // Forward to the recruiter page
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Handle invalid 'idAnnonce' format
            session.setAttribute("message", "ID de l'annonce invalide.");
            response.sendRedirect(request.getContextPath() + "/views/recruteur/RecruiterSpace.jsp");
        } catch (Exception e) {
            // Handle any other exception
            session.setAttribute("message", "Erreur lors de la récupération des candidatures.");
            response.sendRedirect(request.getContextPath() + "/views/recruteur/RecruiterSpace.jsp");
        }
    }
}
