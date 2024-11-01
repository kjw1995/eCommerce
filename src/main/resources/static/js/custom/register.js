const charRegex = /[^a-zA-z0-9]/g; // 특수문자를 검사하는 정규식(숫자, 영문자 허용)
const numRegex = /[^0-9]/g; // 특수문자를 검사하는 정규식(숫자 허용)
const charUniRegex = /[^a-zA-Z0-9\u3131-\uD79D]/g; // 특수문자를 검사하는 정규식(숫자, 영문자, 특정 유니코드 범위 허용)

$("#customerNameRegisterInput").on('input', function() {
    $(this).val($(this).val().replace(charRegex, ''));
});

$("#customerPhoneNumberInput").on('input', function() {
    $(this).val($(this).val().replace(numRegex, ''));
});

$("#customerAddressInput").on('input', function() {
    $(this).val($(this).val().replace(charUniRegex, ''));
});