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

    <!-- Main css -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/logstyle.css">
</head>
<body>

<div class="main">

    <!-- Sign in Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="<%= request.getContextPath() %>/images/loginimg.png" alt="sign in image">
                    </figure>
                    <a href="views/login/register.jsp" class="signup-image-link">Create an account</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign up</h2>
                    <form method="post" action="${pageContext.request.contextPath}/login" class="register-form" id="login-form">
                        <div class="form-group">
                            <label for="email"><i class="fa-solid fa-user"></i></label>
                            <input type="text" name="email" id="email" placeholder="Your Email" required />
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="fa-solid fa-lock"></i></label>
                            <input type="password" name="password" id="password" placeholder="Password" required />
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="<%= request.getContextPath() %>/js/main.js"></script>
</body>
</html>
