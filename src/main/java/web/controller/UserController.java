package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping(value = "/admin")
    public String getAllAdmin(ModelMap model) {
        UserDetails userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authorizedUser", userDetails);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("users", userService.findAll());
        return "admin";


    }

    @GetMapping(value = "user")
    public String getAllUser(ModelMap model) {
        UserDetails userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetails);
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("users", userService.findAll());
        return "user";
    }
}
