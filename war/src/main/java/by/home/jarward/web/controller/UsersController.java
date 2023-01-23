package by.home.jarward.web.controller;

import by.home.jarward.jar.entity.User;
import by.home.jarward.web.authentication.AuthService;
import by.home.jarward.web.facade.UserFacade;
import by.home.jarward.web.form.UserForm;
import by.home.jarward.web.service.intarfaces.UserService;
import by.home.jarward.web.validator.UpdateUserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    UserFacade userFacade;
    @Autowired
    AuthService authService;

    @Qualifier(value = "updateUserValidator")
    @Autowired
    UpdateUserValidator updateUserValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(updateUserValidator);
    }

    @GetMapping(value = "/settings")
    public ModelAndView getSettingsPage(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userService.getByLogin(principal.getUsername());
        user.ifPresent(item -> {
            userFacade.getUserFormFromUser(userForm, item);
        });
        return new ModelAndView("user_settings");
    }

    @PostMapping(value = "/settings")
    public RedirectView update(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpServletRequest req) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm",
                    bindingResult);
            redirectAttributes.addFlashAttribute("userForm", userForm);

            return new RedirectView(req.getContextPath() + "/users/settings");
        } else {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HttpSession session = req.getSession();

            Optional<User> user = userService.getByLogin(principal.getUsername());
            user.ifPresent(item -> {
                try {
                    userFacade.getUserFromUserForm(item, userForm);
                    userService.save(item);

//                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//                    List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + item.getRole().getName());
//                    updatedAuthorities.add(grantedAuthority);
//                    UserDetails userDetails = authService.loadUserByUsername(userForm.getLogin());
//                    Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails,
//                            auth.getCredentials(), updatedAuthorities);
//                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


//                session.setAttribute("login", userForm.getLogin());
//                session.setAttribute("language", userForm.getLanguage());
//                try {
//                    session.setAttribute("photo", userForm.getFileData().getBytes());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
            });
            return new RedirectView(req.getContextPath() + "/logout");
        }

    }

}
