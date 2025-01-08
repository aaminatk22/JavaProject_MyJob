package ma.ensi.controller;

import ma.ensi.model.Utilisateur;
import ma.ensi.service.UtilisateurService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Contrôleur pour gérer les processus de connexion et d'inscription des utilisateurs.
 */
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

    /**
     * Gestion du processus de connexion.
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // Appel du service pour authentifier l'utilisateur
            Utilisateur utilisateur = utilisateurService.login(email, password);

            // Connexion réussie : stocker l'utilisateur en session
            request.getSession().setAttribute("utilisateur", utilisateur);
            response.sendRedirect("views/candidat/annonces.jsp"); // Page après connexion réussie
        } catch (Exception e) {
            // Connexion échouée : afficher un message d'erreur
            request.setAttribute("error", "Email ou mot de passe incorrect !");
            request.getRequestDispatcher("views/login/loginpage.jsp").forward(request, response);
        }
    }

    /**
     * Gestion du processus d'inscription.
     */
    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomUtilisateur = request.getParameter("nomUtilisateur");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");
        String rePass = request.getParameter("rePass");

        // Debug logs
        System.out.println("Nom Utilisateur: " + nomUtilisateur);
        System.out.println("Email: " + email);
        System.out.println("Mot de Passe: " + motDePasse);
        System.out.println("Re-pass: " + rePass);

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
            // Call service to register
            utilisateurService.register(utilisateur);
            System.out.println("User registered successfully!");
            response.sendRedirect("views/login/loginpage.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/login/register.jsp").forward(request, response);
        }
    }

}

