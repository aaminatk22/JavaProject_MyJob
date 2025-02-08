package ma.ensi.controller;

import ma.ensi.model.Annonce;
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
            // Authenticate the user
            Utilisateur utilisateur = utilisateurService.login(email, password);

            if (utilisateur != null) {
                // Set user attributes in session
                HttpSession session = request.getSession();
                session.setAttribute("userId", utilisateur.getIdUtilisateur());
                session.setAttribute("utilisateur", utilisateur);
                session.setAttribute("role", utilisateur.getRole());

                // Log the role for debugging
                System.out.println("Authenticated user: " + utilisateur.getNomUtilisateur() + ", Role: " + utilisateur.getRole());

                // Redirect based on role
                if ("recruteur".equalsIgnoreCase(utilisateur.getRole())) {
                    AnnonceService annonceService = new AnnonceService();
                    // Use getAnnoncesByRecruiter to fetch announcements for the specific recruiter
                    List<Annonce> annonces = annonceService.getAnnoncesByRecruiter(utilisateur.getIdUtilisateur());
                    session.setAttribute("annonces", annonces);
                    response.sendRedirect("views/recruteur/RecruiterSpace.jsp");

                } else if ("candidat".equalsIgnoreCase(utilisateur.getRole())) {
                    AnnonceService annonceService = new AnnonceService();
                    List<Annonce> annonces = annonceService.getAllAnnonces();
                    session.setAttribute("annonces", annonces);
                    response.sendRedirect("views/candidat/annonces.jsp");
                }
                else if ("admin".equalsIgnoreCase(utilisateur.getRole())) {

                    response.sendRedirect("views/admin/adminpage.jsp");
                }
                else if ("universite".equalsIgnoreCase(utilisateur.getRole())) {

                    response.sendRedirect("views/admin/index.jsp");
                }

                else {
                    System.out.println("Unknown role detected, redirecting to login page.");
                    response.sendRedirect("views/login/loginpage.jsp?error=UnknownRole");
                }
            } else {
                // Invalid credentials
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

        if (!motDePasse.equals(rePass)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas !");
            request.getRequestDispatcher("views/login/register.jsp").forward(request, response);
            return;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNomUtilisateur(nomUtilisateur);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setRole("Candidat"); // Default role

        try {
            utilisateurService.register(utilisateur);
            System.out.println("User registered successfully!");
            response.sendRedirect("views/login/loginpage.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue pendant l'inscription !");
            request.getRequestDispatcher("views/login/register.jsp").forward(request, response);
        }
    }
}
