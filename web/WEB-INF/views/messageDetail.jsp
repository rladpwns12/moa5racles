<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <script src="/resources/js/jquery-3.4.1.min.js" ></script>
    <script src="/resources/js/messageDetail.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/messageDetail.css">
    <sec:csrfMetaTags/>
    <title>메세지 보기</title>
</head>
<body>
    <div class ="title">
        <table>
            <tr>
                <c:if test="${messageType == 'send'}">
                    <td class="title_left">받는 사람</td>
                    <td class="title_right">${messageInfo.receiverNick}</td>
                </c:if>
                <c:if test="${messageType == 'receive'}">
                    <td class="title_left">보낸 사람</td>
                    <td class="title_right">${messageInfo.senderNick}</td>
                </c:if>
            </tr>
            <tr>
                <td class="title_left">보낸 시간</td>
                <td class="title_right">${messageInfo.sendDate} ${messageInfo.sendTime}</td>
            </tr>
        </table>
    </div>
    <div class="content">
        <textarea disabled="true" class="content_text">${messageInfo.content}</textarea>
    </div>
    <div class="bottom">

        <c:if test="${messageType == 'send'}">
            <button onclick="sendPopup('${messageInfo.receiverNick}')">재전송</button>
        </c:if>
        <c:if test="${messageType == 'receive'}">
            <button onclick="sendPopup('${messageInfo.senderNick}')">답장</button>
        </c:if>
        <button class="confirm_btn">확인</button>
        <c:choose>
            <c:when test="${messageType == 'receive'}">
                <button onclick="deleteMsg(${messageInfo.messageId},'receive')">삭제</button>
            </c:when>
            <c:when test="${messageType == 'send'}">
                <button onclick="deleteMsg(${messageInfo.messageId},'send')">삭제</button>
            </c:when>
        </c:choose>
    </div>
</body>
</html>
