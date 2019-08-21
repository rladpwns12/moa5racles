function card(price) {
    var IMP = window.IMP; // 생략가능
    IMP.init('imp85881502'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    var msg;

    IMP.request_pay({
        pg: 'inicis',
        pay_method: 'card',
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: 'MOA 카드 결제 테스트',
        amount: price,
    }, function (rsp) {
        console.log(rsp);

        if (rsp.success) {
            msg = '결제가 완료되었습니다.';
            msg += '\n고유ID : ' + rsp.imp_uid;
            msg += '\n상점 거래ID : ' + rsp.merchant_uid;
            msg += '\n결제 금액 : ' + rsp.paid_amount;
            msg += '\n카드 승인번호 : ' + rsp.apply_num;
            //성공시 이동할 페이지
            // location.href = "http://localhost:8089/mypage/requestlist/1";
        } else {
            msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            //실패시 이동할 페이지
            // location.href = "http://localhost:8089/mypage/requestlist/1"
            alert(msg);
        }
    });
};

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function kakao(historyId, price) {
    var IMP = window.IMP; // 생략가능
    IMP.init('imp85881502'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    var msg;

    IMP.request_pay({
        pg: 'kakaopay',
        pay_method: 'card',
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: 'MOA KAKAO 결제 테스트',
        amount: price,
    }, function (rsp) {
        console.log(rsp);

        if (rsp.success) {
            var form = {
                historyId: historyId,
                merchantUid: rsp.merchant_uid,
                impUid: rsp.imp_uid,
                transactionPrice: rsp.paid_amount,
                status: rsp.status
            }
            console.log("결제가 완료되었습니다.");
            console.log(form);

            $.ajax({
                type: "POST",
                url: "/pay/kakao",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(form),
                dataType: "json",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("AJAX", true);
                    xhr.setRequestHeader(header, token);
                },
                success: function (result) {
                    console.log("js.result: " + result);
                    if (result) {
                        alert("DB까지 갔다오기 성공");
                    } else {
                        alert("DB까지 갔다오기 실패");
                    }
                    location.reload();
                }
            });
        } else {
            msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            console.log(msg);
        }
    });
};
