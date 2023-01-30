<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Jarward | Lesson</title>
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
        <div class="lesson_page_container">
            <span class="lesson_title_container">
                ${lesson.title}
            </span>

            <div class="lesson_start_container">
                <span class="course_start_text">Start: ${lesson.dateStart} ${lesson.timeStart} | End: ${lesson.dateEnd} ${lesson.timeEnd}</span>
            </div>

            <div class="absent_container">
                <span class="presence_text">Presence</span>
                <form method="post" action="${pageContext.request.contextPath}/lessons/${lesson.id}">
                    <c:forEach items="${students}" var="student" >
                        <div class="user_presence_item_container">
                            <div style="display:flex; align-items:center; justify-content:space-between">
                                <div>
                                    <img src="${pageContext.request.contextPath}/file/show-avatar?login=${student.login}" class="circle_image_course_page"/>
                                </div>
                                <span class="user_stat_text">
                                    ${student.name} ${student.surname}
                                </span>
                            </div>
                            <input type="checkbox" name="presentStudents" value="${student.id}">
                        </div>
                    </c:forEach>
                    <input class="save_lesson_button" value="Save Lesson" type="submit" required>
                </form>
            </div>
        </div>

    </body>
</html>
