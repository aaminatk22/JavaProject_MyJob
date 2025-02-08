<%@ page import="ma.ensi.model.AgentUniversitaire" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
                        <li><a href="GestionCompteUni.jsp">Gestion</a></li>
					
                        <li><a href="ListeDesUniversite.jsp">Liste des Universite</a></li>
						<li><a href="../GestionUniversite/ListeDesAdminUni.html">Liste des Admins Universites</a></li>
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
					<tbody>
					<%
						List<AgentUniversitaire> agents = (List<ma.ensi.model.AgentUniversitaire>) request.getAttribute("agents");
						if (agents != null) {
							for (ma.ensi.model.AgentUniversitaire agent : agents) {
					%>
					<tr>
						<th scope="row"><%= agent.getIdUtilisateur() %></th>
						<td><%= agent.getNom() %> <%= agent.getPrenom() %></td>
						<td><%= agent.getUniversite() %></td>
						<td>
							<a href="AgentUniversitaireServlet?action=update&id=<%= agent.getIdUtilisateur() %>" class="btn btn-primary">Modifier</a>
						</td>
						<td>
							<a href="AgentUniversitaireServlet?action=delete&id=<%= agent.getIdUtilisateur() %>" class="btn btn-danger">Supprimer</a>
						</td>
					</tr>
					<%
						}
					} else {
					%>
					<tr>
						<td colspan="5">Aucun administrateur universitaire trouvé.</td>
					</tr>
					<% } %>
					</tbody>


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
