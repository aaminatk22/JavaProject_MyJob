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

    // Gestion des messages de session
    String message = (String) session.getAttribute("message");
    if (message != null) {
        response.getWriter().println("<div class='alert'>" + message + "</div>");
        session.removeAttribute("message");
    }
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
    <div class="container mx-auto">
    <h2 class="text-2xl font-bold mb-6">Liste des Candidatures</h2>

    <!-- Check if there are any candidatures -->
    <% if (candidatures != null && !candidatures.isEmpty()) { %>
    <div class="bg-white p-4 rounded-lg shadow">
        <ul>
            <% for (Candidature candidature : candidatures) {
                if (candidature.getCandidat() != null) { %>
            <li class="p-4 border-b border-gray-200 flex justify-between items-center">
                <div>
                    <h3 class="text-lg font-semibold">Candidat : <%= candidature.getCandidat().getNom() %></h3>
                    <p class="text-gray-600">Statut : <%= candidature.getStatut() %></p>
                    <p class="text-gray-500 text-sm">Soumis le : <%= candidature.getDateSoumission() %></p>
                </div>

                <!-- Button to view candidate details -->
                <a
                        href="<%= request.getContextPath() %>candidat/details?idCandidature=<%= candidature.getIdCandidature() %>"
                        class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600">
                    Voir Détails
                </a>



                <!-- Form to accept the candidature -->
                <form id="acceptForm" action="<%= request.getContextPath() + "/postuler" %>" method="POST" class="inline">
                    <input type="hidden" name="idCandidature" value="<%= candidature.getIdCandidature() %>" />
                    <input type="hidden" name="idAnnonce" value="<%= candidature.getIdAnnonce() %>" />
                    <input type="hidden" name="idUtilisateur" value="<%= candidature.getIdUtilisateur() %>" />
                    <input type="hidden" name="statut" value="Accepted" />
                    <input type="hidden" name="action" value="accept" />
                    <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600">
                        Accepter
                    </button>
                </form>

                <!-- Form to reject the candidature -->
                <form id="rejectForm" action="<%= request.getContextPath() + "/postuler" %>" method="POST" class="inline">
                    <input type="hidden" name="idCandidature" value="<%= candidature.getIdCandidature() %>" />
                    <input type="hidden" name="idAnnonce" value="<%= candidature.getIdAnnonce() %>" />
                    <input type="hidden" name="idUtilisateur" value="<%= candidature.getIdUtilisateur() %>" />
                    <input type="hidden" name="statut" value="Refused" />
                    <input type="hidden" name="action" value="reject" />
                    <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600">
                        Refuser
                    </button>
                </form>
            </li>
            <% } else { %>
            <li class="p-4 border-b border-gray-200 text-red-500">
                ⚠️ Erreur : candidature sans candidat !
            </li>
            <% }
            } %>
        </ul>
    </div>
    <% } else { %>
    <p class="text-gray-500">Aucune candidature pour le moment.</p>
    <% } %>
</div>
</div>
</body>
</html>