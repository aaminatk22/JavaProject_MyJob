<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Candidat" %>
<%@ page import="ma.ensi.model.Portfolio" %>
<%@ page import="java.util.List" %>

<%
  Candidat candidat = (Candidat) request.getAttribute("candidat");
  Portfolio portfolio = (Portfolio) request.getAttribute("portfolio");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
  <link rel="stylesheet" href="assets/modules/bootstrap-5.1.3/css/bootstrap.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/fontawesome6.1.1/css/all.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/boxicons/css/boxicons.min.css">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap JS (required for interactive components) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <title>Détails du Candidat</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate__animated  animate__slideInLeft">
  <div class="sidebar-content">
    <div id="sidebar">
      <!-- Logo -->
      <div class="logo">
        <h2 class="mb-0 text-2xl">MyJob.ma</h2>
      </div>
      <ul class="side-menu">
        <li>
          <a href="<%= request.getContextPath() %>/views/recruteur/Profile.jsp" class="active">
            <i class='bx bxs-dashboard icon' ></i> Profile
          </a>
        </li>

        <!-- Divider-->
        <li class="divider" data-text="STARTER">MYJOB.ma</li>

        <li>
          <a href="<%= request.getContextPath() %>/views/recruteur/RecruiterDashboard.jsp">
            <i class='bx bx-columns icon' ></i>
            Espace Recruteur
            <i class='bx bx-chevron-right icon-right' ></i>
          </a>
        </li>

        <li>
          <a href="<%= request.getContextPath() %>/views/recruteur/annoncesR.jsp">
            <i class='bx bx-columns icon' ></i>
            Annonces
            <i class='bx bx-chevron-right icon-right' ></i>
          </a>
        </li>
      </ul>
    </div>


  </div>
</div>


<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
<div class="container mx-auto p-6 bg-white shadow rounded-lg mt-10">
  <h1 class="text-3xl font-bold text-blue-600 mb-6">Détails du Candidat</h1>

  <div class="mb-6">
    <h2 class="text-2xl font-semibold">Informations Personnelles</h2>
    <div class="bg-blue-50 p-6 rounded-lg shadow mb-4">
      <p class="text-xl font-bold text-blue-600"><strong>Nom :</strong> <%= candidat.getNom() %> <%= candidat.getPrenom() %></p>
    </div>
    <div class="bg-blue-50 p-6 rounded-lg shadow mb-4">
      <p class="text-xl font-bold text-blue-600"><strong>Email :</strong><%= candidat.getEmail() %></p>
    </div>
    <div class="bg-blue-50 p-6 rounded-lg shadow mb-4">
      <p class="text-xl font-bold text-blue-600"><strong>Téléphone :</strong> <%= candidat.getTel() %></p>
    </div>

    <div class="bg-blue-50 p-6 rounded-lg shadow mb-4">
      <p class="text-xl font-bold text-blue-600"><strong>Âge :</strong> <%= candidat.getAge() %></p>
    </div>

    <div class="bg-blue-50 p-6 rounded-lg shadow mb-4">
      <p class="text-xl font-bold text-blue-600"><strong>Université :</strong> <%= candidat.getNomUniversite() %></p>
    </div>

    <div class="bg-blue-50 p-6 rounded-lg shadow mb-4">
      <p class="text-xl font-bold text-blue-600"><strong>Niveau d'étude :</strong> <%= candidat.getNiveauEtude() %></p>
    </div>
  </div>
  <div class="bg-blue-50 p-6 rounded-lg shadow mb-6" >
    <h2 class="text-2xl font-semibold">Portfolio</h2>
    <p><strong>Description :</strong> <%= portfolio != null ? portfolio.getDescription() : "Aucun portfolio disponible" %></p>
  </div>

</div>
</div>
</body>
</html>
