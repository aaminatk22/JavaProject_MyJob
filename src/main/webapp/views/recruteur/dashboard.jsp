<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Get the number of annonces from the request attribute
    Integer annonceCount = (Integer) request.getAttribute("annonceCount");
    if (annonceCount == null) {
        annonceCount = 0; // Default to 0 if no annonces exist
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recruiter Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.16/tailwind.min.css">
</head>
<body class="bg-gray-100">

<div class="max-w-4xl mx-auto p-6 mt-12">
    <div class="bg-white p-6 rounded-lg shadow-lg">
        <h1 class="text-2xl font-bold mb-4">Recruiter Dashboard</h1>
        <p class="text-gray-600 mb-4">Welcome to your dashboard!</p>

        <div class="bg-blue-100 p-4 rounded-lg shadow text-center">
            <h2 class="text-xl font-bold">Total Annonces</h2>
            <p class="text-4xl font-extrabold text-blue-600"><%= annonceCount %></p>
        </div>
    </div>
</div>

</body>
</html>
