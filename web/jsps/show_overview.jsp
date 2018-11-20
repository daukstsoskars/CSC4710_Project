<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        td {
           text-align: center;
        }
    </style>
</head>
<body>
<h3 align="center"> Review Table </h3>
<table border="1" width="70%" align="center">
    <tr>
        <%--TODO for Part2--%>
        <%--<th>Report ID</th>--%>
        <%--<th>Submitted date</th>--%>
        <%--<th>Comment</th>--%>
        <%--<th>Recommendation</th>--%>
        <th>Paper ID</th>
        <th>Email</th>
        <th colspan=2>Options</th>

    </tr>
    <c:forEach items="${ReviewList}" var="review">
        <tr>
            <%--<td>${review.reportid }</td>--%>
            <%--<td>${review.sdate }</td>--%>
            <%--<td>${review.comment }</td>--%>
            <%--<td>${review.recommendation }</td>--%>
            <td>${review.paperid }</td>
            <td>${review.email }</td>
            <%--TODO for Part2--%>
            <%--<td><a href="UserControllerReview?action=edit&reportid=<c:out value="${review.reportid}"/>">Update</a></td>--%>
            <%--<td><a href="UserControllerReview?action=delete&reportid=<c:out value="${review.reportid}"/>">Delete</a></td>--%>
            <td>Update</td>
            <td>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>