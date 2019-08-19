<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-08-15
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="/resources/js/upload.js"></script>
<link rel="stylesheet" href="/resources/css/upload.css">

<html>
<head>
    <sec:csrfMetaTags/>
</head>
<body>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">보관할 물품의 사진을 첨부해주세요. 나중에 el로 대체하여 보관글, 보관 요청글 동시 쓸 예정</div>
                <div class="panel-body">
                    <div class="form-group uploadDiv">
                        <input type="file" name="uploadFile" id="storeBoard" multiple>
                    </div>

                    <div class="uploadResult">
                        <ul>

                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
</html>
