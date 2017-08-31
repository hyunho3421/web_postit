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
    
    $('#id').keyup(function (event) {
        if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
            var inputVal = $(this).val();
            $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
        }
    });
});

function validation() {
    var id = $("#id").val();
    if (id.length < 6) {
        alert("아이디는 6글자 이상 입력하셔야 됩니다.");

        $("#id").focus();

        return true;
    }

    if (id.length > 14) {
        alert("아이디는 최대 14글자 까지만 입력가능합니다.");

        $("#id").focus();

        return true;
    }

    if (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(id)) {
        alert("아이디는 영문, 숫자만 사용할 수 있습니다.");

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

    if (pwd.length > 14) {
        alert("패스워드는 최대 14글자 까지만 입력가능합니다.");

        $("#password").focus();

        return true;
    }

    var name = $("#name").val();
    if (name.length < 1) {
        alert("이름을 입력해주세요");

        $("#name").focus();

        return true;
    }

    var email = $("#email").val();
    if (!email.match(/[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/) || email.length < 1) {
        alert("이메일을 형식에 맞게 입력해주세요.");
         
        return true;
    }

    return false;
}


function checkExistID() {
    var id = $("#id").val();
    var isExist = false;

    $.ajax({
        type: 'get',
        url: '/signup/id/' + id,
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
