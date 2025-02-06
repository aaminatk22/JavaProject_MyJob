<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page import="ma.ensi.model.Annonce" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // Check if the user is logged in and has the "Recruteur" role
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Retrieve the list of annonces from the session
    List<Annonce> annonces = (List<Annonce>) session.getAttribute("annonces");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Meta Tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page with Sidebar</title>

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
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <!-- Meta Tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Include Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex">

<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate__animated  animate__slideInLeft">
    <div class="sidebar-content">
        <div id="sidebar">


            <!-- Logo -->

                    <img src="<%= request.getContextPath() %>/images/logo/MyjobLogoWhite.png" class="h-40 w-30">




            <ul class="side-menu">
                <li>
                    <a href="Profile.jsp" class="active">
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
                    <a href="<%= request.getContextPath() %>/views/recruteur/annonces.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Annonces
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>/views/recruteur/ViewApplications.jsp">
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
        <!-- User Profile -->
        <div class="bg-white shadow p-6 max-w-6xl mx-auto mt-4 rounded-lg">
            <span class="text-xl font-bold mb-4 text-black">Bienvenue, <strong><%= utilisateur.getNomUtilisateur() %></strong></span>

            <p class="text-gray-600">Gérez vos annonces avec facilité.</p>
        </div>

        <!-- Main Content -->
        <div class="p-6 max-w-6xl mx-auto">
            <!-- Header -->
            <header class="flex justify-between items-center mb-8">
                <h1 class="text-2xl font-bold ">Vos Annonces</h1>
                <button onclick="openAddModal()" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
                    Ajouter une annonce
                </button>
            </header>

            <!-- Job Listings -->
            <!-- Job Listings -->
            <div class="bg-white p-4 rounded-lg shadow">
                <h2 class="text-lg font-semibold mb-4">Annonces publiées</h2>
                <ul>
                        <%
                if (annonces != null && !annonces.isEmpty()) {
                    for (Annonce annonce : annonces) {
            %>
                    <li class="p-4 border rounded-lg flex justify-between items-center mb-2">
                        <div>
                            <h3 class="text-lg font-semibold"><%= annonce.getTitre() %></h3>
                            <p class="text-gray-600"><%= annonce.getTypeAnnonce() %> - <%= annonce.getDescription() %></p>
                            <p class="text-gray-500 text-sm">Publié le : <%= annonce.getDatePublication() %></p>
                        </div>
                        <div class="flex space-x-2">
                            <!-- Update Button -->
                            <button onclick="openUpdateModal(<%= annonce.getIdAnnonce() %>, '<%= annonce.getTitre() %>', '<%= annonce.getTypeAnnonce() %>', '<%= annonce.getDescription() %>')"
                                    class="px-3 py-1 bg-yellow-500 text-white rounded-lg hover:bg-yellow-600">Modifier</button>
                            <!-- Delete Button -->
                            <form action="<%= request.getContextPath() %>/annonces" method="POST" onsubmit="return confirm('Voulez-vous vraiment supprimer cette annonce ?')">
                                <input type="hidden" name="_method" value="delete">
                                <input type="hidden" name="id" value="<%= annonce.getIdAnnonce() %>">
                                <button type="submit" class="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600">Supprimer</button>
                            </form>
                            <!-- View Applications Button -->
                            <a href="<%= request.getContextPath() %>/postuler?idAnnonce=<%= annonce.getIdAnnonce() %>" class="px-3 py-1 bg-green-500 text-white rounded-lg hover:bg-green-600">
                                Voir les candidatures
                            </a>


                        </div>
                    </li>
                    <%
                        }
                    } else {
                    %>
                    <p class="text-gray-500">Aucune annonce publiée pour le moment.</p>
                    <%
                        }
                    %>
                </ul>
            </div>

        </div>



        <!-- Add Modal -->
        <div id="addModal" class="fixed inset-0 bg-gray-900 bg-opacity-50 hidden flex justify-center items-center">
            <div class="bg-white p-8 rounded-lg shadow-lg w-1/3">
                <h2 class="text-2xl font-bold text-center text-blue-600 mb-6">Ajouter une annonce</h2>
                <form action="<%= request.getContextPath() %>/annonces" method="POST" class="space-y-6">
                    <label class="block">
                        <span class="text-gray-700">Titre</span>
                        <input type="text" name="titre" placeholder="Titre du poste" class="mt-1 p-2 border rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                    </label>
                    <label class="block">
                        <span class="text-gray-700">Type d'annonce</span>
                        <input type="text" name="typeAnnonce" placeholder="Type d'annonce" class="mt-1 p-2 border rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                    </label>
                    <label class="block">
                        <span class="text-gray-700">Description</span>
                        <textarea name="description" placeholder="Description" class="mt-1 p-2 border rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-blue-500" required></textarea>
                    </label>
                    <div class="flex justify-end space-x-4">
                        <button type="button" onclick="closeAddModal()" class="px-4 py-2 bg-gray-300 text-gray-800 rounded-lg hover:bg-gray-400 focus:ring-2 focus:ring-gray-400">Annuler</button>
                        <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 focus:ring-2 focus:ring-blue-400">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- Update Modal -->
        <div id="updateModal" class="fixed inset-0 bg-gray-900 bg-opacity-50 hidden flex justify-center items-center">
            <div class="bg-white p-8 rounded-lg shadow-lg w-1/3">
                <h2 class="text-2xl font-bold text-center text-yellow-600 mb-6">Modifier une annonce</h2>
                <form action="<%= request.getContextPath() %>/annonces" method="POST" class="space-y-6">
                    <input type="hidden" name="_method" value="put">
                    <input type="hidden" id="updateId" name="id">
                    <label class="block">
                        <span class="text-gray-700">Titre</span>
                        <input type="text" id="updateTitre" name="titre" placeholder="Titre du poste" class="mt-1 p-2 border rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-yellow-500" required>
                    </label>
                    <label class="block">
                        <span class="text-gray-700">Type d'annonce</span>
                        <input type="text" id="updateType" name="typeAnnonce" placeholder="Type d'annonce" class="mt-1 p-2 border rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-yellow-500" required>
                    </label>
                    <label class="block">
                        <span class="text-gray-700">Description</span>
                        <textarea id="updateDescription" name="description" placeholder="Description" class="mt-1 p-2 border rounded-lg w-full focus:outline-none focus:ring-2 focus:ring-yellow-500" required></textarea>
                    </label>
                    <div class="flex justify-end space-x-4">
                        <button type="button" onclick="closeUpdateModal()" class="px-4 py-2 bg-gray-300 text-gray-800 rounded-lg hover:bg-gray-400 focus:ring-2 focus:ring-gray-400">Annuler</button>
                        <button type="submit" class="px-4 py-2 bg-yellow-500 text-white rounded-lg hover:bg-yellow-600 focus:ring-2 focus:ring-yellow-400">Mettre à jour</button>
                    </div>
                </form>
            </div>
        </div>


    </div>
</div>
<!-- Scripts -->
<script>
    function openAddModal() {
        document.getElementById("addModal").classList.remove("hidden");
    }

    function closeAddModal() {
        document.getElementById("addModal").classList.add("hidden");
    }

    function openUpdateModal(id, titre, typeAnnonce, description) {
        document.getElementById("updateId").value = id;
        document.getElementById("updateTitre").value = titre;
        document.getElementById("updateType").value = typeAnnonce;
        document.getElementById("updateDescription").value = description;
        document.getElementById("updateModal").classList.remove("hidden");
    }

    function closeUpdateModal() {
        document.getElementById("updateModal").classList.add("hidden");
    }


    function deleteAnnonce(id) {
        fetch('<%= request.getContextPath() %>/annonces?id=${id}', {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            alert('Annonce supprimée avec succès.');
            window.location.reload();
        } else {
            alert('Échec de la suppression de l\'annonce.');
        }
    }).catch(err => {
        console.error('Erreur lors de la suppression:', err);
    });
    }
</script>

<!-- Include Tailwind Icons -->
<script src="https://unpkg.com/boxicons@2.1.2/dist/boxicons.min.js"></script>
</body>
</html>