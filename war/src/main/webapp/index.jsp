<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="translations" var="messages"/>

<html>
    <head>
        <title>JarWard University</title>
        <link href="static/css/reset_styles.css" rel="stylesheet">
        <link href="static/css/containers.css" rel="stylesheet">
        <link href="static/css/buttons.css" rel="stylesheet">
        <link href="static/css/text.css" rel="stylesheet">
        <link href="static/css/icons.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <jsp:include page="WEB-INF/views/header.jsp" flush="true" />
        <jsp:include page="WEB-INF/views/icons.jsp" flush="true" />




    </body>
</html>
