package com.camaraturismoorosi.apicamaraturismoorosi.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("management/cto/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<String> insertUser(@Valid @RequestBody User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity
                .ok()
                .body(String.format("Usuario: %s insertado correctamente", user.getUserName()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
            .body("Error al modificar el usuario" + e.getMessage());
        }
        return ResponseEntity.ok().body("Usuario modificado");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<String> deleteUser(@RequestBody String userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
            .body("Error eliminar el usuario: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Usuario eliminado");
    }

}
