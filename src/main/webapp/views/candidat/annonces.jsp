<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Annonce" %>
<%@ page import="java.util.List" %>

<%
    // Vérification de l'authentification
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Récupération des annonces depuis la session
    List<Annonce> annonces = (List<Annonce>) session.getAttribute("annonces");
%>

<% String message = (String) session.getAttribute("message"); %>
<% if (message != null) { %>
<div class="alert"><%= message %></div>
<% session.removeAttribute("message"); %>
<% } %>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Candidat</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
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

    <script>
        function submitApplication(idAnnonce) {
            const idUtilisateur = "<%= utilisateur.getIdUtilisateur() %>";

            const candidature = {
                idAnnonce: parseInt(idAnnonce),
                idUtilisateur: parseInt(idUtilisateur),
                dateSoumission: new Date().toISOString().split("T")[0],
                statut: "En attente",
            };

            fetch("<%= request.getContextPath() %>/postuler", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(candidature)
            })
                .then(response => response.json())
                .then(data => {
                    console.log("Réponse du serveur :", data); // Log de la réponse complète
                    if (data.success) {
                        alert("Votre candidature a été soumise avec succès !");
                    } else {
                        alert("Erreur lors de la soumission de la candidature : " + data.message);
                    }
                })
                .catch(error => {
                    console.error("Erreur réseau :", error);
                    alert("Une erreur est survenue.");
                });
        }

    </script>
</head>
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

<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
    <div class="container mx-4xl">
    <h2 class="text-2xl font-bold mb-4">Annonces Disponibles</h2>
    <div class="space-y-4">
        <%
            if (annonces != null && !annonces.isEmpty()) {
                for (Annonce annonce : annonces) {
        %>
        <div class="job-listing p-4 border rounded-lg flex justify-between items-center">
            <div>
                <h3 class="text-lg font-semibold"><%= annonce.getTitre() %></h3>
                <p class="text-gray-600">Type: <%= annonce.getTypeAnnonce() %></p>
                <p class="text-gray-600"><%= annonce.getDescription() %></p>
                <p class="text-gray-500 text-sm">Publié le: <%= annonce.getDatePublication() %></p>
            </div>
            <form action="<%= request.getContextPath() %>/postuler" method="post">
                <input type="hidden" name="idAnnonce" value="<%= annonce.getIdAnnonce() %>">
                <input type="hidden" name="idUtilisateur" value="<%= utilisateur.getIdUtilisateur() %>"> <!-- Corrected -->
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Postuler</button>
            </form>


        </div>
        <%
            }
        } else {
        %>
        <p class="text-gray-500">Aucune annonce disponible.</p>
        <%
            }
        %>
    </div>
    </div>
</div>
</body>
</html>
