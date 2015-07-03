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
<h1><fmt:message key="user.search.title"/></h1>
<div class="templatemo-content-container">
  <div class="templatemo-content-widget no-padding">
    <div class="panel panel-default table-responsive">

      <table class="table table-striped table-bordered templatemo-user-table">
        <thead>
        <tr>
          <th><h1><fmt:message key="user.username"/></h1></th>
          <th><h1><fmt:message key="user.password"/></h1></th>
          <th colspan="2">&nbsp;</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${pageContext.request.getAttribute(\"users\")}" varStatus="status">
          <tr valign="baseline">
            <c:set var="userFormId" value="user${status.index}"/>

            <c:url var="editUrl"  value="/edituser.html">
              <c:param name="id" value="${user.id}" />
            </c:url>

            <c:url var="deleteUrl" value="/deleteuser.html"/>
            <form id="${userFormId}" action="${deleteUrl}" method="POST">
              <input id="id" name="id" type="hidden" value="${user.id}"/>
            </form>

            <td ><h6>${user.username}</h6></td>
            <td ><h6>${user.userpassword}</h6></td>
            <td>
                 <a href='<c:out value="${editUrl}"/>'><h6><fmt:message key="button.edit"/></h6></a>
            </td>
            <td>
              <a href="javascript:document.forms['${userFormId}'].submit();"><h6><fmt:message key="button.delete"/></h6></a>

            </td>
          </tr>
        </c:forEach>
        <tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>