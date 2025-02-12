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
	<!-- Apexcharts CSS -->
	<link rel="stylesheet" href="../assets/modules/apexcharts/apexcharts.css">
</head>
<body>
<!--Topbar-->
<div class="topbar transition">
	<div class="bars">
		<button type="button" class="btn transition" id="sidebar-toggle">
			<i class="fa fa-bars"></i>
		</button>
	</div>
	<div class="menu">
		<ul>
			<li class="nav-item dropdown">
				<a class="nav-link" href="#" id="notificationsDropdown" data-bs-toggle="dropdown" aria-expanded="false">
					<i class="fa fa-bell size-icon-1"></i>
					<span class="badge bg-danger notif">4</span>
				</a>
				<div class="dropdown-menu dropdown-list">
					<div class="dropdown-header">Notifications</div>
					<div class="dropdown-list-content">
						<a href="#" class="dropdown-item dropdown-item-unread">
							<div class="dropdown-item-icon bg-primary text-white">
								<i class="fas fa-code"></i>
							</div>
							<div class="dropdown-item-desc">
								holla
								<div class="time text-primary">3 Min Ago</div>
							</div>
						</a>
						<!-- Add more notification items -->
					</div>
					<div class="dropdown-footer text-center">
						<a href="#">View All</a>
					</div>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link" href="#" id="profileDropdown" data-bs-toggle="dropdown" aria-expanded="false">
					<img src="../assets/images/avatar/avatar-1.png" alt="User Avatar">
				</a>
				<ul class="dropdown-menu" aria-labelledby="profileDropdown">
					<li>
						<a class="dropdown-item" href="my-profile.html">
							<i class="fa fa-user size-icon-1"></i> My Profile
						</a>
					</li>
					<hr class="dropdown-divider">
					<li>
						<a class="dropdown-item" href="logout.html">Logout</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>

<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate__animated animate__slideInLeft">
	<div class="sidebar-content">
		<div id="sidebar">
			<div class="logo">
				<h2 class="mb-0">
					<img src="../assets/images/logo.png" alt="MyJob Logo"> MyJob.ma
				</h2>
			</div>
			<ul class="side-menu">
				<li>
					<a href="../index.html" class="active">
						<i class='bx bxs-dashboard icon'></i> Dashboard
					</a>
				</li>
				<li>
					<a href="#">
						<i class='bx bx-columns icon'></i>
						Espace Universite
						<i class='bx bx-chevron-right icon-right'></i>
					</a>
					<ul class="side-dropdown">
						<li><a href="GestionCompteUni.jsp">Gestion</a></li>
						<li><a href="../GestionUniversite/ListeDesUniversite.html">Liste des Universite</a></li>
						<li><a href="ListeDesAdminUni.jsp">Liste des Admins Universites</a></li>
					</ul>
				</li>
				<!-- Add other sidebar items -->
			</ul>
			<div class="ads">
				<a href="#" class="btn-upgrade">Deconnexion</a>
			</div>
		</div>
	</div>
</div>

<!--Content Start-->
<div class="content-start transition">
	<div class="container-fluid dashboard">
		<div class="content-header">
			<h1>Dashboard</h1>
		</div>
		<div class="row">
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
									<th scope="col">UniId</th>
									<th scope="col">Universite</th>
									<th scope="col">Modifier</th>
									<th scope="col">Supprimer</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<th scope="row">#SK2548</th>
									<td>UAE</td>
									<td>
										<button class="btn btn-primary" onclick="editAdmin()">Modifier</button>
									</td>
									<td>
										<button class="btn btn-danger" onclick="deleteAdmin()">Supprimer</button>
									</td>
								</tr>
								</tbody>
							</table>
						</div>
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
			<p>&copy; 2024 MyJob.ma</p>
		</div>
	</div>
</footer>

<!-- Preloader -->
<div class="loader">
	<div class="spinner-border text-light" role="status" aria-label="Loading..."></div>
</div>

<!-- General JS Scripts -->
<script src="../assets/js/atrana.js"></script>
<script src="../assets/modules/jquery/jquery.min.js"></script>
<script src="../assets/modules/bootstrap-5.1.3/js/bootstrap.bundle.min.js"></script>
<script src="../assets/js/script.js"></script>
</body>
</html>
