<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Candidature" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.LocalDate" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des candidatures depuis la requête
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
    Map<Integer, String> annoncesMap = (Map<Integer, String>) session.getAttribute("annoncesMap");

    // Ajout d'une candidature statique si la liste est vide ou null
    if (candidatures == null || candidatures.isEmpty()) {
        candidatures = new ArrayList<>();

        Candidature candidatureTest = new Candidature();
        candidatureTest.setIdCandidature(1);
        candidatureTest.setIdAnnonce(999);
        candidatureTest.setDateSoumission(LocalDate.parse("2024-02-06"));
        candidatureTest.setStatut("En attente");

        candidatures.add(candidatureTest);
    }

    // Vérification que la map contient un titre d'annonce pour l'ID statique
    if (annoncesMap == null) {
        annoncesMap = new HashMap<>();
        session.setAttribute("annoncesMap", annoncesMap);
    }
    annoncesMap.put(999, "Développeur Java");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Candidatures</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
</head>
<body class="bg-gray-100">
<div class="navbar flex items-center justify-between px-6 bg-blue-600 text-white py-3">
    <h1 class="text-xl font-bold">Mes Candidatures</h1>
    <div>
        <span>Bienvenue, <strong><%= utilisateur.getNomUtilisateur() %></strong></span>
        <a href="<%= request.getContextPath() %>/logout" class="ml-4 text-white underline">Se déconnecter</a>
    </div>
</div>

<div class="p-6 max-w-6xl mx-auto">
    <h2 class="text-2xl font-bold mb-4">Mes Candidatures</h2>
    <table class="w-full border-collapse border border-gray-300 bg-white">
        <thead>
        <tr class="bg-gray-200">
            <th class="border border-gray-300 p-2">ID Candidature</th>
            <th class="border border-gray-300 p-2">Titre de l'Annonce</th>
            <th class="border border-gray-300 p-2">Date de Soumission</th>
            <th class="border border-gray-300 p-2">Statut</th>
        </tr>
        </thead>
        <tbody>
        <% for (Candidature candidature : candidatures) { %>
        <tr class="border border-gray-300">
            <td class="p-2"><%= candidature.getIdCandidature() %></td>
            <td class="p-2"><%= annoncesMap.getOrDefault(candidature.getIdAnnonce(), "Annonce Inconnue") %></td>
            <td class="p-2"><%= candidature.getDateSoumission() %></td>
            <td class="p-2"><%= candidature.getStatut() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
