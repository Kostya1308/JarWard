<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="header_container">
    <a href="/" style="display:flex; align-items:center">
        <svg width=30 height=30 class="main_item_icon">
            <use xlink:href="#bank"></use>
        </svg>
        <span class="main_name_text">JarWard</span>
    </a>

    <security:authorize access="isAuthenticated()">
        <div class="buttons_auth_on_header_container">
            <a class="sign_in_header_button" href="/">
                <spring:message code="Home"/>
            </a>
            <a class="sign_in_header_button" href="${pageContext.request.contextPath}/courses/all">
                <spring:message code="Courses"/>
            </a>
            <a class="sign_in_header_button" href="${pageContext.request.contextPath}/teachers">
                <spring:message code="Teachers"/>
            </a>
            <a class="sign_in_header_button" href="${pageContext.request.contextPath}/about">
                <spring:message code="About"/>
            </a>
        </div>
    </security:authorize>

    <security:authorize access="!isAuthenticated()">
        <div class="buttons_on_header_container">
            <a class="sign_in_header_button" href="/login">
                <spring:message code="Sign.in"/>
            </a>
            <a class="sign_up_header_button" href="/sign-up">
                <spring:message code="Sign.up"/>
            </a>
        </div>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <div>
            <img src="${pageContext.request.contextPath}/file/show-avatar" onclick="myFunction()" class="circle_image"/>
        </div>

        <div id="myDropdown" class="dropdown-container">

            <p class="login_on_dropdown">${sessionScope.login}</p>

            <div class="line_2"></div>
                <a class="dropdown_item_container" href="${pageContext.request.contextPath}/courses?login=${sessionScope.login}">
                    <svg width=25 height=25 class="dropdown_item_icon">
                        <use xlink:href="#book"></use>
                    </svg>
                    <span class="dropdown_item_link">
                        <spring:message code="My.Courses"/>
                    <span>
                </a>

            <a class="dropdown_item_container" href="${pageContext.request.contextPath}/users/settings">
                <svg width=25 height=25 class="dropdown_item_icon">
                    <use xlink:href="#settings"></use>
                </svg>
                <span class="dropdown_item_link">
                    <spring:message code="Settings"/>
                <span>
            </a>

            <div class="line_2"></div>

            <a class="dropdown_item_container" href="${pageContext.request.contextPath}/logout">
                <svg width=25 height=25 class="dropdown_item_icon">
                    <use xlink:href="#logout"></use>
                </svg>
                <span class="dropdown_item_link">
                    <spring:message code="Logout"/>
                <span>
            </a>
        </div>
    </security:authorize>
</div>

<script>
    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    window.onclick = function(event) {
        if (!event.target.matches('.circle_image')) {

            var dropdowns = document.getElementsByClassName("dropdown-container");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                  openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>