package ma.ensi.controller;

import ma.ensi.model.Annonce;
import ma.ensi.model.Candidat;
import ma.ensi.model.Recruteur;
import ma.ensi.model.Utilisateur;
import ma.ensi.service.AnnonceService;
import ma.ensi.service.UtilisateurService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UtilisateurController", urlPatterns = {"/login", "/register"})
public class UtilisateurController extends HttpServlet {
    private final UtilisateurService utilisateurService = new UtilisateurService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/login".equals(action)) {
            handleLogin(request, response);
        } else if ("/register".equals(action)) {
            handleRegister(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non reconnue.");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Utilisateur utilisateur = utilisateurService.login(email, password);

            if (utilisateur != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", utilisateur.getIdUtilisateur());
                session.setAttribute("utilisateur", utilisateur);
                session.setAttribute("role", utilisateur.getRole());

                System.out.println("Authenticated user: " + utilisateur.getNomUtilisateur() + ", Role: " + utilisateur.getRole());

                AnnonceService annonceService = new AnnonceService();
                if ("recruteur".equalsIgnoreCase(utilisateur.getRole())) {
                    List<Annonce> annonces = annonceService.getAnnoncesByRecruiter(utilisateur.getIdUtilisateur());
                    session.setAttribute("annonces", annonces);
                    response.sendRedirect("views/recruteur/RecruiterSpace.jsp");
                } else if ("candidat".equalsIgnoreCase(utilisateur.getRole())) {
                    List<Annonce> annonces = annonceService.getAllAnnonces();
                    session.setAttribute("annonces", annonces);
                    response.sendRedirect("views/candidat/annonces.jsp");
                } else {
                    response.sendRedirect("views/login/loginpage.jsp?error=UnknownRole");
                }
            } else {
                request.setAttribute("error", "Email ou mot de passe incorrect !");
                request.getRequestDispatcher("views/login/loginpage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue pendant la connexion !");
            request.getRequestDispatcher("views/login/loginpage.jsp").forward(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomUtilisateur = request.getParameter("nomUtilisateur");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String rePass = request.getParameter("rePass");
        String role = request.getParameter("role");

        if (!motDePasse.equals(rePass)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas !");
            request.getRequestDispatcher("views/login/register.jsp").forward(request, response);
            return;
        }

        Utilisateur utilisateur = null;

        if ("Candidat".equalsIgnoreCase(role)) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String tel = request.getParameter("tel");
            int age = Integer.parseInt(request.getParameter("age"));
            String nomUniversite = request.getParameter("nomUniversite");
            String niveauEtude = request.getParameter("niveauEtude");

            utilisateur = new Candidat(0, nomUtilisateur, email, motDePasse, role, nom, prenom, tel, age, nomUniversite, niveauEtude);
        } else if ("Recruteur".equalsIgnoreCase(role)) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String tel = request.getParameter("tel");
            String entreprise = request.getParameter("entreprise");
            String poste = request.getParameter("poste");

            utilisateur = new Recruteur(0, nomUtilisateur, email, motDePasse, role, nom, prenom, tel, entreprise, poste, null);
        }

        if (utilisateur != null) {
            try {
                utilisateurService.register(utilisateur);
                response.sendRedirect("views/login/loginpage.jsp?success=true");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Une erreur est survenue pendant l'inscription !");
                request.getRequestDispatcher("views/login/register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Rôle invalide !");
            request.getRequestDispatcher("views/login/register.jsp").forward(request, response);
        }
    }

}
