package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


import java.security.Principal;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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

    @GetMapping("/create")
    public String createUsers(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:admin";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/admin";

    }
}
