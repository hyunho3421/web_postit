/**
 * Created by hyunhokim on 2017. 4. 18..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");

    $('#btnSignUp').on("click", function () {

        if (validation()) {
            return;
        }

        formObj.attr("method", "POST");
        formObj.attr("action", "/signup");

        formObj.submit();
    });

    $('#btnSignIn').on("click", function () {
        location.href = "/signin";
    });
});

function validation() {
    var id = $("#id").val();
    if (id.length < 6) {
        alert("아이디는 6글자 이상 입력하셔야 됩니다.");

        $("#id").focus();

        return true;
    }

    if (checkExistID()) {

        alert("이미 존재하는 아이디입니다.");

        $("#id").focus();

        return true;
    }

    var pwd = $("#password").val();
    if (pwd.length < 6) {
        alert("패스워드는 6글자 이상 입력하셔야 됩니다.");

        $("#password").focus();

        return true;
    }

    var name = $("#name").val();
    if (name.length < 1) {
        alert("이름을 입력해주세요");

        $("#name").focus();

        return true;
    }

    return false;
}


function checkExistID() {
    var id = $("#id").val();
    var isExist;

    $.ajax({
        type: 'get',
        url: '/user/join/id/' + id,
        async: false,
        success: function (result) {
            if (result == 'existID') {
                isExist = true;
            } else {
                isExist = false;
            }
        }
    });

    return isExist;
}
