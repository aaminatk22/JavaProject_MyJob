package ma.ensi.controller;

import ma.ensi.model.Candidat;
import ma.ensi.service.CandidatService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/candidat-amina")
public class CandidatController extends HttpServlet {
    private CandidatService candidatService = new CandidatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Candidat> candidats = candidatService.getAllCandidats();
            request.setAttribute("candidats", candidats);
            request.getRequestDispatcher("views/candidat/candidatsList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

