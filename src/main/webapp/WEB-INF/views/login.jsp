<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<html>
<head>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/templatemo-style.css" rel="stylesheet">

</head>

<body >

<div class="templatemo-content-widget templatemo-login-widget light-green">
    <header class="text-center">
        <h3><fmt:message key="login.header"/></h3>
    </header>
    <c:if test="${not empty param.login_error}">
    <font color="red">
        <fmt:message key="login.msg.error.user"/><br/><br/>
        <fmt:message key="login.msg.error.reason"/><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
    </font>
    </c:if>
    <c:url value="/j_spring_security_check" var="UrlPage" />
    <form name="f" action="${UrlPage}" method="POST"  class="templatemo-login-form">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>
                <input type="text" class="form-control" placeholder="abc@fingenius.com" name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>
                <input type="password" name='j_password' class="form-control" placeholder="******">
            </div>
        </div>
        <div class="form-group" >
            <div align="left">
                <button type="submit"  class="button-style"><fmt:message key="button.login"/></button>
            </div>
            <div align="center">
                <button type="reset"  class="button-style"><fmt:message key="button.reset"/></button>
            </div>
        </div>
        <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
    </form>
</div>

</body>
</html>