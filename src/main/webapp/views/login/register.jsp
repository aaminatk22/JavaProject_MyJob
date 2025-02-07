<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form</title>

    <!-- Font Awesome for Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

    <!-- Main CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/logstyle.css">
</head>
<body>

<div class="main">
    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <form method="post" action="${pageContext.request.contextPath}/register" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="name"><i class="fa-solid fa-user"></i></label>
                            <input type="text" name="nomUtilisateur" id="name" placeholder="Votre nom d'utilisateur" required />
                        </div>

                        <div class="form-group">
                            <label for="email"><i class="fa-solid fa-envelope"></i></label>
                            <input type="email" name="email" id="email" placeholder="Votre Email" required />
                        </div>

                        <div class="form-group">
                            <label for="pass"><i class="fa-solid fa-lock"></i></label>
                            <input type="password" name="motDePasse" id="pass" placeholder="Mot de passe" required />
                        </div>

                        <div class="form-group">
                            <label for="re-pass"><i class="fa-solid fa-lock"></i></label>
                            <input type="password" name="rePass" id="re-pass" placeholder="Confirmer le mot de passe" required />
                        </div>

                        <!-- Sélection du rôle -->
                        <div class="form-group">
                            <label for="role"><i class="fa-solid fa-user-tag"></i></label>
                            <select name="role" id="role" required>
                                <option value="" disabled selected>Choisir un rôle</option>
                                <option value="Candidat">Candidat</option>
                                <option value="Recruteur">Recruteur</option>
                            </select>
                        </div>

                        <!-- Champs pour Candidat -->
                        <div id="candidat-fields" style="display: none;">
                            <div class="form-group">
                                <label for="nomUniversite"><i class="fa-solid fa-school"></i></label>
                                <input type="text" name="nomUniversite" id="nomUniversite" placeholder="Nom de l'université" />
                            </div>
                            <div class="form-group">
                                <label for="niveauEtude"><i class="fa-solid fa-graduation-cap"></i></label>
                                <input type="text" name="niveauEtude" id="niveauEtude" placeholder="Niveau d'étude" />
                            </div>
                            <div class="form-group">
                                <label for="age"><i class="fa-solid fa-calendar"></i></label>
                                <input type="number" name="age" id="age" placeholder="Âge" />
                            </div>
                        </div>

                        <!-- Champs pour Recruteur -->
                        <div id="recruteur-fields" style="display: none;">
                            <div class="form-group">
                                <label for="entreprise"><i class="fa-solid fa-building"></i></label>
                                <input type="text" name="entreprise" id="entreprise" placeholder="Nom de l'entreprise" />
                            </div>
                            <div class="form-group">
                                <label for="poste"><i class="fa-solid fa-briefcase"></i></label>
                                <input type="text" name="poste" id="poste" placeholder="Poste occupé" />
                            </div>
                        </div>

                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="S'inscrire" />
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure>
                        <img src="<%= request.getContextPath() %>/images/signupimg.png" alt="sign up image">
                    </figure>
                    <a href="<%= request.getContextPath() %>/views/login/loginpage.jsp" class="signup-image-link">I am already member</a>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- JS -->
<script>
    document.getElementById("role").addEventListener("change", function () {
        let role = this.value;
        document.getElementById("candidat-fields").style.display = (role === "Candidat") ? "block" : "none";
        document.getElementById("recruteur-fields").style.display = (role === "Recruteur") ? "block" : "none";
    });
</script>


</body>
</html>
