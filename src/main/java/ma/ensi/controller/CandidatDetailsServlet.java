package ma.ensi.controller;

import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;
import ma.ensi.model.Portfolio;
import ma.ensi.service.CandidatService;
import ma.ensi.service.CandidatureService;
import ma.ensi.service.PortfolioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/candidat/details")
public class CandidatDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CandidatureService candidatureService = new CandidatureService();
    private final CandidatService candidatService = new CandidatService();
    private final PortfolioService portfolioService = new PortfolioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de la candidature depuis les paramètres
        String idCandidatureParam = request.getParameter("idCandidature");

        if (idCandidatureParam == null || idCandidatureParam.isEmpty()) {
            request.setAttribute("error", "L'ID de la candidature est manquant.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
            return;
        }

        try {
            // Convertir l'ID de la candidature en entier
            int idCandidature = Integer.parseInt(idCandidatureParam);

            // Récupérer la candidature correspondante
            Candidature candidature = candidatureService.getCandidatureById(idCandidature);

            if (candidature == null) {
                request.setAttribute("error", "Candidature introuvable.");
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
                return;
            }

            // Récupérer les informations du candidat lié à cette candidature
            Candidat candidat = candidatService.getCandidatById(candidature.getIdUtilisateur());

            if (candidat == null) {
                request.setAttribute("error", "Candidat introuvable pour cette candidature.");
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
                return;
            }

            // Récupérer le portfolio du candidat (si disponible)
            Portfolio portfolio = portfolioService.getPortfolioByUserId(candidat.getIdUtilisateur());

            // Ajouter les informations du candidat et son portfolio à la requête
            request.setAttribute("candidat", candidat);
            request.setAttribute("portfolio", portfolio);

            // Rediriger vers la page des détails du candidat
            request.getRequestDispatcher("/views/recruteur/candidatDetails.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "L'ID de la candidature est invalide.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur s'est produite lors de la récupération des détails du candidat.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        }
    }
}
