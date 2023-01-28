<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Jarward | Settings</title>
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
        <div class="black_page">
            <div class="update_page_container">
                <div class="update_container">
                    <form:form id="update_form" cssClass="update_form" action="${pageContext.request.contextPath}/users/settings" method="post" modelAttribute="userForm" enctype="multipart/form-data">

                        <div style="display:flex">
                            <div class="item_input_container_sign_up">
                                <form:input id="login_input" cssClass="item_input_sign_up_page" cssErrorClass="item_input_invalidate_sign_up_page" path="login" name="login" required="required"/>
                                <form:label for="login_input" cssClass="item_input_label_sign_up_page" path="login" required="required"> Login </form:label>
                                <form:errors path="login" cssClass="error_sign_up_page"/>
                            </div>

                            <div class="item_input_container_sign_up">
                                <form:input id="email_input" cssClass="item_input_sign_up_page" cssErrorClass="item_input_invalidate_sign_up_page" path="email" name="email" required="required"/>
                                <form:label for="email_input" cssClass="item_input_label_sign_up_page" path="email" required="required"> E-mail </form:label>
                                <form:errors path="email" cssClass="error_sign_up_page"/>
                            </div>
                        </div>

                        <div style="display:flex">
                            <div class="item_input_container_sign_up">
                                <form:input id="name_input" cssClass="item_input_sign_up_page" path="name" name="name" required="required"/>
                                <form:label for="name_input" cssClass="item_input_label_sign_up_page" path="name" required="required"> Name </form:label>
                                <form:errors path="name" cssClass="error_sign_up_page"/>
                            </div>

                            <div class="item_input_container_sign_up">
                                <form:input id="surname_input" cssClass="item_input_sign_up_page" path="surname" name="surname" required="required"/>
                                <form:label for="surname_input" cssClass="item_input_label_sign_up_page" path="login" required="required"> Surname </form:label>
                                <form:errors path="surname" cssClass="error_sign_up_page"/>
                            </div>
                        </div>

                        <div class="item_input_container_sign_up">
                            <form:password id="password_input" cssClass="item_input_sign_up_page" cssErrorClass="item_input_invalidate_sign_up_page" path="password" name="password" required="required"/>
                            <form:label for="password_input" cssClass="item_input_label_sign_up_page" path="password" required="required"> Password </form:label>
                            <form:errors path="password" cssClass="error_sign_up_page"/>
                        </div>

                        <div style="display:flex">

                            <div class="item_input_container_sign_up">
                                <p class="language">Language:</p>
                                <form:select id="select_language" cssClass="select_language" path="language">
                                    <option value="${userForm.language}" class="opt_sign_up_language">${userForm.language}</option>
                                    <c:if test="${userForm.language eq 'en'}">
                                        <option value="fr" class="opt_sign_up_language">French</option>
                                    </c:if>
                                    <c:if test="${userForm.language eq 'fr'}">
                                        <option value="en" class="opt_sign_up_language">English</option>
                                    </c:if>
                                </form:select>
                            </div>
                        </div>

                        <div class="item_input_container_sign_up">
                            <input id="image_uploads" type="file" path="fileData" class="item_input_sign_up_page" name="fileData"/>
                        </div>

                        <div style="display:flex">
                            <input class="sign_up_button" value="Update" type="submit" required/>
                                <a class="main_page_header_button" href="/">Main page</a>
                        </div>

                    </form:form>
                </div>

            </div>
        </div>
    </body>
</html>
