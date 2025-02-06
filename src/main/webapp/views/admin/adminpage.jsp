<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, ma.ensi.model.Candidature" %>

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
                <h2 class="mb-0">
                MyJob.ma</h2>
            </div>

            <ul class="side-menu">
                <li>
                    <a href="href="${pageContext.request.contextPath}/admin/adminpage.jsp"
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


        <div class="col-md-12 grid-margin">
            <div class="card">
                <div class="card-body">
                    <div class="opensource">
                        <nav aria-label="breadcrumb text-dark">
                            <ol class="breadcrumb bg-primary">
                                <li class="breadcrumb-item"><a href="#">Bienvenu dans votre espace</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>

        <div class="row">

            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4 d-flex align-items-center">
                                <i class="fas fa-inbox icon-home bg-primary text-light"></i>
                            </div>
                            <div class="col-8">
                                <p>Universitees</p>
                                <h5></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4 d-flex align-items-center">
                                <i class="fas fa-clipboard-list icon-home bg-success text-light"></i>
                            </div>
                            <div class="col-8">
                                <p>Candidats</p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4 d-flex align-items-center">
                                <i class="fas fa-chart-bar  icon-home bg-info text-light"></i>
                            </div>
                            <div class="col-8">
                                <p>Annonces</p>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6 col-lg-3">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-4 d-flex align-items-center">
                                <i class="fas fa-id-card  icon-home bg-warning text-light"></i>
                            </div>
                            <div class="col-8">
                                <p>Recruteurs</p>

                            </div>
                        </div>
                    </div>
                </div>

            </div>






        </div>


            <div class="col-md-12 grid-margin">


                <div class="row justify-content-center"> <!-- Centre la ligne -->
                <div class="col-md-6 d-flex justify-content-center"> <!-- Centre la colonne -->
                    <div class="card mx-auto text-center" style="width: 100%;"> <!-- Centre la carte -->
                        <div class="card-header">
                            <h4>Voir les statistiques des candidats et annonces à chaque moment</h4>
                        </div>
                        <div class="card-body">
                            <div id="bar"></div>
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

    </div></div></body>
</html>
