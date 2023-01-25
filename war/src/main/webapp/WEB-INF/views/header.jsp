<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="header_container">
    <a href="/" style="display:flex; align-items:center">
        <svg width=30 height=30 class="dropdown_item_icon">
            <use xlink:href="#bank"></use>
        </svg>
        <span class="main_name_text">JarWard</span>
    </a>

    <security:authorize access="isAuthenticated()">
        <div class="buttons_auth_on_header_container">
            <a class="sign_in_header_button" href="/">Home</a>
            <a class="sign_in_header_button" href="/courses/all">Courses</a>
            <a class="sign_in_header_button" href="/sign-up">Teachers</a>
            <a class="sign_in_header_button" href="/sign-up">About</a>
        </div>
    </security:authorize>

    <security:authorize access="!isAuthenticated()">
        <div class="buttons_on_header_container">
            <a class="sign_in_header_button" href="/login">Sign in</a>
            <a class="sign_up_header_button" href="/sign-up">Sign up</a>
        </div>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        <div>
            <img src="${pageContext.request.contextPath}/file/show-avatar" onclick="myFunction()" class="circle_image"/>
        </div>

        <div id="myDropdown" class="dropdown-container">

            <p class="login_on_dropdown">${sessionScope.login}</p>

            <div class="line_2"></div>

            <a class="dropdown_item_container" href="${pageContext.request.contextPath}/courses/user/${sessionScope.login}">
                <svg width=25 height=25 class="dropdown_item_icon">
                    <use xlink:href="#book"></use>
                </svg>
                <span class="dropdown_item_link">My courses<span>
            </a>

            <a class="dropdown_item_container" href="/users/settings">
                <svg width=25 height=25 class="dropdown_item_icon">
                    <use xlink:href="#settings"></use>
                </svg>
                <span class="dropdown_item_link">Settings<span>
            </a>

            <div class="line_2"></div>

            <a class="dropdown_item_container" href="/logout">
                <svg width=25 height=25 class="dropdown_item_icon">
                    <use xlink:href="#logout"></use>
                </svg>
                <span class="dropdown_item_link">Logout<span>
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