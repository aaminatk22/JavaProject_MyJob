<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.ensi.model.Candidature" %>
<%@ page import="ma.ensi.model.Candidat" %>

<%
    Candidature candidature = (Candidature) request.getAttribute("candidature");
    Candidat candidat = (Candidat) request.getAttribute("candidat");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Planifier un entretien</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
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
<div class="container mx-auto mt-10 p-6 bg-white rounded-lg shadow-md">
    <h1 class="text-2xl font-bold text-blue-600 mb-6">Planifier un entretien</h1>

    <!-- Display Candidate Details -->
    <div class="mb-6">
        <h2 class="text-lg font-semibold text-gray-700">Détails du Candidat</h2>
        <p class="text-lg font-medium text-gray-900">
            <span class="text-gray-600">Nom: </span><%= candidat.getNom() %> <%= candidat.getPrenom() %>
        </p>
        <p class="text-lg font-medium text-gray-900">
            <span class="text-gray-600">Email: </span><%= candidat.getEmail() %>
        </p>
    </div>

    <!-- Schedule Interview Form -->
    <form action="<%= request.getContextPath() %>/entretien/schedule" method="POST">
        <input type="hidden" name="idCandidature" value="<%= candidature.getIdCandidature() %>" />

        <div class="mb-4">
            <label for="interviewDate" class="block text-sm font-medium text-gray-700">Date de l'entretien</label>
            <input type="date" id="interviewDate" name="interviewDate" required
                   class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div class="mb-4">
            <label for="interviewTime" class="block text-sm font-medium text-gray-700">Heure de l'entretien</label>
            <input type="time" id="interviewTime" name="interviewTime" required
                   class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div class="mb-4">
            <label for="lieu" class="block text-sm font-medium text-gray-700">Lieu de l'entretien</label>
            <input type="text" id="lieu" name="lieu" placeholder="Entrez le lieu de l'entretien" required
                   class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
        </div>

        <div class="mb-4">
            <label for="interviewMode" class="block text-sm font-medium text-gray-700">Mode de l'entretien</label>
            <select id="interviewMode" name="interviewMode" required
                    class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
                <option value="In-person">En personne</option>
                <option value="Online">En ligne</option>
                <option value="Phone">Téléphone</option>
            </select>
        </div>

        <div class="mb-4">
            <label for="statut" class="block text-sm font-medium text-gray-700">Statut de l'entretien</label>
            <select id="statut" name="statut" required
                    class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500">
                <option value="Scheduled">Planifié</option>
                <option value="Completed">Terminé</option>
                <option value="Cancelled">Annulé</option>
            </select>
        </div>

        <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700">
            Planifier l'entretien
        </button>
    </form>
</div>
</div>
</body>
</html>
