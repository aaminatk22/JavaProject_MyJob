<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.HashMap, java.util.List, java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Espace Recruteur</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
  <div class="flex items-center justify-center bg-blue-500 w-full h-12 fixed top-0 left-0 shadow-md z-10">
    <div class="flex items-center space-x-4">
      <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
        <svg class="w-5 h-5" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024">
          <path d="M946.5 505L560.1 118.8l-25.9-25.9a31.5 31.5 0 0 0-44.4 0L77.5 505a63.9 63.9 0 0 0-18.8 46c.4 35.2 29.7 63.3 64.9 63.3h42.5V940h691.8V614.3h43.4c17.1 0 33.2-6.7 45.3-18.8a63.6 63.6 0 0 0 18.7-45.3c0-17-6.7-33.1-18.8-45.2zM568 868H456V664h112v204zm217.9-325.7V868H632V640c0-22.1-17.9-40-40-40H432c-22.1 0-40 17.9-40 40v228H238.1V542.3h-96l370-369.7 23.1 23.1L882 542.3h-96.1z"></path>
        </svg>
      </button>
      <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
        </svg>
      </button>
      <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
        <svg class="w-5 h-5" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <path d="M12 2.5a5.5 5.5 0 0 1 3.096 10.047 9.005 9.005 0 0 1 5.9 8.181.75.75 0 1 1-1.499.044 7.5 7.5 0 0 0-14.993 0 .75.75 0 0 1-1.5-.045 9.005 9.005 0 0 1 5.9-8.18A5.5 5.5 0 0 1 12 2.5ZM8 8a4 4 0 1 0 8 0 4 4 0 0 0-8 0Z"></path>
        </svg>
      </button>
      <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <circle cx="9" cy="21" r="1"></circle>
          <circle cx="20" cy="21" r="1"></circle>
          <path stroke-linecap="round" stroke-linejoin="round" d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
        </svg>
      </button>
    </div>
  </div>
</nav>

<!-- Main Content -->
<div class="p-6 max-w-6xl mx-auto">
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
