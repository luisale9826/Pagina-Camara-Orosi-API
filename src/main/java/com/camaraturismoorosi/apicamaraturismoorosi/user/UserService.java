package com.camaraturismoorosi.apicamaraturismoorosi.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseService;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDao {

    private final FirebaseService fbService;
    private final PasswordEncoder passwordEncoder;
    private final String COLLECTION_NAME = "users";

    @Autowired
    public UserService(FirebaseService fbService, PasswordEncoder passwordEncoder) {
        this.fbService = fbService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void insertUser(User user) throws Exception {
        user.setRole("admin");
        List<QueryDocumentSnapshot> filter = fbService.getObjects(COLLECTION_NAME).stream()
                .filter(userData -> userData.get("userName").equals(user.getUserName())).collect(Collectors.toList());
        if (!filter.isEmpty()) {
            throw new Exception("El usuario ya se encuentra registrado en el sistema");
        }
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("userName", user.getUserName());
        newUser.put("password", passwordEncoder.encode(user.getPassword()));
        newUser.put("role", user.getRole());
        fbService.insertObject(COLLECTION_NAME, newUser);
    }

    @Override
    public void updateUser(User user) throws Exception {
        Map<String, Object> updateUser = new HashMap<>();
        if (!user.getPassword().equals("not-changed")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            updateUser.put("password", passwordEncoder.encode(user.getPassword()));

        }
        updateUser.put("userName", user.getUserName());
        updateUser.put("role", user.getRole());
        fbService.updateObjectSpecificFields(COLLECTION_NAME, user.getUserId(), updateUser);
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        if (userId.isEmpty() || userId.isBlank()) {
            throw new Exception("Se debe especificar el usuario a ser eliminado");
        }
        fbService.deleteObject(userId, COLLECTION_NAME);
    }

    @Override
    public List<User> getUsers() {
        List<QueryDocumentSnapshot> objects = fbService.getObjects(COLLECTION_NAME);
            return objects.stream()
                    .map(user -> new User(user.getId(), user.getString("userName"), user.getString("password"), user.getString("role")))
                    .collect(Collectors.toList()); 
    }
    
}
