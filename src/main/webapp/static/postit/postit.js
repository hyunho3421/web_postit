/**
 * Created by hyunhokim on 2017. 6. 6..
 */
var css_postit_idx = 1;

$(document).ready(function () {

    //
    // CKEDITOR.replace('ckeditor', {
    //     // filebrowserUploadUrl:'/uploadCkeditor' + csrf
    // });

    // CKEDITOR.on('instanceLoaded', function (e) {
    //     e.editor.resize($(".post-it").width, $(".post-it").height)
    // });

    // TODO: 데이터베이스가 비었을 경우 생성하도록 수정예정.
    // create_postit();

    $(".post-it")
        .resizable({
            helper: "ui-resizable-helper",

        })
        .draggable({
            opacity: 0.8
        })
        .mousedown(function () {    // mousedown 이벤트 생성
            $(this).css('z-index', css_postit_idx); // 클릭한 이미지만 z-index 증가시킴
            css_postit_idx++;   // 그러면 이미지가 겹칠경우 클릭한 것이 항상 위에 표시됨
        })
        .css('z-index', css_postit_idx);    // 생성시에 최상단으로 나오도록 설정

    $(".plus").on("click", function () {
        create_postit();
    });

    $(".modify").on("click", function () {
        // ajax로 수정 내용 변경

        $(this).closest(".post-it").find(".post-it_editor").toggle();
        $(this).closest(".post-it").find(".save").toggle();

        // 수정 버튼 토글
        $(this).toggle();
    });

    $(".remove").on("click", function () {
        // 삭제하는게 마지막 포스트잇이면 새 포스트잇 한개 추가.
        var postit_count = $(".container").children(".post-it").length;

        if (postit_count == 1) {
            create_postit();
        }

        var postit = $(this).closest(".post-it");
        postit.remove();
    });

    $(".save").on("click", function () {
        $(this).toggle();
        $(this).closest(".post-it").find(".modify").toggle();
        $(this).closest(".post-it").find(".post-it_editor").toggle();
    });

    $(".post-it_editor").toggle();
});

// 이벤트 바인딩 함수
function postit_event_binding(last_postit) {

    last_postit
        .resizable()
        .draggable({
            opacity: 0.8
        })
        .mousedown(function () {    // mousedown 이벤트 생성
            $(this).css('z-index', css_postit_idx); // 클릭한 이미지만 z-index 증가시킴
            css_postit_idx++;   // 그러면 이미지가 겹칠경우 클릭한 것이 항상 위에 표시됨
        })
        .css('z-index', css_postit_idx);    // 생성시에 최상단으로 나오도록 설정

    last_postit.find(".plus").on("click", function () {
        create_postit();
    });

    last_postit.find(".modify").on("click", function () {
        // ajax로 수정 내용 변경

        $(this).closest(".post-it").find(".post-it_editor").toggle();
        $(this).closest(".post-it").find(".save").toggle();

        // 수정 버튼 토글
        $(this).toggle();
    });

    last_postit.find(".remove").on("click", function () {
        // 삭제하는게 마지막 포스트잇이면 새 포스트잇 한개 추가.
        var postit_count = $(".container").children(".post-it").length;

        if (postit_count == 1) {
            create_postit();
        }

        var postit = $(this).closest(".post-it");
        postit.remove();
    });

    last_postit.find(".save").on("click", function () {

        var postit = $(this).closest(".post-it");
        $(this).toggle();
        postit.find(".modify").toggle();
        postit.find(".post-it_editor").toggle();

        save4ajax(postit);
    });

    last_postit.find(".config").on("click", function () {
        console.log("click config class");
    });
}

// 포스트잇 생성 함수
function create_postit() {
    // 새 포스트 잇 생성
    var new_postit = "";
    new_postit +=
        "<div class='post-it'>"
        + "<div class='header'>"
        + "<div class='content'>"
        + "<div class='plus'>"
        + "<span class='glyphicon glyphicon-plus'></span>"
        + "</div>"
        + "&nbsp"
        + "<div class='modify' style='display: none;'>"
        + "<span class='glyphicon glyphicon-pencil'></span>"
        + "</div>"
        + "<div class='save'>"
        + "<span class='glyphicon glyphicon-ok'></span>"
        + "</div>"
        + "&nbsp"
        + "<dic class='config'>"
        + "<span class='glyphicon glyphicon-cog'></span>"
        + "</dic>"
        + "<div class='remove'>"
        + "<span class='glyphicon glyphicon-remove'></span>"
        + "</div>"
        + "</div>"
        + "</div>"
        + "<div class='content'>"
        + "<form role='form' method='post' action='' class='post-it_editor'>"
        + "<input type='hidden' name='${_csrf.parameterName}' value='${_csrf.token}' id='csrf'/>"
        + "<div class='form-group'>"
        + "<textarea name='content' id='ckeditor' cols='15' rows='5'></textarea>"
        + "</div>"
        + "</form>"
        + "</div>";

    $(".container").append(new_postit);

    var last_postit = $(".container").children(":last");

    postit_event_binding(last_postit);
}

function save4ajax(postit) {

    var csrf = "?" + $("#csrf").attr("name") + "=" + $("#csrf").val();
    var content = postit.find("#ckeditor").val()
    var pos = postit.position();
    var pos_x = pos.left;
    var pos_y = pos.top;

    console.log(csrf);
    console.log(pos_x);
    console.log(pos_y);

    $.ajax({
        url: "/postit/save" + csrf,
        type: "POST",
        headers : {
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "POST"
        },
        data: JSON.stringify({
            content : content,
            pos_x : pos_x,
            pos_y : pos_y
        }),
        success: function () {

        }
    });
}

