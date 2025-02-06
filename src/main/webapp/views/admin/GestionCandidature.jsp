<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, ma.ensi.model.Candidature" %>
<%
	List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
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
					<a class="nav-link" href="#" id="navbarDropdown1" role="button" data-bs-toggle="dropdown" aria-expanded="false">
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

					  
		 		  </li>
			 
				  <li class="nav-item dropdown">
					<a class="nav-link" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
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
                        <li><a href="../GestionRecrutement/GestionDesProfile.jsp">Gestion des Profile</a></li>
						<li><a href="../GestionRecrutement/GestionDesAnnonce.jsp">Gestion des Annonces</a></li>
						<li><a href="../GestionRecrutement/ListeDesSociete.jsp">Liste des Societe</a></li>
                    </ul>
                </li>

				<li>
                    <a href="#">
						<i class='bx bx-columns icon' ></i> 
						Espace Candidat
						<i class='bx bx-chevron-right icon-right' ></i>
					</a>
                    <ul class="side-dropdown">
                        <li><a href="GestionCompteCandidat.jsp">Gestion des comptes</a></li>
						<li><a href="../GestionCandidat/GestionCandidature.html">Gestion des Candidature</a></li>
                    
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
									<% if (candidatures != null) { %>
									<% for (Candidature c : candidatures) { %>
									<tr>
										<th scope="row"><%= c.getIdCandidature() %></th>
										<td><%= c.getNomCandidat() %></td>
										<td><%= c.getPrenomCandidat() %></td>
										<td><%= c.getTitrePoste() %></td>
										<td><%= c.getDateSoumission() %></td>
										<td><%= c.getStatut() %></td>
										<td>
											<form action="CandidatureServlet" method="post">
												<input type="hidden" name="action" value="edit">
												<input type="hidden" name="id" value="<%= c.getIdCandidature() %>">
												<select name="statut">
													<option value="En attente" <%= "En attente".equals(c.getStatut()) ? "selected" : "" %>>En attente</option>
													<option value="Accepté" <%= "Accepté".equals(c.getStatut()) ? "selected" : "" %>>Accepté</option>
													<option value="Rejeté" <%= "Rejeté".equals(c.getStatut()) ? "selected" : "" %>>Rejeté</option>
												</select>
												<button type="submit" class="btn btn-primary">Modifier</button>
											</form>
										</td>
										<td>
											<form action="CandidatureServlet" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette candidature ?');">
												<input type="hidden" name="action" value="delete">
												<input type="hidden" name="id" value="<%= c.getIdCandidature() %>">
												<button type="submit" class="btn btn-danger">Supprimer</button>
											</form>
										</td>
									</tr>
									<% } %>
									<% } else { %>
									<tr>
										<td colspan="8" class="text-center">Aucune candidature trouvée.</td>
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
