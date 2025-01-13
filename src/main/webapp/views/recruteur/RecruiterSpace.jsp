<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Authentication logic: Redirect to login page if user is not authenticated
    if (session == null || session.getAttribute("userId") == null || session.getAttribute("userRole") == null ||
            !session.getAttribute("userRole").equals("recruteur")) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
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
            <span class="mr-4">Bonjour, <strong><%= session.getAttribute("userName") %></strong></span>
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
            <c:forEach var="annonce" items="${sessionScope.annonces}">
                <li class="p-4 border rounded-lg flex justify-between items-center mb-2">
                    <div>
                        <h3 class="text-lg font-semibold">${annonce.titre}</h3>
                        <p class="text-gray-600">${annonce.typeAnnonce} - ${annonce.description}</p>
                        <p class="text-gray-500 text-sm">Publié le : ${annonce.datePublication}</p>
                    </div>
                    <button class="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600">Supprimer</button>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<!-- Modal -->
<div id="modal" class="fixed inset-0 bg-gray-800 bg-opacity-50 hidden justify-center items-center">
    <div class="bg-white p-6 rounded-lg w-1/3">
        <h2 class="text-lg font-bold mb-4">Ajouter une annonce</h2>
        <form action="${pageContext.request.contextPath}/annonces" method="POST" class="space-y-4">
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
