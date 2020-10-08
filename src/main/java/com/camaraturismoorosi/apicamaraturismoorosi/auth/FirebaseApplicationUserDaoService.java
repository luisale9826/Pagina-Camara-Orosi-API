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
import org.springframework.stereotype.Repository;


@Repository("firebase")
public class FirebaseApplicationUserDaoService implements ApplicationUserDao {
    private final FirebaseConfig fbConfig;
    private final String COLLECTION_NAME = "users";

    @Autowired
    public FirebaseApplicationUserDaoService(FirebaseConfig fbConfig) {
        this.fbConfig = fbConfig;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        Firestore fbInstance = fbConfig.getInstance();
        Query query = fbInstance.collection(COLLECTION_NAME).whereEqualTo("role", "admin");
        ApiFuture<QuerySnapshot> queryResult = query.get();
        try {
            return queryResult.get().getDocuments().stream()
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
