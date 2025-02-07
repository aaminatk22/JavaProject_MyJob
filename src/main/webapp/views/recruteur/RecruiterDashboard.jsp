<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.HashMap, java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Espace Recruteur</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <!-- Include Required CSS -->
  <link rel="stylesheet" href="assets/modules/bootstrap-5.1.3/css/bootstrap.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/fontawesome6.1.1/css/all.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/boxicons/css/boxicons.min.css">
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap JS (required for interactive components) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>


<body class="bg-gray-100">


<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate__animated  animate__slideInLeft">
  <div class="sidebar-content">
    <div id="sidebar">
      <!-- Logo -->
      <img src="<%= request.getContextPath() %>/images/logo/MyjobLogoWhite.png" class="h-40 w-30">
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
            Espace Recrueteur
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
<!-- End sidebar -->

<!-- Main Content -->
<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
  <div class="container mx-auto">
  <header class="mb-8">
    <h1 class="text-2xl font-bold">Espace Recruteur</h1>
    <p class="text-gray-600">Consultez les candidatures reçues pour vos annonces publiées.</p>
  </header>

  <!-- Job Listings -->
  <div class="bg-white p-4 rounded-lg shadow">
    <h2 class="text-lg font-semibold mb-4">Vos annonces</h2>

    <!-- Simulate job listings -->
    <%
      List<Map<String, String>> jobListings = new ArrayList<Map<String, String>>();

      Map<String, String> job1 = new HashMap<String, String>();
      job1.put("title", "Développeur Java");
      job1.put("function", "Développement");
      job1.put("experience", "2 ans");
      job1.put("contract", "CDI");
      job1.put("id", "1");

      Map<String, String> job2 = new HashMap<String, String>();
      job2.put("title", "Chef de projet");
      job2.put("function", "Gestion de projet");
      job2.put("experience", "5 ans");
      job2.put("contract", "CDD");
      job2.put("id", "2");

      jobListings.add(job1);
      jobListings.add(job2);
    %>

    <% if (jobListings != null && !jobListings.isEmpty()) { %>
    <ul class="space-y-4">
      <% for (Map<String, String> job : jobListings) { %>
      <li class="p-4 border rounded-lg">
        <div class="flex justify-between items-center">
          <div>
            <h3 class="text-lg font-semibold"><%= job.get("title") %></h3>
            <p class="text-gray-600"><%= job.get("function") %> - <%= job.get("experience") %> - <%= job.get("contract") %></p>
          </div>
          <button onclick="openModal(<%= job.get("id") %>)" class="px-3 py-1 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
            Voir les candidatures
          </button>
        </div>
      </li>
      <% } %>
    </ul>
    <% } else { %>
    <p class="text-gray-600">Aucune annonce publiée.</p>
    <% } %>
  </div>

  <!-- Modal for job applications -->
  <div id="modal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden">
    <div class="bg-white p-6 rounded-lg w-full max-w-lg">
      <h2 class="text-lg font-semibold mb-4">Candidatures pour : <span id="jobTitle"></span></h2>

      <!-- Job Applications -->
      <div id="applications" class="space-y-4">
        <!-- Static data of the job applications -->
        <div class="p-4 border rounded-lg">
          <h3 class="text-md font-semibold">Nom du candidat : Jean Dupont</h3>
          <p class="text-gray-600">Message : Très intéressé par le poste!</p>
          <ul class="list-disc list-inside text-gray-600">
            <li><a href="#" class="text-blue-500 underline hover:text-blue-700">CV.pdf</a></li>
            <li><a href="#" class="text-blue-500 underline hover:text-blue-700">Lettre de motivation.pdf</a></li>
          </ul>
        </div>
      </div>

      <div class="flex justify-end mt-4">
        <button onclick="closeModal()" class="px-4 py-2 bg-gray-300 rounded-lg hover:bg-gray-400">Fermer</button>
      </div>
    </div>
  </div>
</div>
</div>
<script>
  function openModal(jobId) {
    document.getElementById("modal").classList.remove("hidden");
    document.getElementById("jobTitle").innerText = "Titre de l'annonce " + jobId;
  }

  function closeModal() {
    document.getElementById("modal").classList.add("hidden");
  }
</script>

</body>
</html>
