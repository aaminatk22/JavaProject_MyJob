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
</head>
<body class="bg-gray-100">

<!-- Progress Bar -->
<div class="bg-gray-200 w-full h-2 mt-4">
    <div id="progress-bar" class="bg-blue-600 h-2" style="width: 0%;"></div>
</div>

<!-- Form Container -->
<div class="max-w-4xl mx-auto bg-white p-6 mt-8 rounded-lg shadow-lg">
    <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
        <!-- Step 1: Personal Info -->
        <div id="step-1" class="step">
            <h2 class="text-lg font-bold mb-4">Step 1: Personal Information</h2>
            <div class="mb-4">
                <label for="firstName" class="block text-gray-700">First Name</label>
                <input type="text" id="firstName" name="firstName" class="w-full p-2 border rounded-lg" value="<%= utilisateur.getNom() %>" required>
            </div>
            <div class="mb-4">
                <label for="lastName" class="block text-gray-700">Last Name</label>
                <input type="text" id="lastName" name="lastName" class="w-full p-2 border rounded-lg" value="<%= utilisateur.getPrenom() %>" required>
            </div>
            <div class="mb-4">
                <label for="email" class="block text-gray-700">Email</label>
                <input type="email" id="email" name="email" class="w-full p-2 border rounded-lg" value="<%= utilisateur.getEmail() %>" required>
            </div>
            <div class="mb-4">
                <label for="tel" class="block text-gray-700">Phone</label>
                <input type="tel" id="tel" name="tel" class="w-full p-2 border rounded-lg" value="<%= utilisateur.getTel() %>" required>
            </div>
            <div class="mb-4">
                <label for="age" class="block text-gray-700">Age</label>
                <input type="number" id="age" name="age" class="w-full p-2 border rounded-lg" required>
            </div>

            <!-- University Info -->
            <div class="mb-4">
                <label for="university" class="block text-gray-700">University</label>
                <input type="text" id="university" name="university" class="w-full p-2 border rounded-lg" required>
            </div>
            <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg">Submit</button>
        </div>
    </form>
</div>

</body>
</html>
