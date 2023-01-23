package by.home.jarward.web.interceptor;

import by.home.jarward.jar.entity.User;
import by.home.jarward.web.service.intarfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.util.Optional;
@Component
public class AuthInterceptor extends WebContentInterceptor {
    @Autowired
    UserService userService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = Optional.empty();
        if (principal instanceof UserDetails){
            user = userService.getByLogin(((UserDetails) principal).getUsername());
        }

        if (session != null && session.getAttribute("login") == null) {
            user.ifPresent(item -> {
                session.setAttribute("login", item.getLogin());
                session.setAttribute("photo", item.getPhoto());
                session.setAttribute("language", item.getLanguage());
                session.removeAttribute("isValid");
            });
        }

    }
}
