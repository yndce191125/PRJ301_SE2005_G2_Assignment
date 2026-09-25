<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Register - The Royal Crown Hotel</title>

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

        .register-container {

            width: 450px;

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

            margin-bottom: 18px;
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

        .btn-register {

            width: 100%;

            padding: 13px;

            background: #c9a227;

            color: #111111;

            border: none;

            border-radius: 5px;

            font-weight: bold;

            cursor: pointer;
        }

        .btn-register:hover {

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

        .login-link {

            margin-top: 20px;

            text-align: center;

            color: #cccccc;
        }

        .login-link a {

            color: #c9a227;

            text-decoration: none;
        }

        .login-link a:hover {

            text-decoration: underline;
        }

    </style>

</head>

<body>

    <div class="register-container">

        <div class="logo">

            <h1>ROYAL CROWN</h1>

            <p>
                Create your account
            </p>

        </div>


        <%-- Display registration error --%>

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


        <form action="<%= request.getContextPath() %>/RegisterServlet"
              method="post">


            <div class="form-group">

                <label for="fullName">
                    Full Name
                </label>

                <input
                    type="text"
                    id="fullName"
                    name="fullName"
                    placeholder="Enter your full name"
                    required
                >

            </div>


            <div class="form-group">

                <label for="email">
                    Email
                </label>

                <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="Enter your email"
                    required
                >

            </div>


            <div class="form-group">

                <label for="phone">
                    Phone
                </label>

                <input
                    type="tel"
                    id="phone"
                    name="phone"
                    placeholder="Enter your phone number"
                >

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


            <div class="form-group">

                <label for="confirmPassword">
                    Confirm Password
                </label>

                <input
                    type="password"
                    id="confirmPassword"
                    name="confirmPassword"
                    placeholder="Confirm your password"
                    required
                >

            </div>


            <button
                type="submit"
                class="btn-register">

                REGISTER

            </button>

        </form>


        <div class="login-link">

            Already have an account?

            <a href="<%= request.getContextPath() %>/LoginServlet">

                Login

            </a>

        </div>

    </div>

</body>

</html>