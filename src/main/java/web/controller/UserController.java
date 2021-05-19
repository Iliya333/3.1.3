package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.RoleServiceImp;
import web.service.UserServiceImp;

@Controller
public class UserController {

    private final RoleServiceImp roleServiceImp;
    private final UserServiceImp userServiceImp;

    @Autowired
    public UserController(RoleServiceImp roleServiceImp, UserServiceImp userServiceImp) {
        this.roleServiceImp = roleServiceImp;
        this.userServiceImp = userServiceImp;
    }


    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping(value = "/admin")
    public String getAllAdmin(ModelMap model) {
        UserDetails userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", userDetails);
        return "admin";


    }

    @GetMapping(value = "user")
    public String getAllUser(ModelMap model) {
        UserDetails userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetails);
        model.addAttribute("roles", roleServiceImp.getAllRoles());
        model.addAttribute("users", userServiceImp.getAllUsers());
        return "user";
    }
}

