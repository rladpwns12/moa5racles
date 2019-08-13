(function ($) {
    'use strict';
    try {
        $('.js-datepicker').daterangepicker({
            "singleDatePicker": true,
            "showDropdowns": true,
            "autoUpdateInput": false,
            locale: {
                format: 'DD/MM/YYYY'
            },
        });

        var myCalendar = $('.js-datepicker');
        var isClick = 0;

        $(window).on('click', function () {
            isClick = 0;
        });

        $(myCalendar).on('apply.daterangepicker', function (ev, picker) {
            isClick = 0;
            $(this).val(picker.startDate.format('DD/MM/YYYY'));

        });

        $('.js-btn-calendar').on('click', function (e) {
            e.stopPropagation();

            if (isClick === 1) isClick = 0;
            else if (isClick === 0) isClick = 1;

            if (isClick === 1) {
                myCalendar.focus();
            }
        });

        $(myCalendar).on('click', function (e) {
            e.stopPropagation();
            isClick = 1;
        });

        $('.daterangepicker').on('click', function (e) {
            e.stopPropagation();
        });
    } catch (er) {
    }

    try {
        var selectSimple = $('.js-select-simple');

        selectSimple.each(function () {
            var that = $(this);
            var selectBox = that.find('select');
            var selectDropdown = that.find('.select-dropdown');
            selectBox.select2({
                dropdownParent: selectDropdown
            });
        });

    } catch (err) {
        console.log(err);
    }
})(jQuery);

var execDaumPostcode = function () {
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
            document.getElementById("detailAddress").focus()
        }
    }).open({
        left: (window.screen.width / 2) - (width / 2),
        top: (window.screen.height / 2) - (height / 2)
    });
}

function submit() {
    let name = $('#name').val();
    let nickname = $('#nickname').val();
    let email = $('#email').val();
    let password = $('#password').val();
    let password2 = $('#password2').val();
    let phone = $('#phone').val();
    let postcode = $('#postcode').val();
    let address = $('#address').val();
    let detailAddress = $('#detailAddress').val();
    let latitude = 14.02;
    let longitude = 13.001;

    let form = {
        name, nickname, email, password, password2, phone, postcode, address, detailAddress, latitude, longitude
    }

    if (!isValid(form))
        return;

    $.ajax({
        type: "POST",
        url: "registerationForm",
        data: {
            name, nickname, email, password, phone, postcode, address, detailAddress, latitude, longitude
        },
        cache: false,
        success(data) {
            if (data) {
                alert("회원가입에 성공하셨습니다.");
                $('#regForm')[0].reset();
                location.href = "/login";
            } else {
                alert("회원가입에 실패하셨습니다.");
            }
        },
        error: function () {
            alert("전송 오류 발생");
        }
    });
}

function isValid(input) {
    if (!isNameValid(input.name)) {
        $("#name").focus();
        return;
    }
    if (!isNicknameValid(input.nickname)) {
        $("#nickname").focus();
        return;
    }
    if (!isEmailValid(input.email)) {
        $("#email").focus();
        return;
    }
    if (!isPasswordValid(input.password)) {
        $("#password").focus();
        return;
    }
    if (!isPassword2Valid(input.password2, input.password)) {
        $("#password2").focus();
        return;
    }
    if (!isPhoneValid(input.phone)) {
        $("#phone").focus();
        return;
    }
    if (!isPostcodeValid(input.postcode)) {
        return;
    }
    if (!isDetailAddressValid(input.detailAddress)) {
        $("#detailAddress").focus();
        return;
    }
    return true;
}

function isNameValid(input) {
    if (!emptyCheck(input)) {
        alert("이름을 입력하세요");
        $("#name").val("");
        return false;
    }
    if (getByteLength(input) > 100) {
        alert("이름이 너무 깁니다");
        return false;
    }
    return true;
}

function isNicknameValid(input) {
    if (!emptyCheck(input)) {
        alert("닉네임을 입력하세요");
        $("#nickname").val("");
        return false;
    }
    if (getByteLength(input) > 20) {
        alert("닉네임이 너무 깁니다");
        return false;
    }
    return true;
}

function isEmailValid(input) {
    if (!emptyCheck(input)) {
        alert("이메일을 입력하세요");
        $("#email").val("");
        return false;
    }
    if (getByteLength(input) > 60) {
        alert("이메일이 너무 깁니다");
        return false;
    }
    let emailValid = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;//이메일 정규식
    if (!emailValid.test(input)) {
        alert("이메일 형식이 올바르지 않습니다.");
        $("email").focus();
        return false;
    }
    return true;
}

function isPasswordValid(input) {
    if (!emptyCheck(input)) {
        alert("비밀번호를 입력하세요");
        $("#password").val("");
        return false;
    }
    if (getByteLength(input) < 5) {
        alert("비밀번호가 너무 짧습니다");
        $("#password").val("");
        return false;
    }
    if (getByteLength(input) > 20) {
        alert("비밀번호가 너무 깁니다");
        $("#password").val("");
        return false;
    }

    // let passwordValid = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{5, 20}$/;
    let passwordValid = /^[A-Za-z0-9]{5,20}$/;
    if (!passwordValid.test(input)) {
        alert("비밀번호 형식이 올바르지 않습니다.");
        $("#password").val("");
        $("#password").focus();
        return false;
    }
    return true;
}

function isPassword2Valid(input1, input2) {
    if (!emptyCheck(input1)) {
        alert("비밀번호를 입력하세요");
        $("#password2").val("");
        return false;
    }
    if (input1 != input2) {
        alert("비밀번호가 일치하지 않습니다");
        $("#password2").val("");
        return false;
    }
    return true;
}

function isPhoneValid(input) {
    if (!emptyCheck(input)) {
        alert("휴대폰 인증이 필요합니다");
        return false;
    }
    /*let phoneValid = /^\d{3}-\d{3,4}-\d{4}$/;
    if (!phoneValid.test(input)) {
        alert("휴대폰 번호가 올바르지 않습니다.");
        return false;
    }*/
    return true;
}

function isPostcodeValid(input) {
    if (!emptyCheck(input)) {
        alert("우편번호를 입력하세요");
        return false;
    }
    return true;
}

function isDetailAddressValid(input) {
    if (!emptyCheck(input)) {
        alert("상세주소을 입력하세요");
        return false;
    }
    if (getByteLength(input) > 200) {
        alert("상세주소가 너무 깁니다.");
        return false;
    }
    return true;
}

function emptyCheck(input) {
    if (input == null || input.trim() == "")
        return false;
    return true;
}

function getByteLength(input) {
    let byte = 0;
    for (var idx = 0; idx < input.length; idx++) {
        let c = escape(input.charAt(idx));
        if (c.length == 1) {
            byte++;
        } else if (c.indexOf("%u") != -1) {
            byte += 3;
        } else if (c.indexOf("%") != -1) {
            byte += c.length / 3;
        }
    }
    return byte;
}
