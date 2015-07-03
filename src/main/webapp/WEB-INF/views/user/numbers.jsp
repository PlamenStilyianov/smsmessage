<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/templatemo-style.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="number.form.title"/></h1>
<div class="templatemo-content-container">
    <div class="templatemo-content-widget no-padding">
        <div class="panel panel-default table-responsive">
            <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                <tr>
                    <th><h6><fmt:message key="message.from.number"/></h6></th>
                    <th><h6><fmt:message key="customer.number.form.title"/></h6></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sender" items="${pageContext.request.getAttribute(\"senders\")}" varStatus="status">
                    <tr >
                        <c:url var="numberUrl" value="/messages.html">
                           <c:param name="number" value="${sender.fromNumber}" />
                        </c:url>
                        <td>
                            &nbsp;
                        </td>
                        <td >
                            <a class="" href='<c:out value="${numberUrl}"/>'><h6><c:out value="${sender.fromNumber}" /></h6></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>


