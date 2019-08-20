<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="/resources/js/reportSend.js" ></script>
    <link rel="stylesheet" href="/resources/css/reportSend.css"></head>
    <sec:csrfMetaTags/>
    <title>신고하기</title>
</head>
<body>
    <div class ="title">
        <table>
            <tr>
                <td class="title_left">신고자</td>

                <td id="userNick" class="title_right">
                    <sec:authentication property="principal.loginVO.nick"/>
                </td>
                <input type="hidden" id="userId" value="<sec:authentication property="principal.loginVO.userId"/>" />
            </tr>
            <tr>
                <td class="title_left">신고 대상</td>
                <td class="title_right">
                    <input id="targetNick" type="text" name="receiveNick" value="" placeholder="신고 대상 닉네임">
                </td>
            </tr>
        </table>
    </div>
    <div class="content">
        <textarea  id="content" name="content" value="" class="content_text" placeholder="신고 내용에 대해 자세히 작성해주세요."></textarea>
    </div>
    <div class="bottom">
        <button class="submit_btn">신고</button>
        <button class="cancel_btn">취소</button>
    </div>
</body>
</html>
