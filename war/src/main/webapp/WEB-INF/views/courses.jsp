<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Jarward | Courses</title>

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
            <div class="courses_page_container">
                <span class="text_future_stars_container">
                    Upcoming events
                </span>
                <c:forEach items="${courses}" var="course" >
                    <div class="course_container">
                    <div style="display:flex; justify-content: space-between; align-items: center; width:850px">
                        <span class="title_course">
                            ${course.title}
                        </span>
                        <c:if test="${pageContext.request.contextPath} != '/courses/sessionScope.login'}">
                        <div>
                            <a href="${pageContext.request.contextPath}/courses/registration" class="appoint_button_courses_page">Registration</a>
                        </div>
                        </c:if>
                    </div>
                    <div style="width: 600px">
                        <p class="desc_course">
                            ${course.description}
                        </p>
                    </div>
                    <div>
                        <p class="desc_course">
                            Start: ${course.dateStart}
                        </p>
                    </div>
                    </div>
                </c:forEach>

                <div class="pagination_container">
                    <c:if test="${page.hasPrevious()==true}">
                        <a href="${pageContext.request.contextPath}/courses/all?pageNumber=${page.getNumber() - 1}">
                            <svg width=30 height=30 class="left_icon">
                                <use xlink:href="#left"></use>
                            </svg>
                        </a>
                    </c:if>
                    <c:if test="${page.hasNext()==true}">
                        <a href="${pageContext.request.contextPath}/courses/all?pageNumber=${page.getNumber() + 1}">
                            <svg width=30 height=30 class="left_icon">
                                <use xlink:href="#right"></use>
                            </svg>
                        </a>
                    </c:if>
                </div>
            </div>
    </body>
</html>
