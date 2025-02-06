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
</head>
<body class="bg-gray-100 min-h-screen flex flex-col">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
    <div class="flex items-center justify-center bg-blue-500 w-full h-12 fixed top-0 left-0 shadow-md z-10">
        <div class="flex items-center space-x-6">
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg class="w-5 h-5" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024">
                    <path d="M946.5 505L560.1 118.8l-25.9-25.9a31.5 31.5 0 0 0-44.4 0L77.5 505a63.9 63.9 0 0 0-18.8 46c.4 35.2 29.7 63.3 64.9 63.3h42.5V940h691.8V614.3h43.4c17.1 0 33.2-6.7 45.3-18.8a63.6 63.6 0 0 0 18.7-45.3c0-17-6.7-33.1-18.8-45.2zM568 868H456V664h112v204zm217.9-325.7V868H632V640c0-22.1-17.9-40-40-40H432c-22.1 0-40 17.9-40 40v228H238.1V542.3h-96l370-369.7 23.1 23.1L882 542.3h-96.1z"></path>
                </svg>
            </button>

            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg class="w-5 h-5" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M12 2.5a5.5 5.5 0 0 1 3.096 10.047 9.005 9.005 0 0 1 5.9 8.181.75.75 0 1 1-1.499.044 7.5 7.5 0 0 0-14.993 0 .75.75 0 0 1-1.5-.045 9.005 9.005 0 0 1 5.9-8.18A5.5 5.5 0 0 1 12 2.5ZM8 8a4 4 0 1 0 8 0 4 4 0 0 0-8 0Z"></path>
                </svg>
            </button>
            <div><a href="/loginpage" class="hover:text-gray-200 transition">Logout</a></div>

        </div>
    </div>
</nav>

<!-- Profile Section -->
<div class="container mx-auto px-4 py-8">
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



</body>
</html>


