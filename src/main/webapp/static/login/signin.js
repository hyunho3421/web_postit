/**
 * Created by hyunhokim on 2017. 6. 6..
 */
$(document).ready(function () {
    var formObj = $("form[role='form']");

    $("#btnLogin").on("click", function () {
        formObj.attr("method", "POST");
        formObj.submit();
    });

    //엔터키 눌렀을 로그인 되도록
    $("#btnLogin").keypress(function (e) {
        if (e.keyCode == 13) {
            formObj.attr("method", "POST");
            formObj.attr("action", "/login");
            formObj.submit();
        }
    });


});