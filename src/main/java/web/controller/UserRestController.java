package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Iterable<User>> getUsers() {

        return ResponseEntity.ok().body( userService.findAll());
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Iterable<Role>> getRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().body("User with id=" + id + " deleted");
    }

    @PostMapping(value = "/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody Map<String, Object> payload) {
        User user = new User();
        user.setId(Long.valueOf(String.valueOf(payload.get("id"))));
        fillUser(payload, user);
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/saveNewUser")
    public ResponseEntity<User> saveNewUser(@RequestBody Map<String, Object> payload){
        User user = new User();
        fillUser(payload, user);
        UserDetails byUsername = userService.loadUserByUsername(user.getUsername());
        if (byUsername != null) {
            return ResponseEntity.unprocessableEntity().body(user);
        }
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    private void fillUser(Map<String, Object> payload, User user) {
        user.setLastName(String.valueOf(payload.get("name")));
        user.setAge(Integer.parseInt(String.valueOf(payload.get("age"))));
        user.setPassword(String.valueOf(payload.get("password")));
        Iterable<String> roles = (Iterable<String>) payload.get("roles");
        StringBuilder stringBuilder = new StringBuilder();
        roles.forEach(role -> stringBuilder.append(role).append(" "));
        user.setRoles(stringBuilder.toString());
    }
}
