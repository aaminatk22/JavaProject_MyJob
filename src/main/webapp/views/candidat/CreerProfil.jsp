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
    <script src="${pageContext.request.contextPath}/js/stepper.js" defer></script>
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
            <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-lg" onclick="goToStep(2)">Next</button>
        </div>

        <!-- Step 2: Academic Info -->
        <div id="step-2" class="step hidden">
            <h2 class="text-lg font-bold mb-4">Step 2: Academic Information</h2>
            <div class="mb-4">
                <label for="university" class="block text-gray-700">University</label>
                <input type="text" id="university" name="university" class="w-full p-2 border rounded-lg" required>
            </div>
            <div class="mb-4">
                <label for="level" class="block text-gray-700">Level of Study</label>
                <input type="text" id="level" name="level" class="w-full p-2 border rounded-lg" required>
            </div>
            <div class="mb-4">
                <label for="languages" class="block text-gray-700">Languages</label>
                <input type="text" id="languages" name="languages" class="w-full p-2 border rounded-lg">
            </div>
            <div class="mb-4">
                <label for="description" class="block text-gray-700">Profile Description</label>
                <textarea id="description" name="description" class="w-full p-2 border rounded-lg"></textarea>
            </div>
            <button type="button" class="px-4 py-2 bg-gray-300 rounded-lg mr-2" onclick="goToStep(1)">Back</button>
            <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-lg" onclick="goToStep(3)">Next</button>
        </div>

        <!-- Step 3: Projects, Skills, Experiences -->
        <div id="step-3" class="step hidden">
            <h2 class="text-lg font-bold mb-4">Step 3: Projects, Skills, Experiences</h2>

            <!-- Projects -->
            <div class="mb-4">
                <label for="projectInput" class="block text-gray-700">Add a Project</label>
                <div class="flex">
                    <input type="text" id="projectInput" class="w-full p-2 border rounded-lg">
                    <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-lg ml-2"
                            onclick="addToList('projectsList', 'projectInput')">Add</button>
                </div>
                <ul id="projectsList" class="mt-4"></ul>
            </div>

            <!-- Skills -->
            <div class="mb-4">
                <label for="skillInput" class="block text-gray-700">Add a Skill</label>
                <div class="flex">
                    <input type="text" id="skillInput" class="w-full p-2 border rounded-lg">
                    <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-lg ml-2"
                            onclick="addToList('skillsList', 'skillInput')">Add</button>
                </div>
                <ul id="skillsList" class="mt-4"></ul>
            </div>

            <!-- Experiences -->
            <div class="mb-4">
                <label for="experienceInput" class="block text-gray-700">Add an Experience</label>
                <div class="flex">
                    <input type="text" id="experienceInput" class="w-full p-2 border rounded-lg">
                    <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-lg ml-2"
                            onclick="addToList('experiencesList', 'experienceInput')">Add</button>
                </div>
                <ul id="experiencesList" class="mt-4"></ul>
            </div>

            <button type="button" class="px-4 py-2 bg-gray-300 rounded-lg mr-2" onclick="goToStep(2)">Back</button>
            <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-lg" onclick="goToStep(4)">Next</button>
        </div>

        <!-- Step 4: Resume Upload -->
        <div id="step-4" class="step hidden">
            <h2 class="text-lg font-bold mb-4">Step 4: Upload Resume</h2>
            <div class="mb-4">
                <label for="resume" class="block text-gray-700">Upload Resume</label>
                <input type="file" id="resume" name="resume" class="w-full p-2 border rounded-lg" required>
            </div>
            <button type="button" class="px-4 py-2 bg-gray-300 rounded-lg mr-2" onclick="goToStep(3)">Back</button>
            <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
