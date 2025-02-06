<%@ page import="ma.ensi.model.Candidat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  Candidat candidat = (Candidat) request.getAttribute("candidat");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Détails du Candidat</title>
  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
  <div class="flex items-center justify-between max-w-6xl mx-auto">
    <h1 class="text-lg font-bold">Détails du Candidat</h1>
    <div>
      <a href="<%= request.getContextPath() + "/recruiter" %>" class="text-white underline">Retour</a>
    </div>
  </div>
</nav>

<!-- Main Content -->
<div class="p-6 max-w-6xl mx-auto">
  <h1 class="text-2xl font-bold mb-8">Détails de <%= candidat.getNom() %></h1>
  <div class="bg-white p-4 rounded-lg shadow">
    <div class="space-y-4">
      <div>
        <h3 class="text-lg font-semibold">Nom:</h3>
        <p class="text-gray-600"><%= candidat.getNom() %></p>
      </div>
      <div>
        <h3 class="text-lg font-semibold">Email:</h3>
        <p class="text-gray-600"><%= candidat.getEmail() %></p>
      </div>
      <div>
        <h3 class="text-lg font-semibold">Portfolio:</h3>
        <p class="text-gray-600"><a href="<%= candidat.getPortfolioUrl() %>" class="text-blue-500 underline" target="_blank">Voir le portfolio</a></p>
      </div>
      <!-- Add more candidate details as needed -->
    </div>
  </div>
</div>

</body>
</html>