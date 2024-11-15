const charRegex = /[^a-zA-z0-9]/g; // 특수문자를 검사하는 정규식(숫자, 영문자 허용)
const numRegex = /[^0-9]/g; // 특수문자를 검사하는 정규식(숫자 허용)
const charUniRegex = /[^a-zA-Z0-9\u3131-\uD79D]/g; // 특수문자를 검사하는 정규식(숫자, 영문자, 특정 유니코드 범위 허용)

$(document).ready(function(){

    $(".register-form").on('input change', function() {

        if(checkJoinInputValidation()) {
            $("#join-btn").removeAttr("disabled");
        }

    });

    $("#join-btn").on('click', function(event) {

        event.preventDefault();

        const joinRequestObj = {

            userId: $("#customerNameRegisterInput").val(),
            password: $("#customerPasswordRegisterInput").val(),
            email: $("#customerEmailRegisterInput").val(),
            phoneNumber: $("#customerPhoneNumberInput").val(),
            address: $("#customerAddressInput").val()

        };

        $.ajax({

            url: '/users',
            method: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(joinRequestObj),
            success: function() {
                alert("회원가입 성공!!");
            },
            error: function() {
                alert("회원가입 실패!!");
            }

        });

    });

})

$("#customerNameRegisterInput").on('input', function() {
    $(this).val($(this).val().replace(charRegex, ''));
});

$("#customerPhoneNumberInput").on('input', function() {
    $(this).val($(this).val().replace(numRegex, ''));
});

$("#customerAddressInput").on('input', function() {
    $(this).val($(this).val().replace(charUniRegex, ''));
});

function checkJoinInputValidation() {

    const idInputVal = $("#customerNameRegisterInput").val();
    const pwInputVal = $("#customerPasswordRegisterInput").val();
    const pwCheckInputVal = $("#customerPasswordRegisterCheckInput").val();
    const emailInputVal = $("#customerEmailRegisterInput").val();
    const phoneNumberInputVal = $("#customerPhoneNumberInput").val();
    const addressInputVal = $("#customerAddressInput").val();

    if (!idInputVal || !pwInputVal || pwInputVal.length < 4 || !pwCheckInputVal || pwCheckInputVal.length < 4 || !emailInputVal || !phoneNumberInputVal || !addressInputVal) {
        return false;
    }

    return true;

}

function checkId() {

    $.ajax({



    });



}