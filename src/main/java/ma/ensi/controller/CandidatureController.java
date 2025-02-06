package ma.ensi.controller;

import com.google.gson.Gson;
import ma.ensi.model.Candidature;
import ma.ensi.service.CandidatureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.GsonBuilder;

@WebServlet("/postuler")
public class CandidatureController extends HttpServlet {
    private final CandidatureService candidatureService = new CandidatureService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        try {
            BufferedReader reader = request.getReader();
            Candidature candidature = gson.fromJson(reader, Candidature.class);

            // Debug
            System.out.println("Candidature reçue : " + candidature);

            candidatureService.saveCandidature(candidature);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Candidature enregistrée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erreur lors de l'enregistrement de la candidature.");
        }
    }
}

