<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Jarward | Sing Up</title>
        <link href="static/css/reset_styles.css" rel="stylesheet">
        <link href="static/css/containers.css" rel="stylesheet">
        <link href="static/css/buttons.css" rel="stylesheet">
        <link href="static/css/text.css" rel="stylesheet">
        <link href="static/css/inputs.css" rel="stylesheet">
        <link href="static/css/lines.css" rel="stylesheet">
        <link href="static/css/forms.css" rel="stylesheet">
        <link href="static/css/icons.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="icons.jsp" flush="true" />
    </head>
    <body>

        <div class="sign_up_page_container">
            <div class="sign_up_container">
                <form:form id="sign_up_form" cssClass="sign_up_form" action="${pageContext.request.contextPath}/sign-up" method="post" modelAttribute="userForm" enctype="multipart/form-data">

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

                    <div style="display:flex">
                        <div class="item_input_container_sign_up">
                            <form:password id="password_input" cssClass="item_input_sign_up_page" cssErrorClass="item_input_invalidate_sign_up_page" path="password" name="password" required="required"/>
                            <form:label for="password_input" cssClass="item_input_label_sign_up_page" path="password" required="required"> Password </form:label>
                            <form:errors path="password" cssClass="error_sign_up_page"/>
                        </div>

                        <div class="item_input_container_sign_up">
                            <form:password id="password_confirm_input" cssClass="item_input_sign_up_page" cssErrorClass="item_input_invalidate_sign_up_page" path="passwordConfirm" name="passwordConfirm" required="required"/>
                            <form:label for="password_confirm_input" cssClass="item_input_label_sign_up_page" path="passwordConfirm" required="required"> Password Confirm </form:label>
                            <div class="question_sign_up_form" data-title="Use at least eight characters, at least one number and one capital letter">
                                <svg width=15 height=15>
                                    <use xlink:href="#question"></use>
                                </svg>
                            </div>
                            <form:errors path="passwordConfirm" cssClass="error_sign_up_page"/>
                        </div>
                    </div>

                    <div style="display:flex">
                        <div>
                            <div class="item_input_container_sign_up">
                                <form:input id="date_input" cssClass="item_input_sign_up_page" style="width:255px;" path="dateOfBirth" name="dateOfBirth" type="date" min="1900-00-00" max="2222-12-12" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"/>
                                <form:label for="date_input" cssClass="item_input_label_sign_up_page" path="dateOfBirth"> Date of Birth </form:label>
                            </div>
                        </div>
                        <div class="item_input_container_sign_up">
                            <form:select id="select_language" cssClass="select_language" path="language">
                                <option selected disabled hidden class="opt_sign_up_language_hidden">Choose your language</option>
                                <option value="en" class="opt_sign_up_language">English</option>
                                <option value="fr" class="opt_sign_up_language">French</option>
                            </form:select>
                        </div>
                    </div>

                    <div class="item_input_container_sign_up">
                        <p class="gender">Gender:</p>
                        <div style="position:relative; margin:0px 15px; display:flex; align-items:center">
                            <form:radiobutton id="gender_male" path="gender" name="gender" value="Male"/>
                            <form:label for="gender_male" cssClass="item_input_label_radio_sign_up_page" path="gender"> Male </form:label>
                        </div>

                        <div style="position:relative; margin:0px 15px; display:flex; align-items:center">
                            <form:radiobutton id="gender_female" path="gender" name="gender" value="Female"/>
                            <form:label for="gender_female" cssClass="item_input_label_radio_sign_up_page" path="gender"> Female </form:label>
                        </div>

                        <div style="position:relative; margin:0px 15px; display:flex; align-items:center">
                            <form:radiobutton id="gender_other" path="gender" name="gender" value="Other"/>
                            <form:label for="gender_other" cssClass="item_input_label_radio_sign_up_page" path="gender"> Other </form:label>
                        </div>
                    </div>
                        <div class="item_input_container_sign_up">
                            <input id="image_uploads" type="file" path="fileData" class="item_input_sign_up_page" name="fileData" required />
                        </div>

                    <div style="display:flex">
                        <input class="sign_up_button" value="Sign up" type="submit" required/>
                            <a class="main_page_header_button" href="/">Main page</a>
                    </div>

                </form:form>
            </div>

        </div>
    </body>
</html>
