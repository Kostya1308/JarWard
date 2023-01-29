<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<fmt:setLocale value="en_US"/>


<html>
    <head>
        <title>JarWard | Home</title>
        <link href="static/css/reset_styles.css" rel="stylesheet">
        <link href="static/css/containers.css" rel="stylesheet">
        <link href="static/css/buttons.css" rel="stylesheet">
        <link href="static/css/text.css" rel="stylesheet">
        <link href="static/css/icons.css" rel="stylesheet">
        <link href="static/css/lines.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Merriweather:wght@300&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/file/show-photo?param=2" type="image/jpg">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="icons.jsp" flush="true" />
    </head>

    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="black_page_index">
            <div class="index_page_container">

                <div class="text_about_university_container">
                    <span class="text_about_university"><spring:message code="Like.Harvard"/></span>
                    <span class="text_2_about_university"> &#169 Constantine Piskunov</span>
                </div>

                <div class="image_student_container">
                    <img src="${pageContext.request.contextPath}/file/show-photo?param=1"/>
                    <div class="descr_courses_index_page_container">
                        <span class="text_3_about_university">
                            <spring:message code="Our.courses"/>
                        </span>
                        <span class="text_4_about_university">
                            <spring:message code="we.offer"/>
                        </span>
                        <div>
                            <a href="/courses/all" class="courses_button_index_page">
                                <spring:message code="Courses"/>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="about_teachers_container">
                        <span class="text_3_about_university">
                            Our Trainers
                        </span>
                        <span class="text_5_about_university">
                            All mentors are experienced practitioners from the IT industry. They provide quality feedback on assignments, answer questions, and help the student achieve their goals during the program. Alumni rate the mentor support a 9.1 out of 10.
                        </span>
                        <div style="width:300px display:flex; align-items:center">
                            <a href="/teachers" class="trainers_button_index_page"/>Trainers</a>
                        </div>
                </div>

            </div>

        </div>

    </body>
</html>
