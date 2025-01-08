<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Annonce" %>
<%@ page import="java.util.List" %>

<%
    //Check if the user is logged in
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Retrieve the list of announcements from the session
    List<Annonce> annonces;
    annonces = (List<Annonce>) session.getAttribute("annonces");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Candidat</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <style>
        .navbar {
            background-color: #0077b6;
            color: white;
            padding: 10px 0;
        }
        .job-listing {
            border: 1px solid #d1d5db;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .job-listing h3 {
            color: #03045e;
        }
        .modal-content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
        }
    </style>
    <script>
        function openModal(jobId, jobTitle) {
            document.getElementById("selectedJobTitle").innerText = jobTitle;
            document.getElementById("selectedJobId").value = jobId;
            document.getElementById("jobModal").classList.remove("hidden");
        }

        function closeModal() {
            document.getElementById("jobModal").classList.add("hidden");
            document.getElementById("candidateMessage").value = "";
            document.getElementById("uploadedFiles").value = null;
        }

        function submitApplication() {
            const idAnnonce = document.getElementById("selectedJobId").value;
            const messageCandidat = document.getElementById("candidateMessage").value;
            const idUtilisateur = "<%= utilisateur.getIdUtilisateur() %>"; // ID utilisateur récupéré de la session

            // Validation des champs
            if (!idAnnonce || !messageCandidat || !idUtilisateur) {
                alert("Veuillez remplir tous les champs !");
                return;
            }

            // Construction des données à envoyer
            const candidature = {
                idAnnonce: parseInt(idAnnonce),
                idUtilisateur: parseInt(idUtilisateur),
                dateSoumission: new Date().toISOString().split("T")[0], // Date au format YYYY-MM-DD
                statut: "En attente",
                messageCandidat: messageCandidat
            };

            fetch("<%= request.getContextPath() %>/postuler", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(candidature)
            })
                .then(response => {
                    if (response.ok) {
                        alert("Votre candidature a été soumise avec succès !");
                        closeModal();
                    } else {
                        response.text().then(text => console.error("Erreur de réponse :", text));
                        alert("Erreur lors de la soumission de la candidature.");
                    }
                })
                .catch(error => {
                    console.error("Erreur réseau :", error);
                    alert("Une erreur est survenue.");
                });
        }

    </script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<div class="navbar flex items-center justify-between px-6">
    <h1 class="text-xl font-bold">Espace Candidat</h1>
    <div>
        <span>Bienvenue, <strong><%= utilisateur.getNomUtilisateur() %></strong></span>
        <a href="<%= request.getContextPath() %>/logout" class="ml-4 text-white underline">Se déconnecter</a>
    </div>
</div>

<!-- Content -->
<div class="p-6 max-w-6xl mx-auto">
    <header class="mb-8">
        <h2 class="text-2xl font-bold">Annonces Disponibles</h2>
        <p class="text-gray-600">Consultez les annonces d'emploi publiées par les recruteurs.</p>
    </header>

    <div class="bg-white p-4 rounded-lg shadow">
        <h2 class="text-lg font-semibold mb-4">Annonces disponibles</h2>
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
                <button onclick="openModal(<%= annonce.getIdAnnonce() %>, '<%= annonce.getTitre() %>')"
                        class="px-3 py-1 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Postuler</button>
            </div>
            <%
                }
            } else {
            %>
            <p class="text-gray-500">Aucune annonce n'est disponible pour le moment.</p>
            <%
                }
            %>
        </div>
    </div>
</div>

<!-- Modal -->
<div id="jobModal" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 hidden">
    <div class="modal-content w-full max-w-lg p-6">
        <h2 class="text-lg font-semibold mb-4">Postuler à : <span id="selectedJobTitle"></span></h2>
        <input type="hidden" id="selectedUserId" value="<%= utilisateur.getIdUtilisateur() %>">
        <label for="candidateMessage"></label><textarea id="candidateMessage" placeholder="Écrivez un message..." class="w-full p-2 border rounded-lg mb-4" rows="4"></textarea>
        <input type="file" id="uploadedFiles" class="w-full p-2 border rounded-lg mb-4">
        <input type="hidden" id="selectedJobId">
        <div class="flex justify-end space-x-2">
            <button onclick="closeModal()" class="px-4 py-2 bg-gray-300 rounded-lg hover:bg-gray-400">Annuler</button>
            <button onclick="submitApplication()" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Soumettre</button>
        </div>
    </div>
</div>

</body>
</html>


