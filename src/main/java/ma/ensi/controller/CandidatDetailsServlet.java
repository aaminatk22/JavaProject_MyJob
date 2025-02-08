package ma.ensi.controller;

import ma.ensi.dao.PortfolioDAO;
import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;
import ma.ensi.model.Portfolio;
import ma.ensi.model.Competence;
import ma.ensi.model.Experiences;
import ma.ensi.model.Projet;
import ma.ensi.model.Document;
import ma.ensi.service.CandidatService;
import ma.ensi.service.CandidatureService;
import ma.ensi.service.PortfolioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/candidat/details")
public class CandidatDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CandidatureService candidatureService = new CandidatureService();
    private final CandidatService candidatService = new CandidatService();
    private final PortfolioService portfolioService = new PortfolioService();
    private final PortfolioDAO portfolioDAO = new PortfolioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCandidatureParam = request.getParameter("idCandidature");

        if (idCandidatureParam == null || idCandidatureParam.isEmpty()) {
            request.setAttribute("error", "L'ID de la candidature est manquant.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
            return;
        }

        try {
            int idCandidature = Integer.parseInt(idCandidatureParam);
            Candidature candidature = candidatureService.getCandidatureById(idCandidature);

            if (candidature == null) {
                request.setAttribute("error", "Candidature introuvable.");
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
                return;
            }

            Candidat candidat = candidatService.getCandidatById(candidature.getIdUtilisateur());

            if (candidat == null) {
                request.setAttribute("error", "Candidat introuvable.");
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
                return;
            }

            Portfolio portfolio = portfolioService.getPortfolioByUserId(candidat.getIdUtilisateur());

            // Fetch additional portfolio details
            List<Competence> competences = portfolio != null ? portfolio.getCompetences() : null;
            List<Experiences> experiences = portfolio != null ? portfolio.getExperiences() : null;
            List<Projet> projets = portfolio != null ? portfolio.getProjets() : null;
            List<Document> documents = portfolio != null ? portfolioDAO.getDocumentsByPortfolioId(portfolio.getIdPortfolio()) : null;

            // Send data to the JSP page
            request.setAttribute("candidat", candidat);
            request.setAttribute("portfolio", portfolio);
            request.setAttribute("competences", competences);
            request.setAttribute("experiences", experiences);
            request.setAttribute("projets", projets);
            request.setAttribute("documents", documents);

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
