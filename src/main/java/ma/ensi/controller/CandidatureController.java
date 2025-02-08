package ma.ensi.controller;

import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;
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
import java.util.List;

@WebServlet("/postuler")
public class CandidatureController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CandidatureService candidatureService = new CandidatureService();
    private final CandidatService candidatService = new CandidatService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAnnonceParam = request.getParameter("idAnnonce");
        String idUtilisateurParam = request.getParameter("idUtilisateur");
        String idCandidatureParam = request.getParameter("idCandidature");
        String statut = request.getParameter("statut");
        String action = request.getParameter("action");

        if (idAnnonceParam == null || idUtilisateurParam == null) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Les paramètres sont manquants.");
            response.sendRedirect("views/recruteur/viewApplications.jsp");
            return;
        }

        try {
            HttpSession session = request.getSession();

            if (idCandidatureParam != null && statut != null) {
                int idCandidature = Integer.parseInt(idCandidatureParam);

                Candidature candidature = candidatureService.getCandidatureById(idCandidature);
                if (candidature != null) {
                    candidature.setStatut(statut);
                    candidatureService.updateCandidature(candidature);

                    session.setAttribute("message", "Candidature mise à jour avec succès !");

                    // Redirect to interview scheduling if candidature is accepted
                    if ("Accepted".equalsIgnoreCase(statut)) {
                        response.sendRedirect(request.getContextPath() + "/entretien/schedule?idCandidature=" + idCandidature);
                        return;
                    }
                } else {
                    session.setAttribute("message", "Candidature introuvable.");
                }

                // Reload candidatures
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
                int idAnnonce = Integer.parseInt(idAnnonceParam);
                int idUtilisateur = Integer.parseInt(idUtilisateurParam);

                Candidature candidature = new Candidature();
                candidature.setIdAnnonce(idAnnonce);
                candidature.setIdUtilisateur(idUtilisateur);
                candidature.setDateSoumission(LocalDate.now());
                candidature.setStatut("En attente");

                candidatureService.saveCandidature(candidature);
                session.setAttribute("message", "Votre candidature a été envoyée avec succès !");
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

        if (idAnnonce != null) {
            int id = Integer.parseInt(idAnnonce);

            List<Candidature> candidatures = candidatureService.getCandidaturesByAnnonce(id);

            for (Candidature candidature : candidatures) {
                if (candidature.getCandidat() == null) {
                    try {
                        Candidat candidat = candidatService.getCandidatById(candidature.getIdUtilisateur());
                        candidature.setCandidat(candidat);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            request.setAttribute("candidatures", candidatures);
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/views/recruteur/espaceRecruteur.jsp");
        }
    }
}
