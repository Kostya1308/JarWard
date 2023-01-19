<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Sing Up</title>
        <link href="static/css/reset_styles.css" rel="stylesheet">
        <link href="static/css/containers.css" rel="stylesheet">
        <link href="static/css/buttons.css" rel="stylesheet">
        <link href="static/css/text.css" rel="stylesheet">
        <link href="static/css/inputs.css" rel="stylesheet">
        <link href="static/css/lines.css" rel="stylesheet">
        <link href="static/css/forms.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="sign_up_page_container">
            <div class="sign_up_container">
                <form:form id="sign_up_form" cssClass="sign_up_form" action="${pageContext.request.contextPath}/*" method="post" modelAttribute="userForm">
                    <div class="item_input_container_sign_up">
                        <form:input id="login_input" cssClass="item_input_sign_up_page" path="login" name="login" required="required"/>
                        <form:label for="login_input" cssClass="item_input_label_sign_up_page" path="login" required="required"> Login </form:label>
                        <form:errors path="login" cssClass="error_sign_up_page"/>
                    </div>



                </form:form>
            </div>

        </div>
    </body>
</html>
