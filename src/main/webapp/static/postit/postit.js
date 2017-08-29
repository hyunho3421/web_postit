/**
 * Created by hyunhokim on 2017. 6. 6..
 */
var css_postit_idx = 1;
var color;

$(document).ready(function () {

    // color Class 생성
    color = new Color();

    list4Ajax();

    // TODO: CKEDITOR 추가 예정.
    // CKEDITOR.replace('ckeditor', {
    //     // filebrowserUploadUrl:'/uploadCkeditor' + csrf
    // });

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
        var postit = $(this).closest(".post-it");
        postit.find(".post-it_editor").toggle();
        postit.find(".post-it_view").toggle();
        postit.find(".save").toggle();     //저장 버튼 토글
        $(this).toggle();                                       // 수정 버튼 토글

        // textarea 크기 포스트잇 크기에 맞추기
        postit.find(".post-it_editor textarea").css({
            width: postit.width() - 10 + "px",
            height: postit.height() - 40 + "px"
        });
    });

    last_postit.find(".remove").on("click", function () {
        // 삭제하는게 마지막 포스트잇이면 새 포스트잇 한개 추가.
        var postit_count = $(".container").children(".post-it").length;

        if (postit_count == 1) {
            create_postit();
        }

        var postit = $(this).closest(".post-it");

        remove4ajax(postit)

        postit.remove();
    });

    last_postit.find(".save").on("click", function () {
        var postit = $(this).closest(".post-it");

        $(this).toggle();                   // 저장 버튼 토글
        postit.find(".modify").toggle();    // 수정 버튼 토글
        postit.find(".post-it_editor").toggle();
        postit.find(".post-it_view").toggle();

        save4ajax(postit);
    });

    last_postit.find(".config").on("click", function () {
        $(this).closest(".post-it").find(".mod_config").toggle();
    });

    last_postit.find(".calendar").on("click", function () {
        // TODO: 달력 눌렀을때 날짜 입력 기능 구현
        // TODO: datepicker로 구현
        // TODO: 입력된 날짜는 content 최상단에 보이게 함.
        // TODO: 참고 : https://zetawiki.com/wiki/JQuery_UI_%EB%82%A0%EC%A7%9C%EC%84%A0%ED%83%9D%EA%B8%B0_datepicker
    });

    // 포스트잇 이동 시 저장
    last_postit.on("dragstop", function () {
        save4ajax($(this));
    });

    // 포스트잇 사이즈 변경 시 저장
    last_postit.on("resizestop", function () {
        save4ajax($(this));

        //포스트잇 사이즈 맞춰서 textarea 사이즈도 변경
        $(this).find(".post-it_editor textarea").css({
            width: $(this).width() - 10 + "px",
            height: $(this).height() - 40 + "px"
        });
    });

    last_postit.find(".blue").on("click", function () {
        color.changeColor($(this));
    });

    last_postit.find(".orange").on("click", function () {
        color.changeColor($(this));
    });

    last_postit.find(".green").on("click", function () {
        color.changeColor($(this));
    });
}

// 포스트잇 생성 함수
function create_postit() {
    // 새 포스트 잇
    var new_postit = makePostit(this);

    $(".container").append(new_postit);

    var last_postit = $(".container").children(":last");

    create4ajax(last_postit);

    postit_event_binding(last_postit);
}

function save4ajax(postit) {

    var csrf = "?" + $("#csrf").attr("name") + "=" + $("#csrf").val();
    var id = postit.attr("data-id");
    var content = postit.find("#ckeditor").val();
    var pos = postit.position();
    var pos_x = pos.left;
    var pos_y = pos.top;
    var h_color = postit.find(".header").css("background-color");
    var c_color = postit.css("background-color");
    var width = postit.width();
    var height = postit.height();

    $.ajax({
        url: "/postit/save" + csrf,
        type: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-HTTP-Method-Override": "POST"
        },
        data: JSON.stringify({
            id: id,
            content: content,
            pos_x: pos_x,
            pos_y: pos_y,
            h_color: h_color,
            c_color: c_color,
            width: width,
            height: height
        }),
        success: function () {
            // textarea로 저장된 값에서 \n을 <br> 태그로 변환
            postit.find(".post-it_view").html(content.replace(/\r\n|\r|\n/g, "<br />"));
        }
    });
}

function create4ajax(postit) {

    var csrf = "?" + $("#csrf").attr("name") + "=" + $("#csrf").val();
    var content = "";
    var pos = postit.position();
    var pos_x = pos.left;
    var pos_y = pos.top;
    var h_color = "#FFB700";
    var c_color = "#FFD700";

    $.ajax({
        url: "/postit/create" + csrf,
        type: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-HTTP-Method-Override": "POST"
        },
        data: JSON.stringify({
            content: content,
            pos_x: pos_x,
            pos_y: pos_y,
            h_color: h_color,
            c_color: c_color
        }),
        success: function (result) {
            postit.attr("data-id", result);
            postit.css("background-color", c_color);
            postit.find(".header").css("background-color", h_color);
        }
    });
}

function remove4ajax(postit) {

    var csrf = "?" + $("#csrf").attr("name") + "=" + $("#csrf").val();
    var id = postit.attr("data-id");

    $.ajax({
        url: "/postit/remove/" + id + csrf,
        type: "POST",
        headers: {
            "Content-Type": "application/json",
            "X-HTTP-Method-Override": "POST"
        },
        success: function (result) {
        }
    });
}

function list4Ajax() {
    var csrf = "?" + $("#csrf").attr("name") + "=" + $("#csrf").val();
    var url = "/postit/list";

    $.getJSON(url, function (data) {

        if (data.list.length < 1) {
            // 저장된 포스트잇이 한개도 없을 경우 기본 포스트잇 한개 생성
            create_postit();
        } else {

            $(data.list).each(function () {
                var postit = makePostit(this);

                $(".container").append(postit);
                var last_postit = $(".container").children(":last");
                last_postit.css({
                    "top": this.pos_y + "px",
                    "left": this.pos_x + "px",
                    "background-color": this.c_color,
                    "width": this.width,
                    "height": this.height
                });
                last_postit.find(".header").css("background-color", this.h_color);
                postit_event_binding(last_postit);
            });
        }
    });
}

function makePostit(data) {
    var postit = "";
    postit +=
        "<div class='post-it' data-id='" + ((data.id == undefined) ? '' : data.id) + "'>"
        + "<div class='header'>"
        + "<div class='content'>"
        + "<div class='plus'>"
        + "<span class='glyphicon glyphicon-plus'></span>"
        + "</div>"
        + "&nbsp"
        + "<div class='modify'>"
        + "<span class='glyphicon glyphicon-pencil'></span>"
        + "</div>"
        + "<div class='save' style='display: none;'>"
        + "<span class='glyphicon glyphicon-ok'></span>"
        + "</div>"
        + "&nbsp"
        + "<div class='config'>"
        + "<span class='glyphicon glyphicon-cog'></span>"
        + "</div>"
        + "&nbsp"
        + "<div class='calendar'>"
        + "<span class='glyphicon glyphicon-calendar'></span>"
        + "</div>"
        + "<div class='remove'>"
        + "<span class='glyphicon glyphicon-remove'></span>"
        + "</div>"
        + "</div>"
        + "</div>"
        + "<div class='mod_config' style='display:none;'>"
        + "<div class='orange'>"
        + "</div>"
        + "&nbsp"
        + "<div class='blue'>"
        + "</div>"
        + "&nbsp"
        + "<div class='green'>"
        + "</div>"
        + "&nbsp"
        + "</div>"
        + "<div class='content'>"
        + "<div class='post-it_editor' style='display: none;'>"
        + "<textarea name='content' id='ckeditor' cols='15' rows='5'>"
        + ((data.content == undefined) ? '' : data.content)
        + "</textarea>"
        + "</div>"
        + "<div class='post-it_view'>"
        + ((data.content == undefined) ? '' : data.content.replace(/\r\n|\r|\n/g, "<br />"))
        + "</div>"
        + "</div>";

    return postit;
}


// Color 클래스
function Color() {
    this.h_color = "#FFB700";
    this.c_color = "#FFD700";

    this.orange = function () {
        this.h_color = "#FFB700";
        this.c_color = "#FFD700";
    }

    this.blue = function () {
        this.h_color = "#5CB1E5";
        this.c_color = "#5CD1E5";
    }

    this.green = function () {
        this.h_color = "#86C57F";
        this.c_color = "#86E57F";
    }

    this.changeColor = function (obj) {
        var selectColor = obj.attr("class");

        if (selectColor == "orange") {
            this.orange();
        } else if (selectColor == "blue") {
            this.blue();
        } else if (selectColor == "green") {
            this.green();
        }

        var postit = obj.closest(".post-it");
        postit.closest(".post-it").css("background-color", this.c_color);
        postit.closest(".post-it").find(".header").css("background-color", this.h_color);
        save4ajax(postit);
    }
}