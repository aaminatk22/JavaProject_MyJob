<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Utilisateur" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <style>
        .navbar {
            background-color: #0077b6;
            color: white;
            padding: 10px 0;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            color: #03045e;
            margin-bottom: 10px;
        }
        .form-container label {
            font-weight: bold;
            color: #333;
        }
    </style>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<div class="navbar flex items-center justify-between px-6">
    <h1 class="text-xl font-bold">Create Profile</h1>
    <div>
        <span>Welcome, <strong><%= utilisateur.getNomUtilisateur() %></strong></span>
        <a href="<%= request.getContextPath() %>/logout" class="ml-4 text-white underline">Log Out</a>
    </div>
</div>

<!-- Content -->
<div class="p-6 max-w-4xl mx-auto">
    <header class="mb-8">
        <h2 class="text-2xl font-bold">Create Your Profile</h2>
        <p class="text-gray-600">Fill in the details below to create your professional profile.</p>
    </header>

    <div class="form-container">
        <h2 class="text-lg font-semibold">Profile Details</h2>
        <form action="<%= request.getContextPath() %>/profile" method="post" enctype="multipart/form-data" class="space-y-4">
            <!-- Description -->
            <div>
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" class="w-full p-2 border rounded-lg" required></textarea>
            </div>

            <!-- Competences -->
            <div>
                <label for="competence1">Competence 1:</label>
                <input id="competence1" name="competence1" type="text" class="w-full p-2 border rounded-lg">
            </div>
            <div>
                <label for="competence2">Competence 2:</label>
                <input id="competence2" name="competence2" type="text" class="w-full p-2 border rounded-lg">
            </div>

            <!-- Project -->
            <div>
                <label for="projet1">Project Title:</label>
                <input id="projet1" name="projet1" type="text" class="w-full p-2 border rounded-lg">
            </div>
            <div>
                <label for="projetDescription1">Project Description:</label>
                <textarea id="projetDescription1" name="projetDescription1" rows="4" class="w-full p-2 border rounded-lg"></textarea>
            </div>

            <!-- Experience -->
            <div>
                <label for="experience1">Experience Title:</label>
                <input id="experience1" name="experience1" type="text" class="w-full p-2 border rounded-lg">
            </div>
            <div>
                <label for="entreprise1">Company:</label>
                <input id="entreprise1" name="entreprise1" type="text" class="w-full p-2 border rounded-lg">
            </div>

            <!-- Resume Upload -->
            <div>
                <label for="resume">Upload Resume:</label>
                <input id="resume" name="resume" type="file" class="w-full p-2 border rounded-lg">
            </div>

            <!-- Submit Button -->
            <div class="flex justify-end">
                <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Save Profile</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
