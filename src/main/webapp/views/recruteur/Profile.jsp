<%@ page import="ma.ensi.model.Recruteur" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Check if the user is logged in and has the "Recruteur" role
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }
    Integer annonceCount = (Integer) request.getAttribute("annonceCount");
    if (annonceCount == null) {
        annonceCount = 0; // Default to 0 if not set
    }
    // Retrieve data from the request attributes
    Integer totalCandidatures = (Integer) request.getAttribute("totalCandidatures");
    Integer acceptedCandidatures = (Integer) request.getAttribute("acceptedCandidatures");
    Integer rejectedCandidatures = (Integer) request.getAttribute("rejectedCandidatures");

    // Default values if null
    totalCandidatures = (totalCandidatures != null) ? totalCandidatures : 0;



%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recruiter Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.16/tailwind.min.css">
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
<body class="bg-gray-100 min-h-screen flex flex-col">

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

<!-- Profile Section -->
<!-- Main Content -->
<div class="flex-1 ml-64 p-6 bg-[#ebf3ff]">
    <div class="container mx-auto">
    <div class="bg-white rounded-xl shadow-lg p-8">
        <h1 class="text-3xl font-bold mb-6 text-gray-800">Recruiter Dashboard</h1>
        <p class="text-gray-600 mb-8">Welcome to your dashboard, here is a summary of your activity.</p>

        <!-- Recruiter Information -->
        <div class="grid grid-cols-1 sm:grid-cols-2 gap-6 mb-8">
            <div class="bg-blue-50 p-6 rounded-lg shadow">

                <div class="flex items-center space-x-2">
                    <!-- Icon -->
                    <img src ="<%= request.getContextPath() %>/images/user.png"
                         alt="User Icon"
                         class="w-6 h-6">
                    <h2 class="text-lg font-semibold text-gray-700">Recruiter</h2>

                </div>

                <!-- Name -->
                <p class="text-xl font-bold text-blue-600"> <%= utilisateur.getNomUtilisateur() %></p>
            </div>
            <div class="bg-blue-50 p-6 rounded-lg shadow">
                <h2 class="text-lg font-semibold text-gray-700">Contact Email</h2>
                <p class="text-xl font-bold text-blue-600"><%= utilisateur.getEmail() %></p>
            </div>
        </div>

        <!-- Statistics Section -->
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
            <!-- Total Annonces -->
            <div class="bg-blue-100 p-6 rounded-lg shadow text-center">
                <h3 class="text-lg font-semibold text-gray-700">Total Annonces</h3>
                <p class="text-5xl font-extrabold text-blue-600"><%= annonceCount %></p>
            </div>

            <!-- Total Candidatures -->
            <div class="bg-blue-50 p-6 rounded-lg shadow text-center">
                <h3 class="text-lg font-semibold text-gray-700">Total Candidatures</h3>
                <p class="text-5xl font-extrabold text-blue-500"><%= totalCandidatures %></p>
            </div>


        </div>
    </div>
</div>
</div>


</body>
</html>


