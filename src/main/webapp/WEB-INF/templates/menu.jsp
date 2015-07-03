<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="side-bar">
<sec:authorize ifNotGranted="IS_AUTHENTICATED_ANONYMOUSLY,ROLE_ADMIN,ROLE_USER" >
        <a href="<c:url value="/about.html"/>"><fmt:message key="sms.menu.about"/></a>
    </sec:authorize>
    <sec:authorize ifAllGranted="ROLE_USER">
        <p><fmt:message key="sms.menu.main.title"/></p>
        <a href="<c:url value="/numbers.html"/>"><fmt:message key="button.sender"/></a>
    </sec:authorize>
    <sec:authorize ifAllGranted="ROLE_ADMIN" >
        <a href="<c:url value="/createuser.html"/>"><fmt:message key="button.create.user"/></a>
        <a href="<c:url value="/listusers.html"/>"><fmt:message key="button.list.users"/></a>
    </sec:authorize>
    <s:url value="/j_spring_security_logout" var="logoutUrl"/>
    <sec:authorize ifAnyGranted="IS_AUTHENTICATED_ANONYMOUSLY,ROLE_ADMIN,ROLE_USER" >
        <a href="${logoutUrl}"><fmt:message key="sms.menu.logout"/></a>
    </sec:authorize>

</div>
