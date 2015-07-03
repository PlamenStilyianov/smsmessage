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
<form name="f" action="<c:url var="createuserUrl" value="/createuser.html" />" method="post">

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
              <label for="userName"><h6><fmt:message key="user.username"/>:</h6></label>

            </td>
            <td>
              <span class="input"><h6><input type="text"  name="userName" id="userName" /></h6></span>
            </td>
          </tr>
          <tr>
            <td>
             <label for="userPassword"><h6><fmt:message key="user.password"/>:</h6></label>
            </td>
            <td>
              <span class="input"><h6><input type="password"  name="userPassword" id="userPassword" /></h6></span>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <div class="form-buttons">
                <div class="button">
                  <input name="submit" id="submit" type="submit" value="<fmt:message key="button.save"/>"/>&#160;
                </div>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</form>
</body>
</html>