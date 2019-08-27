var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

function card(historyId, price) {
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
            var form = {
                historyId: historyId,
                merchantUid: rsp.merchant_uid,
                impUid: rsp.imp_uid,
                transactionPrice: rsp.paid_amount,
                status: rsp.status,
                depositDate: new Date().getTime()
            }

            $.ajax({
                type: "POST",
                // url: "/pay/card",
                url: "/pay",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(form),
                dataType: "json",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("AJAX", true);
                    xhr.setRequestHeader(header, token);
                },
                success: function (result) {
                    if (result) {
                        alert("결제에 성공하였습니다.");
                    } else {
                        alert("결제에 실패하였습니다.");
                    }
                    location.reload();
                }
            });
        } else {
            alert("결제에 실패하였습니다.");
            msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            console.log(msg);
        }
    });
};

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
                status: rsp.status,
                depositDate: new Date().getTime()
            }

            $.ajax({
                type: "POST",
                // url: "/pay/kakao",
                url: "/pay",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(form),
                dataType: "json",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("AJAX", true);
                    xhr.setRequestHeader(header, token);
                },
                success: function (result) {
                    if (result) {
                        alert("결제에 성공 하였습니다.");
                    } else {
                        alert("결제에 실패 하였습니다.");
                    }
                    location.reload();
                }
            });
        } else {
            alert("결제에 실패 하였습니다.");
            msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            console.log(msg);
        }
    });
};
