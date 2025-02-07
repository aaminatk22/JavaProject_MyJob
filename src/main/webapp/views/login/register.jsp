<%-- Created by IntelliJ IDEA. User: hp Date: 28/12/2024 Time: 15:15 To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

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
                            <input type="text" name="nomUtilisateur" id="name" placeholder="Your Username" required />
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="fa-solid fa-envelope"></i></label>
                            <input type="email" name="email" id="email" placeholder="Your Email" required />
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="fa-solid fa-lock"></i></label>
                            <input type="password" name="motDePasse" id="pass" placeholder="Password" required />
                        </div>
                        <div class="form-group">
                            <label for="re-pass"><i class="fa-solid fa-lock"></i></label>
                            <input type="password" name="rePass" id="re-pass" placeholder="Repeat your password" required />
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                        </div>
                    </form>

                </div>
                <div class="signup-image">
                    <figure>
                        <img src="<%= request.getContextPath() %>/images/signupimg.png" alt="sign up image">
                    </figure>
                    <a href="views/login/loginpage.jsp" class="signup-image-link">I am already
                        member</a>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- JS -->
<script src="<%= request.getContextPath() %>/js/main.js"></script>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
