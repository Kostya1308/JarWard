<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Sing in</title>
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
        <div class="black_page">
            <div class="sign_in_page_container">
                <div class="sign_in_container">
                    <form:form id="sign_in_form" cssClass="sign_in_form" action="${pageContext.request.contextPath}/loginS" method="post" modelAttribute="userForm">
                        <div class="item_input_container_sign_in">
                            <c:if test="${sessionScope.isValid==null}">
                                <form:input id="login_input" cssClass="item_input_sign_in_page" path="login" name="login" required="required"/>
                            </c:if>
                            <c:if test="${sessionScope.isValid==false}">
                                <form:input id="login_input" cssClass="item_input_invalidate_sign_in_page" path="login" name="login" required="required"/>
                            </c:if>
                            <form:label for="login_input" cssClass="item_input_label_sign_in_page" path="login" required="required"> Login </form:label>
                        </div>
                        <div class="item_input_container_sign_in">
                            <c:if test="${sessionScope.isValid==null}">
                                <form:password id="password_input" cssClass="item_input_sign_in_page" path="password" name="password" required="required"/>
                            </c:if>
                            <c:if test="${sessionScope.isValid==false}">
                                <form:password id="password_input" cssClass="item_input_invalidate_sign_in_page" path="password" name="password" required="required"/>
                            </c:if>
                            <form:label for="password_input" cssClass="item_input_label_sign_in_page" path="password" required="required"> Password </form:label>
                        </div>
                        <div display:flex>
                            <input id="remember-me" type="checkbox" name="remember-me"/>
                            <small class="text_1">Remember me</small>
                        </div>
                        <input class="sign_in_button" value="Sign in" type="submit" required>
                    </form:form>
                    <div class="line">
                    </div>
                    <c:if test="${sessionScope.isValid==null}">
                        <p class="text_1">Install our mobile app.</p>
                        <p class="text_1">Soon to the AppStore and PlayMarket</p>
                    </c:if>
                    <c:if test="${sessionScope.isValid==false}">
                        <p class="text_1_invalidate">Incorrect data or unverified user</p>
                        <p class="text_1_invalidate">Try again or contact us.</p>
                    </c:if>
                </div>
                <div class="sign_up_on_login_page_container">
                    <div>
                        <a href="sign-up" class="sign_up_on_sigh_in_page_button">Sign up</a>
                    </div>
                    <div>
                        <p class="text_1">if you don't have an account yet, join and get access to all the features</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
