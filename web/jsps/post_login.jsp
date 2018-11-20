<%--
  Created by IntelliJ IDEA.
  User: oskars
  Date: 11/13/18
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <script>
    function hide() {
        localStorage.databaseInit = "true";
        document.getElementById("initializeButton").style.color = "#C3C3C3";
        document.getElementById("initializeButton").style.background = "#F5F5F5";
        document.getElementById("initializeButton").innerText = "Database is Initialized";
    }
    function startUp() {
        if(localStorage.databaseInit == "true"){
            hide();
            document.getElementById("assignReviewers").style.display = "block";
        }
        else{
            document.getElementById("showReview").disabled = "true";
        }
    }
  </script>
  <style>
    h1{
      text-align: center;
    }

    a{
      margin: 5px;
      padding: 5px;
      border: solid 2px;
    }
    label{
      margin-right: 5px;
    }
    input{
      margin: 5px;
    }
    #assignReviewers{
      display: none;
    }
    button:disabled{
      background: #F5F5F5;
      color : #C3C3C3;
    }
  </style>

  <title>main</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <meta http-equiv="content-type" content="text/html;charset=utf-8">

</head>

<body onload="startUp()">
  <h1>Welcome to CSC 4710 Demo!</h1>
    <hr>
    <div style=" width: 100%; text-align: center; margin-top: 50px">
      <form action="<c:url value='/InitializeDBServ'/>" method="post">
          <button id="initializeButton" onclick="hide()" type="submit" value="InitButton">Initialize Database</button>
          <button id="showReview" type="submit" value="InitButton" formaction="/ReviewServ">Review Table</button>
          <button id="showPapers" type="submit" formaction="/PaperServ">Paper Table</button>
        </form>
      <hr>
      <div id="assignReviewers">
        <p>Assign at most three reviewers to a Paper: </p>
        <form action="<c:url value='/AssignPaperToReviewersServ'/>" method="post">
          <p style="color: red; font-weight: 900"> ${author1}</p>
          <label> Email 1: </label>
          <input type="text" name="email1" value = ""/>
          <br>

          <p style="color: red; font-weight: 900"> ${author2}</p>
          <label> Email 2: </label>
          <input type="text" name="email2" value = ""/>

          <br>
          <p style="color: red; font-weight: 900"> ${author3}</p>
          <label> Email 3: </label>
          <input type="text" name="email3" value = ""/>

          <br>
          <p style="color: red; font-weight: 900"> ${overflow}</p>
          <label>Paper Id: </label>
          <input type = "text" name = "paperid" value = ""/>
          <br>
          <input type="submit" value="Submit" name ="entries" />
        </form>
      </div>
    </div>
</body>
</html>