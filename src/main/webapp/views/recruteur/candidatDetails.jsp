<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Candidat" %>
<%@ page import="ma.ensi.model.Portfolio" %>
<%@ page import="ma.ensi.model.Competence, ma.ensi.model.Experiences, ma.ensi.model.Projet, ma.ensi.model.Document" %>
<%@ page import="java.util.List" %>

<%
  Candidat candidat = (Candidat) request.getAttribute("candidat");
  Portfolio portfolio = (Portfolio) request.getAttribute("portfolio");
  List<Competence> competences = (List<Competence>) request.getAttribute("competences");
  List<Projet> projets = (List<Projet>) request.getAttribute("projets");
  List<Experiences> experiences = (List<Experiences>) request.getAttribute("experiences");
  List<Document> documents = (List<Document>) request.getAttribute("documents");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Détails du Candidat</title>
  <!-- Include Required CSS -->
  <link rel="stylesheet" href="assets/modules/bootstrap-5.1.3/css/bootstrap.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/fontawesome6.1.1/css/all.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/boxicons/css/boxicons.min.css">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap JS (required for interactive components) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate_animated  animate_slideInLeft">
  <div class="sidebar-content">
    <div id="sidebar">
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
          <a href="<%= request.getContextPath() %>/views/recruteur/RecruiterSpace.jsp">
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



<!-- Main Content -->
<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
  <div class="container mx-auto p-6 bg-white shadow rounded-lg mt-10">
    <h1 class="text-3xl font-bold text-blue-600 mb-6">Détails du Candidat</h1>

    <!-- Personal Information -->
    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
      <div class="bg-blue-50 p-6 rounded-lg shadow">
        <p class="text-lg font-bold"><strong>Nom :</strong> <%= candidat.getNom() %> <%= candidat.getPrenom() %></p>
      </div>
      <div class="bg-blue-50 p-6 rounded-lg shadow">
        <p class="text-lg font-bold"><strong>Email :</strong> <%= candidat.getEmail() %></p>
      </div>
      <div class="bg-blue-50 p-6 rounded-lg shadow">
        <p class="text-lg font-bold"><strong>Téléphone :</strong> <%= candidat.getTel() %></p>
      </div>
      <div class="bg-blue-50 p-6 rounded-lg shadow">
        <p class="text-lg font-bold"><strong>Université :</strong> <%= candidat.getNomUniversite() %></p>
      </div>
    </div>

    <!-- Portfolio -->
    <div class="bg-blue-50 p-6 rounded-lg shadow mb-6">
      <h2 class="text-2xl font-semibold">Portfolio</h2>
      <p><strong>Description :</strong> <%= portfolio != null ? portfolio.getDescription() : "Aucun portfolio disponible" %></p>
    </div>

    <!-- Competences -->
    <div class="bg-white p-6 rounded-lg shadow mb-6">
      <h2 class="text-2xl font-semibold mb-4">Compétences</h2>
      <% for (Competence competence : competences) { %>
      <p><strong><%= competence.getNom() %></strong> - <%= competence.getNiveau() %></p>
      <% } %>
    </div>

    <!-- Experiences -->
    <div class="bg-white p-6 rounded-lg shadow mb-6">
      <h2 class="text-2xl font-semibold mb-4">Expériences</h2>
      <% for (Experiences experience : experiences) { %>
      <p><strong><%= experience.getTitre() %></strong> chez <%= experience.getEntreprise() %></p>
      <% } %>
    </div>

    <!-- Projects -->
    <div class="bg-white p-6 rounded-lg shadow mb-6">
      <h2 class="text-2xl font-semibold mb-4">Projets</h2>
      <% for (Projet projet : projets) { %>
      <p><strong><%= projet.getTitre() %></strong> - <%= projet.getDescription() %></p>
      <% } %>
    </div>

    <!-- Documents -->
    <div class="bg-blue-50 p-6 rounded-lg shadow mb-6">
      <h2 class="text-2xl font-semibold">Documents</h2>
      <%
        if (portfolio != null && portfolio.getDocuments() != null && !portfolio.getDocuments().isEmpty()) {
          for (Document document : portfolio.getDocuments()) {
      %>
      <div class="mb-4">
        <p><strong>Type:</strong> <%= document.getType() %></p>
        <a href="<%= document.getFilePath() %>" class="text-blue-600 underline" target="_blank">View Document</a>
      </div>
      <%
        }
      } else {
      %>
      <p>No documents available.</p>
      <%
        }
      %>
    </div>

  </div>
</div>
</body>
</html>
