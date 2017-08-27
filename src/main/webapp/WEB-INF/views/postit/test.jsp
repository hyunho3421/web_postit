<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title></title>
    <link rel="stylesheet" href="/resources/js/jquery-ui-1.12.1/jquery-ui.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/post-it.css"/>
</head>
<body>
<div class="container">
    <br/>

    <div class="post-it">
        <div class="header">
            <div class="content">
                <div class="plus">
                    <span class="glyphicon glyphicon-plus"></span>
                </div>
                <div class="modify">
                    <span class="glyphicon glyphicon-pencil"></span>
                </div>
                <div class="save" style="display: none;">
                    <span class="glyphicon glyphicon-ok"></span>
                </div>
                <dic class="cog">
                    <span class="glyphicon glyphicon-cog"></span>
                </dic>
                <div class="remove">
                    <span class="glyphicon glyphicon-remove"></span>
                </div>
            </div>
        </div>
        <div class="content">
            <form role="form" method="post" action="" class="post-it_editor">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrf"/>

                <div class="form-group">
                    <textarea name="content" id="ckeditor" cols="15" rows="5"></textarea>
                </div>
            </form>
        </div>
    </div>

</div>

<script src="/resources/js/jquery-3.2.1.js"></script>
<script src="/resources/js/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="/resources/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
<script src="/static/postit/postit.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script>
</script>
</body>
</html>