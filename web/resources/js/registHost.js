$(document).ready(function () {
    var addrLat = 0;
    var addrLng = 0;
    var addr = $('#address').val();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

    function searchLocation(adr) {
    var geocoder = new kakao.maps.services.Geocoder();
    var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
            addrLat = result[0].y;
            addrLng = result[0].x;
        }
    };
    geocoder.addressSearch(adr, callback);
}//-- end of find lat&lng
    searchLocation(addr);

    $('input[type=radio][name=storage_type_answer]').on('click', function () {
        var chkValue = $('input[type=radio][name=storage_type_answer]:checked').val();
        if (chkValue == 'home') {
            $('#other_text').attr('disabled', true);
            $('#other_text').val('');
            $('.company_info').hide();

        } else if (chkValue == 'store' || chkValue == 'company') {
            $('#other_text').attr('disabled', true);
            $('#other_text').val('');
            $('.company_info').show();
        } else {
            $('.company_info').hide();
            $('#other_text').attr('disabled', false);
        }
    });

    $('input[type=radio][name=origin_or_new]').on('click', function () {
        var chkValue = $('input[type=radio][name=origin_or_new]:checked').val();

        if (chkValue == 'origin') {
            $('.search_address_btn').hide();
            $('.address_combo').show();
        } else {
            $('.address_combo').hide();
            $('.search_address_btn').show();
        }
    });


    $('#search_address_btn').click(function () {
        var width = 500;
        var height = 600;

        new daum.Postcode({
            width: width,
            height: height,
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
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


    })
    $('#host_apply_btn').click(function () {
        var storage_type = $('input[name=storage_type_answer]:checked').val();
        var other_text = $('#other_text').val();
        var origin_or_new = $('input[name=origin_or_new]:checked').val();
        var address_id = $('select[name=address_combo]').val();
        var postcode = $('#postcode').val();
        var address = $('#address').val();
        var detailAddress = $('#detailAddress').val();
        var company_name = $('#company_name').val();
        var company_registration_name = $('#company_registration_name').val();
        var company_representative_name = $('#company_representative_name').val();


        if(!isValid()) return;

        $.ajax({
            type: "POST",
            url: getContextPath() + "/registhost",
            data: {
                storage_type: storage_type,
                other_text: other_text,
                origin_or_new: origin_or_new,
                address_id: address_id,
                postcode: postcode,
                address: address,
                detailAddress: detailAddress,
                company_name: company_name,
                company_registration_name: company_registration_name,
                company_representative_name: company_representative_name,
                latitude: addrLat,
                longitude: addrLng
            },
            cache: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("AJAX", true);
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                console.log(data)
                if (data == "success") {
                    alert("호스트 신청이 성공적으로 완료되었습니다.");
                    location.href = "/main"
                } else {
                    alert("호스트 신청이 실패하였습니다.")
                }
            },
            error: function (e) {
                alert("error!");
            }
        });
    })

    function getContextPath() {
        var hostIndex = location.href.indexOf(location.host) + location.host.length;
        var contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
        return contextPath;
    }

    function isValid(){
        if (!isStorageTypeValid()) {
            return false;
        }
        if (!isOriginOrNewValid()) {
            return false;
        }
        if (!isNewAddressValid()){
            return false;
        }
        if (!isCompanyInfoValid()){
            return false;
        }

        return true;
    }

    function isStorageTypeValid(){
        if(!$('input:radio[name=storage_type_answer]').is(':checked')){
            alert("보관 형태를 선택해 주세요.");
            return false;
        }
        else{
            if($('input:radio[name=storage_type_answer]:checked').val() == 'other'){
                if($('#other_text').val() == ''){
                    alert("보관 형태를 입력해 주세요.");
                    $('#other_text').focus();
                    return false;
                }
            }
        }
        return true;
    }

    function isOriginOrNewValid(){
        if(!$('input:radio[name=origin_or_new]').is(':checked')){
            alert('주소 형태를 선택해 주세요.');
            return false;
        }
        return true;
    }

    function isNewAddressValid(){
        if($('input:radio[name=origin_or_new]:checked').val() == 'new') {
            if ($('#postcode').val() == '' || $('#postcode').val() == null) {
                alert("주소를 입력해 주세요.");
                return false;
            }
            if ($('#address').val() == '' || $('#address').val() == null) {
                alert("주소를 입력해 주세요.");
                return false;
            }
            if ($('#detailAddress').val() == '' || $('#detailAddress').val() == null) {
                alert("상세주소를 입력해 주세요.");
                $('#detailAddress').focus();
                return false;
            }
        }
        return true;
    }

    function isCompanyInfoValid(){
        var storage_type_answer = $('input:radio[name=storage_type_answer]:checked').val();

        if(storage_type_answer == 'company' || storage_type_answer == 'store') {
            var company_name = $('#company_name').val();
            var company_registration_name = $('#company_registration_name').val();
            var company_representative_name = $('#company_representative_name').val();

            if(company_name == '' || company_name == null){
                alert("상호명을 입력해 주세요.");
                $('#company_name').focus();
                return false;
            }
            if(company_registration_name == '' || company_registration_name == null){
                alert("사업자등록번호를 입력해 주세요.");
                $('#company_registration_name').focus();
                return false;
            }
            if(company_representative_name == '' || company_representative_name == null){
                alert("대표자명을 입력해 주세요.");
                $('#company_representative_name').focus();
                return false;
            }
        }
        return true;
    }
});