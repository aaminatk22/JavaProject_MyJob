<%@ page import="ma.ensi.model.Utilisateur, ma.ensi.model.Candidat" %>
<%@ page session="true" %><!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidate Profile</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Include Required CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>recruteur/assets/modules/bootstrap-5.1.3/css/bootstrap.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/fontawesome6.1.1/css/all.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/boxicons/css/boxicons.min.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JS (required for interactive components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="bg-gray-100 font-sans leading-normal tracking-normal">

<%
    // Check if the user is logged in and has the "candidat" role
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"candidat".equals(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Retrieve the "nomUniversite" if the user is of type "Candidat"
    String nomUniversite = "";
    if (utilisateur instanceof Candidat) {
        Candidat candidat = (Candidat) utilisateur;
        nomUniversite = candidat.getNomUniversite(); // Access nomUniversite from Candidat
    }
%>

<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate_animated  animate_slideInLeft">
    <div class="sidebar-content">
        <div id="sidebar">
            <!-- Logo -->
            <img src="<%= request.getContextPath() %>/images/logo/MyjobLogoWhite.png" class="h-40 w-30">
            <ul class="side-menu">
                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/CandidatProfil.jsp" class="active">
                        <i class='bx bxs-dashboard icon' ></i> Candidate Profile
                    </a>
                </li>

                <!-- Divider-->
                <li class="divider" data-text="STARTER">MYJOB.ma</li>

                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/createportfolio.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Créer Portfolio
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>

                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/CreerProfil.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Créer Profil
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/views/recruteur/viewApplications.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Candidatures
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>
            </ul>
        </div>


    </div>
</div>

<!-- Main Content -->
<div class="ml-64 flex-1 p-6">
    <div class="container mx-auto">
        <div class="bg-white rounded-xl shadow-lg p-8">
            <h1 class="text-3xl font-bold mb-6 text-gray-800">Candidate Dashboard</h1>
            <p class="text-gray-600 mb-8">Welcome to your dashboard, here is your profile summary.</p>

            <!-- Candidate Information -->
            <div class="grid grid-cols-1 sm:grid-cols-2 gap-6 mb-8">
                <div class="bg-blue-50 p-6 rounded-lg shadow">
                    <h2 class="text-lg font-semibold text-gray-700">Username</h2>
                    <p class="text-xl font-bold text-blue-600"><%= utilisateur.getNomUtilisateur() %></p>
                </div>
                <div class="bg-blue-50 p-6 rounded-lg shadow">
                    <h2 class="text-lg font-semibold text-gray-700">Email</h2>
                    <p class="text-xl font-bold text-blue-600"><%= utilisateur.getEmail() %></p>
                </div>
                <div class="bg-blue-50 p-6 rounded-lg shadow">
                    <h2 class="text-lg font-semibold text-gray-700">Full Name</h2>
                    <p class="text-xl font-bold text-blue-600"><%= utilisateur.getNom() %> <%= utilisateur.getPrenom() %></p>
                </div>
                <div class="bg-blue-50 p-6 rounded-lg shadow">
                    <h2 class="text-lg font-semibold text-gray-700">Phone</h2>
                    <p class="text-xl font-bold text-blue-600"><%= utilisateur.getTel() %></p>
                </div>
                <div class="bg-blue-50 p-6 rounded-lg shadow">
                    <h2 class="text-lg font-semibold text-gray-700">Nom Université</h2>
                    <p class="text-xl font-bold text-blue-600"><%= nomUniversite %></p>
                </div>
                <div class="flex space-x-4">
                    <button class="bg-blue-500 text-white py-2 px-4 rounded-lg shadow hover:bg-blue-600">
                        Edit Profile
                    </button>
                    <button class="bg-green-500 text-white py-2 px-4 rounded-lg shadow hover:bg-green-600">
                        Edit Portfolio
                    </button>
                </div>
            </div>


        </div>
    </div>
</div>


</body>
</html>
