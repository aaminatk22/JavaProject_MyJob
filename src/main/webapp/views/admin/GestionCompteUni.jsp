<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="ma.ensi.model.AgentUniversitaire" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.ensi.controller.AgentUniversitaireServlet" %>

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
		<div class="d-flex justify-content-center align-items-center vh-100">
			<div class="card w-75">
				<div class="card-header text-center">
					<h4>Création Compte Admin</h4>
				</div>

				<div class="card-body">
					<div class="row">
						<!-- Left Section -->
						<div class="col-md-6">
							<div class="mb-3">
								<label>Nom d'utilisateur</label>
								<input type="text" name="nom_utilisateur" class="form-control" placeholder="Nom d'utilisateur" required>
							</div>

							<div class="mb-3">
								<label>Email</label>
								<input type="email" name="email" class="form-control" placeholder="Email" required>
							</div>

							<div class="mb-3">
								<label>Mot de passe</label>
								<input type="password" name="mot_de_passe" class="form-control" placeholder="Mot de passe" required>
							</div>
						</div>

						<!-- Right Section -->
						<div class="col-md-6">
							<div class="mb-3">
								<label>Nom Université</label>
								<input type="text" name="prenom" class="form-control" placeholder="Nom Université" required>
							</div>

							<div class="mb-3">
								<label>Nom Établissement</label>
								<input type="text" name="nom" class="form-control" placeholder="Nom Établissement" required>
							</div>

							<div class="mb-3">
								<%--@declare id="universite"--%><label for="universite">Choisissez une option :</label>
								<div>
									<input type="radio" id="option1" name="universite" value="Facultee" required>
									<label for="option1">Faculté</label>
								</div>
								<div>
									<input type="radio" id="option2" name="universite" value="EcoleSup" required>
									<label for="option2">École Sup</label>
								</div>
							</div>

							<div class="mb-3">
								<%--@declare id="secteur"--%><label for="secteur">Secteur :</label>
								<div>
									<input type="radio" id="option3" name="secteur" value="Public" required>
									<label for="option3">Public</label>
								</div>
								<div>
									<input type="radio" id="option4" name="secteur" value="Prive" required>
									<label for="option4">Privé</label>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Submit Button Centered -->
				<div class="card-footer text-center">
					<button type="submit" class="btn btn-primary">Valider</button>
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
