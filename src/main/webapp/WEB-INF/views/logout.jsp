<%@page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Logout Page</title>
<head>
    <script type="text/javascript">
        function reloadUrl() {
            var url = "/login.html";
            var ctx = "${pageContext.request.contextPath}";
            var fullUrl = ctx + url;
            window.setTimeout(window.location.href = fullUrl,30000);
        }
    </script>
</head>
<body onload="reloadUrl()">
<h2>You have been successfully logged out.</h2>
</body>
</html>