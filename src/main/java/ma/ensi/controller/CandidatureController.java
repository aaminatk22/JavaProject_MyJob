package ma.ensi.controller;
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

@WebServlet("/postuler")
public class CandidatureController extends HttpServlet {
    private final CandidatureService candidatureService = new CandidatureService();

}

