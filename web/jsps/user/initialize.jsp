<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <style>
        input{
            margin: 5px;
        }
    </style>
    <script>
        var buttonClick = 0;
        function hide() {
            if(localStorage.buttonClick == 1){
                document.getElementById("initializeDB").style.display = "none";
                document.getElementById("InitDatabase").innerText = "Database is Initialized";
            }
        }

        function increment(){
            localStorage.buttonClick = 1;
        }
    </script>

    <title>Initialize Database</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">


</head>

<body onload="hide()">
    <div style="text-align: center; width:500px; display: block; border-style: solid;padding: 10px;
    margin: 0 auto;" id="one">
        <h1 id="InitDatabase"> Initialize Database:</h1>

        <form action="<c:url value='/InitializeDBServ'/>" method="post">
            <button onclick="increment()" type="submit" id="initializeDB" value="InitButton">Initialize</button>
        </form>
        <hr>
        <h1>Assign reviewers to paper.</h1><br>
        <p>To assign at most three reviewers to a Paper: </p>


        <form action="<c:url value='/ReviewServ'/>" method="post">
            <label> Email 1: </label>
            <input type="text" name="email1" value = ""/>
            <br>

            <label> Email 2: </label>
            <input type="text" name="email2" value = ""/>

            <br>
            <label> Email 3: </label>
            <input type="text" name="email3" value = ""/>
            <br>
            <label>Paper Id: </label>
            <input type = "text" name = "paperid" value = ""/>
            <br>
            <input type="submit" value="Submit" name ="sbtrv" />
        </form>
    </div>
</body>
</html>