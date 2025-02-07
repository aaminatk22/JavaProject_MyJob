package ma.ensi.controller;

import ma.ensi.dao.CandidatureDAO;
import ma.ensi.model.Annonce;
import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;
import ma.ensi.model.Utilisateur;
import ma.ensi.service.AnnonceService;
import ma.ensi.service.CandidatService;
import ma.ensi.service.CandidatureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/postuler")
public class CandidatureController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CandidatureService candidatureService = new CandidatureService();
    private final CandidatService candidatService = new CandidatService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les paramètres du formulaire
        String idAnnonceParam = request.getParameter("idAnnonce");
        String idUtilisateurParam = request.getParameter("idUtilisateur");
        String idCandidatureParam = request.getParameter("idCandidature");  // Nouveau paramètre pour mise à jour
        String statut = request.getParameter("statut");  // Nouveau paramètre pour mise à jour
        String action = request.getParameter("action");

        // Validation des paramètres pour une nouvelle candidature
        if (idAnnonceParam == null || idUtilisateurParam == null) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Les paramètres sont manquants.");
            response.sendRedirect("views/recruteur/viewApplications.jsp"); // Rediriger vers la page correcte
            return;
        }

        try {
            HttpSession session = request.getSession();

            // Si on a un ID de candidature et un statut, c'est une mise à jour de candidature (acceptation ou refus)
            if (idCandidatureParam != null && statut != null) {
                int idCandidature = Integer.parseInt(idCandidatureParam);

                Candidature candidature = candidatureService.getCandidatureById(idCandidature);
                if (candidature != null) {
                    candidature.setStatut(statut); // Mise à jour du statut
                    candidatureService.updateCandidature(candidature); // Sauvegarder la mise à jour
                    session.setAttribute("message", "Candidature mise à jour avec succès !");
                } else {
                    session.setAttribute("message", "Candidature introuvable.");
                }

                // Reload candidatures after the update (using doGet logic)
                int idAnnonce = Integer.parseInt(idAnnonceParam);
                List<Candidature> candidatures = candidatureService.getCandidaturesByAnnonce(idAnnonce);

                for (Candidature c : candidatures) {
                    if (c.getCandidat() == null) {
                        Candidat candidat = candidatService.getCandidatById(c.getIdUtilisateur());
                        c.setCandidat(candidat);
                    }
                }

                request.setAttribute("candidatures", candidatures);
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);

            } else {
                // Si ce n'est pas une mise à jour, c'est une nouvelle candidature
                int idAnnonce = Integer.parseInt(idAnnonceParam);
                int idUtilisateur = Integer.parseInt(idUtilisateurParam);

                // Créer une nouvelle candidature
                Candidature candidature = new Candidature();
                candidature.setIdAnnonce(idAnnonce);
                candidature.setIdUtilisateur(idUtilisateur);
                candidature.setDateSoumission(LocalDate.now());
                candidature.setStatut("En attente"); // Statut par défaut

                // Sauvegarder la candidature
                candidatureService.saveCandidature(candidature);
                session.setAttribute("message", "Votre candidature a été envoyée avec succès !");

                // Rediriger vers la page des annonces du candidat après la soumission d'une nouvelle candidature
                response.sendRedirect("views/candidat/annonces.jsp");
            }

        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Erreur de format de numéro.");
        } catch (Exception e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Erreur lors de la soumission de votre candidature.");
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idAnnonce = request.getParameter("idAnnonce");

        // Vérifier que l'ID de l'annonce est valide
        if (idAnnonce != null) {
            int id = Integer.parseInt(idAnnonce);

            // Récupérer les candidatures associées à cette annonce
            List<Candidature> candidatures = candidatureService.getCandidaturesByAnnonce(id);

            // Associer chaque candidature à son candidat
            for (Candidature candidature : candidatures) {
                if (candidature.getCandidat() == null) {
                    // Récupérer le candidat depuis son ID
                    Candidat candidat = null;
                    try {
                        candidat = candidatService.getCandidatById(candidature.getIdUtilisateur());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    candidature.setCandidat(candidat); // Associer le candidat récupéré
                }
            }

            // Stocker les candidatures mises à jour dans la requête
            request.setAttribute("candidatures", candidatures);

            // Rediriger vers la page JSP qui affiche les candidatures
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        } else {
            // Rediriger si l'ID est invalide
            response.sendRedirect(request.getContextPath() + "/views/recruteur/espaceRecruteur.jsp");
        }
    }
}

