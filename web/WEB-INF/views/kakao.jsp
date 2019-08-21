<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
//    String name = (String) request.getAttribute("name");
//    String email = (String) request.getAttribute("email");
//    String phone = (String) request.getAttribute("phone");
//    String address = (String) request.getAttribute("address");
    int price = (int) request.getAttribute("price");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>카카오 결제창</title>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>

<body>
<script>
    $(function () {
        var IMP = window.IMP; // 생략가능
        IMP.init('imp85881502'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;

        IMP.request_pay({
            pg: 'kakaopay',
            pay_method: 'card',
            merchant_uid: 'merchant_' + new Date().getTime(),
            name: 'MOA KAKAO 결제 테스트',
            amount: <%=price%>,
            <%--buyer_email: '<%=email%>',--%>
            <%--buyer_name: '<%=name%>',--%>
            <%--buyer_tel: '<%=phone%>',--%>
            <%--buyer_addr: '<%=address%>',--%>
            // buyer_postcode: '123-456',
        }, function (rsp) {
            console.log(rsp);

            if (rsp.success) {
                msg = '결제가 완료되었습니다.';
                msg += '\n고유ID : ' + rsp.imp_uid;
                msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                msg += '\n결제 금액 : ' + rsp.paid_amount;
                msg += '\n카드 승인번호 : ' + rsp.apply_num;
                //성공시 이동할 페이지
                console.log(msg);
                console.log("카카오 결제 성공");
                // location.href = "http://localhost:8089/mypage/requestlist/1"
                return true;
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                // location.href = "http://localhost:8089/mypage/requestlist/1"
                console.log(msg);
                console.log("카카오 결제 실패");
                return false;
            }
        });
    });
</script>

</body>
</html>
