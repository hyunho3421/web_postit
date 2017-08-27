/**
 * Created by hyunhokim on 2017. 6. 6..
 */
var css_postit_idx = 10;

$(document).ready(function () {

    postit_event_binding();
});

// 이벤트 바인딩 함수
function postit_event_binding() {
    $(".post-it")
        .resizable()
        .draggable({
            opacity: 0.8
        })
        .mousedown(function () {    // mousedown 이벤트 생성
            $(this).css('z-index', css_postit_idx); // 클릭한 이미지만 z-index 증가시킴
            css_postit_idx++;   // 그러면 이미지가 겹칠경우 클릭한 것이 항상 위에 표시됨
        });

    $(".plus").on("click", function () {
        create_postit($(this));
    });

    $(".remove").on("click", function () {
        // 삭제시에 포스트잇 모두 삭제 불가능하게
        // 삭제하는게 마지막 포스트잇이면 새 포스트잇 한개 추가.
        var postit = $(this).closest(".post-it");
        postit.remove();
    });
}

// 포스트잇 생성 함수
function create_postit(postit) {
    // 새 포스트 잇 생성
    var $new_postit = $(
        "<div class='post-it'>"
        + "<div class='header'>"
        + "<div class='content'>"
        + "<div class='plus'>"
        + "<span class='glyphicon glyphicon-plus'></span>"
        + "</div>"
        + "<div class='remove'>"
        + "<span class='glyphicon glyphicon-remove'></span>"
        + "</div>"
        + "</div>"
        + "</div>"
        + "<div class='content'>"
        + "</div>");

    $(".container").append($new_postit);

    // 마지막 추가된 포스트잇 이벤트 동적 바인딩
    var last_postit = $(".container").children(":last");

    console.log(css_postit_idx);
    last_postit
        .resizable()
        .draggable({
            opacity: 0.8
        })
        .mousedown(function () {
            $(this).css('z-index', css_postit_idx); // 클릭한 이미지만 z-index 증가시킴
            css_postit_idx++;   // 그러면 이미지가 겹칠경우 클릭한 것이 항상 위에 표시됨
        })
        .css('z-index', css_postit_idx);    //처음 생성할때 최상단으로 나오도록 설정

    last_postit.find(".remove");
    last_postit.find(".plus");

    postit_event_binding(last_postit);
}

function create_postit_ajax() {
    $.ajax({
        url: "",
        type: "POST",
        data: {
            
        },
        dataType: "",
        success: function () {

        }
    });
}

/*
 *
 *
 * // 추가 버튼 누른 포스트잇 위치 구하기
 var pos = $(this).position();
 var x = pos.left;
 var y = pos.top;


 // 추가 버튼 누른 포스트잇 크기 구하기
 var postit = $(this).closest(".post-it");
 var width = postit.outerWidth();
 var height = postit.outerHeight();
 *
 *
 * */


$(".uploadedList").on("click", "small", function () {
    var that = $(this);

    $.ajax({
        url: "/deleteFile" + csrf,
        type: "POST",
        data: {
            fileName: $(this).attr("data-src")
        },
        dataType: "text",
        success: function (result) {
            if (result == 'deleted') {
                that.closest(".col-sm-4").remove();
            }
        }
    });
});