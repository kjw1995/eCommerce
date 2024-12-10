$(document).ready(function () {

    $("#aside-right-customer-login-btn").on('click', function () {

        const loginRequestObj = {

            id: $("#aside-right-customer-login-id").val(),
            password: $("#aside-right-customer-login-password").val()

        };

        $.ajax({

            url: "/login",
            method: "POST",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(loginRequestObj),
            success: function (response) {

                if (response.status === "SUCCESS") {
                    alert("로그인 성공");
                } else {
                    alert("로그인 실패");
                }

            },
            error: function () {
                alert("로그인 에러");
            }
        });

    });

})

