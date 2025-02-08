package ma.ensi.controller;

import ma.ensi.model.Candidature;
import ma.ensi.model.Candidat;
import ma.ensi.model.Entretien;
import ma.ensi.service.CandidatureService;
import ma.ensi.service.CandidatService;
import ma.ensi.service.EntretienService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/entretien/schedule")
public class InterviewScheduleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CandidatureService candidatureService = new CandidatureService();
    private final CandidatService candidatService = new CandidatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the candidature
        String idCandidatureParam = request.getParameter("idCandidature");

        if (idCandidatureParam == null || idCandidatureParam.isEmpty()) {
            request.setAttribute("error", "L'ID de la candidature est manquant.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
            return;
        }

        try {
            int idCandidature = Integer.parseInt(idCandidatureParam);

            // Retrieve the candidature
            Candidature candidature = candidatureService.getCandidatureById(idCandidature);

            if (candidature == null) {
                request.setAttribute("error", "Candidature introuvable.");
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
                return;
            }

            // Retrieve the candidate details
            Candidat candidat = candidatService.getCandidatById(candidature.getIdUtilisateur());

            if (candidat == null) {
                request.setAttribute("error", "Candidat introuvable pour cette candidature.");
                request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
                return;
            }

            // Pass the candidature and candidate details to the JSP
            request.setAttribute("candidature", candidature);
            request.setAttribute("candidat", candidat);

            // Redirect to the interview scheduling page
            request.getRequestDispatcher("/views/recruteur/scheduleInterview.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "L'ID de la candidature est invalide.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur s'est produite lors de la récupération des informations.");
            request.getRequestDispatcher("/views/recruteur/viewApplications.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form inputs
        String idCandidatureParam = request.getParameter("idCandidature");
        String interviewDate = request.getParameter("interviewDate");
        String interviewTime = request.getParameter("interviewTime");
        String lieu = request.getParameter("lieu"); // Location of the interview
        String statut = request.getParameter("statut"); // Status of the interview

        // Validate the inputs
        if (idCandidatureParam == null || interviewDate == null || interviewTime == null || lieu == null || statut == null) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("/views/recruteur/scheduleInterview.jsp").forward(request, response);
            return;
        }

        try {
            int idCandidature = Integer.parseInt(idCandidatureParam);

            // Create a new Entretien object
            Entretien entretien = new Entretien();
            entretien.setIdCandidature(idCandidature);
            entretien.setDateEntretien(LocalDate.parse(interviewDate)); // Parse interview date
            entretien.setHeureEntretien(LocalTime.parse(interviewTime)); // Parse interview time
            entretien.setLieu(lieu);
            entretien.setStatut(statut);

            // Save the interview using the EntretienService
            EntretienService entretienService = new EntretienService();
            entretienService.scheduleEntretien(entretien);

            // Redirect back with a success message
            request.setAttribute("message", "Entretien planifié avec succès !");
            response.sendRedirect(request.getContextPath() + "/views/recruteur/viewApplications.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "L'ID de la candidature est invalide.");
            request.getRequestDispatcher("/views/recruteur/scheduleInterview.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur s'est produite lors de la sauvegarde de l'entretien.");
            request.getRequestDispatcher("/views/recruteur/scheduleInterview.jsp").forward(request, response);
        }
    }



}
