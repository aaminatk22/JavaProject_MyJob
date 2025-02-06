<%@ page import="ma.ensi.model.Utilisateur, ma.ensi.model.Portfolio" %>
<%@ page session="true" %>

<%
  // Retrieve the candidate's ID from the URL
  String utilisateurId = request.getParameter("idUtilisateur");

  // Check if the ID is provided
  if (utilisateurId == null || utilisateurId.isEmpty()) {
    response.sendRedirect(request.getContextPath() + "/views/error.jsp"); // Redirect if no user ID
    return;
  }

  // Set the utilisateurId as request attribute to pass it to ProfileController and PortfolioController
  request.setAttribute("idUtilisateur", utilisateurId);

  // Forward the request to ProfileController to fetch the Utilisateur data
  request.getRequestDispatcher("/profile").forward(request, response);  // This should be the mapped URL for ProfileController

  // Forward the request to PortfolioController to fetch the Portfolio data
  request.getRequestDispatcher("/candidat/createPortfolio").forward(request, response);  // This should be the mapped URL for PortfolioController
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile du Candidat</title>
  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
  <div class="flex items-center justify-between max-w-6xl mx-auto">
    <h1 class="text-lg font-bold">Profile du Candidat</h1>
  </div>
</nav>

<!-- Candidate Profile -->
<div class="bg-white shadow p-6 max-w-6xl mx-auto mt-4 rounded-lg">
  <h2 class="text-2xl font-bold">Nom: <%= request.getAttribute("utilisateur.nom") %> <%= request.getAttribute("utilisateur.prenom") %></h2>
  <p><strong>Nom d'utilisateur:</strong> <%= request.getAttribute("utilisateur.nomUtilisateur") %></p>
  <p><strong>Email:</strong> <%= request.getAttribute("utilisateur.email") %></p>
  <p><strong>Téléphone:</strong> <%= request.getAttribute("utilisateur.tel") %></p>

  <!-- Portfolio Section -->
  <h3 class="mt-4 text-xl font-semibold">Portfolio</h3>
  <%
    Portfolio portfolio = (Portfolio) request.getAttribute("portfolio");
    if (portfolio != null) {
  %>
  <p><strong>Projet:</strong> <%= portfolio.getProjet() %></p>
  <p><strong>Description:</strong> <%= portfolio.getDescription() %></p>
  <!-- Add more details as needed -->
  <% } else { %>
  <p>Aucun portfolio disponible.</p>
  <% } %>
</div>

</body>
</html>
