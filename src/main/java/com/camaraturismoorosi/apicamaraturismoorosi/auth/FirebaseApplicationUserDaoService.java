package com.camaraturismoorosi.apicamaraturismoorosi.auth;

import static com.camaraturismoorosi.apicamaraturismoorosi.security.ApplicationUserRole.ADMIN;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.camaraturismoorosi.apicamaraturismoorosi.firebase.FirebaseConfig;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository("firebase")
public class FirebaseApplicationUserDaoService implements ApplicationUserDao {
    private final FirebaseConfig fbConfig;
    private final String COLLECTION_NAME = "users";
    private final PasswordEncoder passwordEncoder;
    private final AdminUserConfig adminUserConfig;

    @Autowired
    public FirebaseApplicationUserDaoService(FirebaseConfig fbConfig, PasswordEncoder passwordEncoder, AdminUserConfig adminUserConfig) {
        this.fbConfig = fbConfig;
        this.passwordEncoder = passwordEncoder;
        this.adminUserConfig = adminUserConfig;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        Firestore fbInstance = fbConfig.getFirestoreInstance();
        Query query = fbInstance.collection(COLLECTION_NAME).whereEqualTo("role", "admin");
        ApiFuture<QuerySnapshot> queryResult = query.get();
        try {
            List<ApplicationUser> users = queryResult.get().getDocuments().stream()
            .map(user -> 
            new ApplicationUser(
                    user.getString("userName"), 
                    user.getString("password"),
                    user.getString("userEmail"), 
                    ADMIN.getGrantedAuthorities(), 
                    true, 
                    true,
                    true, 
                    true
                    )
                )
            .collect(Collectors.toList());
            users.add(new ApplicationUser(
                adminUserConfig.getUsername(),
                passwordEncoder.encode(adminUserConfig.getPassword()),
                null, 
                ADMIN.getGrantedAuthorities(), 
                true, 
                true,
                true, 
                true
                )
            );
            return users;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
