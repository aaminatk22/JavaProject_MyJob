<%@ page import="ma.ensi.model.Candidature" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des candidatures depuis la requête
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidatures - Espace Recruteur</title>
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
<div class="sidebar transition overlay-scrollbars animate_animated  animate_slideInLeft">
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
<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
    <div class="container mx-auto">
    <h2 class="text-2xl font-bold mb-6">Liste des Candidatures</h2>

    <!-- Check if there are any candidatures -->
    <% if (candidatures != null && !candidatures.isEmpty()) { %>
    <div class="bg-white p-4 rounded-lg shadow">
        <ul>
            <% for (Candidature candidature : candidatures) { %>
            <li class="p-4 border-b border-gray-200 flex justify-between items-center">
                <div>
                    <h3 class="text-lg font-semibold">Candidat : <%= candidature.getCandidat().getNom() %></h3>
                    <p class="text-gray-600">Statut : <%= candidature.getStatut() %></p>
                    <p class="text-gray-500 text-sm">Soumis le : <%= candidature.getDateSoumission() %></p>
                </div>
                <div class="flex space-x-2">
                    <!-- Buttons to Change Status -->
                </div>
            </li>
            <% } %>
        </ul>
    </div>
    <% } else { %>
    <p class="text-gray-500">Aucune candidature pour le moment.</p>
    <% } %>
</div>
</div>
</body>
</html>