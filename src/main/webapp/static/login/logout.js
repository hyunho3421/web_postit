/**
 * Created by hyunhokim on 2017. 8. 30..
 */
//로그 아웃
function logout() {
    var formObj = $("#logout");

    $.ajax({
        url: "/logout",
        type: "POST",
        data: formObj.serialize(),
        success: function (result) {
            location.reload();
        }
    });
}