<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, ma.ensi.model.Recruteur" %>
<%
	List<Recruteur> recruteurs = (List<Recruteur>) request.getAttribute("recruteurs");
%>

<!doctype html>
<html lang="en">
  <head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>MyJob.ma</title>

	<!-- Bootstrap CSS-->
	<link rel="stylesheet" href="../assets/modules/bootstrap-5.1.3/css/bootstrap.css">
	<!-- Style CSS -->
	<link rel="stylesheet" href="../assets/css/style.css">
	<!-- FontAwesome CSS-->
	<link rel="stylesheet" href="../assets/modules/fontawesome6.1.1/css/all.css">
	<!-- Boxicons CSS-->
	<link rel="stylesheet" href="../assets/modules/boxicons/css/boxicons.min.css">
	<!-- Apexcharts  CSS -->
	<link rel="stylesheet" href="../assets/modules/apexcharts/apexcharts.css">
</head>
<body>
  
  <!--Topbar -->
  <div class="topbar transition">
	<div class="bars">
		<button type="button" class="btn transition" id="sidebar-toggle">
			<i class="fa fa-bars"></i>
		</button>
	</div>
		<div class="menu">
			<ul>
				<li class="nav-item dropdown dropdown-list-toggle">
					<a class="nav-link" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					   <i class="fa fa-bell size-icon-1"></i><span class="badge bg-danger notif">4</span>
					</a> 				 
					<div class="dropdown-menu dropdown-list">
						<div class="dropdown-header">Notifications</div>
						<div class="dropdown-list-content dropdown-list-icons">
							<div class="custome-list-notif"> 
							<a href="#" class="dropdown-item dropdown-item-unread">
								<div class="dropdown-item-icon bg-primary text-white">
								  <i class="fas fa-code"></i>
								</div>
								<div class="dropdown-item-desc">
									holla
								  <div class="time text-primary">3 Min Ago</div>
								</div>
							  </a>

							  <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-info text-white">
								  <i class="far fa-user"></i>
								</div>
								<div class="dropdown-item-desc">
								  hey
								  <div class="time">12 Hours Ago</div>
								</div>
							  </a>

							  <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-danger text-white">
								  <i class="fas fa-check"></i>
								</div>
								<div class="dropdown-item-desc">
                                    heloollle
									<div class="time">20 Hours Ago</div>
								</div>
							  </a>

						  
							  <a href="#" class="dropdown-item">
								<div class="dropdown-item-icon bg-info text-white">
								  <i class="fas fa-bell"></i>
								</div>
								<div class="dropdown-item-desc">
								  what
								  <div class="time">Yesterday</div>
								</div>
							  </a>
 
							</div>
						</div>

						<div class="dropdown-footer text-center">
						  <a href="#">View All</a>
						</div>
					</div>
					  
				  </li>
			 
				  <li class="nav-item dropdown">
					<a class="nav-link" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					  <img src="../assets/images/avatar/avatar-1.png" alt="">
					</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="my-profile.html"><i class="fa fa-user size-icon-1"></i> <span>My Profile</span></a>

						<hr class="dropdown-divider">
						
					</ul>
				  </li>
			</ul>
		</div>
	</div>

	<!--Sidebar-->
	<div class="sidebar transition overlay-scrollbars animate__animated  animate__slideInLeft">
        <div class="sidebar-content"> 
        	<div id="sidebar">
			
			<!-- Logo -->
			<div class="logo">
					<h2 class="mb-0"><img src="../assets/images/logo.png"> MyJob.ma</h2>
			</div>

            <ul class="side-menu">
                <li>
					<a href="../index.html" class="active">
						<i class='bx bxs-dashboard icon' ></i> Dashboard
					</a>
				</li>

				<!-- Divider-->
            
                <li>
                    <a href="#">
						<i class='bx bx-columns icon' ></i> 
						Espace Universite
						<i class='bx bx-chevron-right icon-right' ></i>
					</a>
                    <ul class="side-dropdown">
                        <li><a href="../GestionUniversite/GestionCompteUni.jsp">Gestion des comptes</a></li>
                        <li><a href="../GestionUniversite/ListeDesUniversite.jsp">Liste des Universite</a></li>
						<li><a href="../GestionUniversite/ListeDesAdminUni.jsp">Liste des Admins Universites</a></li>
                    </ul>
                </li>

				<li>
                    <a href="#">
						<i class='bx bx-columns icon' ></i> 
						Espace Recruteur
						<i class='bx bx-chevron-right icon-right' ></i>
					</a>
                    <ul class="side-dropdown">
                        <li><a href="../GestionRecrutement/GestionDesProfile.html">Gestion des Profile</a></li>
						<li><a href="GestionDesAnnonce.jsp">Gestion des Annonces</a></li>
						<li><a href="ListeDesSociete.jsp">Liste des Societe</a></li>
                    </ul>
                </li>

				<li>
                    <a href="#">
						<i class='bx bx-columns icon' ></i> 
						Espace Candidat
						<i class='bx bx-chevron-right icon-right' ></i>
					</a>
                    <ul class="side-dropdown">
                        <li><a href="../GestionCandidat/GestionCompteCandidat.jsp">Gestion des comptes</a></li>
						<li><a href="../GestionCandidat/GestionCandidature.jsp">Gestion des Candidature</a></li>
                    
                    </ul>
                </li>
       <div class="ads">
				<div class="wrapper">
					
					
                    <a href="" class="btn-upgrade">Deconnexion</a>
                 </div>
            </div>
        </div>

       </div> 
	 </div>
	</div><!-- End Sidebar-->


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
							<h4>Liste des Admin</h4>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
									<tr>
										<th scope="col">AdminId</th>
										<th scope="col">Nom</th>
										<th scope="col">Société</th>
										<th scope="col">Modifier</th>
										<th scope="col">Supprimer</th>
									</tr>
									</thead>
									<tbody>
									<% if (recruteurs != null) { %>
									<% for (Recruteur recruteur : recruteurs) { %>
									<tr>
										<th scope="row"><%= recruteur.getIdUtilisateur() %></th>
										<td><%= recruteur.getNom() %></td>
										<td><%= recruteur.getEntreprise() %></td>
										<td>
											<form action="RecruteurServlet" method="post">
												<input type="hidden" name="action" value="edit">
												<input type="hidden" name="id" value="<%= recruteur.getIdUtilisateur() %>">
												<button type="submit" class="btn btn-primary">Modifier</button>
											</form>
										</td>
										<td>
											<form action="RecruteurServlet" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce recruteur ?');">
												<input type="hidden" name="action" value="delete">
												<input type="hidden" name="id" value="<%= recruteur.getIdUtilisateur() %>">
												<button type="submit" class="btn btn-danger">Supprimer</button>
											</form>
										</td>
									</tr>
									<% } %>
									<% } else { %>
									<tr>
										<td colspan="5" class="text-center">Aucun recruteur trouvé.</td>
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
	<script src="../assets/js/atrana.js"></script>

	<!-- JS Libraies -->
	<script src="../assets/modules/jquery/jquery.min.js"></script>
	<script src="../assets/modules/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/modules/popper/popper.min.js"></script>

	<!-- Chart Js -->
	<script src="../assets/modules/apexcharts/apexcharts.js"></script>
	<script src="../assets/js/ui-apexcharts.js"></script>

    <!-- Template JS File -->
	<script src="../assets/js/script.js"></script>
	<script src="../assets/js/custom.js"></script>
 </body>
</html>
