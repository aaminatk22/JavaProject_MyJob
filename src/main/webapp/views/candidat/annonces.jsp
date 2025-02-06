<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Annonce" %>
<%@ page import="java.util.List" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des annonces depuis la session
    List<Annonce> annonces = (List<Annonce>) session.getAttribute("annonces");
%>

<% String message = (String) session.getAttribute("message"); %>
<% if (message != null) { %>
<div class="alert"><%= message %></div>
<% session.removeAttribute("message"); %>
<% } %>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Candidat</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <script>
        function submitApplication(idAnnonce) {
            const idUtilisateur = "<%= utilisateur.getIdUtilisateur() %>";

            const candidature = {
                idAnnonce: parseInt(idAnnonce),
                idUtilisateur: parseInt(idUtilisateur),
                dateSoumission: new Date().toISOString().split("T")[0],
                statut: "En attente",
            };

            fetch("<%= request.getContextPath() %>/postuler", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(candidature)
            })
                .then(response => response.json())
                .then(data => {
                    console.log("Réponse du serveur :", data); // Log de la réponse complète
                    if (data.success) {
                        alert("Votre candidature a été soumise avec succès !");
                    } else {
                        alert("Erreur lors de la soumission de la candidature : " + data.message);
                    }
                })
                .catch(error => {
                    console.error("Erreur réseau :", error);
                    alert("Une erreur est survenue.");
                });
        }

    </script>
</head>
<body class="bg-gray-100">
<div class="navbar flex items-center justify-between px-6 bg-blue-600 text-white py-3">
    <h1 class="text-xl font-bold">Espace Candidat</h1>
    <div>
        <span>Bienvenue, <strong><%= utilisateur.getNomUtilisateur() %></strong></span>
        <a href="<%= request.getContextPath() %>/logout" class="ml-4 text-white underline">Se déconnecter</a>
    </div>
</div>

<div class="p-6 max-w-6xl mx-auto">
    <h2 class="text-2xl font-bold mb-4">Annonces Disponibles</h2>
    <div class="space-y-4">
        <%
            if (annonces != null && !annonces.isEmpty()) {
                for (Annonce annonce : annonces) {
        %>
        <div class="job-listing p-4 border rounded-lg flex justify-between items-center">
            <div>
                <h3 class="text-lg font-semibold"><%= annonce.getTitre() %></h3>
                <p class="text-gray-600">Type: <%= annonce.getTypeAnnonce() %></p>
                <p class="text-gray-600"><%= annonce.getDescription() %></p>
                <p class="text-gray-500 text-sm">Publié le: <%= annonce.getDatePublication() %></p>
            </div>
            <form action="<%= request.getContextPath() %>/postuler" method="post">
                <input type="hidden" name="idAnnonce" value="<%= annonce.getIdAnnonce() %>">
                <input type="hidden" name="idUtilisateur" value="<%= utilisateur.getIdUtilisateur() %>"> <!-- Corrected -->
                <button type="submit">Postuler</button>
            </form>


        </div>
        <%
            }
        } else {
        %>
        <p class="text-gray-500">Aucune annonce disponible.</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
