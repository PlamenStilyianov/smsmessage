<%@ page import="com.fingenius.domain.Message" %>
<%@ page import="com.fingenius.domain.Receiver" %>
<%@ page import="com.fingenius.domain.Sender" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <script type="text/javascript">
        function makeVisible(){
            document.getElementById("msg").style.visibility = "visible";
        }

        function makeUnVisible(){
            document.getElementById("msg").style.visibility = "hidden";
        }

        function reloadPage(){
            location.reload();
        }

        function postMessage(msg) {
            var url = document.getElementById('smsId').value;
            var ctx = "${pageContext.request.contextPath}";

            window.location.href= ctx + url + msg;
        }
    </script>
    <script src="resources/js/angular.js" ></script>
</head>
<body ng-app="smsMessageModule" onload="makeUnVisible()">
    <script>
        angular.module('smsMessageModule', []).controller('smsMessageController', ['$scope', '$log', '$interval', '$timeout', '$http' ,function($scope, $log, $interval, $timeout, $http) {

            $scope.$log = $log;

            $scope.getCounter = function(url, number, count) {
                $scope.$log.info('in getCounter');
                $scope.uriVar = url + '/rest/counter/number';
                $scope.numberVar = '+' + number;
                $scope.countVar = count;

                $scope.$log.info($scope.numberVar + ' : ' + $scope.countVar);
                $scope.$log.info($scope.uriVar);
            };

            $scope.checkRestData = function(){
                $scope.$log.info('in checkRestData');
                if($scope.uriVar != null) {
                    $http.get($scope.uriVar).success(function (response) {
                        $scope.counter = response;
                    });

                    var obj = $scope.counter;
                    if($scope.counter != null) {
                        $scope.$log.info('json number: ' + obj["counter"].number + ' : ' + 'json count: ' + obj["counter"].count);

                        if ($scope.numberVar === obj["counter"].number && $scope.countVar !== obj["counter"].count) {
                            makeVisible();
                        }
                    }
                }
            };

            $interval(function () {
                $scope.checkRestData();
            }, 2000);

        }]);
    </script>
    <h1><fmt:message key="message.form.title"/></h1>
<div ng-controller="smsMessageController" data-ng-init="getCounter(<%= "'"+((HttpServletRequest)pageContext.getRequest()).getRequestURL().substring(0,((HttpServletRequest)pageContext.getRequest()).getRequestURL().indexOf("W")-1) + "'"%>, ${messageNumberCounter.number}, ${messageNumberCounter.count})">

    <table border="0">
        <tr><td colspan="2">&nbsp;</td></tr>
        <c:forEach var="message" items="${messages}">
            <tr><td><label><textarea rows="4" cols="50">${message.body}</textarea></label></td>
                <td><table>
                    <c:set var="number" scope="request" value="${receiver.toNumber}" />
                    <c:choose>
                      <c:when test="${number == message.fromNumber}">
                          <tr><td><h2>${message.fromNumber}</h2></td></tr>
                          <tr><td><h2><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss" value="${message.dateSend}"/></h2></td></tr>
                          <tr><td>&nbsp;</td></tr>
                      </c:when>
                      <c:when test="${number != message.fromNumber}">
                          <tr><td><h1>${message.fromNumber}</h1></td></tr>
                          <tr><td><h1><fmt:formatDate pattern="dd-MM-yyyy hh:mm:ss" value="${message.dateSend}"/></h1></td></tr>
                          <tr><td>&nbsp;</td></tr>
                      </c:when>
                    </c:choose>
                </table>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form name="form3" style="text-align: left">

        <input type=hidden id="smsId" name="smsName" value=<%= new StringBuilder("/messagesreply.html?")
                .append("From=%2B" + (((Receiver)request.getAttribute("receiver")).getToNumber()).substring(1))
                .append("&FromCountry=" + ((Receiver)request.getAttribute("receiver")).getToCountry())
                .append("&AccountSid=" + request.getAttribute("messageAccountSid"))
                .append("&MessageSid=" + ((List<Message>)request.getAttribute("messages")).get(((List<Message>)request.getAttribute("messages")).size() - 1).getMessageSid())
                .append("&SmsMessageSid=" + ((List<Message>)request.getAttribute("messages")).get(((List<Message>)request.getAttribute("messages")).size() - 1).getSmsMessageSid())
                .append("&SmsStatus=" + ((List<Message>)request.getAttribute("messages")).get(((List<Message>)request.getAttribute("messages")).size() - 1).getSmsStatus())
                .append("&SmsSid=" + ((List<Message>)request.getAttribute("messages")).get(((List<Message>)request.getAttribute("messages")).size() - 1).getSmsSid())
                .append("&To=%2B" + (((Sender)request.getAttribute("sender")).getFromNumber()).substring(1))
                .append("&ToState=" + ((Receiver)request.getAttribute("receiver")).getToState())
                .append("&ToCountry=" + ((Receiver)request.getAttribute("receiver")).getToCountry())
                .append("&Body=")
                .toString()%>
                />

        <table border=0 >
            <tr><th colspan="2" align="left">&nbsp;</th><th>&nbsp;</th></tr>
            <tr><td colspan="2"><TEXTAREA name="messageText"  rows="4" cols="50"></TEXTAREA></td><td><img id="msg" src="resources/images/new-message.png" onclick="reloadPage()" title="Click to reload the message"  height="70" width="70" ></td></tr>
            <tr><td colspan="2"><button type="button" id="POST" onclick="postMessage(document.form3.messageText.value)"><fmt:message key="message.button.post"/></button></td></tr>
        </table>
    </form>

</div>
</body>
</html>

