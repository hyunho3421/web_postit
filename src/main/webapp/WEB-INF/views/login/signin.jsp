<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>Post-it</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css" />
    <link rel="icon" type="image/png"  href="/favicon.ico"/>
</head>
<body>
<div class="container">
    <br />

    <div class="well">
        <form role="form" action="/login">
            <div class="form-group">
                <label for="id">ID:</label>
                <input type="text" class="form-control" id="id" name="id">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>


            <div class="row">
                <div class="col-md-4 text-left">
                    <div class="checkbox">
                            <label><input id = "remember_me" name ="remember_me" type = "checkbox"/>
                                Remember me &nbsp;/&nbsp; <a href="/signup">Sign Up</a></label>
                    </div>
                </div>
                <div class="col-md-8 text-right">
                    <button class="btn btn-default" id="btnLogin">Sign In</button>
                </div>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>

</div>

<script src="/resources/js/jquery-3.2.1.js" ></script>
<script src="/resources/js/bootstrap.js" ></script>
<script src="/static/login/signin.js"></script>
<script>
    var error = '${error}';

    if (error == 'login_fail') {
        alert('아이디 또는 비밀번호가 올바르지 않습니다.');
    } else if (error == 'duplicate_login') {
        alert('다른 PC에서 로그인했습니다.');
    }
</script>
</body>
</html>