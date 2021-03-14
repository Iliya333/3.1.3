package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


import java.security.Principal;
import java.util.List;
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
    public String getLogin(){
        return "login";
            }


    @GetMapping(value = "admin")
    public ModelAndView getAllAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.listUser();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "user")
    public ModelAndView getAllUser(Principal currentUser) {
        User user = userService.findByEmail(currentUser.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("users", user);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createUsers(User user) {
        ModelAndView modelAndView = new ModelAndView();
        Set<Role> uniqueRoles = roleService.findAllRoles();
        modelAndView.addObject("users", user);
        modelAndView.addObject("roles", uniqueRoles);
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:admin";

    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:admin";
    }

    @GetMapping("update/{id}")
    public ModelAndView updateUsers(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("users", userService.getUserId(id));
        modelAndView.addObject("roles", roleService.findAllRoles());
        return modelAndView;
    }

    @PostMapping("update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:admin";

    }
}
