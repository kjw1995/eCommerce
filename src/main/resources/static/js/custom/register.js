const charRegex = /[^a-zA-z0-9]/g; // 특수문자를 검사하는 정규식(숫자, 영문자 허용)
const numRegex = /[^0-9]/g; // 특수문자를 검사하는 정규식(숫자 허용)
const charUniRegex = /[^a-zA-Z0-9\u3131-\uD79D]/g; // 특수문자를 검사하는 정규식(숫자, 영문자, 특정 유니코드 범위 허용)

$(document).ready(function () {

    $("#join-btn").on('click', function (event) {

        if (checkJoinInputValidation()) {

            event.preventDefault();

            const joinRequestObj = {

                userId: $("#customerNameRegisterInput").val(),
                password: $("#customerPasswordRegisterInput").val(),
                email: $("#customerEmailRegisterInput").val(),
                phoneNumber: $("#customerPhoneNumberInput").val(),
                address: $("#customerAddressInput").val(),
                detailAddress: $("#customerDetailAddressInput").val()

            };

            $.ajax({

                url: '/users',
                method: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(joinRequestObj),
                success: function () {
                    alert("회원가입 성공!!");
                },
                error: function () {
                    alert("회원가입 실패!!");
                }

            });

        }

    });

})

$("#customerNameRegisterInput").on('input', function () {
    $(this).val($(this).val().replace(charRegex, ''));
});

$("#customerPhoneNumberInput").on('input', function () {
    $(this).val($(this).val().replace(numRegex, ''));
});

$("#customerAddressInput").on('input', function () {
    $(this).val($(this).val().replace(charUniRegex, ''));
});

function checkJoinInputValidation() {

    const fields = [{element: $("#customerNameRegisterInput"), validMessage: "아이디", invalidMessage: "아이디를 입력해주세요."}, {
        element: $("#customerPasswordRegisterInput"),
        validMessage: "비밀번호(최소4자리 이상)",
        invalidMessage: "비밀번호를 4자 이상 입력해주세요.",
        minLength: 4
    }, {
        element: $("#customerPasswordRegisterCheckInput"),
        validMessage: "비밀번호 확인",
        invalidMessage: "비밀번호 확인을 4자 이상 입력해주세요.",
        minLength: 4
    }, {
        element: $("#customerEmailRegisterInput"),
        validMessage: "이메일",
        invalidMessage: "이메일을 입력해주세요."
    }, {
        element: $("#customerPhoneNumberInput"),
        validMessage: "전화번호('-'없이 번호만입력)",
        invalidMessage: "전화번호를 입력해주세요."
    }, {
        element: $("#customerAddressInput"),
        validMessage: "주소",
        invalidMessage: "주소를 입력해주세요."
    }, {element: $("#customerDetailAddressInput"), validMessage: "상세주소", invalidMessage: "상세주소를 입력해주세요."}];

    let isValid = true;

    fields.forEach(field => {
        const value = field.element.val();
        const minLength = field.minLength || 1; // 기본 최소 길이 1
        if (!value || value.length < minLength) {
            field.element.addClass('is-invalid').removeClass('is-valid');
            $(`label[for='${field.element.attr('id')}']`).text(field.invalidMessage);
            isValid = false;
        } else {
            field.element.addClass('is-valid').removeClass('is-invalid');
            field.element.next('.invalid-feedback').remove(); // 오류 메시지 제거
            $(`label[for='${field.element.attr('id')}']`).text(field.validMessage);
        }
    });

    return isValid;

}

function checkId(element) {

    const $element = $(element);

    if ($element.val().length <= 0 || $element.val() == null) {
        $element.addClass('is-invalid').removeClass('is-valid');
        $("label[for='customerNameRegisterInput']").text("아이디");
        return;
    }

    $.ajax({

        url: '/users' + "/" + $element.val(), method: 'GET', dataType: 'json', success: function (result) {
            if (result.status === "FAILED") {
                $element.addClass('is-invalid').removeClass('is-valid');
                $("label[for='customerNameRegisterInput']").text("중복 ID 입니다.");
            } else {
                $element.addClass('is-valid').removeClass('is-invalid');
                $("label[for='customerNameRegisterInput']").text("사용가능한 ID 입니다.");
            }
        }, error: function () {
            alert("회원가입 중 문제가 발생하였습니다.");
        }

    });

}

function findAddressWithDaum() {
    new daum.Postcode({
        oncomplete: function (data) {
            console.dir(data);
            $("#customerAddressInput").val(data.address);
            $("#customerSidoAddress").val(data.sido);
            $("#customerSigunguAddress").val(data.sigungu);
        }
    }).open();
}