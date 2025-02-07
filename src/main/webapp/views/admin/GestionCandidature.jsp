<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, ma.ensi.model.admincandidature" %>
<%@ page import="ma.ensi.model.admincandidature" %>
<%
	List<admincandidature> admincandidatures = (List<admincandidature>) request.getAttribute("admincandidatures");
%>

<!doctype html>
<html lang="en">
<head>

	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>MyJob.ma</title>

	<!-- Bootstrap CSS-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/modules/bootstrap-5.1.3/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/modules/fontawesome6.1.1/css/all.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/modules/boxicons/css/boxicons.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/modules/apexcharts/apexcharts.css">

</head>
<body>



<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate__animated  animate__slideInLeft">
	<div class="sidebar-content">
		<div id="sidebar">

			<!-- Logo -->
			<div class="logo">
				<h2 class="mb-0"> MyJob.ma</h2>
			</div>

			<ul class="side-menu">
				<li>
					<a href="href="${pageContext.request.contextPath}/my-profile.jsp"
					" class="active">
					<i class='bx bxs-dashboard icon' ></i> Dashboard
					</a>
				</li>

				<!-- Divider-->

				<li>
					<a href="#">
						<i class='bx bx-columns icon'></i>
						Espace Universite
						<i class='bx bx-chevron-right icon-right'></i>
					</a>
					<ul class="side-dropdown">
						<li><a href="${pageContext.request.contextPath}/views/admin/GestionCompteUni.jsp">Gestion des comptes</a></li>
						<li><a href="${pageContext.request.contextPath}/views/admin/ListeDesUniversite.jsp">Liste des Universités</a></li>
						<li><a href="${pageContext.request.contextPath}/views/admin/ListeDesAdminUni.jsp">Liste des Admins Universités</a></li>
					</ul>
				</li>

				<li>
					<a href="#">
						<i class='bx bx-columns icon'></i>
						Espace Recruteur
						<i class='bx bx-chevron-right icon-right'></i>
					</a>
					<ul class="side-dropdown">
						<li><a href="${pageContext.request.contextPath}/views/admin/GestionDesProfile.jsp">Gestion des Profils</a></li>
						<li><a href="${pageContext.request.contextPath}/views/admin/GestionDesAnnonce.jsp">Gestion des Annonces</a></li>
						<li><a href="${pageContext.request.contextPath}/views/admin/ListeDesSociete.jsp">Liste des Sociétés</a></li>
					</ul>
				</li>

				<li>
					<a href="#">
						<i class='bx bx-columns icon'></i>
						Espace Candidat
						<i class='bx bx-chevron-right icon-right'></i>
					</a>
					<ul class="side-dropdown">
						<li><a href="${pageContext.request.contextPath}/views/admin/GestionCompteCandidat.jsp">Gestion des comptes</a></li>
						<li><a href="${pageContext.request.contextPath}/views/admin/GestionCandidature.jsp">Gestion des Candidatures</a></li>
					</ul>
				</li>
				<div class="ads">
					<div class="wrapper">


						<a href="" class="btn-upgrade">Deconnexion</a>
					</div>
				</div>
			</ul>
		</div>

	</div>
</div>
</div>
  <!-- End Sidebar-->


	<div class="sidebar-overlay"></div>


	<!--Content Start-->
	<div class="content-start transition">
		<div class="container-fluid dashboard">
			<div class="content-header">
				<h1>Dashboard</h1>
				<p></p>
			</div>
			
			<div class="row">


				</div>
		

				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h4>Liste des Candidats</h4>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
									<tr>
										<th scope="col">ID Candidature</th>
										<th scope="col">Nom</th>
										<th scope="col">Prénom</th>
										<th scope="col">Titre du Poste</th>
										<th scope="col">Date</th>
										<th scope="col">Statut</th>
										<th scope="col">Modifier</th>
										<th scope="col">Supprimer</th>
									</tr>
									</thead>
									<tbody>
									<% if (admincandidatures != null) { %>
									<% for (admincandidature c : admincandidatures) { %>
									<tr>
										<th scope="row"><%= c.getId_candidature() %></th>
										<td><%= c.getNom_utilisateur() %></td>
										<td><%= c.getPrenom() %></td>
										<td><%= c.getTitre() %></td>
										<td><%= c.getDate_soumission() %></td>
										<td><%= c.getStatut() %></td>
										<td>
											<form action="CandidatureServlet" method="post">
												<input type="hidden" name="action" value="edit">
												<input type="hidden" name="id" value="<%= c.getId_candidature() %>">
												<select name="statut">
													<option value="En attente" <%= "En attente".equals(c.getStatut()) ? "selected" : "" %>>En attente</option>
													<option value="Accepté" <%= "Accepté".equals(c.getStatut()) ? "selected" : "" %>>Accepté</option>
													<option value="Rejeté" <%= "Rejeté".equals(c.getStatut()) ? "selected" : "" %>>Rejeté</option>
												</select>
												<button type="submit" class="btn btn-primary">Modifier</button>
											</form>
										</td>
										<td>
											<form action="CandidatureServlet" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette admincandidature ?');">
												<input type="hidden" name="action" value="delete">
												<input type="hidden" name="id" value="<%= c.getId_candidature() %>">
												<button type="submit" class="btn btn-danger">Supprimer</button>
											</form>
										</td>
									</tr>
									<% } %>
									<% } else { %>
									<tr>
										<td colspan="8" class="text-center">Aucune admincandidature trouvée.</td>
									</tr>
									<% } %>
									</tbody>
								</table>
							</div>
					</div>
				</div>

		   </div>
		</div>
	</div>


	<!-- Footer -->				
	<footer>
		<div class="footer">
			<div class="float-start">
				<p>2024 , Lymooxx</p>
			</div>
			
		</div>
	</footer>


	<!-- Preloader -->
	<div class="loader">
		<div class="spinner-border text-light" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>
	
	<!-- Loader -->
	<div class="loader-overlay"></div>

	<!-- General JS Scripts -->
  <script src="${pageContext.request.contextPath}/js/atrana.js"></script>

  <!-- JS Librairies -->
  <script src="${pageContext.request.contextPath}/modules/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/modules/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/modules/popper/popper.min.js"></script>

  <!-- Chart Js -->
  <script src="${pageContext.request.contextPath}/modules/apexcharts/apexcharts.js"></script>
  <script src="${pageContext.request.contextPath}/js/ui-apexcharts.js"></script>

  <!-- Template JS File -->
  <script src="${pageContext.request.contextPath}/js/script.js"></script>
  <script src="${pageContext.request.contextPath}/js/custom.js"></script>

 </body>
</html>
