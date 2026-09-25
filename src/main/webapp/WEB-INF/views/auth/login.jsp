<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login - The Royal Crown Hotel</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #111111;
            color: #ffffff;

            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            width: 400px;
            padding: 40px;
            background: #1c1c1c;
            border: 1px solid #c9a227;
            border-radius: 10px;
            box-shadow: 0 0 25px rgba(201, 162, 39, 0.15);
        }

        .logo {
            text-align: center;
            margin-bottom: 30px;
        }

        .logo h1 {
            margin: 0;
            color: #c9a227;
            font-family: Georgia, serif;
            letter-spacing: 2px;
        }

        .logo p {
            margin-top: 8px;
            color: #cccccc;
            font-size: 14px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #dddddd;
        }

        .form-group input {
            width: 100%;
            padding: 12px;

            background: #292929;
            color: #ffffff;

            border: 1px solid #555555;
            border-radius: 5px;

            outline: none;
        }

        .form-group input:focus {
            border-color: #c9a227;
        }

        .btn-login {
            width: 100%;
            padding: 13px;

            background: #c9a227;
            color: #111111;

            border: none;
            border-radius: 5px;

            font-weight: bold;
            cursor: pointer;
        }

        .btn-login:hover {
            background: #e0bd45;
        }

        .error-message {
            margin-bottom: 20px;
            padding: 10px;

            background: #3a1717;
            color: #ff6b6b;

            border: 1px solid #8b3030;
            border-radius: 5px;
        }

        .success-message {
            margin-bottom: 20px;
            padding: 10px;

            background: #173a22;
            color: #6bff91;

            border: 1px solid #308b4a;
            border-radius: 5px;
        }

        .register-link {
            margin-top: 20px;
            text-align: center;
            color: #cccccc;
        }

        .register-link a {
            color: #c9a227;
            text-decoration: none;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

    <div class="login-container">

        <div class="logo">
            <h1>ROYAL CROWN</h1>
            <p>Luxury Hotel Management System</p>
        </div>

        <%-- Display login error --%>
        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>

        <div class="error-message">
            <%= error %>
        </div>

        <%
            }
        %>

        <%-- Display registration success message --%>
        <%
            String registered = request.getParameter("registered");

            if ("true".equals(registered)) {
        %>

        <div class="success-message">
            Registration successful. Please login.
        </div>

        <%
            }
        %>

        <form action="<%= request.getContextPath() %>/LoginServlet"
              method="post">

            <div class="form-group">

                <label for="email">
                    Email
                </label>

                <input type="email"
       name="email"
       value="${rememberedEmail != null ? rememberedEmail : ''}"
       required>

            </div>

            <div class="form-group">

                <label for="password">
                    Password
                </label>

                <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="Enter your password"
                    required
                >

            </div>
            <div>
    <label>
       <input type="checkbox"
       name="rememberEmail"
       ${rememberEmail == true ? 'checked' : ''}>
        Remember Email
    </label>
</div>

            <button type="submit" class="btn-login">
                LOGIN
            </button>

        </form>

        <div class="register-link">

            Don't have an account?

            <a href="<%= request.getContextPath() %>/RegisterServlet">
                Register
            </a>

        </div>

    </div>

</body>

</html>