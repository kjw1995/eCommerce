$(document).ready(function () {

    $(".aside-right-login-form").on('input change', function () {

        $("#aside-right-login-form-resultmsg").remove();

    });

    $("#aside-right-customer-login-btn").on('click', function () {

        if ($("#aside-right-login-form-resultmsg").length) {
            $("#aside-right-login-form-resultmsg").remove();
        }

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

                    location.href = URL_CONSTANTS.MAIN;

                } else {

                    $(".form-label-fixed").append(`<text id='aside-right-login-form-resultmsg'>` + response.message + `</text>`);
                }

            },
            error: function () {
                alert("로그인 에러");
            }
        });

    });

    $("#aside-right-customer-logout-btn").on("click", function (event) {

        event.preventDefault();

        $.ajax({

            url: '/logout',
            method: 'POST',
            dataType: 'json',
            success: function (response) {

                if (response.status === "SUCCESS") {

                    location.href = URL_CONSTANTS.MAIN;

                } else {
                    alert("로그아웃 실패");
                }

            },
            error: function () {
                alert("로그아웃 에러");
            }

        });

    });

})

