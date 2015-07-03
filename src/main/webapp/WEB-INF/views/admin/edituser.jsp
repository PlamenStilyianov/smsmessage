<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
          type='text/css'>
    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/templatemo-style.css" rel="stylesheet">
</head>
<body>
<h1><fmt:message key="user.form.title"/></h1>
<form:form modelAttribute="user" id="f">
<form:hidden path="id"/>
    <div class="templatemo-content-container">
        <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
                    <table class="table table-striped table-bordered templatemo-user-table">
                        <thead>
                        <tr>
                            <th colspan="2"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <label for="username"><h6><fmt:message key="user.username"/>:</h6></label>
                            </td>
                            <td>
                                <span class="input"><h6><input type="text" size="20" readonly id="username"
                                                           value="${user.username}"/></h6></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="userpassword"><h6><fmt:message key="user.password"/>:</h6></label>
                            </td>
                            <td>
                                <span class="input"><h6><form:input path="userpassword"/></h6></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="btn-group-justified" align="right">
                                    <div>
                                        <button   id="btnSave" class="btn-default" name="_eventId_save" ><fmt:message key="button.save"/></button>
                                        <input   type="button" value="<fmt:message key="button.cancel"/>" onclick="window.location.href='${pageContext.request.contextPath}/listusers.html';" />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
           </div>
        </div>
    </div>
</form:form>
</body>
</html>