<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: oskars
  Date: 11/17/18
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script>
        localStorage.clear();
    </script>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {
            border: 3px solid #f1f1f1;
            width: 50%;
            margin: 0 auto;
        }

        h2{
            margin-top: 100px;
            text-align: center;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }


        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }

        img.avatar {
            width: 40%;
            border-radius: 50%;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
        }
        p {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Login Form</h2>
<p style="color: red; font-weight: 900"> ${msg}</p>
    <c:choose>
        <c:when test="${empty sessionScope.session_user }">
            <form action="<c:url value='LoginServ'/>" method="post">
                <div class="imgcontainer">
                    <img src="jsps/img_avatar2.png" alt="Avatar" class="avatar">
                </div>
                    <div class="container">
                        <input type="hidden" name="method" value="login"/>
                        <label><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" required>

                        <label><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>

                        <input type="submit" value="Login"/>
                    </div>
            </form>
        </c:when>
        <c:otherwise>
            Hello：${sessionScope.session_user.username };
            <a href="<c:url value='/jsps/post_login.jsp'/>" target="_parent">START HERE</a>
        </c:otherwise>

    </c:choose>


</body>
</html>
