package com.camaraturismoorosi.apicamaraturismoorosi.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Map<String, Object>> insertUser(@Valid @RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al insertar el usuario", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Usuario Insertado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<Map<String, Object>> updateUser(@Valid @RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al modificar el usuario", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Usuario modificado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody String userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("Error al eliminar el usuario", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("message", "Usuario eliminado satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.CREATED);
    }

}
