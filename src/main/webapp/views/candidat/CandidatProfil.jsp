<%@ page import="ma.ensi.model.Utilisateur, ma.ensi.model.Candidat" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<%
    // Check if the user is logged in and has the "candidat" role
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"candidat".equals(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }

    // Retrieve the "nomUniversite" if the user is of type "Candidat"
    String nomUniversite = "";
    if (utilisateur instanceof Candidat) {
        Candidat candidat = (Candidat) utilisateur;
        nomUniversite = candidat.getNomUniversite(); // Access nomUniversite from Candidat
    }
%>

<h1>Profile</h1>

<!-- If the user is logged in and has the "candidat" role, show personal information -->
<h2>Personal Information</h2>
<p><strong>Username:</strong> ${sessionScope.utilisateur.nomUtilisateur}</p>
<p><strong>Name:</strong> ${sessionScope.utilisateur.nom} ${sessionScope.utilisateur.prenom}</p>
<p><strong>Email:</strong> ${sessionScope.utilisateur.email}</p>
<p><strong>Phone:</strong> ${sessionScope.utilisateur.tel}</p>
<p><strong>Nom Universite:</strong> <%= nomUniversite %></p>

<h2>Actions</h2>
<button onclick="location.href='${pageContext.request.contextPath}/views/candidat/CreerProfil.jsp'">Edit Profile</button>
<button onclick="location.href='${pageContext.request.contextPath}/views/candidat/createportfolio.jsp'">Edit Portfolio</button>

</body>
</html>
