$(document).ready(function () {
    var addrLat = 0;
    var addrLng = 0;
    var addr = $('#address').val();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    //start of find lat&lng
    function searchLocation(adr){
        var geocoder = new kakao.maps.services.Geocoder();
        var callback = function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                addrLat = result[0].y;
                addrLng = result[0].x;
            }
        };
        geocoder.addressSearch(adr, callback);
    }//-- end of find lat&lng
    searchLocation(addr);

    //-- start of find address
    $('#search_address_btn').click(function () {
        var width = 500;
        var height = 600;
        new daum.Postcode({
            width: width,
            height: height,
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
                searchLocation(addr);
            }
        }).open({
            left: (window.screen.width / 2) - (width / 2),
            top: (window.screen.height / 2) - (height / 2)
        });
    });//--end of #search_address_btn.click



    $('#identification').click(function () {
        console.log("휴대폰 인증 서비스 수리중");
    });

    //-- start of update user information
    $('#submit_btn').click(function () {
        var userVO = {
            name :$('#name').val(),
            nick :$('#nickname').val(),
            email : $('#email').val(),
            password : $('#password').val(),
            phoneNumber : $('#phone').val(),
            profile : 'profile.png'

        };
        var addressVO = {
            postCode :$('#postcode').val(),
            baseAddress:$('#address').val(),
            detailAddress:$('#detailAddress').val(),
            lat : addrLat ,
            lng : addrLng
        };

        //-- start of validation
        //1. 빈칸 검사
        if(userVO.password === '' || userVO.password === null){
            alert("비밀번호를 입력해주세요.");
            document.getElementById("password").focus();
            return;
        }
        if(userVO.detailAddress === '' || userVO.detailAddress === null){
            alert("상세 주소를 입력해주세요.");
            document.getElementById("detailAddress").focus();
            return;
        }
        //2. 기본값 검사(이름,닉네임,이메일) -- 휴대폰의 경우 변경하였을 때만
        //-- wait
        //3. 비밀번호 검사
        var pw= JSON.stringify($('#password').val());
        $.ajax({
            url:"/mypage/myinfo/check",
            type:"POST",
            contentType:"application/json",
            dataType:"json",
            data : pw,
            beforeSend: function(xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success:function(result) {
                if (result == false) {
                    alert("비밀번호가 일치하지 않습니다.");
                    return;
                }
                else{
                    $.ajax({
                        url:"/mypage/myinfo/update",
                        type:"POST",
                        data : {
                            phoneNumber : ($('#phone').val()),
                            profile : ('profile.png'),
                            postCode :($('#postcode').val()),
                            baseAddress:($('#address').val()),
                            detailAddress:($('#detailAddress').val()),
                            lat : addrLat ,
                            lng : addrLng
                        },
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("AJAX", true);
                            xhr.setRequestHeader(header, token);
                        },
                        success:function(result) {
                            if (result == true) {
                                alert("수정이 완료되었습니다.");
                            }
                            else{
                                alert("비정상적으로 수정이 완료되었습니다.");
                            }
                        },
                        error:function(request,status,error){
                            alert("데이터 전송 실패");
                        }
                    });//--end of submit
                }
            },
                error:function(request,status,error){
                    alert("[경고] 데이터 전송 실패");
                }
            });//-- end of validation
            //--start of submit

    });
    //-- end of update user information
});
