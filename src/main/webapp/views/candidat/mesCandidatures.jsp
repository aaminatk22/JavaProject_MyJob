<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Candidature" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.LocalDate" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des candidatures depuis la requête
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
    Map<Integer, String> annoncesMap = (Map<Integer, String>) session.getAttribute("annoncesMap");

    // Ajout d'une candidature statique si la liste est vide ou null
    if (candidatures == null || candidatures.isEmpty()) {
        candidatures = new ArrayList<>();

        Candidature candidatureTest = new Candidature();
        candidatureTest.setIdCandidature(1);
        candidatureTest.setIdAnnonce(999);
        candidatureTest.setDateSoumission(LocalDate.parse("2024-02-06"));
        candidatureTest.setStatut("En attente");

        candidatures.add(candidatureTest);
    }

    // Vérification que la map contient un titre d'annonce pour l'ID statique
    if (annoncesMap == null) {
        annoncesMap = new HashMap<>();
        session.setAttribute("annoncesMap", annoncesMap);
    }
    annoncesMap.put(999, "Développeur Java");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Candidatures</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <!-- Include Required CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>assets/modules/bootstrap-5.1.3/css/bootstrap.css">
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
            <!-- Logo -->
            <div class="logo">
                <h2 class="mb-0 text-2xl">MyJob.ma</h2>
            </div>
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
                    <a href="<%= request.getContextPath() %>/views/candidat/mesCandidatures.jsp">
                        <i class='bx bx-columns icon' ></i>
                       Mes Candidatures
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>
            </ul>
        </div>


    </div>
</div>


<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
    <div class="container mx-auto p-6 max-w-6xl ">
    <h2 class="text-2xl font-bold mb-4">Mes Candidatures</h2>
    <table class="w-full border-collapse border border-gray-300 bg-white">
        <thead>
        <tr class="bg-gray-200">
            <th class="border border-gray-300 p-2 text-black">ID Candidature</th>
            <th class="border border-gray-300 p-2 text-black">Titre de l'Annonce</th>
            <th class="border border-gray-300 p-2 text-black">Date de Soumission</th>
            <th class="border border-gray-300 p-2 text-black">Statut</th>
        </tr>
        </thead>
        <tbody>
        <% for (Candidature candidature : candidatures) { %>
        <tr class="border border-gray-300">
            <td class="p-2 text-black"><%= candidature.getIdCandidature() %></td>
            <td class="p-2 text-black"><%= annoncesMap.getOrDefault(candidature.getIdAnnonce(), "Annonce Inconnue") %></td>
            <td class="p-2 text-black"><%= candidature.getDateSoumission() %></td>
            <td class="p-2 text-black"><%= candidature.getStatut() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</div>
</body>
</html>
