<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <style type="text/css">
        A:link {text-decoration: none}
        A:visited {text-decoration: none}
        A:active {text-decoration: none}
        A:hover {font-size:inherit; font-weight:bold; color: red;}
    </style>

</head>
<body>

    <div align="right">
        <div>
            <fmt:message key="button.locale"/>:
            <c:url var="englishLocaleUrl" value="/login.html">
                <c:param name="locale" value="" />
            </c:url>
            <c:url var="spanishLocaleUrl" value="/login.html">
                <c:param name="locale" value="es" />
            </c:url>

            <a href='<c:out value="${englishLocaleUrl}"/>' class="hyperlink"><fmt:message key="locale.english"/></a>
            <a href='<c:out value="${spanishLocaleUrl}"/>'><fmt:message key="locale.spanish"/></a>
        </div>

        <div>&nbsp;</div>

        <div><fmt:message key="site.footer"/></div>
    </div>
</body>
</html>