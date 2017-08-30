
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

    this.red = function () {
        this.h_color = "#FF87A7";
        this.c_color = "#FFA7A7"
    }

    this.gray = function () {
        this.h_color = "#878787";
        this.c_color = "#BDBDBD";
    }

    this.puple = function () {
        this.h_color = "#D192FF";
        this.c_color = "#D1B2FF";
    }

    this.changeColor = function (obj) {
        // var selectColor = obj.attr("class");
        var selectColor = obj.attr("id");

        if (selectColor == "orange") {
            this.orange();
        } else if (selectColor == "blue") {
            this.blue();
        } else if (selectColor == "green") {
            this.green();
        } else if (selectColor == "red") {
            this.red();
        } else if (selectColor == "gray") {
            this.gray();
        } else if (selectColor == "puple") {
            this.puple();
        }

        var postit = obj.closest(".post-it");
        postit.closest(".post-it").css("background-color", this.c_color);
        postit.closest(".post-it").find(".header").css("background-color", this.h_color);
        save4ajax(postit);
    }
}