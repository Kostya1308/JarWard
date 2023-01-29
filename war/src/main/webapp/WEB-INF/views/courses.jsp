<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>

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
                <span class="course_title_container">
                    Upcoming events
                </span>

                <div class="course_sort_container">
                    <c:if test="${page.sort.getOrderFor('dateStart').isAscending()}">
                        <a class="course_sort_text" href="${pageContext.request.contextPath}/courses/all?sort-dir=desc">by start date desc</a>
                    </c:if>
                    <c:if test="${page.sort.getOrderFor('dateStart').isDescending()}">
                        <a class="course_sort_text" href="${pageContext.request.contextPath}/courses/all?sort-dir=asc">by start date asc</a>
                    </c:if>
                </div>

                <c:forEach items="${courses}" var="course" >
                    <div class="course_container">
                    <div style="display:flex; justify-content: space-between; align-items: center; width:850px">
                        <span class="title_course">
                            ${course.title}
                        </span>

                        <c:if test="${isMyCourses!=true}">
                            <security:authorize access="(hasRole('student') || !isAuthenticated())">
                                <div>
                                    <a href="${pageContext.request.contextPath}/courses/registration?course=${course.id}" class="appoint_button_courses_page">Registration</a>
                                </div>
                            </security:authorize>
                        </c:if>
                        <c:if test="${isMyCourses==true}">
                            <div>
                                <a href="${pageContext.request.contextPath}/courses/${course.id}" class="appoint_button_courses_page">Enter</a>
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
                        <c:if test="${page.sort.getOrderFor('dateStart').isAscending()}">
                            <a href="${pageContext.request.contextPath}/courses/all?pageNumber=${page.getNumber() - 1}">
                                <svg width=30 height=30 class="left_icon">
                                    <use xlink:href="#left"></use>
                                </svg>
                            </a>
                        </c:if>
                        <c:if test="${page.sort.getOrderFor('dateStart').isDescending()}">
                            <a href="${pageContext.request.contextPath}/courses/all?pageNumber=${page.getNumber() - 1}&sort-dir=desc">
                                <svg width=30 height=30 class="left_icon">
                                    <use xlink:href="#left"></use>
                                </svg>
                            </a>
                        </c:if>
                    </c:if>
                    <c:if test="${page.hasNext()==true}">
                        <c:if test="${page.sort.getOrderFor('dateStart').isAscending()}">
                            <a href="${pageContext.request.contextPath}/courses/all?pageNumber=${page.getNumber() + 1}">
                                <svg width=30 height=30 class="left_icon">
                                    <use xlink:href="#right"></use>
                                </svg>
                            </a>
                        </c:if>
                        <c:if test="${page.sort.getOrderFor('dateStart').isDescending()}">
                            <a href="${pageContext.request.contextPath}/courses/all?pageNumber=${page.getNumber() + 1}&sort-dir=desc">
                                <svg width=30 height=30 class="left_icon">
                                    <use xlink:href="#right"></use>
                                </svg>
                            </a>
                        </c:if>
                    </c:if>
                </div>
            </div>
    </body>
</html>
