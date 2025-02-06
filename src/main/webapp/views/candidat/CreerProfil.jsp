<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Utilisateur" %>

<%
    // Check if the user is logged in and has the "candidat" role
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"candidat".equals(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Profile</title>
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

    <div class="max-w-4xl mx-auto bg-white p-8 rounded-xl shadow-lg">
        <h1 class="text-3xl font-bold text-blue-600 mb-6">Create Your Profile</h1>
        <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data" class="space-y-6">
            <!-- Personal Information -->
            <div>
                <h2 class="text-2xl font-semibold text-gray-800 mb-4">Personal Information</h2>
                <div class="grid grid-cols-2 gap-6">
                    <div>
                        <label for="firstName" class="block text-gray-700 font-medium">First Name</label>
                        <input type="text" id="firstName" name="firstName" class="text-black w-full p-3 border rounded-lg focus:ring focus:ring-blue-300" value="<%= utilisateur.getNom() %>" required>
                    </div>
                    <div>
                        <label for="lastName" class="block text-gray-700 font-medium">Last Name</label>
                        <input type="text" id="lastName" name="lastName" class="text-black w-full p-3 border rounded-lg focus:ring focus:ring-blue-300" value="<%= utilisateur.getPrenom() %>" required>
                    </div>
                    <div>
                        <label for="email" class="block text-gray-700 font-medium">Email</label>
                        <input type="email" id="email" name="email" class="text-black w-full p-3 border rounded-lg focus:ring focus:ring-blue-300" value="<%= utilisateur.getEmail() %>" required>
                    </div>
                    <div>
                        <label for="tel" class="block text-gray-700 font-medium">Phone</label>
                        <input type="tel" id="tel" name="tel" class="text-black w-full p-3 border rounded-lg focus:ring focus:ring-blue-300" value="<%= utilisateur.getTel() %>" required>
                    </div>
                    <div>
                        <label for="age" class="block text-gray-700 font-medium">Age</label>
                        <input type="number" id="age" name="age" class="text-black w-full p-3 border rounded-lg focus:ring focus:ring-blue-300" required>
                    </div>
                    <div>
                        <label for="university" class="block text-gray-700 font-medium">University</label>
                        <input type="text" id="university" name="university" class="text-black w-full p-3 border rounded-lg focus:ring focus:ring-blue-300" required>
                    </div>
                </div>
            </div>

            <!-- Submit Button -->
            <div class="text-right">
                <button type="submit" class="px-6 py-3 bg-blue-600 text-white font-semibold rounded-lg shadow-md hover:bg-blue-700 transition">Submit</button>
            </div>
        </form>
    </div>
</div>



</body>
</html>
