<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Jarward | Course</title>
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
        <jsp:include page="icons.jsp" flush="true" />
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="course_page_container">
            <span class="course_title_container">
                ${course.title}
            </span>
            <div class="average_mark_container">
                <span class="average_mark_text">Your average mark:</span>
                <span class="average_mark">${average}</span>
            </div>
            <div class="schedule_homework_container">
                <div class="schedule_container">
                    <span class="schedule_text">Schedule</span>

                    <c:forEach items="${lessons}" var="lesson" >
                        <jsp:useBean id="now" class="java.util.Date"/>
                        <fmt:parseDate value = "${lesson.dateEnd}" var = "parsedEndDate" pattern = "yyyy-MM-dd" />
                        <fmt:parseDate value = "${lesson.dateStart}" var = "parsedStartDate" pattern = "yyyy-MM-dd" />
                        <div class="lesson_item_container">
                            <c:if test="${parsedEndDate le now}">
                                <span class="lesson_title_text_course_page_passed">${lesson.title}</span>
                                <span class="lesson_title_text_course_page_passed">
                                    <c:out value="${lesson.dateStart}"/>
                                </span>
                                <span class="lesson_title_text_course_page_passed">
                                    <c:out value="${lesson.timeStart}"/>
                                </span>
                            </c:if>
                            <c:if test="${parsedEndDate ge now}">
                                <span class="lesson_title_text_course_page">${lesson.title}</span>
                                <span class="lesson_title_text_course_page">
                                    <c:out value="${lesson.dateStart}"/>
                                </span>
                                <span class="lesson_title_text_course_page">
                                    <c:out value="${lesson.timeStart}"/>
                                </span>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>

                <div class="homework_container">
                    <span class="schedule_text">Marks</span>
                    <c:if test="${marks == null}">
                        <span class="homework_title_text_course_page">You haven't marks yet</span>
                    </c:if>
                    <c:if test="${marks != null}">
                        <c:forEach items="${marks}" var="mark" >
                            <fmt:parseDate value = "${mark.dateCreate}" var = "parsedDateCreate" pattern = "yyyy-MM-dd" />
                            <fmt:parseDate value = "${mark.markId.homework.deadLine}" var = "parsedDeadline" pattern = "yyyy-MM-dd" />

                            <c:if test="${parsedDateCreate le parsedDeadline}">
                                <div class="homework_item_container">
                                    <span class="homework_title_text_course_page">${mark.markId.homework.title}</span>
                                    <span class="homework_title_text_course_page">${mark.mark}</span>
                                </div>
                            </c:if>
                            <c:if test="${parsedDateCreate ge parsedDeadline}">
                                <div class="homework_item_container">
                                    <span class="homework_title_text_course_page_red">${mark.markId.homework.title}</span>
                                    <span class="homework_title_text_course_page_red">Deadline - ${mark.markId.homework.deadLine}</span>
                                    <span class="homework_title_text_course_page_red">${mark.mark}</span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
