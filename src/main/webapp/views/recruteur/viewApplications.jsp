<%@ page import="ma.ensi.model.Candidature" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des candidatures depuis la requête
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidatures - Espace Recruteur</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
    <div class="flex items-center justify-between max-w-6xl mx-auto">
        <h1 class="text-lg font-bold">Candidatures - Espace Recruteur</h1>
        <div>
            <a href="<%= request.getContextPath() + "/logout" %>" class="text-white underline">Déconnexion</a>
        </div>
    </div>
</nav>

<!-- Main Content -->
<div class="p-6 max-w-6xl mx-auto">
    <h2 class="text-2xl font-bold mb-6">Liste des Candidatures</h2>

    <!-- Check if there are any candidatures -->
    <% if (candidatures != null && !candidatures.isEmpty()) { %>
    <div class="bg-white p-4 rounded-lg shadow">
        <ul>
            <% for (Candidature candidature : candidatures) { %>
            <li class="p-4 border-b border-gray-200 flex justify-between items-center">
                <div>
                    <h3 class="text-lg font-semibold">Candidat : <%= candidature.getCandidat().getNom() %></h3>
                    <p class="text-gray-600">Statut : <%= candidature.getStatut() %></p>
                    <p class="text-gray-500 text-sm">Soumis le : <%= candidature.getDateSoumission() %></p>
                </div>
                <div class="flex space-x-2">
                    <!-- Buttons to Change Status -->
                </div>
            </li>
            <% } %>
        </ul>
    </div>
    <% } else { %>
    <p class="text-gray-500">Aucune candidature pour le moment.</p>
    <% } %>
</div>

</body>
</html>