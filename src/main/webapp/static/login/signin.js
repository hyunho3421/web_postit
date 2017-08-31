/**
 * Created by hyunhokim on 2017. 6. 6..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");

    $("#btnLogin").on("click", function () {
        Login();
    });

    //엔터키 눌렀을 로그인 되도록
    $("#btnLogin").keypress(function (e) {
        if (e.keyCode == 13) {
            Login();
        }
    });
});

function Login() {
    var formObj = $("form[role='form']");

    if (validation()) {
        return;
    }

    formObj.attr("method", "POST");
    formObj.submit();
}

function validation() {
    var id = $("#id").val();

    if (id.length < 1) {
        alert("아이디를 입력하세요");

        $("#id").focus();

        return true;
    }

    var pwd = $("#password").val();
    if (pwd.length < 1) {
        alert("패스워드를 입력하세요.");

        $("#password").focus();

        return true;
    }
}