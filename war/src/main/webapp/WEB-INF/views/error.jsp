<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Jarward | Error</title>
        <link href="${pageContext.request.contextPath}/static/css/reset_styles.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/containers.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/buttons.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/text.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/inputs.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/lines.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/forms.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/icons.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/file/show-photo?param=2" type="image/jpg">
        <jsp:include page="icons.jsp" flush="true" />
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="black_page">
            <div class="verify_info_page_container">
                <svg class="sad_icon" width=150 height=150>
                    <use xlink:href="#sad"></use>
                </svg>
                <p class="text_mail_sent_bold">Something went wrong...</p>
                <p class="text_mail_sent">Our team is already working on fixing. Try again later</p>
            </div>
        </div>
    </body>
</html>
