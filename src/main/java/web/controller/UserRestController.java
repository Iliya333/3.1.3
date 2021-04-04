package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.RoleService;
import web.service.UserService;

@RestController
@RequestMapping("/api/vl/table/")
public class UserRestController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService){
        this.userService= userService;
        this.roleService = roleService;
    }
    


}
