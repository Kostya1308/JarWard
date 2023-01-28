<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="icons.jsp" flush="true" />
    </head>

    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="black_page">
          <div class="index_page_container">
                <div class="text_about_university_container">
                    <span class="text_about_university">"Like Harvard, but Jarward"</span>
                    <span class="text_2_about_university">Piskunov Constantine</span>
                </div>
                <div class="image_student_container">
                    <img src="${pageContext.request.contextPath}/file/show-photo?param=1"/>
                    <div class="descr_courses_index_page_container">
                            <span class="text_3_about_university">Our courses</span>
                            <span class="text_4_about_university">We offer training courses in the most current areas.
                            The center was created to train aspiring IT professionals to work in the industry. We provide
                            high-quality IT education and train highly qualified specialists who are able to work in a
                            dynamically developing industry. The programs are designed for different levels of training.
                            </span>
                            <div>
                                <a href="/courses/all" class="courses_button_index_page">Courses</a>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
