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
        <link href="https://fonts.googleapis.com/css2?family=Merriweather:wght@300&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="WEB-INF/views/icons.jsp" flush="true" />
    </head>

    <body>
        <jsp:include page="WEB-INF/views/header.jsp" flush="true" />
        <div class="black_page">
            <div class="index_page_container">
                <div class="text_about_university_container">
                    <span class="text_about_university">...Like Harvard, but Jarward</span>
                    <span class="text_2_about_university">Piskunov Constantine</span>
                </div>
                <div class="image_student_container">
                    <img src="${pageContext.request.contextPath}/file/show-photo?param=1"/>
                    <div class="descr_courses_index_page_container">
                        <span class="text_about_university">Our courses</span>
                        <span class="text_2_about_university">We offer training courses in the most current areas.</span>
                        <span class="text_2_about_university">The programs are designed for different levels of training.</span>
                        <div>
                            <a href="/courses" class="courses_button_index_page">Courses</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
