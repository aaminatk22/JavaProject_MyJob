<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Annonce" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Check if the user is logged in and has the "Recruteur" role
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Retrieve the list of annonces from the session
    List<Annonce> annonces = (List<Annonce>) session.getAttribute("annonces");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Recruteur</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
    <div class="flex items-center justify-between max-w-6xl mx-auto">
        <h1 class="text-lg font-bold">Espace Recruteur</h1>
        <div>
            <span>Bienvenue, <strong><%= utilisateur.getNomUtilisateur() %></strong></span>
            <a href="<%= request.getContextPath() + "/logout" %>" class="text-white underline">Déconnexion</a>
        </div>
    </div>
</nav>

<!-- User Profile -->
<div class="bg-white shadow p-6 max-w-6xl mx-auto mt-4 rounded-lg">
    <h2 class="text-lg font-bold">Bienvenue, Recruteur!</h2>
    <p class="text-gray-600">Gérez vos annonces avec facilité.</p>
</div>

<!-- Main Content -->
<div class="p-6 max-w-6xl mx-auto">
    <!-- Header -->
    <header class="flex justify-between items-center mb-8">
        <h1 class="text-2xl font-bold">Vos Annonces</h1>
        <button onclick="openModal()" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
            Ajouter une annonce
        </button>
    </header>

    <!-- Job Listings -->
    <div class="bg-white p-4 rounded-lg shadow">
        <h2 class="text-lg font-semibold mb-4">Annonces publiées</h2>
        <ul>
            <%
                if (annonces != null && !annonces.isEmpty()) {
                    for (Annonce annonce : annonces) {
            %>
            <li class="p-4 border rounded-lg flex justify-between items-center mb-2">
                <div>
                    <h3 class="text-lg font-semibold"><%= annonce.getTitre() %></h3>
                    <p class="text-gray-600"><%= annonce.getTypeAnnonce() %> - <%= annonce.getDescription() %></p>
                    <p class="text-gray-500 text-sm">Publié le : <%= annonce.getDatePublication() %></p>
                </div>
                <button class="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600">Supprimer</button>
            </li>
            <%
                }
            } else {
            %>
            <p class="text-gray-500">Aucune annonce publiée pour le moment.</p>
            <%
                }
            %>
        </ul>
    </div>
</div>

<!-- Modal -->
<div id="modal" class="fixed inset-0 bg-gray-800 bg-opacity-50 hidden justify-center items-center">
    <div class="bg-white p-6 rounded-lg w-1/3">
        <h2 class="text-lg font-bold mb-4">Ajouter une annonce</h2>
        <form action="<%= request.getContextPath() %>/annonces" method="POST" class="space-y-4">
            <label>
                <input type="text" name="titre" placeholder="Titre du poste" class="p-2 border rounded-lg w-full" required>
            </label>
            <label>
                <input type="text" name="typeAnnonce" placeholder="Type d'annonce" class="p-2 border rounded-lg w-full" required>
            </label>
            <label>
                <textarea name="description" placeholder="Description" class="p-2 border rounded-lg w-full" required></textarea>
            </label>
            <div class="mt-6 flex justify-end space-x-4">
                <button type="button" onclick="closeModal()" class="px-4 py-2 bg-gray-300 rounded-lg hover:bg-gray-400">Annuler</button>
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Ajouter</button>
            </div>
        </form>
    </div>
</div>

<!-- Scripts -->
<script>
    function openModal() {
        document.getElementById("modal").classList.remove("hidden");
    }

    function closeModal() {
        document.getElementById("modal").classList.add("hidden");
    }
</script>

</body>
</html>
