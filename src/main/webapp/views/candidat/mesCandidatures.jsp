<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Candidature" %>
<%@ page import="ma.ensi.model.Entretien" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des candidatures et entretiens depuis la requête
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
    Map<Integer, String> annoncesMap = (Map<Integer, String>) session.getAttribute("annoncesMap");
    Map<Integer, Entretien> entretiensMap = (Map<Integer, Entretien>) request.getAttribute("entretiensMap");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Candidatures</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
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
<body>

<!-- Sidebar -->
<div class="sidebar transition overlay-scrollbars animate_animated animate_slideInLeft">
    <div class="sidebar-content">
        <div id="sidebar">
            <div class="logo">
                <h2 class="mb-0 text-2xl">MyJob.ma</h2>
            </div>
            <ul class="side-menu">
                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/CandidatProfil.jsp" class="active">
                        <i class='bx bxs-dashboard icon'></i> Candidate Profile
                    </a>
                </li>
                <li class="divider" data-text="STARTER">MYJOB.ma</li>
                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/createportfolio.jsp">
                        <i class='bx bx-columns icon'></i> Créer Portfolio
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/CreerProfil.jsp">
                        <i class='bx bx-columns icon'></i> Créer Profil
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/mesCandidatures.jsp">
                        <i class='bx bx-columns icon'></i> Mes Candidatures
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- Main Content -->

<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
    <div class="container mx-auto p-6 max-w-6xl">
        <h2 class="text-2xl font-bold mb-4">Mes Candidatures</h2>
        <table class="w-full border-collapse border border-gray-300 bg-white">
            <thead>
            <tr class="bg-gray-200">
                <th class="border border-gray-300 p-2 text-black">ID Candidature</th>
                <th class="border border-gray-300 p-2 text-black">Titre de l'Annonce</th>
                <th class="border border-gray-300 p-2 text-black">Date de Soumission</th>
                <th class="border border-gray-300 p-2 text-black">Statut</th>
                <th class="border border-gray-300 p-2 text-black">Entretien</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (candidatures != null && !candidatures.isEmpty()) {
                    for (Candidature candidature : candidatures) {
            %>
            <tr class="border border-gray-300">
                <td class="p-2 text-black"><%= candidature.getIdCandidature() %></td>
                <td class="p-2 text-black"><%= annoncesMap.getOrDefault(candidature.getIdAnnonce(), "Annonce Inconnue") %></td>
                <td class="p-2 text-black"><%= candidature.getDateSoumission() %></td>
                <td class="p-2 text-black"><%= candidature.getStatut() %></td>
                <td class="p-2 text-black">
                    <%
                        Entretien entretien = (entretiensMap != null) ? entretiensMap.get(candidature.getIdCandidature()) : null;
                        if (entretien != null) {
                    %>
                    📅 <strong>Date:</strong> <%= entretien.getDateEntretien() %><br>
                    ⏰ <strong>Heure:</strong> <%= entretien.getHeureEntretien() %><br>
                    📍 <strong>Lieu:</strong> <%= entretien.getLieu() %><br>
                    🏷️ <strong>Statut:</strong> <%= entretien.getStatut() %>
                    <% } else { %>
                    ❌ <strong>Aucun entretien planifié</strong>
                    <% } %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" class="text-center text-gray-500 p-4">Aucune candidature trouvée</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
