<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script>
	window.parent.CKEDITOR.tools.callFunction(${fileDTO.CKEditorFuncNum}, '${fileUrl}', '이미지를 업로드 하였습니다.');
</script>